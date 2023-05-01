package se.kth.iv1350.checkoutproc.controller;

import se.kth.iv1350.checkoutproc.integration.*;
import se.kth.iv1350.checkoutproc.model.*;

public class Controller {
       private Printer printer;
       private InventoryHandler inventoryHandler;
       private DiscountHandler discountHandler;
       private AccountingHandler accountingHandler;
       private Sale sale;

       public Controller(){
               printer = new Printer();
               inventoryHandler = new InventoryHandler();
               discountHandler = new DiscountHandler();
               accountingHandler = new AccountingHandler();
       }

       public void initiateSale(){
               sale = new Sale(accountingHandler, inventoryHandler, printer);
       }

        /**
         * adds mumber of an item type to the {@link Sale}.
         * @param itemID unique item identifier
         * @param count the amount of the item to be added
         *
         * @return the item with the specified identifier,
         * if such item does not exist, no item will be added, and {@link null} will be returned.
         */
        public ItemDTO addItem(int itemID, int count){
                //TODO return correct item
                ItemDTO itemData = inventoryHandler.fetchItem(itemID);
                if(itemData == null)
                        return null;
                sale.addItems(itemData, count);
                return itemData;
        }


        /**
         * adds a single item to the {@link Sale}
         * @param itemID unique item idenifier
         * @return the item added, null if no such item exists
         */
        public ItemDTO addItem(int itemID){
               return addItem(itemID, 1);
        }

        /**
         * fetches information about the sale
         * @return a dto with the requested sale info
         */
        public SaleInfoDTO fetchSaleInfo(){
                return sale.fetchSaleInfo();
        }
        public ItemDTO getItemInfo(int itemID){
               //TODO
                return null;

        }

        /**
         * This method ends the sale and returns the amount of change
         * @param payment the payment
         * @return the amount of change to be given back to the customer
         */
        public Change endSale(Payment payment){
               //TODO
                return null;
        }
        public void requestDiscount(int customerID){
               //TODO
        }

}
