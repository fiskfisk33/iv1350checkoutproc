package se.kth.iv1350.checkoutproc.view;

import se.kth.iv1350.checkoutproc.model.SalePaymentObserver;

import java.math.BigDecimal;

public class TotalRevenueView implements SalePaymentObserver {
        private BigDecimal totalRevenue = BigDecimal.ZERO;

        /**
        * whenever a sale is completed, its revenue will be reported via this method
         * the total revenue will then be written to console
        *
        * @param saleRevenue the sale revenue
        */
        @Override
        public void reportSaleRevenue(BigDecimal saleRevenue) {
                addRevenue(saleRevenue);
                printRevenue();
        }
        private void addRevenue(BigDecimal revenue){
                totalRevenue = totalRevenue.add(revenue);
        }
        private void printRevenue(){
                System.out.println("****Revenue****\n" +
                        totalRevenue.toString() + "\n"+
                        "****       ****");

        }
}
