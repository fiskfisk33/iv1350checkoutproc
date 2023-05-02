package se.kth.iv1350.checkoutproc.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.checkoutproc.integration.ItemDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
class ItemTest {
        Item item;

        @BeforeEach
        void setUp() {

                item = createTestItem();
        }

        private ItemDTO createTestItemDTO(){
                int itemID = 625;
                String description = "test item";
                BigDecimal vat = new BigDecimal("0.25");
                BigDecimal price = new BigDecimal(666);

                return new ItemDTO(itemID,description,vat,price);
        }
        private Item createTestItem(){
                ItemDTO itemDTO = createTestItemDTO();
                return new Item(itemDTO);
        }

        @AfterEach
        void tearDown() {
                item = null;
        }

        @Test
        void getItemID() {
                assertEquals(625, item.getItemID(), "wrong item id");
        }

        @Test
        void getDescription() {
                assertEquals("test item", item.getDescription(), "wrong description");
        }

        @Test
        void getVat() {
                assertEquals(new BigDecimal("0.25"), item.getVat(), "wrong VAT");
        }

        @Test
        void getDiscount() {
                assertEquals(BigDecimal.ZERO, item.getDiscount(), "discount should start out zero");
        }

        @Test
        void setDiscount() {
                item.setDiscount(new BigDecimal("0.25"));
                assertEquals(new BigDecimal("0.25"), item.getDiscount(), "wrong discount");
        }
}