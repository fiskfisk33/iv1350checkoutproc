package se.kth.iv1350.checkoutproc.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.checkoutproc.integration.InventoryHandler;
import se.kth.iv1350.checkoutproc.integration.ItemDTO;
import se.kth.iv1350.checkoutproc.integration.ItemNotInDbException;
import se.kth.iv1350.checkoutproc.integration.LogFileWriter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
        Cart cart;
        ItemDTO itemDTO1;
        ItemDTO itemDTO2;
        ItemDTO itemDTO3;

        @BeforeEach
        void setUp() {
                cart = new Cart();
                InventoryHandler inventoryHandler = new InventoryHandler(new LogFileWriter("test"));
                try {
                        itemDTO1 = inventoryHandler.fetchItem(12345);
                        itemDTO2 = inventoryHandler.fetchItem(1324);
                        itemDTO3 = inventoryHandler.fetchItem(38940);
                } catch (ItemNotInDbException e) {
                        throw new RuntimeException(e);
                }
        }

        @AfterEach
        void tearDown() {
                cart = null;
                itemDTO1 = itemDTO2 = itemDTO3 = null;
        }

        @Test
        void addItems() {
                cart.addItems(itemDTO1, 1);
                Item item1 = new Item(itemDTO1);
                assertEquals(1,cart.getItemCount(item1));

                cart.addItems(itemDTO2, 16);
                Item item2 = new Item(itemDTO2);
                assertEquals(16, cart.getItemCount(item2));

                cart.addItems(itemDTO3, 4);
                cart.addItems(itemDTO3, 2);
                Item item3 = new Item(itemDTO3);
                assertEquals(6, cart.getItemCount(item3));

        }

        @Test
        void getTotalPrice() {
                cart.addItems(itemDTO1, 1);
                BigDecimal price1 = new BigDecimal(1299);
                BigDecimal price1test = cart.getTotalPrice();
                assertEquals(price1, price1test, "wrong price");

                cart.addItems(itemDTO2, 16);
                BigDecimal price2 = itemDTO2.getPrice().multiply(new BigDecimal(16)).add(price1);
                BigDecimal price2test = cart.getTotalPrice();
                assertEquals(price2, price2test, "wrong price");

                cart.addItems(itemDTO3, 4);
                cart.addItems(itemDTO3, 2);
                BigDecimal price3 = itemDTO3.getPrice().multiply(new BigDecimal(6)).add(price2);
                BigDecimal price3test = cart.getTotalPrice();
                assertEquals(price3, price3test, "wrong price");
        }

        @Test
        void getTotalVAT() {
                cart.addItems(itemDTO1, 1);
                BigDecimal vat1 = itemDTO1.getPrice().multiply(itemDTO1.getVat());
                BigDecimal vat1test = cart.getTotalVAT();
                assertEquals(vat1, vat1test, "wrong vat");

                cart.addItems(itemDTO2, 16);
                BigDecimal vat2 = (itemDTO2.getPrice()
                                        .multiply(BigDecimal.valueOf(16))
                                        .multiply(itemDTO2.getVat())
                                        .add(vat1)).setScale(2, RoundingMode.HALF_DOWN);
                BigDecimal vat2test = cart.getTotalVAT();
                assertEquals(vat2, vat2test, "wrong vat");

                cart.addItems(itemDTO3, 4);
                cart.addItems(itemDTO3, 2);
                BigDecimal vat3 = itemDTO3.getPrice()
                                        .multiply(BigDecimal.valueOf(6))
                                        .multiply(itemDTO3.getVat())
                                        .add(vat2);
                BigDecimal vat3test = cart.getTotalVAT();
                assertEquals(vat3, vat3test, "wrong vat");

        }

        @Test
        void getItemCount() {
                cart.addItems(itemDTO1, 1);
                Item item1 = new Item(itemDTO1);
                assertEquals(1,cart.getItemCount(item1));

                cart.addItems(itemDTO2, 16);
                Item item2 = new Item(itemDTO2);
                assertEquals(16, cart.getItemCount(item2));

                cart.addItems(itemDTO3, 4);
                cart.addItems(itemDTO3, 2);
                Item item3 = new Item(itemDTO3);
                assertEquals(6, cart.getItemCount(item3));
        }

        @Test
        void applyCartDiscount() {
                cart.addItems(itemDTO1, 1);
                cart.addItems(itemDTO2, 16);
                cart.addItems(itemDTO3, 4);
                cart.addItems(itemDTO3, 2);
                BigDecimal priceBefore = cart.getTotalPrice();
                cart.applyCartDiscount(new BigDecimal("0.95"));
                BigDecimal price5pdiscount = cart.getTotalPrice();

                assertEquals(priceBefore.multiply(new BigDecimal("0.95")), price5pdiscount, "wrong price");

        }

        @Test
        void iterator() {
                cart.addItems(itemDTO1, 1);
                cart.addItems(itemDTO2, 16);
                cart.addItems(itemDTO3, 4);
                cart.addItems(itemDTO3, 2);
                Set<Integer> itemIDs = new HashSet<>();
                int i = 0;
                for(Item item : cart){
                        itemIDs.add(item.getItemID());
                }
                assertTrue(itemIDs.contains(itemDTO1.getItemID()), "item missing");
                assertTrue(itemIDs.contains(itemDTO2.getItemID()), "item missing");
                assertTrue(itemIDs.contains(itemDTO2.getItemID()), "item missing");
                assertEquals(3, itemIDs.size());
        }
}