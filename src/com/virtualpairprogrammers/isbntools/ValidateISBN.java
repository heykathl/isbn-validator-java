package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {
    public boolean checkISBN(String isbn) {
        int total = 0;

        if (isbn.length() != 10){
            throw new NumberFormatException("Error - not 10 digits long");
        }

        for(int i = 0; i < 10; i++){
            total += isbn.charAt(i) * (10 - i);
        }

        if (total % 11 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
