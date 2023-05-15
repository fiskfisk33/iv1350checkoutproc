package se.kth.iv1350.checkoutproc.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.checkoutproc.integration.ItemDTO;
import se.kth.iv1350.checkoutproc.model.Change;
import se.kth.iv1350.checkoutproc.model.Item;
import se.kth.iv1350.checkoutproc.model.Payment;
import se.kth.iv1350.checkoutproc.model.SaleInfoDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
        Controller controller;
        int itemID;

        @BeforeEach
        void setUp() {
                controller = new Controller();
                itemID = 12345;
        }

        @AfterEach
        void tearDown() {
                controller = null;
        }

        @Test
        void initiateSale() {
                //controller.fetchSaleInfo();
                //Här vill jag testa efter NullPointerExceptions, men får inte ännu
                controller.initiateSale();

                try {
                        controller.addItem(itemID);
                        assertEquals(itemID, controller.addItem(itemID).getItemID());
                } catch (NoSuchItemException e) {
                        throw new RuntimeException(e);
                } catch (InventoryException e) {
                        throw new RuntimeException(e);
                }
        }

        @Test
        void addItem() throws InventoryException, NoSuchItemException {

                //controller.addItem(itemID);
                //exception expected

                controller.initiateSale();

                assertEquals(itemID, controller.addItem(itemID).getItemID());

                SaleInfoDTO saleInfoDTO = controller.fetchSaleInfo();
                assertEquals(BigDecimal.valueOf(1299),saleInfoDTO.getTotalPrice());

                controller.addItem(itemID, 3);
                SaleInfoDTO saleInfoDTO2 = controller.fetchSaleInfo();
                assertEquals(BigDecimal.valueOf(1299*4), saleInfoDTO2.getTotalPrice());

                assertThrows(NoSuchItemException.class,
                        ()-> controller.addItem(1111), "no or wrong exception thrown");
                assertThrows(InventoryException.class,
                        ()-> controller.addItem(218), "no or wrong exception thrown");


        }

        @Test
        void fetchSaleInfo() throws InventoryException, NoSuchItemException {
                Instant bcreation = Instant.now();
                controller.initiateSale();
                Instant acreation = Instant.now();

                controller.addItem(itemID);
                SaleInfoDTO saleInfoDTO = controller.fetchSaleInfo();

                saleInfoDTO.getTotalPrice();
                saleInfoDTO.getSaleTime();
                assertTrue(saleInfoDTO.getSaleTime().isBefore(acreation), "not correct time");
                assertTrue(saleInfoDTO.getSaleTime().isAfter(bcreation), "not correct time");

                assertEquals(BigDecimal.valueOf(1299), saleInfoDTO.getTotalPrice());

                assertEquals(BigDecimal.valueOf(1299*.25).setScale(2, RoundingMode.HALF_DOWN), saleInfoDTO.getTotalVAT());
        }

        @Test
        void endSale() throws InventoryException, NoSuchItemException {
                controller.initiateSale();
                controller.addItem(itemID);
                Payment payment = new Payment(BigDecimal.valueOf(2000));
                BigDecimal expectedPrice = new BigDecimal(1299+1299*.25).setScale(2, RoundingMode.HALF_DOWN);
                BigDecimal expectedChange = payment.getAmount().subtract(expectedPrice);
                Change change = controller.endSale(payment);
                assertEquals(expectedChange, change.getAmount());
        }

        @Test
        void requestDiscount() throws InventoryException, NoSuchItemException {
                controller.initiateSale();
                controller.addItem(itemID);
                Payment payment = new Payment(BigDecimal.valueOf(2000));
                BigDecimal expectedPrice = (new BigDecimal((1100+1100*.25)*0.9).setScale(2, RoundingMode.HALF_DOWN));
                BigDecimal expectedChange = payment.getAmount().subtract(expectedPrice);

                controller.requestDiscount(123123);
                Change change = controller.endSale(payment);
                assertEquals(expectedChange, change.getAmount());

        }
}