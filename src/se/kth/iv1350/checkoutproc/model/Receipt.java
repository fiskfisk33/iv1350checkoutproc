package se.kth.iv1350.checkoutproc.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * models a reciept for a sale
 * for now it's simply a wrapped SaleInfoDTO
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
        public String fetchReceiptText(){
                String receiptstring = "********************************* \n";
                receiptstring = "*Receipt:------------------------ \n";
                receiptstring += "* ";
                receiptstring += getSaleTime().toString();
                receiptstring += "\n* - \n";
                for (Item item : this){
                        String name = item.getDescription();
                        int quantity = getItemQuantity(item);
                        BigDecimal price = item.getPrice().multiply(BigDecimal.ONE.add(item.getVat())).setScale(2, RoundingMode.HALF_DOWN);
                        receiptstring += "* " + quantity + " x " +  name ;
                        receiptstring += "  à " + price +"\n";
                        receiptstring += "*  = " + price.multiply(new BigDecimal(quantity)) + "\n*\n";
                }
                receiptstring += "***Gross: " + getTotalPrice() + "\n";
                receiptstring += "***VAT  : " + getTotalVAT() + "\n";
                receiptstring += "***Total: " + (getTotalPrice().add(getTotalVAT())) + "\n";
                receiptstring += "***Paid : " + getPayment().getAmount() + "\n";
                receiptstring += "***Change : " + getChange().getAmount() + "\n";
                receiptstring += "**********************************************\n\n";
                return receiptstring;
        }
}
