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
    private final int MENU_OPTION_ONE = 1;
    private final int MENU_OPTION_TWO = 2;
    private final String INVALID_MENU_OPTION = "100";
    private final String INVALID_MENU_OPTION_WITH_LETTERS = "ABC";

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


    private void provideInput(String data) {
        inContent = new ByteArrayInputStream(data.getBytes());
        System.setIn(inContent);
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
        BibliotecaApp.menu.selectOption(this.MENU_OPTION_ONE);
        assertThat(outContent.toString(), containsString(BibliotecaApp.library.getBookListTablePrintable()));
    }


    @Test
    public void shouldExitToMenuWhenOptionTwoWasChosenFromMenu() throws InvalidMenuOptionException {
        BibliotecaApp.populateMenu();
        BibliotecaApp.menu.selectOption(this.MENU_OPTION_TWO);
        assertFalse(BibliotecaApp.appRunning);
    }

    @Test
    public void shouldPrintInvalidErrorWhenUserInputInvalidMenuOption() {
        BibliotecaApp.populateMenu();
        BibliotecaApp.showMenu();
        provideInput(this.INVALID_MENU_OPTION);
        BibliotecaApp.askUserMenuOption();
        assertThat(outContent.toString(), containsString(new InvalidMenuOptionException().getMessage()));
    }

    @Test
    public void shouldPrintInvalidErrorWhenUserInputInvalidMenuOptionWithLetters() {
        BibliotecaApp.populateMenu();
        BibliotecaApp.showMenu();
        provideInput(this.INVALID_MENU_OPTION_WITH_LETTERS);
        BibliotecaApp.askUserMenuOption();
        assertThat(outContent.toString(), containsString(new InvalidMenuOptionException().getMessage()));
    }
}
