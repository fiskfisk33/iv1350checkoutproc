package se.kth.iv1350.checkoutproc.startup;

import se.kth.iv1350.checkoutproc.controller.Controller;
import se.kth.iv1350.checkoutproc.view.View;

/**
 * Entry point, Initializes the program
 */
public class Main {
        /**
         * entry point, initializes the program
         */
    public static void main(String[] args) {
            Controller contr = new Controller();
            View view = new View(contr);
    }
}