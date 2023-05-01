package se.kth.iv1350.checkoutproc.integration;

import java.math.BigDecimal;

public class ItemDTO {
        private int itemID;
        private String description;
        private BigDecimal vat;
        private BigDecimal price;

        public ItemDTO(int itemID, String description, BigDecimal vat, BigDecimal price) {
                this.itemID = itemID;
                this.description = description;
                this.vat = vat;
                this.price = price;
        }

        public int getItemID() {
                return itemID;
        }

        public String getDescription() {
                return description;
        }

        public BigDecimal getVat() {
                return vat;
        }

        public BigDecimal getPrice() {
                return price;
        }
}
