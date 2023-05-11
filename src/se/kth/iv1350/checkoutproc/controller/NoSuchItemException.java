package se.kth.iv1350.checkoutproc.controller;

/**
 * Thrown when no matching item is found in the item db for a supplied itemID
 */
public class NoSuchItemException extends Exception{
        public NoSuchItemException(String msg, Exception e){
                super(msg, e);
        }
}
