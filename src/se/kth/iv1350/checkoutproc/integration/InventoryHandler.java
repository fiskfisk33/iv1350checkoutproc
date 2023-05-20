package se.kth.iv1350.checkoutproc.integration;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * this class is the interface between the inventory database and the rest of the program.
 * this class is now a singleton
 */
public class InventoryHandler {
        private static final InventoryHandler INVENTORY_HANDLER = new InventoryHandler();


        /**
         * for now this class is mostly placeholder.
         * since we have no actual database access
         * we fake it in this class.
         */
        private AbstractMap<Integer, ItemDTO> items;
        private Set<Logger> loggers = new HashSet<>();

        /**
         * placeholder constructor
         * This creates a "fake database" with a few items in it.
         */
        private InventoryHandler(){
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
         * @return the only instance of this singleton.
         */
        public static InventoryHandler getInventoryHandler(){
                return INVENTORY_HANDLER;
        }

        /**
         * add a logger to the set of loggers this logs to
         * @param logger the logger
         */
        public void addLogger (Logger logger){
                loggers.add(logger);
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
         * @throws ItemNotInDbException if no item with supplied itemID found in db
         */
        public ItemDTO fetchItem(int itemID) throws ItemNotInDbException {
                 //dummy database error
                 if(itemID == 218){
                         //this simulates a database unreachable error
                         RuntimeException e = new DbUnreachableException("database not responding to request");
                         loggers.forEach( (logger) -> logger.log(e.toString()) );
                         throw e;
                 }
                 ItemDTO item = items.get(itemID);
                 if (item == null)
                         throw new ItemNotInDbException("Item with id \"" + itemID + "\" not found in db");
                 return item;
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
