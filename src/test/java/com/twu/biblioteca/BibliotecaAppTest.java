package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BibliotecaAppTest {
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
    public void shouldPrintTheWelcomeMessageWhenShowWelcomeMessageIsCalled() {
        String expectedMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        BibliotecaApp.showWelcomeMessage();
        assertThat(outContent.toString(), containsString(expectedMessage));
    }

    @Test
    public void shouldHaveALibraryWithBooksWhenPopulateLibraryIsCalled() {
        BibliotecaApp.populateLibrary();
        assertTrue(BibliotecaApp.library.getBooks().size() > 0);
    }

    @Test
    public void shouldHaveAMenuWithOptionsWhenPopulateMenuIsCalled() {
        BibliotecaApp.populateMenu();
        assertTrue(BibliotecaApp.menu.getOptions().size() > 0);
    }

    @Test
    public void shouldPrintMenuWhenShowMenuIsCalled() {
        BibliotecaApp.populateMenu();
        BibliotecaApp.showMenu();
        assertThat(outContent.toString(), containsString(BibliotecaApp.menu.getMenuPrintable()));
    }

    @Test
    public void shouldPrintListOfBooksWhenOptionOneWasChosenFromMenu() {
        BibliotecaApp.populateLibrary();
        BibliotecaApp.populateMenu();
        BibliotecaApp.menu.selectOption(1);
        assertThat(outContent.toString(), containsString(BibliotecaApp.library.getBookListTablePrintable()));
    }
}
