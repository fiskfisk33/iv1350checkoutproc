package se.kth.iv1350.checkoutproc.view;

import java.math.BigDecimal;

public class TotalRevenueView extends TotalRevenueTemplate {
        @Override
        protected void doShowTotalIncome(BigDecimal totalRevenue) throws Exception {
                System.out.println("****Revenue****\n" +
                        totalRevenue.toString() + "\n"+
                        "****       ****");
        }
        @Override
        protected void handleErrors(Exception e) {
                throw new RuntimeException(e);
        }
}

