package se.kth.iv1350.checkoutproc.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class TotalRevenueFileOutput extends TotalRevenueTemplate {
        private PrintWriter logStream;

        public TotalRevenueFileOutput(String file) {
                try {
                        logStream = new PrintWriter(new FileWriter(file + ".txt"), true);
                } catch (IOException ioe) {
                        System.out.println("CAN NOT WRITE TO REVENUE FILE");
                        ioe.printStackTrace();
                }
        }

        @Override
        protected void doShowTotalIncome(BigDecimal totalRevenue) throws Exception {
                logStream.println( totalRevenue.toString() );
        }

        @Override
        protected void handleErrors(Exception e) {
                throw new RuntimeException(e);
        }
}
