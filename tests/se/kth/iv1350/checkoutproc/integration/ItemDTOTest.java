package se.kth.iv1350.checkoutproc.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ItemDTOTest {
        ItemDTO itemDTO;
        int itemID;
        String description;
        BigDecimal vat;
        BigDecimal price;
        @BeforeEach
        void setUp() {
                itemID = 625;
                description = "test item";
                vat = new BigDecimal("0.25");
                price = new BigDecimal(666);

                itemDTO = new ItemDTO(itemID,description,vat,price);

        }

        @AfterEach
        void tearDown() {
                itemDTO = null;
        }

        @Test
        void getItemID() {
                assertEquals(itemID, itemDTO.getItemID(), "wrong ID");
        }

        @Test
        void getDescription() {
                assertEquals(description, itemDTO.getDescription(), "wrong Description");
        }

        @Test
        void getVat() {
                assertEquals(vat, itemDTO.getVat(), "wrong vat");
        }

        @Test
        void getPrice() {
                assertEquals(price, itemDTO.getPrice(), "wrong price");
        }
}