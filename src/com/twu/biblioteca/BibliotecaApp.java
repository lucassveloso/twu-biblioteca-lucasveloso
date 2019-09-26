package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Library;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
    static Library library;

    static void showWelcomeMessage() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    static void showBookList() {
        String divider = new String(new char[86]).replace("\0", "-");
        String tableFormater = "%40s  %30s %8s\n";

        System.out.println("\nList of Books:");
        System.out.println(divider);
        System.out.printf(tableFormater, "TITLE", "AUTHOR", "YEAR");
        System.out.println(divider);
        for(Book book : library.getBooks()) {
            System.out.format(tableFormater, book.getTitle(), book.getAuthor(), book.getYear());
        }
        System.out.println(divider);
    }

    static List<Book> getBookList() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Harry Potter Philosopher's Stone", "J. K. Rowling", 1997));
        books.add(new Book("Harry Potter The Chamber of Secrets", "J. K. Rowling", 1998));
        books.add(new Book("Harry Potter The Prisoner of Azkaban", "J. K. Rowling", 1999));
        books.add(new Book("Harry Potter The Goblet of Fire", "J. K. Rowling", 2000));
        books.add(new Book("Harry Potter The Order of the Phoenix", "J. K. Rowling", 2003));
        books.add(new Book("Harry Potter The Half-Blood Prince", "J. K. Rowling", 2005));
        books.add(new Book("Harry Potter The Deathly Hallows", "J. K. Rowling", 2007));
        books.add(new Book("A Game of Thrones", "George R. R. Martin", 1996));
        books.add(new Book("A Clash of Kings", "George R. R. Martin", 1998));
        books.add(new Book("A Storm of Swords", "George R. R. Martin", 2000));
        books.add(new Book("A Feast for Crows", "George R. R. Martin", 2005));
        books.add(new Book("A Dance with Dragons", "George R. R. Martin", 2011));
        return books;
    }

    public static void main(String[] args) {
        showWelcomeMessage();
        library = new Library(getBookList());
        showBookList();
    }
}
