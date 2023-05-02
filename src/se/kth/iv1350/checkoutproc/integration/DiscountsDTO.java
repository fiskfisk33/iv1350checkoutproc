package se.kth.iv1350.checkoutproc.integration;

import se.kth.iv1350.checkoutproc.model.ItemDiscount;

import java.math.BigDecimal;
import java.util.AbstractMap;

/**
 * a DTO for transferring discount data from the database
 * has a list of item discounts and a percentage discount for the whole cart
 */
public class DiscountsDTO {
        //<item ID, item discount>
        private AbstractMap<Integer, ItemDiscount> itemDiscounts;
        /**
         * cartDiscount is stored as a multiplier
         * eg, a discount of 5% is 0.95
         */
        private BigDecimal cartDiscountMultiplier;

        /**
         * creates a {@link DiscountsDTO} with a list of {@link ItemDiscount}s and a cart discount
         * @param itemDiscounts a map of the {@link ItemDiscount}s on the form
         *                      < itemID, {@link ItemDiscount}>
         * @param cartDiscount a discount on the whole cart as a percentage.
         *                     eg a 5% discount could be new BigDecimal("5%"}
         */
        public DiscountsDTO(AbstractMap<Integer, ItemDiscount> itemDiscounts, BigDecimal cartDiscount) {
                this.itemDiscounts = itemDiscounts;
                this.cartDiscountMultiplier = BigDecimal.ONE.subtract(cartDiscount);
        }

        /**
         * returns the discount to the given item ID, or null if none exists.
         * @param itemID unique {@link se.kth.iv1350.checkoutproc.model.Item} identifier
         * @return the discount
         */
        public ItemDiscount getItemDiscount(int itemID){
                return itemDiscounts.get(itemID);
        }

        /**
         * gets the cart discount stored in this DTO
         * @return
         */
        public BigDecimal getCartDiscount() {
                return cartDiscountMultiplier;
        }
}
