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
        StringBuilder message = new StringBuilder("\nList of Books:\n");
        for(Book book : library.getBooks()) {
            message.append(book).append("\n");
        }
        System.out.println(message);
    }

    static List<Book> getBookList() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Harry Potter Philosopher's Stone"));
        books.add(new Book("Harry Potter The Chamber of Secrets"));
        books.add(new Book("Harry Potter The Prisoner of Azkaban"));
        books.add(new Book("Harry Potter The Goblet of Fire"));
        books.add(new Book("Harry Potter The Order of the Phoenix"));
        books.add(new Book("Harry Potter The Half-Blood Prince"));
        books.add(new Book("Harry Potter The Deathly Hallows"));
        return books;
    }

    public static void main(String[] args) {
        showWelcomeMessage();
        library = new Library(getBookList());
        showBookList();
    }
}
