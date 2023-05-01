package se.kth.iv1350.checkoutproc.model;

public class Receipt extends SaleInfoDTO {
        public Receipt(SaleInfoDTO saleInfo) {
                //TODO return errors if payment and/or change are missing
                super(saleInfo);
        }
}
