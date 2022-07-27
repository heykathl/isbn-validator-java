package com.virtualpairprogrammers.isbntools;

public class StockManager {

    private ExternalISBNDataService webService;
    private ExternalISBNDataService databaseService;

    public void setWebService(ExternalISBNDataService webService) {
        this.webService = webService;
    }

    public void setDatabaseServicee(ExternalISBNDataService databaseService) {
        this.databaseService = databaseService;
    }

    public String getLocatorCode(String isbn) {
        Book book = databaseService.lookup(isbn);
        if (book == null) book = webService.lookup(isbn);
        StringBuilder locator = new StringBuilder();
        // Grab last four digits of isbn
        locator.append(isbn.substring(isbn.length() - 4));
        // Grab first character of author
        locator.append(book.getAuthor().substring(0, 1));
        // Grab number of words in title
        locator.append(book.getTitle().split(" ").length);
        return locator.toString();
    }


}
