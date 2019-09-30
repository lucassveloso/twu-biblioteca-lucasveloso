package com.twu.biblioteca.models;

import com.twu.biblioteca.BibliotecaApp;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {
    List<Book> books;
    Library library;

    @Before
    public void setUp() {
        books = new ArrayList<Book>();
        books.add(new Book(1, "Book 1", "Author 1", 2000));
        books.add(new Book(2, "Book 2", "Author 2", 2010));

        library = new Library(books);
    }

    @Test
    public void shouldHaveAListOfBooks() {
        assertThat(library.getBooks(), is(books));
    }

    @Test
    public void shouldReturnBookListTableHeaderWhenGetBookListTablePrintableIsCalled() {
        String bookListTablePrintable = library.getBookListTablePrintable();

        assertThat(bookListTablePrintable, containsString("List of Books"));
        assertThat(bookListTablePrintable, containsString("TITLE"));
        assertThat(bookListTablePrintable, containsString("AUTHOR"));
        assertThat(bookListTablePrintable, containsString("YEAR"));
    }

    @Test
    public void shouldReturnBookListTableBodyWhenGetBookListTablePrintableIsCalled() {
        String bookListTablePrintable = library.getBookListTablePrintable();
        for(Book book : books) {
            assertThat(bookListTablePrintable, containsString(book.getTitle()));
            assertThat(bookListTablePrintable, containsString(book.getAuthor()));
            assertThat(bookListTablePrintable, containsString(String.valueOf(book.getYear())));
        }
    }

}
