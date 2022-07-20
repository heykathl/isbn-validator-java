package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {
    public boolean checkISBN(String isbn) {
//        Checks whether isbn is 13 digits long
        if (isbn.length() == 13) {
            int total = 0;
//          Multiply second digit by 1 and every other by 3
            for (int i = 0; i < 13; i++) {
                if (i % 2 == 0) {
                    total += Character.getNumericValue(isbn.charAt(i));
                } else {
                    total += Character.getNumericValue(isbn.charAt(i)) * 3;
                }
            }

            if (total % 10 == 0) {
                return true;
            } else {
                return false;
            }

        } else {

//          Checking whether isbn is 10 digits long
            if (isbn.length() != 10) {
//            Alternative way to check if each character is a digit
//            || !isbn.matches("\\d+")) {
                throw new NumberFormatException("Error - invalid number");
            }
            ;
            int total = 0;
//        Checking that each digit is a number
            for (int i = 0; i < 10; i++) {
                if (!Character.isDigit(isbn.charAt(i))) {
//              Checking that the last character is an X
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
    }
