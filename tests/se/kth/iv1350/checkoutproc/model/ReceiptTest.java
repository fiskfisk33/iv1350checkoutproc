package se.kth.iv1350.checkoutproc.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.checkoutproc.integration.InventoryHandler;
import se.kth.iv1350.checkoutproc.integration.ItemNotInDbException;
import se.kth.iv1350.checkoutproc.integration.Printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptTest {

        private PrintStream stdOut = System.out;
        private ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        private Printer printer;
        SaleInfoDTO saleInfo;
        InventoryHandler inventory;

        BigDecimal totalPrice;
        BigDecimal totalVAT;
        Payment payment;
        Change change;
        Instant saleTime;
        @BeforeEach
        void setUp() {
                System.setOut(new PrintStream(testOut));
                inventory = InventoryHandler.getInventoryHandler();

                TestCart cart = new TestCart();
                try {
                        cart.addItems(inventory.fetchItem(12345),1);
                        cart.addItems(inventory.fetchItem(38940),1);
                        cart.addItems(inventory.fetchItem(48950),10);
                } catch (ItemNotInDbException e) {
                        throw new RuntimeException(e);
                }


                printer = new Printer();
                totalPrice = BigDecimal.valueOf(1337.42);
                totalVAT = BigDecimal.valueOf(12.2);
                payment = new Payment(BigDecimal.valueOf(1615.9));
                change = new Change(BigDecimal.valueOf(4367.1));
                saleTime = Instant.now();

                saleInfo = new SaleInfoDTO(totalPrice, totalVAT, payment, change, saleTime, cart);
        }

        @AfterEach
        void tearDown() {
                System.setOut(stdOut);
        }

        @Test
        void printReceipt() {
                Receipt receipt = new Receipt(saleInfo);
                printer.printReceipt(receipt);
                String output = testOut.toString().trim();
                int begIndex = output.indexOf("\n");
                int endIndex = output.indexOf("\n", begIndex+1);

                //////////////////////////////////////////////////////////////////
                System.setOut(stdOut);

                String result = output.substring(begIndex, endIndex).trim();
                String expected = "* " + saleTime;

                assertEquals(expected, result, "sale time was not printed correctly");

                begIndex = output.indexOf("\n", endIndex + 1);
                endIndex = output.indexOf("\n", begIndex + 1);
                result = output.substring(begIndex, endIndex).trim();
                expected = "* 10 x Lorem Ipsum  à 41.87";


                assertEquals(expected, result, "item line was not printed correctly");

                begIndex = endIndex;
                endIndex = output.indexOf("\n", begIndex + 1);
                result = output.substring(begIndex, endIndex).trim();
                expected = "*  = 418.70";

                assertEquals(expected, result, "item sum was not printed correctly");

                begIndex = output.indexOf("\n", endIndex + 1);
                endIndex = output.indexOf("\n", begIndex + 1);
                result = output.substring(begIndex, endIndex).trim();
                expected = "* 1 x Unladen African Swallow  à 51.25";

                assertEquals(expected, result, "item line was not printed correctly");

                begIndex = endIndex;
                endIndex = output.indexOf("\n", begIndex + 1);
                result = output.substring(begIndex, endIndex).trim();
                expected = "*  = 51.25";

                assertEquals(expected, result, "item sum was not printed correctly");

                begIndex = output.indexOf("\n", endIndex + 1);
                endIndex = output.indexOf("\n", begIndex + 1);
                result = output.substring(begIndex, endIndex).trim();
                expected = "* 1 x Cordless Drill  à 1623.75";

                assertEquals(expected, result, "item line was not printed correctly");

                begIndex = endIndex;
                endIndex = output.indexOf("\n", begIndex + 1);
                result = output.substring(begIndex, endIndex).trim();
                expected = "*  = 1623.75";

                assertEquals(expected, result, "item sum was not printed correctly");

                begIndex = output.indexOf("\n", endIndex + 1);
                endIndex = output.indexOf("\n", begIndex + 1);
                result = output.substring(begIndex, endIndex).trim();
                expected = "***Gross: " + totalPrice;

                assertEquals(expected, result, "Total prices was not printed correctly");

                begIndex = endIndex;
                endIndex = output.indexOf("\n", begIndex + 1);
                result = output.substring(begIndex, endIndex).trim();
                expected = "***VAT  : " + totalVAT;

                assertEquals(expected, result, "Total VAT was not printed correctly");

                begIndex = output.indexOf("\n", endIndex + 1);
                endIndex = output.indexOf("\n", begIndex + 1);
                result = output.substring(begIndex, endIndex).trim();
                expected = "***Paid : " + payment.getAmount();

                assertEquals(expected, result, "Total VAT was not printed correctly");

                begIndex = endIndex;
                endIndex = output.indexOf("\n", begIndex + 1);
                result = output.substring(begIndex, endIndex).trim();
                expected = "***Change : " + change.getAmount();

                assertEquals(expected, result, "Total VAT was not printed correctly");
        }


        private class TestCart extends Cart{
                TestCart(){
                        super();
                }
        }
}