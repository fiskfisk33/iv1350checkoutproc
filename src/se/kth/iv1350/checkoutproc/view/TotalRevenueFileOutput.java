package se.kth.iv1350.checkoutproc.view;

import se.kth.iv1350.checkoutproc.model.SalePaymentObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class TotalRevenueFileOutput implements SalePaymentObserver {
        private BigDecimal totalRevenue = BigDecimal.ZERO;
        private PrintWriter logStream;

        public TotalRevenueFileOutput(String file) {
                try {
                        logStream = new PrintWriter(new FileWriter(file + ".txt"), true);
                } catch (IOException ioe) {
                        System.out.println("CAN NOT WRITE TO REVENUE FILE");
                        ioe.printStackTrace();
                }
        }

        /**
         * whenever a sale is completed, its revenue will be reported via this method
         * the total revenue from all sales made will then be written to file
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
                logStream.println( totalRevenue.toString() );
        }
}
