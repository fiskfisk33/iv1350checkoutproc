package se.kth.iv1350.checkoutproc.controller;

import se.kth.iv1350.checkoutproc.integration.*;
import se.kth.iv1350.checkoutproc.model.*;

/**
 * represents the Controller, an interface between the view and data related classes
 * and the internal model.
 */
public class Controller {
       private final Printer printer;
       private final InventoryHandler inventoryHandler;
       private final DiscountHandler discountHandler;
       private final AccountingHandler accountingHandler;
       private Sale sale;

        /**
         * Initializes a controller with all it's associated connections
         */
       public Controller(){
               LogFileWriter logFileWriter = new LogFileWriter("runtime");
               printer = new Printer();
               inventoryHandler = InventoryHandler.getInventoryHandler();
               inventoryHandler.addLogger(logFileWriter);
               discountHandler = new DiscountHandler();
               accountingHandler = new AccountingHandler();
       }

        /**
         * initiates a sale, represented as an instance of {@link Sale}
         */
       public void initiateSale(){
               sale = new Sale(accountingHandler, inventoryHandler, printer);

       }

        /**
         * adds mumber of an item type to the {@link Sale}.
         * @param itemID unique item identifier
         * @param count the amount of the item to be added
         *
         * @return the item with the specified identifier,
         *         if such item does not exist, no item will be added, and {@link null} will be returned.
         * @throws NoSuchItemException if no item matching the supplied itemID is found
         * @throws InventoryException if there was an error when trying to access the inventory
         */
        public ItemDTO addItem(int itemID, int count) throws NoSuchItemException, InventoryException {
                try {
                        ItemDTO itemData = inventoryHandler.fetchItem(itemID);
                        sale.addItems(itemData, count);
                        return itemData;
                } catch (ItemNotInDbException e) {
                        throw new NoSuchItemException("No item with ID "+itemID+" found", e);
                } catch (DbUnreachableException e){
                        throw new InventoryException(e);
                }
        }



        /**
         * adds a single item to the {@link Sale}
         * @param itemID unique item identifier
         * @return the item with the specified identifier,
         *         if such item does not exist, no item will be added, and {@link null} will be returned.
         * @throws NoSuchItemException if no item matching the supplied itemID is found
         * @throws InventoryException if there was an error when trying to access the inventory
         *
         */
        public ItemDTO addItem(int itemID) throws NoSuchItemException, InventoryException {
               return addItem(itemID, 1);
        }

        /**
         * fetches information about the sale
         * @return a dto with the requested sale info
         */
        public SaleInfoDTO fetchSaleInfo(){
                return sale.fetchSaleInfo();
        }

        /**
        *   This method ends the sale and returns the amount of change
        * to be given back to the customer
        * @param payment the payment
        * @return the amount of change
        *
        */
        public Change endSale(Payment payment){
                Change change = sale.endSale(payment);
                sale = null;
                return change;
        }

        /**
         * Fetches discounts from the discount db and applies them to the Sale
         * @param customerID unique customer ID
         */
        public void requestDiscount(int customerID){
               DiscountsDTO discountsDTO = discountHandler.fetchDiscounts(customerID);
               sale.applyDiscounts(discountsDTO);
        }

        /**
         * Add a an observer that will be notified with the revenue of the sale
         * whenever the sale is finalized.
         * TODO this throws a null pointer exception when called without a sale being active. worth reviewing.
         * @param salePaymentObserver the observer.
         */
        public void addSalePaymentObserver(SalePaymentObserver salePaymentObserver){
                sale.addPaymentObserver(salePaymentObserver);
        }

}
