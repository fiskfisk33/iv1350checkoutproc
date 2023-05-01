package se.kth.iv1350.checkoutproc.model;

import se.kth.iv1350.checkoutproc.integration.ItemDTO;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Symbolizes the collection of items that are about to be bought.
 */
public class Cart implements Iterable<Item>{
        private AbstractMap<Integer, CartEntry> items;

        Cart() {
                items = new HashMap<>();
        }



        void addItems(ItemDTO itemData, int count){
                int itemID = itemData.getItemID();
                if (items.containsKey(itemID))
                        items.get(itemID).increaseCount(count);
                else{
                       CartEntry cartEntry = new CartEntry(new Item(itemData), count);
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
                return totalPrice;
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
                return  totalVAT;
        }

        /**
         * get count of item in {@link Cart}
         * TODO throw error on no such item
         * @param item the item to count
         * @return the count
         */
        public int getItemCount(Item item){
                //TODO handle errors (item does not exist)
                int itemID = item.getItemID();
                return items.get(itemID).getCount();
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
                        return item.getPrice().multiply(count);
                }

                /**
                 * calculates the total VAT of the items in this {@link CartEntry}
                 * @return total VAT.
                 */
                public BigDecimal getTotalVAT(){
                        BigDecimal vat = item.getVat();
                        return getTotalPrice().multiply(vat);
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
         * (heavily inspiret by Nateowami's answer on https://stackoverflow.com/questions/21512250/how-can-i-make-my-class-iterable-so-i-can-use-foreach-syntax)
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
