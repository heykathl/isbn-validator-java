package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {
    public boolean checkISBN(String isbn) {
        int total = 0;

        if (isbn.length() != 10 || !isbn.matches("\\d+")) {
            throw new NumberFormatException("Error - invalid number");
        }

        for(int i = 0; i < 10; i++){
//            Alternative way to check if each character is a digit
//            if(!Character.isDigit(isbn.charAt(i))) throw new NumberFormatException("Error");
            total += isbn.charAt(i) * (10 - i);
        }

        if (total % 11 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
