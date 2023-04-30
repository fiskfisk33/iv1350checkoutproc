package se.kth.iv1350.checkoutproc.startup;

import se.kth.iv1350.checkoutproc.controller.Controller;
import se.kth.iv1350.checkoutproc.view.View;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
            Controller contr = new Controller();
            View view = new View(contr);
    }
}