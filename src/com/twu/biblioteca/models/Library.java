package com.twu.biblioteca.models;

import java.util.List;

public class Library {
    private List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getBookListTablePrintable () {
        StringBuilder message = new StringBuilder();
        String divider = new String(new char[86]).replace("\0", "-") + "\n";
        String tableFormater = "%40s  %30s %8s\n";

        message.append("\nList of Books:\n");
        message.append(divider);
        message.append(String.format(tableFormater, "TITLE", "AUTHOR", "YEAR"));
        message.append(divider);
        for(Book book : this.getBooks()) {
            message.append(String.format(tableFormater, book.getTitle(), book.getAuthor(), book.getYear()));
        }
        message.append(divider);

        return message.toString();
    }
}
