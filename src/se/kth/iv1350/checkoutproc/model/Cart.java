package se.kth.iv1350.checkoutproc.model;

import se.kth.iv1350.checkoutproc.integration.ItemDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Symbolizes the collection of items that are about to be bought.
 */
public class Cart implements Iterable<Item>{
        private AbstractMap<Integer, CartEntry> items;
        private BigDecimal cartDiscount;

        /**
         * creates a Cart
         */
        Cart() {
                items = new HashMap<>();
                cartDiscount = BigDecimal.ONE;
        }

        /**
         * Adds one or many items (of the same type) to the {@link Cart}
         * @param itemData a dto with the item info
         * @param count how many of the item to add
         */
        void addItems(ItemDTO itemData, int count){
                int itemID = itemData.getItemID();
                if (items.containsKey(itemID))
                        items.get(itemID).increaseCount(count);
                else{
                       CartEntry cartEntry = new CartEntry(new Item(itemData), count);
                       items.put(itemID, cartEntry);
                }
        }

        /**
         * calculates the total price of the {@link Item's} in the {@link Cart}
         * @return the total price, without VAT
         */
        public BigDecimal getTotalPrice(){
                BigDecimal totalPrice = BigDecimal.ZERO;
                for(var entry : items.entrySet())
                        totalPrice = totalPrice.add(
                                entry.getValue().getTotalPrice());
                return totalPrice.multiply(cartDiscount);
        }

        /**
         * calculates the total VAT of the {@link Item's} in the {@link Cart}
         * @return the total VAT
         */
        public BigDecimal getTotalVAT(){
                BigDecimal totalVAT = BigDecimal.ZERO;
                for(var entry : items.entrySet())
                         totalVAT = totalVAT.add(
                                entry.getValue().getTotalVAT());
                return  totalVAT.multiply(cartDiscount).setScale(2, RoundingMode.HALF_DOWN);
        }

        /**
         * get count of item in {@link Cart}
         * TODO throw error on no such item
         * @param item the item to count
         * @return the count
         */
        public int getItemCount(Item item){
                //TODO handle errors (item does not exist)
                return getItemCount(item.getItemID());
        }
        public int getItemCount(ItemDTO itemDTO){
                return getItemCount(itemDTO.getItemID());
        }
        public int getItemCount(int itemID){
                return items.get(itemID).getCount();
        }

	/**
	 * applies a discount to the whole cart
	 * @param cartDiscount the discount as a multiplier (a 5% would be 0.95)
	 */
        public void applyCartDiscount(BigDecimal cartDiscount){
		this.cartDiscount = this.cartDiscount.multiply(cartDiscount);
        }

        /**
         * Symbolizes an entry into the {@link Cart}, has an {@link Item} and its count.
         */
        private class CartEntry {
                private final Item item;
                private int count;

                public CartEntry(Item item, int count) {
                        this.item = item;
                        this.count = count;
                }

                /**
                 * calculates the total price of the items in this {@link CartEntry}
                 * @return total price, without vat
                 */
                public BigDecimal getTotalPrice(){
                        BigDecimal count = BigDecimal.valueOf(this.count);
                        BigDecimal totalPrice = item.getPrice().multiply(count);
                        return totalPrice;
                }

                /**
                 * calculates the total VAT of the items in this {@link CartEntry}
                 * @return total VAT.
                 */
                public BigDecimal getTotalVAT(){
                        BigDecimal vat = item.getVat();
                        return getTotalPrice().multiply(vat).setScale(2, RoundingMode.HALF_DOWN);
                }

                public int increaseCount(int incr){
                        count += incr;
                        return count;
                }
                public int getCount(){
                        return count;
                }
                public Item getItem(){
                        return item;
                }
        }

        /**
         * in order for outside classes to be able to use {@link Cart} as a collection of items,
         * and thereby keep {@link CartEntry} private, We implement this iterator.
         *
         * (heavily inspired by Nateowami's answer on https://stackoverflow.com/questions/21512250/how-can-i-make-my-class-iterable-so-i-can-use-foreach-syntax)
         * @return the iterator
         */
        @Override
        public Iterator<Item> iterator() {
                return new Iterator<Item>(){
                        private final Iterator<CartEntry> iter = items.values().iterator();

                        @Override
                        public boolean hasNext() {
                                return iter.hasNext();
                        }
                        @Override
                        public Item next() {
                                return iter.next().getItem();
                        }
                        @Override
                        public void remove() {
                                throw new UnsupportedOperationException("no changes allowed");
                        }
                };
        }
}
