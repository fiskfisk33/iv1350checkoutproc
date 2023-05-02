package se.kth.iv1350.checkoutproc.model;


import se.kth.iv1350.checkoutproc.integration.ItemDTO;

import java.math.BigDecimal;

/**
 * Models an item sold in the store
 */
public class Item {
        private int itemID;
        private String description;
        private BigDecimal vat;
        private BigDecimal price;
        private BigDecimal discount;

        /**
         * Gets the items unique ID
         * @return the unique ID
         */
        public int getItemID() {
                return itemID;
        }

        /**
         * Gets the item description
         * @return item description
         */
        public String getDescription() {
                return description;
        }

        /**
         * Gets the VAT
         * @return VAT
         */
        public BigDecimal getVat() {
                return vat;
        }

        /**
         * Gets the item price
         * @return the price
         */
        public BigDecimal getPrice() {
                return price;
        }

        /**
         * gets the items Discount
         * @return item discount
         */
        public BigDecimal getDiscount() {
                return discount;
        }

        /**
         * sets the item Discount
         * @param discount the discount
         */
        public void setDiscount(BigDecimal discount) {
                this.discount = discount;
        }

        /**
         * Constructor, creates an item from an {@link ItemDTO}
         * @param itemDTO an itemDTO
         */
        public Item(ItemDTO itemDTO) {
                this.itemID = itemDTO.getItemID();
                this.description = itemDTO.getDescription();
                this.vat = itemDTO.getVat();
                this.price = itemDTO.getPrice();
        }
}
