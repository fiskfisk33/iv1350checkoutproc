package se.kth.iv1350.checkoutproc.model;

import java.math.BigDecimal;

public abstract class MonetaryAmount {
        private final BigDecimal amount;

        public MonetaryAmount(BigDecimal amount) {
                this.amount = amount;
        }

        public BigDecimal getAmount() {
                return amount;
        }
}
