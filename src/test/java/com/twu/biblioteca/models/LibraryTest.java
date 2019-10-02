package com.twu.biblioteca.models;

import com.twu.biblioteca.BibliotecaApp;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LibraryTest {
    List<Book> books;
    Library library;

    @Before
    public void setUp() {
        books = new ArrayList<Book>();
        books.add(new Book("Book 1", "Author 1", 2000));
        books.add(new Book( "Book 2", "Author 2", 2010));

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
        assertThat(bookListTablePrintable, containsString("ID"));
        assertThat(bookListTablePrintable, containsString("TITLE"));
        assertThat(bookListTablePrintable, containsString("AUTHOR"));
        assertThat(bookListTablePrintable, containsString("YEAR"));
    }

    @Test
    public void shouldReturnBookListTableBodyWhenGetBookListTablePrintableIsCalled() {
        String bookListTablePrintable = library.getBookListTablePrintable();
        for(Book book : books) {
            assertThat(bookListTablePrintable, containsString(String.valueOf(book.getId())));
            assertThat(bookListTablePrintable, containsString(book.getTitle()));
            assertThat(bookListTablePrintable, containsString(book.getAuthor()));
            assertThat(bookListTablePrintable, containsString(String.valueOf(book.getYear())));
        }
    }

    @Test
    public void shouldCheckoutABookWhenCheckoutBookIsCalled() {
        UUID bookId = library.getBooks().get(0).getId();
        library.checkoutBookById(bookId);
        Book book = library.getBookById(bookId);
        assertTrue(book.isCheckedOut());
    }

    @Test
    public void shouldNotListCheckedOutBooksWhenGetBooksAvailableIsCalled() {
        UUID bookId = library.getBooks().get(0).getId();
        library.checkoutBookById(bookId);

        List<Book> booksAvailable = library.getBooksAvailable();

        assertThat(booksAvailable.get(0).getId(), not(is(bookId)));
    }

    @Test
    public void shouldNotListCheckedOutBooksWhenGetBookListTablePrintableIsCalled() {
        UUID bookId = library.getBooks().get(0).getId();
        library.checkoutBookById(bookId);

        String bookListTablePrintable = library.getBookListTablePrintable();

        assertThat(bookListTablePrintable, not(containsString(String.valueOf(bookId))));
    }
}
