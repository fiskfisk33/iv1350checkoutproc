package se.kth.iv1350.checkoutproc.model;

import java.time.Instant;

public class Sale {
        private final long saleTimestamp;
        private Cart cart;

        /**
         *
         */
        public Sale() {
                saleTimestamp = Instant.now().getEpochSecond();

        }

        /**
         * @param dateTime
         */
        void setSaleDateTime(int dateTime){
                saleDateTime = dateTime;
        }
}
