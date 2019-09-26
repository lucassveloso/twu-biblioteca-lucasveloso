package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Library;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.isA;

public class BibliotecaTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldPrintTheWelcomeMessage() {
        String expectedMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        BibliotecaApp.main(new String[] {});
        assertThat(outContent.toString(), containsString(expectedMessage));
    }

    @Test
    public void shouldHaveALibraryInstance() {
        BibliotecaApp.main(new String[]{});
        assertThat(BibliotecaApp.library, isA(Library.class));
    }

    @Test
    public void shouldHaveALibraryWithBooks() {
        BibliotecaApp.main(new String[]{});
        assertThat(BibliotecaApp.library.getBooks(), isA(List.class));
        assertThat(BibliotecaApp.library.getBooks().get(0), isA(Book.class));
    }

    @Test
    public void shouldPrintListOfBooksHeader() {
        BibliotecaApp.main(new String[]{});
        assertThat(outContent.toString(), containsString("List of Books"));
        assertThat(outContent.toString(), containsString("TITLE"));
        assertThat(outContent.toString(), containsString("AUTHOR"));
        assertThat(outContent.toString(), containsString("YEAR"));
    }

    @Test
    public void shouldPrintListOfBooks() {
        BibliotecaApp.main(new String[]{});
        List<Book> books = BibliotecaApp.library.getBooks();
        for(Book book : books) {
            assertThat(outContent.toString(), containsString(book.getTitle()));
            assertThat(outContent.toString(), containsString(book.getAuthor()));
            assertThat(outContent.toString(), containsString(String.valueOf(book.getYear())));
        }
    }
}
