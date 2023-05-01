package se.kth.iv1350.checkoutproc.model;


import se.kth.iv1350.checkoutproc.integration.ItemDTO;

import java.math.BigDecimal;

public class Item {
        private int itemID;
        private String description;
        private BigDecimal vat;
        private BigDecimal price;

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

        public Item(ItemDTO itemDTO) {
                this.itemID = itemDTO.getItemID();
                this.description = itemDTO.getDescription();
                this.vat = itemDTO.getVat();
                this.price = itemDTO.getPrice();
        }
}
