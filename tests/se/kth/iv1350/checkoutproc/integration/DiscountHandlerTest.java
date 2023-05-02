package se.kth.iv1350.checkoutproc.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.checkoutproc.model.Item;
import se.kth.iv1350.checkoutproc.model.ItemDiscount;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DiscountHandlerTest {
        DiscountHandler discountHandler;
        @BeforeEach
        void setUp() {
                discountHandler = new DiscountHandler();
        }

        @AfterEach
        void tearDown() {
                discountHandler = null;
        }

        @Test
        void testFetchDiscounts() {
                DiscountsDTO discounts = discountHandler.fetchDiscounts(0);
                BigDecimal cartDiscount = discounts.getCartDiscount();
                assertEquals(new BigDecimal("0.90"),cartDiscount, "wrong cart discount");

                ItemDiscount itemDiscount1 = discounts.getItemDiscount(12345);
                assertEquals(new BigDecimal(199), itemDiscount1.getAmount(), "wrong item discount");
                assertEquals(12345, itemDiscount1.getItemID(), "wrong item discount");

        }
}