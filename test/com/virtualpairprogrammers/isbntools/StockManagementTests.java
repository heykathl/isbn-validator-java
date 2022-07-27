package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class StockManagementTests {

    @Test
    public void testGetCorrectLocatorCode() {
        ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);
        when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Title", "Author"));

        ExternalISBNDataService testDatabaseService = mock(ExternalISBNDataService.class);
        when(testDatabaseService.lookup(anyString())).thenReturn(null);

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396A1", locatorCode);
    }

    // Create Mock, similar to stub but can find out if methods were called, instead of what the returned values are
    @Test
    public void databaseUsedIfDataPresent() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "Title", "Author"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookup("0140177396");
        // Alternative: verify(databaseService).lookup("0140177396");

        verify(webService, times(0)).lookup(anyString());
        // Alternative: verify(webService, never()).lookup(anyString());
    }
    @Test
    public void webServiceUsedIfDataNotPresentInDatabase() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0140177396")).thenReturn(null);
        when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "Title", "Author"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService).lookup("0140177396");
        verify(webService).lookup("0140177396");
    }

}

