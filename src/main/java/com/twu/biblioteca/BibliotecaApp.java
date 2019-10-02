package com.twu.biblioteca;

import com.twu.biblioteca.menus.Menu;
import com.twu.biblioteca.menus.MenuOption;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Library;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
    static Library library;
    static Menu menu;

    static void showWelcomeMessage() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }

    static void populateLibrary() {
        library = new Library(getBookList());
    }

    static void populateMenu() {
        menu = new Menu(getMenuOptions());
    }

    static List<MenuOption> getMenuOptions() {
        List<MenuOption> options = new ArrayList<MenuOption>();
        options.add(new MenuOption("List of Books", () -> { library.showBookListTablePrintable(); }));
        options.add(new MenuOption("Quit", () -> { menu.stopRunning(); }));
        return options;
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
        books.add(new Book( "A Storm of Swords", "George R. R. Martin", 2000));
        books.add(new Book( "A Feast for Crows", "George R. R. Martin", 2005));
        books.add(new Book( "A Dance with Dragons", "George R. R. Martin", 2011));
        return books;
    }

    public static void main(String[] args) {
        showWelcomeMessage();
        populateLibrary();
        populateMenu();
        menu.run();
    }
}
