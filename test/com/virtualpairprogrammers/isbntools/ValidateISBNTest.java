package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ValidateISBNTest {

    @Test
    public void checkValidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result =  validator.checkISBN(140449116);
        assertTrue(result);
    }
    @Test
    public void checkInvalidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result =  validator.checkISBN(140449117);
        assertFalse(result);
    }
}
