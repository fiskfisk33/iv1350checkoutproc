package se.kth.iv1350.checkoutproc.model;

import se.kth.iv1350.checkoutproc.util.MonetaryAmount;

import java.math.BigDecimal;

/**
 * models a Payment
 */
public class Payment extends MonetaryAmount {
        /**
         * constructor, initializes a Payment with an amount of money.
         * @param amount the amount of money
         */
        public Payment(BigDecimal amount) {
                super(amount);
        }
}

