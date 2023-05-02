package se.kth.iv1350.checkoutproc.integration;

import se.kth.iv1350.checkoutproc.model.ItemDiscount;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashMap;

/**
 * this class is the interface between discounts database and the rest of the program
 */
public class DiscountHandler {
        /**
         * Fetches the discounts of a given customer from the discounts db
         *
         * This is a dummy method, since we have no discounts db for the moment
         * @param customerID customer unique ID
         * @return a dto with the discount data
         */
        public DiscountsDTO fetchDiscounts(int customerID){
                //TODO this is a dummy method
                AbstractMap<Integer, ItemDiscount> itemDiscounts = new HashMap<>();
                ItemDiscount itemDiscount1 = new ItemDiscount(new BigDecimal(199), 12345);
                ItemDiscount itemDiscount2 = new ItemDiscount(new BigDecimal("99.90"), 47895);
                ItemDiscount itemDiscount3 = new ItemDiscount(new BigDecimal("9.50"), 48950);
                ItemDiscount itemDiscount4 = new ItemDiscount(new BigDecimal(1), 38940);
                itemDiscounts.put(itemDiscount1.getItemID(), itemDiscount1);
                itemDiscounts.put(itemDiscount2.getItemID(), itemDiscount2);
                itemDiscounts.put(itemDiscount3.getItemID(), itemDiscount3);
                itemDiscounts.put(itemDiscount4.getItemID(), itemDiscount4);

                DiscountsDTO discountsDTO = new DiscountsDTO(itemDiscounts, new BigDecimal("10%"));

                return discountsDTO;
        }
}
