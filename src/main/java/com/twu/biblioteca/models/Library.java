package com.twu.biblioteca.models;

import com.twu.biblioteca.menus.Menu;
import com.twu.biblioteca.menus.MenuOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private Menu menu;

    public Library(List<Book> books) {
        this.books = books;
        this.populateMenu();
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void populateMenu() {
        this.menu = new Menu(this.getMenuOptions());
    }

    public List<MenuOption> getMenuOptions() {
        List<MenuOption> options = new ArrayList<MenuOption>();
        options.add(new MenuOption("Checkout a book", () -> {
            this.startCheckoutProcess();
        }));
        options.add(new MenuOption("Back", () -> { menu.stopRunning(); }));
        return options;
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

    public void showBookListTablePrintable() {
        System.out.println(this.getBookListTablePrintable());
        menu.run();
    }

    public void checkoutBookById(UUID id) {
        Book book = this.getBookById(id);
        book.setCheckedOut(true);
    }

    public Book getBookById(UUID id) {
       return this.books.stream().filter((book) ->  book.getId().equals(id)).collect(Collectors.toList()).get(0);
    }

    public void startCheckoutProcess() {
        System.out.println("Write the book ID to checkout:");
        try {
            Scanner in = new Scanner(System.in);
            UUID id = UUID.fromString(in.next());
            this.checkoutBookById(id);
            System.out.println("Thank you! Enjoy the book\n");
            this.menu.stopRunning();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
