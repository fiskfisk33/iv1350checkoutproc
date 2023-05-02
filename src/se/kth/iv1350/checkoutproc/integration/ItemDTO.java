package se.kth.iv1350.checkoutproc.integration;

import java.math.BigDecimal;

/**
 * a DTO meant for transferring item data from the integration layer to the model layer
 */
public class ItemDTO {
        private int itemID;
        private String description;
        private BigDecimal vat;
        private BigDecimal price;

        /**
         * creates an ItemDTO with data from the Inventory DB
         * @param itemID
         * @param description
         * @param vat
         * @param price
         */
        public ItemDTO(int itemID, String description, BigDecimal vat, BigDecimal price) {
                this.itemID = itemID;
                this.description = description;
                this.vat = vat;
                this.price = price;
        }

        /**
         * Gets itemID
         * @return itemID
         */
        public int getItemID() {
                return itemID;
        }

        /**
         * Gets item description
         * @return the item description
         */
        public String getDescription() {
                return description;
        }

        /**
         * Gets the VAT
         * @return the VAT
         */
        public BigDecimal getVat() {
                return vat;
        }

        /**
         * Gets the price
         * @return price
         */
        public BigDecimal getPrice() {
                return price;
        }
}
