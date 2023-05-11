package se.kth.iv1350.checkoutproc.integration;

/**
 * Thrown when no matching item is found in the item db for a supplied itemID
 */
public class DbUnreachableException extends RuntimeException{
        public DbUnreachableException(String msg){
                super(msg);
        }

}
