package se.kth.iv1350.checkoutproc.model;

import se.kth.iv1350.checkoutproc.integration.*;

import java.math.BigDecimal;
import java.time.Instant;

public class Sale {
        private final Cart cart;
        private final Instant saleTime;
        private final AccountingHandler accountingHandler;
        private final InventoryHandler inventoryHandler;
        private final Printer printer;
        private Payment payment;
        private Change change;
        private boolean finalized;

        /**
         * models a sale,
         * handles a cart of items and the transaction of those items in excange for {@link Payment}
         * @param accountingHandler
         * @param inventoryHandler
         * @param printer
         */
        public Sale(AccountingHandler accountingHandler, InventoryHandler inventoryHandler, Printer printer) {
                this.accountingHandler = accountingHandler;
                this.inventoryHandler = inventoryHandler;
                this.printer = printer;
                saleTime = Instant.now();
                cart = new Cart();
                finalized = false;
        }

        /**
         * add one {@link Item}, or multiples of it, to the cart of items to be sold.
         * @param itemData a dto with the item data.
         * @param count how many to add
         */
        public void addItems(ItemDTO itemData, int count){
                cart.addItems(itemData, count);
        }

        /**
         * fetches the available info about the sale
         * @return a {@link SaleInfoDTO} containing the requested sale info
         */
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
                if(finalized){
                        //TODO throw an error
                        return this.change;
                }
                finalized = true;

                for(Item item : cart){
                        int count = cart.getItemCount(item);
                        inventoryHandler.updateInventory(item.getItemID(), count);
                }

                this.payment = payment;
                BigDecimal priceWithVat = cart.getTotalPrice().add(cart.getTotalPrice());
                this.change = new Change(
                                priceWithVat.subtract(payment.getAmount()));

                accountingHandler.logSale(fetchSaleInfo());

                Receipt receipt = new Receipt(fetchSaleInfo());
                printer.printReceipt(receipt);

                return this.change;
        }

        /**
         * applies the discounts in discountDTO to the {@link Cart} and its {@link Item}s
         * @param discountsDTO the dto with the discounts.
         */
        public void applyDiscounts(DiscountsDTO discountsDTO) {
                for(Item item : cart){
                        ItemDiscount itemDiscount = discountsDTO.getItemDiscount(item.getItemID());
                        if(itemDiscount != null){
                                item.setDiscount(itemDiscount.getAmount());
                        }
                }
                cart.applyCartDiscount(discountsDTO.getCartDiscount());
        }
}
