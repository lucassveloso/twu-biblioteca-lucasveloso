package com.twu.biblioteca.models;

import com.twu.biblioteca.HelperIO;
import com.twu.biblioteca.menus.Menu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class LibraryTest {

    List<Book> books;
    Library library;
    private HelperIO helperIO;

    @Before
    public void setUp() {
        helperIO = new HelperIO();
        books = new ArrayList<Book>();
        books.add(new Book("Book 1", "Author 1", 2000));
        books.add(new Book( "Book 2", "Author 2", 2010));
        library = new Library(books);
    }

    @After
    public void tearDown() {
        helperIO.restoreIO();
    }

    @Test
    public void shouldHaveAListOfBooks() {
        assertThat(library.getProducts(), is(books));
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
    public void shouldNotListCheckedOutBooksWhenGetBookListTablePrintableIsCalled() {
        UUID bookId = library.getProducts().get(0).getId();
        library.checkoutProductById(bookId);

        String bookListTablePrintable = library.getBookListTablePrintable();

        assertThat(bookListTablePrintable, not(containsString(String.valueOf(bookId))));
    }


    @Test
    public void shouldPrintListBooksWhenShowBookListIsCalled() {
        library.setMenu(mock(Menu.class));
        library.showBookListTablePrintable();
        assertThat(helperIO.getOutContent(), containsString(library.getBookListTablePrintable()));
    }
}
