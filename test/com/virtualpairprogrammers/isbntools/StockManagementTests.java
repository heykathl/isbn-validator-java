package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import static org.mockito.Mockito.*;

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

    // Create Mock, similar to stub but can find out if methods were called
    @Test
    public void databaseUsedIfDataPresent() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "Title", "Author"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseServicee(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookup("0140177396");
        verify(webService, times(0)).lookup(anyString());
    }
    @Test
    public void webServiceUsedIfDataNotPresentInDatabase() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0140177396")).thenReturn(null);
        when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "Title", "Author"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseServicee(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookup("0140177396");
        verify(webService, times(1)).lookup("0140177396");
    }

}

