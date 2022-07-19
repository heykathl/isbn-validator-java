package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {
    public boolean checkISBN(String isbn) {
        int total = 0;

//        Checks whether isbn is 13 digits long
        if (isbn.length() == 13) {
            return true;
        }

//        Checking whether isbn is 10 digits long
        if (isbn.length() != 10) {
//            Alternative way to check if each character is a digit
//            || !isbn.matches("\\d+")) {
            throw new NumberFormatException("Error - invalid number");
        }

//        Checking that each digit is a number
        for(int i = 0; i < 10; i++){
            if(!Character.isDigit(isbn.charAt(i))) {
//            Checking that the last character is an X
                if (i == 9 && isbn.charAt(i) == 'X') {
                    total += 10;
                } else {
                    throw new NumberFormatException("Error");
                }
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
            }

        }

//        Part of the isbn validation method to divide the total by 11
        if (total % 11 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
