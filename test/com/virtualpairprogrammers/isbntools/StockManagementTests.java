package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StockManagementTests {

    @Test
    public void testGetCorrectLocatorCode() {

        // Creating a test stub as there is dependency on an external service
        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            // Inject this stub into our class to ensure our business logic works
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
            }
        };

        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };


        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseServicee(testDatabaseService);
        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

}

