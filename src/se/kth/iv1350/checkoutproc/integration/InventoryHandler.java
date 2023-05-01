package se.kth.iv1350.checkoutproc.integration;

import se.kth.iv1350.checkoutproc.model.Item;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class InventoryHandler {
        /**
         * for now this class is mostly placeholder.
         * since we have no actual database access
         * we fake it in this class.
         */

        private AbstractMap<Integer, ItemDTO> items;

        /**
         * placeholder constructor
         * This creates a "fake database" with a few items in it.
         */
        public InventoryHandler(){
                //TODO this is placeholder stuff
                items = new HashMap<>();
                createPlaceholderEntry(new ItemDTO(12345, "Cordless Drill", new BigDecimal(.25), new BigDecimal(1299)));
                createPlaceholderEntry(new ItemDTO(1738, "Camping Tent", new BigDecimal(.25), new BigDecimal(499)));
                createPlaceholderEntry(new ItemDTO(47895, "Three Blind Mice", new BigDecimal(.12), new BigDecimal("799.90")));
                createPlaceholderEntry(new ItemDTO(1324, "Foo Bar", new BigDecimal(.12), new BigDecimal("1337.99")));
                createPlaceholderEntry(new ItemDTO(78397, "Portable Haircut", new BigDecimal(.06), new BigDecimal(1599)));
                createPlaceholderEntry(new ItemDTO(48950, "Lorem Ipsum", new BigDecimal(.06), new BigDecimal("39.50")));
                createPlaceholderEntry(new ItemDTO(38940, "Unladen African Swallow", new BigDecimal(.25), new BigDecimal(41)));
        }

        /**
         * adds an item to our fake database
         * @param itemDTO the item to add
         */
        private void createPlaceholderEntry(ItemDTO itemDTO){
                items.put(itemDTO.getItemID(), itemDTO);
        }


        /**
         * returns DTO with item info from database.
         * @param itemID uniue item ID
         * @return {@link ItemDTO} with item data from db
         * if no such item exists, returns null.
         */
        public ItemDTO fetchItem(int itemID){
                return items.get(itemID);
        }

        /**
         * Updates the inventory with what items have been sold
         *
         * This is a dummy method, as there as of now exists no actual inventory db
         * @param itemID the item to be updated
         * @param count the number of the item to be removed from inventory.
         */
        public void updateInventory(int itemID, int count){
                //TODO dummy method
        }
}
