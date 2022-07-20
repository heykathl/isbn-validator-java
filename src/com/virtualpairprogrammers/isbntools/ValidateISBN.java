package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {

    public static final int LONG_ISBN = 13;
    public static final int SHORT_ISBN = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN(String isbn) {
//        Checks whether isbn is 13 digits long
        if (isbn.length() == LONG_ISBN) {
            return isValid13ISBN(isbn);

        } else {

//          Checking whether isbn is 10 digits long
            if (isbn.length() != SHORT_ISBN) {
//            Alternative way to check if each character is a digit
//            || !isbn.matches("\\d+")) {
                throw new NumberFormatException("Error - invalid number");
            }
            ;
            return isValid10ISBN(isbn);
        }
    }

    private boolean isValid10ISBN(String isbn) {
        int total = 0;
//        Checking that each digit is a number
        for (int i = 0; i < SHORT_ISBN; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
//              Checking that the last character is an X
                if (i == 9 && isbn.charAt(i) == 'X') {
                    total += 10;
                } else {
                    throw new NumberFormatException("Error");
                }
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN - i);
            }
        }
//        Part of the isbn validation method to divide the total by 11
        if (total % SHORT_ISBN_MULTIPLIER == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isValid13ISBN(String isbn) {
        int total = 0;
//          Multiply second digit by 1 and every other by 3
        for (int i = 0; i < LONG_ISBN; i++) {
            if (i % 2 == 0) {
                total += Character.getNumericValue(isbn.charAt(i));
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
            }
        }
//        Part of the isbn validation method to divide the total by 10
        if (total % LONG_ISBN_MULTIPLIER == 0) {
            return true;
        } else {
            return false;
        }
    }
}
