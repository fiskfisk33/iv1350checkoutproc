package se.kth.iv1350.checkoutproc.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.checkoutproc.integration.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
        Sale sale;
        ItemDTO itemDTO1;
        ItemDTO itemDTO2;
        ItemDTO itemDTO3;

        @BeforeEach
        void setUp() throws ItemNotInDbException {
                AccountingHandler accountingHandler = new AccountingHandler();
                InventoryHandler inventoryHandler = InventoryHandler.getInventoryHandler();
                inventoryHandler.addLogger(new LogFileWriter("test"));
                Printer printer = new Printer();
                sale = new Sale(accountingHandler,inventoryHandler,printer);

                itemDTO1 = inventoryHandler.fetchItem(12345);
                itemDTO2 = inventoryHandler.fetchItem(1324);
                itemDTO3 = inventoryHandler.fetchItem(38940);
        }

        @AfterEach
        void tearDown() {
        }

        @Test
        void addItems() {
                sale.addItems(itemDTO1, 1);
                sale.addItems(itemDTO2, 16);
                sale.addItems(itemDTO3, 3);
                sale.addItems(itemDTO3, 1);
                SaleInfoDTO saledto = sale.fetchSaleInfo();

                Set<Integer> itemIDs = new HashSet<>();
                for(Item item : saledto){
                        itemIDs.add(item.getItemID());
                }
                assertEquals(3, itemIDs.size(), "wrong number of items");
        }

        @Test
        void fetchSaleInfo() {
                sale.addItems(itemDTO1, 1);
                sale.addItems(itemDTO2, 16);
                sale.addItems(itemDTO3, 3);
                sale.addItems(itemDTO3, 1);
                SaleInfoDTO saledto = sale.fetchSaleInfo();

                BigDecimal expectedPrice = itemDTO1.getPrice()
                        .add(itemDTO2.getPrice().multiply(BigDecimal.valueOf(16)))
                        .add(itemDTO3.getPrice().multiply(BigDecimal.valueOf(4)));
                BigDecimal actualPrice = saledto.getTotalPrice();
                assertEquals(expectedPrice, actualPrice, "wrong total price");

                BigDecimal expectedVAT = (itemDTO1.getPrice().multiply(itemDTO1.getVat())
                        .add(itemDTO2.getPrice().multiply(BigDecimal.valueOf(16)).multiply(itemDTO2.getVat()))
                        .add(itemDTO3.getPrice().multiply(BigDecimal.valueOf(4)).multiply(itemDTO3.getVat())))
                        .setScale(2,RoundingMode.HALF_DOWN);
                BigDecimal actualVAT = saledto.getTotalVAT();
                assertEquals(expectedVAT, actualVAT, "wrong vat");

                assertNotNull(saledto.getSaleTime());
                assertNull(saledto.getPayment());
                assertNull(saledto.getChange());

                Payment payment = new Payment(BigDecimal.valueOf(40000));
                sale.endSale(payment);
                SaleInfoDTO saledto2 = sale.fetchSaleInfo();


                assertEquals(payment, saledto2.getPayment());

                BigDecimal expectedChange = payment.getAmount().subtract(expectedPrice.add(expectedVAT));

                Change actualChange = saledto2.getChange();
                assertEquals(expectedChange, actualChange.getAmount(), "wrong change amount");
        }

        @Test
        void endSale() {
                Change change = sale.endSale(new Payment(new BigDecimal(132)));
                assertNotNull(change);
        }

        @Test
        void applyDiscounts() {
                ItemDTO item1 = new ItemDTO(123, "mjölk", BigDecimal.valueOf(0.25),BigDecimal.valueOf(100));
                ItemDTO item2 = new ItemDTO(432, "havre", BigDecimal.valueOf(0.25),BigDecimal.valueOf(10));
                ItemDTO item3 = new ItemDTO(285, "beige", BigDecimal.valueOf(0.05),BigDecimal.valueOf(100));

                AbstractMap<Integer, ItemDiscount> itemDiscounts = new HashMap<>();
                itemDiscounts.put(123, new ItemDiscount(BigDecimal.valueOf(90),123));
                itemDiscounts.put(444, new ItemDiscount(BigDecimal.valueOf(90),444));

                DiscountsDTO discountsDTO = new DiscountsDTO(itemDiscounts, BigDecimal.valueOf(0.25));

                sale.addItems(item1, 2);
                sale.addItems(item2, 1);
                sale.addItems(item3, 2);
                SaleInfoDTO saleInfoDTObefore = sale.fetchSaleInfo();

                sale.applyDiscounts(discountsDTO);

                SaleInfoDTO saleInfoDTOafter = sale.fetchSaleInfo();
                BigDecimal priceAfter = saleInfoDTOafter.getTotalPrice();

                /*
                        (10 * 2 +
                        10 * 1 +
                        100 * 2) * 0.75
                 */
                BigDecimal expectedPriceAfter = BigDecimal.valueOf((10*2+10+100*2)*0.75).setScale(2,RoundingMode.HALF_DOWN);
                assertEquals(expectedPriceAfter, priceAfter, "wrong price after discount");

                BigDecimal vatAfter = saleInfoDTOafter.getTotalVAT();

                /*
                        (2.5 * 2 +
                        2.5 * 1 +
                        5*2) * 0.75
                 */
                BigDecimal expectedVatAfter = BigDecimal.valueOf((5+2.5+10)*0.75).setScale(2,RoundingMode.HALF_DOWN);
                assertEquals(expectedVatAfter, vatAfter, "wrong vat");
        }
}