package com.twu.biblioteca.models;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public List<Book> getBooksAvailable() {
        return books.stream().filter(book -> !book.isCheckedOut()).collect(Collectors.toList());
    }


    public String getBookListTablePrintable () {
        StringBuilder message = new StringBuilder();
        String divider = new String(new char[125]).replace("\0", "-") + "\n";
        String tableFormater = "%40s %40s %30s %8s\n";

        message.append("\nList of Books:\n");
        message.append(divider);
        message.append(String.format(tableFormater, "ID", "TITLE", "AUTHOR", "YEAR"));
        message.append(divider);
        for(Book book : this.getBooksAvailable()) {
            message.append(String.format(tableFormater, book.getId(), book.getTitle(), book.getAuthor(), book.getYear()));
        }
        message.append(divider);

        return message.toString();
    }

    public void checkoutBookById(UUID id) {
        Book book = this.getBookById(id);
        book.setCheckedOut(true);
    }

    public Book getBookById(UUID id) {
       return books.stream().filter((book) ->  book.getId() == id).collect(Collectors.toList()).get(0);
    }
}
