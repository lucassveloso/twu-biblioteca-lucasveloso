package com.twu.biblioteca.models;

public class Book extends Product{
    private String author;

    public Book(String title, String author, int year) {
        super(title, year);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
}
