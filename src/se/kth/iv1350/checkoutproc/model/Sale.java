package se.kth.iv1350.checkoutproc.model;

import se.kth.iv1350.checkoutproc.integration.AccountingHandler;
import se.kth.iv1350.checkoutproc.integration.InventoryHandler;
import se.kth.iv1350.checkoutproc.integration.ItemDTO;
import se.kth.iv1350.checkoutproc.integration.Printer;

import java.math.BigDecimal;
import java.time.Instant;

public class Sale {
        private Cart cart;
        private final Instant saleTime;
        private final AccountingHandler accountingHandler;
        private final InventoryHandler inventoryHandler;
        private final Printer printer;

        private Payment payment;
        private Change change;

        /**
         *
         */
        public Sale(AccountingHandler accountingHandler, InventoryHandler inventoryHandler, Printer printer) {
                this.accountingHandler = accountingHandler;
                this.inventoryHandler = inventoryHandler;
                this.printer = printer;
                saleTime = Instant.now();
                cart = new Cart();
        }

        public void addItems(ItemDTO itemData, int count){
                cart.addItems(itemData, count);
        }

        public SaleInfoDTO fetchSaleInfo(){
                return new SaleInfoDTO(
                                cart.getTotalPrice(),
                                cart.getTotalVAT(),
                                payment,
                                change,
                                saleTime,
                                cart);
        }

        /**
         * ends the sale and returns the change
         * @param payment the payment
         * @return the change that needs to be paid back to the customer
         */
        public Change endSale(Payment payment){
               //TODO update Inventory
                for(Item item : cart){
                        int count = cart.getItemCount(item);
                        inventoryHandler.updateInventory(item.getItemID(), count);
                }

                this.payment = payment;
                BigDecimal priceWithVat = cart.getTotalPrice().add(cart.getTotalPrice());
                this.change = new Change(
                                priceWithVat.subtract(payment.getAmount()));

               //TODO log sale info
                accountingHandler.logSale(fetchSaleInfo());

                //TODO create and print receipt
                Receipt receipt = new Receipt(fetchSaleInfo());
                printer.printReceipt(receipt);

                return this.change;
        }
}
