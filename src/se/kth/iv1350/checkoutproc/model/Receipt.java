package se.kth.iv1350.checkoutproc.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Receipt extends SaleInfoDTO {
        public Receipt(SaleInfoDTO saleInfo) {
                //TODO return errors if payment and/or change are missing
                super(saleInfo);
        }
}
