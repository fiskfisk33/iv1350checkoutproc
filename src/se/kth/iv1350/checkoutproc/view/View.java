package se.kth.iv1350.checkoutproc.view;

import se.kth.iv1350.checkoutproc.controller.Controller;
import se.kth.iv1350.checkoutproc.controller.InventoryException;
import se.kth.iv1350.checkoutproc.integration.ItemDTO;
import se.kth.iv1350.checkoutproc.controller.NoSuchItemException;
import se.kth.iv1350.checkoutproc.model.Change;
import se.kth.iv1350.checkoutproc.model.Payment;
import se.kth.iv1350.checkoutproc.model.SaleInfoDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * the view, represents the UI part of the program
 * this is not modeled for the time being
 */
public class View {
        private Controller controller;
        private TotalRevenueView totalRevenueView;
        private TotalRevenueFileOutput totalRevenueFileOutput;

        /**
         * creates a view, with a {@link Controller} instance it uses to communicate with the program
         * @param controller
         */
        public View(Controller controller) {
                this.controller = controller;
                totalRevenueFileOutput = new TotalRevenueFileOutput("revenue");
                totalRevenueView = new TotalRevenueView();
                dryRun();
                dryRun();
                dryRun();
                dryRun();
                dryRun();
        }

        /**
         * This method simulates the running of the program
         * it goes through the motions of an example sale being carried out.
         */
        private void dryRun(){
                initiateSale();
                System.out.println("---Ready To Scan---");
                scanItem(12345);
                scanItem(38940);
                scanItem(218);
                scanItem(12425);
                scanItem(48950, 10);
                scanItem(1599);
                scanItem(38940);
                scanItem( 183243, 4);
                scanItem(1738, 3);
                scanItem(218, 3);
                scanItem(47895);
                scanItem(78397, 2);
                scanItem(48950, 7);
                Payment payment = new Payment(BigDecimal.valueOf(10000));
                System.out.println("-------------------------");
                System.out.println("| Recieved payment:" + payment.getAmount()+"kr");
                Change change = controller.endSale(payment);
                System.out.println("| Change: "+ change.getAmount());

        }

        private void scanItem(int itemID){
                scanItem(itemID, 1);
        }
        private void scanItem(int itemID, int amount){
                System.out.println("-------------------------");
                try {
                        ItemDTO item = controller.addItem(itemID, amount);
                        System.out.println("| " + amount + " x "+ item.getDescription());
                        BigDecimal itemPrice = item.getPrice().multiply(BigDecimal.ONE.add(item.getVat())).setScale(2, RoundingMode.HALF_DOWN);
                        System.out.println("| Price à:       " + itemPrice + ":-");
                        SaleInfoDTO saleInfoDTO = controller.fetchSaleInfo();
                        BigDecimal runningTotal = saleInfoDTO.getTotalPrice().add(saleInfoDTO.getTotalVAT());
                        System.out.println("| Running Total: "+ runningTotal + ":-");
                } catch (NoSuchItemException e) {
                        System.out.println("---Invalid ItemID Entered---");
                } catch (InventoryException e){
                        System.out.println("---Something went wrong, please try again---");
                }
        }
        private void initiateSale(){
                controller.initiateSale();
                controller.addSalePaymentObserver(totalRevenueFileOutput);
                controller.addSalePaymentObserver(totalRevenueView);
        }

}
