package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidateISBNTest {

    @Test
    public void checkValidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result =  validator.checkISBN("0140449116");
        assertTrue("first value", result);
        result = validator.checkISBN("0140177396");
        assertTrue("second value", result);
    }
    @Test
    public void checkInvalidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result =  validator.checkISBN("0140449117");
        assertFalse(result);
    }
}
