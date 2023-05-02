package se.kth.iv1350.checkoutproc.model;

/**
 * models a reciept for a sale
 */
public class Receipt extends SaleInfoDTO {


        /**
         * creates the Receipt from sale info
         * @param saleInfo sale info in a DTO
         */
        public Receipt(SaleInfoDTO saleInfo) {
                //TODO return errors if payment and/or change are missing
                super(saleInfo);
        }
}
