package se.kth.iv1350.checkoutproc.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.checkoutproc.model.ItemDiscount;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DiscountsDTOTest {
        DiscountsDTO discountsDTO;
        AbstractMap<Integer, ItemDiscount> itemDiscounts;
        ItemDiscount itemDiscount1;
        ItemDiscount itemDiscount2;
        ItemDiscount itemDiscount3;
        ItemDiscount itemDiscount4;
        BigDecimal cartDiscount;
        @BeforeEach
        void setUp() {
                itemDiscount1 = new ItemDiscount(new BigDecimal(199), 12345);
                itemDiscount2 = new ItemDiscount(new BigDecimal("99.90"), 47895);
                itemDiscount3 = new ItemDiscount(new BigDecimal("9.50"), 48950);
                itemDiscount4 = new ItemDiscount(new BigDecimal(1), 38940);
                itemDiscounts = new HashMap<>();
                itemDiscounts.put(itemDiscount1.getItemID(), itemDiscount1);
                itemDiscounts.put(itemDiscount2.getItemID(), itemDiscount2);
                itemDiscounts.put(itemDiscount3.getItemID(), itemDiscount3);
                itemDiscounts.put(itemDiscount4.getItemID(), itemDiscount4);
                cartDiscount = new BigDecimal(".05");
                discountsDTO = new DiscountsDTO(itemDiscounts, cartDiscount);
        }

        @AfterEach
        void tearDown() {
                itemDiscounts = null;
                itemDiscount1 = null;
                itemDiscount2 = null;
                itemDiscount3 = null;
                itemDiscount4 = null;
        }

        @Test
        void getItemDiscount() {
                assertEquals(
                        itemDiscount1,
                        discountsDTO.getItemDiscount(12345),
                        "wrong discount amount");
                assertEquals(
                        itemDiscount2,
                        discountsDTO.getItemDiscount(47895),
                        "wrong discount amount");
                assertEquals(
                        itemDiscount3,
                        discountsDTO.getItemDiscount(48950),
                        "wrong discount amount");
                assertEquals(
                        itemDiscount4,
                        discountsDTO.getItemDiscount(38940),
                        "wrong discount amount");
        }

        @Test
        void getCartDiscount() {
                assertEquals(
                        BigDecimal.ONE.subtract(cartDiscount),
                        discountsDTO.getCartDiscount(),
                        "wrong cart discount returned");
        }
}