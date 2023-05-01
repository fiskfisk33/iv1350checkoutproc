package se.kth.iv1350.checkoutproc.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryHandlerTest {
        private InventoryHandler inventoryHandler;

        @BeforeEach
        void setUp() {
                inventoryHandler = new InventoryHandler();
        }

        @AfterEach
        void tearDown() {
                inventoryHandler = null;
        }

        @Test
        void fetchItem() {
                ItemDTO item12345 = inventoryHandler.fetchItem(12345);
                ItemDTO item_unexistant = inventoryHandler.fetchItem(646464);

                assertEquals("Cordless Drill", item12345.getDescription(), "wrong description");
                assertEquals(12345, item12345.getItemID(), "wrong ID");
                assertEquals(null, item_unexistant, "item should be null");
        }

}