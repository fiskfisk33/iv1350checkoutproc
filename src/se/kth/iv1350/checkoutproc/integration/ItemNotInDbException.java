package se.kth.iv1350.checkoutproc.integration;

/**
 * Thrown when no matching item is found in the item db for a supplied itemID
 */
public class ItemNotInDbException extends Exception{
        public ItemNotInDbException(String msg){
                super(msg);
        }
}
