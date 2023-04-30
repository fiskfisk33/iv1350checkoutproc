package se.kth.iv1350.checkoutproc.model;

public class CartEntry {
        private final Item item;
        private int count;

        public CartEntry(Item item, int count) {
                this.item = item;
                this.count = count;
        }

        public CartEntry(Item item) {
                this(item, 1);
        }

        public int increaseCount(int incr){
                count += incr;
                return count;
        }
}