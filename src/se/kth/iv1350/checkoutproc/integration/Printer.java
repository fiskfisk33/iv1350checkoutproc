package se.kth.iv1350.checkoutproc.integration;

import se.kth.iv1350.checkoutproc.model.Receipt;

/**
 * handles the connection between the program and a physical printer
 * TODO this is a dummy class for now
 */
public class Printer {
        /**
         * prints the given receipt
         *
         * dummy function, does nothing
         * @param receipt the receipt to be printed
         */
        public void printReceipt(Receipt receipt){
                //TODO dummy function
                System.out.print(receipt.fetchReceiptText());
        }
}
