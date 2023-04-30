package se.kth.iv1350.checkoutproc.controller;

import se.kth.iv1350.checkoutproc.integration.*;
import se.kth.iv1350.checkoutproc.model.Change;
import se.kth.iv1350.checkoutproc.model.Sale;
import se.kth.iv1350.checkoutproc.model.SaleInfoDTO;

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
               sale = new Sale();
               //TODO?
       }

       public void addItems(int itemID, int count){
               //TODO
       }
       public SaleInfoDTO getSaleInfo(){
               //TODO
               return null;
       }
        public ItemDTO getItemInfo(int itemID){
               //TODO
                return null;

        }
        public Change endSale(){
               //TODO
                return null;
        }
        public void requestDiscount(int customerID){
               //TODO
        }

}
