package com.twu.biblioteca.models;

import com.twu.biblioteca.BibliotecaApp;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LibraryTest {
    private final int MENU_OPTION_ONE = 1;
    private final int MENU_OPTION_TWO = 2;
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

    @Test
    public void shouldPrintListBooksWhenShowBookListIsCalled() {
        library.setMenu(mock(Menu.class));
        library.showBookListTablePrintable();
        assertThat(helperIO.getOutContent(), containsString(library.getBookListTablePrintable()));
    }

    @Test
    public void shouldHaveAMenuWithOptionsWhenPopulateMenuIsCalled() {
        library.populateMenu();
        assertTrue(library.getMenu().getOptions().size() > 0);
    }

    @Test
    public void shouldCallStopRunningWhenOptionTwoWasChosenFromMenu() {
        library.populateMenu();
        Menu menu = spy(new Menu(library.getMenu().getOptions()));

        library.setMenu(menu);
        library.getMenu().selectOption(this.MENU_OPTION_TWO);

        verify(menu, times(1)).stopRunning();
    }


    @Test
    public void shouldCallStartCheckoutProcessWhenOptionOneWasChosenFromMenu() {
        List<Book> books = library.getBooks();
        Library libraryMock = spy(new Library(books));
        libraryMock.populateMenu();
        libraryMock.getMenu().selectOption(this.MENU_OPTION_ONE);

        verify(libraryMock, times(1)).startCheckoutProcess();
    }

    @Test
    public void shouldPrintASuccessMessageWhenAnAvailableBookIdIsCheckedOut() {
        UUID id = library.getBooksAvailable().get(0).getId();
        helperIO.setIn(id.toString());
        library.startCheckoutProcess();
        assertThat(helperIO.getOutContent(), containsString("Thank you! Enjoy the book"));
    }
}
