package se.kth.iv1350.checkoutproc.model;

import se.kth.iv1350.checkoutproc.util.MonetaryAmount;

import java.math.BigDecimal;

/**
 * models an amount of change to be given back to the customer
 */
public class Change extends MonetaryAmount {
        public Change(BigDecimal amount) {
                super(amount);
        }
}
