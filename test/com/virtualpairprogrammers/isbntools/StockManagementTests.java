package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StockManagementTests {

    @Test
    public void testGetCorrectLocatorCode() {
        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

}

