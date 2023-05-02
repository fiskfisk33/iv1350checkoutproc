package se.kth.iv1350.checkoutproc.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InventoryHandlerTest {
        InventoryHandler inventoryHandler;

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
                ItemDTO itemNotFound = inventoryHandler.fetchItem(1111);
                assertNull(itemNotFound);
                ItemDTO itemDrill = inventoryHandler.fetchItem(12345);
                assertEquals(new BigDecimal(1299), itemDrill.getPrice(), "wrong item");
                ItemDTO itemSwallow = inventoryHandler.fetchItem(38940);
                assertEquals("Unladen African Swallow", itemSwallow.getDescription(), "wrong item");

        }

        @Test
        void updateInventory() {
                inventoryHandler.updateInventory(12343, 12);
                //TODO nothing to test

        }

}