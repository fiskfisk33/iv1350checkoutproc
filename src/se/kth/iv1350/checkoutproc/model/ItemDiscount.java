package se.kth.iv1350.checkoutproc.model;

import se.kth.iv1350.checkoutproc.util.MonetaryAmount;

import java.math.BigDecimal;

/**
 * models an item discount
 */
public class ItemDiscount extends MonetaryAmount {
        private final int itemID;

        /**
         * creates an {@link ItemDiscount} from a discount amount and an item id.
         * @param amount the discount amount as an amount of money.
         * @param itemID the id of the item this discount applies to.
         */
        public ItemDiscount(BigDecimal amount, int itemID) {
                super(amount);
                this.itemID = itemID;
        }

        /**
         * Gets the item id
         * @return item id of the item this discount is associate with.
         */
        public int getItemID(){
                return itemID;
        }
}
