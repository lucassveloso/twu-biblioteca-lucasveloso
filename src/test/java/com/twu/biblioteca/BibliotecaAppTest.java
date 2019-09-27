package com.twu.biblioteca;

import com.twu.biblioteca.menus.InvalidMenuOptionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent;

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
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
    public void shouldPrintListOfBooksWhenOptionOneWasChosenFromMenu() throws InvalidMenuOptionException {
        BibliotecaApp.populateLibrary();
        BibliotecaApp.populateMenu();
        BibliotecaApp.menu.selectOption(1);
        assertThat(outContent.toString(), containsString(BibliotecaApp.library.getBookListTablePrintable()));
    }


    @Test
    public void shouldExitToMenuWhenOptionTwoWasChosenFromMenu() throws InvalidMenuOptionException {
        BibliotecaApp.populateMenu();
        assertTrue(BibliotecaApp.appRunning);
        BibliotecaApp.menu.selectOption(2);
        assertFalse(BibliotecaApp.appRunning);
    }

    private void provideInput(String data) {
        inContent = new ByteArrayInputStream(data.getBytes());
        System.setIn(inContent);
    }

    @Test
    public void shouldPrintInvalidErrorWhenUserInputInvalidMenuOption() {
        BibliotecaApp.populateMenu();
        BibliotecaApp.showMenu();
        provideInput("100");
        BibliotecaApp.askUserMenuOption();
        assertThat(outContent.toString(), containsString(new InvalidMenuOptionException().getMessage()));
    }

    @Test
    public void shouldPrintInvalidErrorWhenUserInputInvalidMenuOptionWithLetters() {
        BibliotecaApp.populateMenu();
        BibliotecaApp.showMenu();
        provideInput("ABC");
        BibliotecaApp.askUserMenuOption();
        assertThat(outContent.toString(), containsString(new InvalidMenuOptionException().getMessage()));
    }

}
