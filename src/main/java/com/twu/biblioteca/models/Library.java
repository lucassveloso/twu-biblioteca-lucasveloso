package com.twu.biblioteca.models;
import java.util.List;

public class Library extends Store {
    public Library(List<Book> books) {
        super(books, "book");
    }

    public String getBookListTablePrintable () {
        StringBuilder message = new StringBuilder();
        String divider = new String(new char[125]).replace("\0", "-") + "\n";
        String tableFormater = "%40s %40s %30s %8s\n";

        message.append("\nList of Books:\n");
        message.append(divider);
        message.append(String.format(tableFormater, "ID", "TITLE", "AUTHOR", "YEAR"));
        message.append(divider);
        for(Book book : (List<Book>) this.getProductsAvailable()) {
            message.append(String.format(tableFormater, book.getId(), book.getTitle(), book.getAuthor(), book.getYear()));
        }
        message.append(divider);

        return message.toString();
    }

    public void showBookListTablePrintable() {
        System.out.println(this.getBookListTablePrintable());
        this.getMenu().run();
    }
}
