package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ValidateISBNTest {

    @Test
    public void checkValid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result =  validator.checkISBN("0140449116");
        assertTrue("first value", result);
        result = validator.checkISBN("0140177396");
        assertTrue("second value", result);
    }

    @Test
    public void checkValid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result =  validator.checkISBN("9781853260087");
        assertTrue("first value", result);
        result =  validator.checkISBN("9781853267338");
        assertTrue("second value",  result);
    }

    @Test
    public void TenDigitISBNNumbersEndingInXAreValid() {
        ValidateISBN validator = new ValidateISBN();
        boolean result =  validator.checkISBN("012000030X");
        assertTrue(result);
    }

    @Test
    public void checkInvalid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result =  validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    public void checkInvalid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result =  validator.checkISBN("9781853267336");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void nineDigitsISBNNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("123456789");
    }

    @Test(expected = NumberFormatException.class)
    public void charactersNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("helloworld");
        validator.checkISBN("helloworldman");
    }
}
