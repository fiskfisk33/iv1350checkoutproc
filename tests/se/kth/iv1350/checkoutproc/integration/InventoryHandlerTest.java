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
                inventoryHandler = new InventoryHandler(new LogFileWriter("test"));
        }

        @AfterEach
        void tearDown() {
                inventoryHandler = null;
        }

        @Test
        void fetchItem() throws ItemNotInDbException {
                assertThrows(ItemNotInDbException.class,
                        ()-> inventoryHandler.fetchItem(1111), "no or wrong exception thrown");
                assertThrows(DbUnreachableException.class,
                        ()-> inventoryHandler.fetchItem(218), "no or wrong exception thrown");
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