package se.kth.iv1350.checkoutproc.util;

import java.math.BigDecimal;

/**
 * Models a fixed amount of money
 */
public abstract class MonetaryAmount {
        private final BigDecimal amount;

        /**
         * creates a MonetaryAmount object with a given amount
         * @param amount the amount
         */
        public MonetaryAmount(BigDecimal amount) {
                this.amount = amount;
        }

        /**
         * gets the amount
         * @return the amount
         */
        public BigDecimal getAmount() {
                return amount;
        }
}
