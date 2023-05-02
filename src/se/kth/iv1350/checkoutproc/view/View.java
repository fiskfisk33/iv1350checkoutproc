package se.kth.iv1350.checkoutproc.view;

import se.kth.iv1350.checkoutproc.controller.Controller;

/**
 * the view, represents the UI part of the program
 * this is not modeled for the time being
 */
public class View {
        private Controller controller;

        /**
         * creates a view, with a {@link Controller} instance it uses to communicate with the program
         * @param controller
         */
        public View(Controller controller) {
                this.controller = controller;
        }
}
