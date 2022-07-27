package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class StockManagementTests {

    ExternalISBNDataService testWebService;
    StockManager stockManager;
    ExternalISBNDataService testDatabaseService;
    String isbn;

    @Before
    public void setup() {
        testWebService = mock(ExternalISBNDataService.class);
        testDatabaseService = mock(ExternalISBNDataService.class);

        stockManager = new StockManager();

        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        isbn = "0140177396";

    }

    @Test
    public void testGetCorrectLocatorCode() {
        when(testWebService.lookup(anyString())).thenReturn(new Book(isbn, "Title", "Author"));
        when(testDatabaseService.lookup(anyString())).thenReturn(null);

        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396A1", locatorCode);
    }

    // Create Mock, similar to stub but can find out if methods were called, instead of what the returned values are
    @Test
    public void databaseUsedIfDataPresent() {

        when(testDatabaseService.lookup("0140177396")).thenReturn(new Book(isbn, "Title", "Author"));

        stockManager.getLocatorCode(isbn);

        verify(testDatabaseService, times(1)).lookup(isbn);
        // Alternative: verify(databaseService).lookup("0140177396");

        verify(testWebService, times(0)).lookup(anyString());
        // Alternative: verify(webService, never()).lookup(anyString());
    }
    @Test
    public void webServiceUsedIfDataNotPresentInDatabase() {
        when(testDatabaseService.lookup("0140177396")).thenReturn(null);
        when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "Title", "Author"));

        stockManager.getLocatorCode(isbn);

        verify(testDatabaseService).lookup("0140177396");
        verify(testWebService).lookup("0140177396");
    }

}

