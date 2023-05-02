package se.kth.iv1350.checkoutproc.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

/**
 * collect the available information about a {@link Sale}
 */
public class SaleInfoDTO implements Iterable<Item> {
        private final BigDecimal totalPrice;
        private final BigDecimal totalVAT;
        private final Payment payment;
        private final Change change;
        private final Instant saleTime;
        //AbstractMap<Item, int count>
        private final AbstractMap<Item,Integer> items;


        /**
         * Creates a SaleInfoDTO from a collection of information about a sale
         * @param totalPrice
         * @param totalVAT
         * @param payment
         * @param change
         * @param saleTime
         * @param cart
         */
        SaleInfoDTO(BigDecimal totalPrice, BigDecimal totalVAT, Payment payment, Change change, Instant saleTime, Cart cart){
                this.totalPrice = totalPrice;
                this.totalVAT = totalVAT;
                this.payment = payment;
                this.change = change;
                this.saleTime = saleTime;
                items = new HashMap<>();
                for(Item item : cart){
                        items.put(item, cart.getItemCount(item));
                }
        }

        /**
         * Copy constructor, makes a copy of another {@link SaleInfoDTO}
         * @param saleInfo the object to copy
         */
        SaleInfoDTO(SaleInfoDTO saleInfo){
                totalPrice = saleInfo.totalPrice;
                totalVAT = saleInfo.totalVAT;
                payment = saleInfo.payment;
                change = saleInfo.change;
                saleTime = saleInfo.saleTime;
                items = new HashMap<Item, Integer>(saleInfo.items);
        }
        public int getItemCount(Item item){
                return items.get(item);
        }

        /**
         * Gets the price without vat
         * @return total price without vat
         */
        public BigDecimal getTotalPrice() {
                return totalPrice;
        }

        /**
         * gets the payment
         * @return the payment
         */
        public Payment getPayment(){
                return payment;
        }

        /**
         * gets the Change
         * @return change
         */
        public Change getChange() {
                return change;
        }

        /**
         * gets the total VAT
         * @return total VAT
         */
        public BigDecimal getTotalVAT() {
                return totalVAT;
        }

        /**
         * gets the sale time
         * @return sale time
         */
        public Instant getSaleTime() {
                return saleTime;
        }

        /**
         * Make this class iterable so we easily can use it to transfer a list
         * of {@link Item}'s and their associated amounts.
         *
         * (heavily inspiret by Nateowami's answer on https://stackoverflow.com/questions/21512250/how-can-i-make-my-class-iterable-so-i-can-use-foreach-syntax)
         * @return the iterator
         */
        @Override
        public Iterator<Item> iterator() {
                return new Iterator<Item>(){
                        private final Iterator<Item> iter = items.keySet().iterator();

                        @Override
                        public boolean hasNext() {
                                return iter.hasNext();
                        }
                        @Override
                        public Item next() {
                                return iter.next();
                        }
                        @Override
                        public void remove() {
                                throw new UnsupportedOperationException("no changes allowed");
                        }
                };
        }
}
