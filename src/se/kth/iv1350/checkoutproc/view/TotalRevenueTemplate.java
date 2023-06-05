package se.kth.iv1350.checkoutproc.view;

import se.kth.iv1350.checkoutproc.model.SalePaymentObserver;

import java.math.BigDecimal;

public abstract class TotalRevenueTemplate implements SalePaymentObserver {
        private BigDecimal totalRevenue = BigDecimal.ZERO;

        // This is the method defined in the observer interface .
        public void reportSaleRevenue ( BigDecimal priceOfTheSaleThatWasJustMade) {
                calculateTotalIncome(priceOfTheSaleThatWasJustMade);
                showTotalIncome ();
        }
        private void calculateTotalIncome(BigDecimal priceOfTheSaleThatWasJustMade){
                totalRevenue = totalRevenue.add(priceOfTheSaleThatWasJustMade);
        }
        private void showTotalIncome () {
                try {
                        doShowTotalIncome(totalRevenue);
                } catch (Exception e) {
                        handleErrors (e);
                }
        }
        protected abstract void doShowTotalIncome(BigDecimal totalRevenue) throws Exception ;
        protected abstract void handleErrors ( Exception e );
}
