package se.kth.iv1350.checkoutproc.model;

import java.math.BigDecimal;

/**
 * classes implementing this interface will get updated whenever a payment is made in a sale.
 */
public interface SalePaymentObserver {
        /**
         * whenever a sale is completed, its revenue will be reported via this method
         * @param saleRevenue the sale revenue
         */
        public void reportSaleRevenue(BigDecimal saleRevenue);


}
