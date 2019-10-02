package com.twu.biblioteca;

import com.twu.biblioteca.menus.Menu;
import com.twu.biblioteca.models.Library;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    private final int MENU_OPTION_ONE = 1;
    private final int MENU_OPTION_TWO = 2;
    private HelperIO helperIO;

    @Before
    public void setUp() {
        helperIO = new HelperIO();
    }

    @After
    public void tearDown() {
        helperIO.restoreIO();
    }

    @Test
    public void shouldPrintTheWelcomeMessageWhenShowWelcomeMessageIsCalled() {
        String expectedMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        BibliotecaApp.showWelcomeMessage();
        assertThat(helperIO.getOutContent(), containsString(expectedMessage));
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
    public void shouldCallShowBookListTablePrintableWhenOptionOneWasChosenFromMenu() {
        BibliotecaApp.populateLibrary();
        BibliotecaApp.populateMenu();
        Library library = mock(Library.class);
        BibliotecaApp.library = library;

        BibliotecaApp.menu.selectOption(this.MENU_OPTION_ONE);

        verify(BibliotecaApp.library, times(1)).showBookListTablePrintable();
    }


    @Test
    public void shouldCallStopRunningWhenOptionTwoWasChosenFromMenu() {
        BibliotecaApp.populateMenu();
        Menu menu = spy(new Menu(BibliotecaApp.menu.getOptions()));

        BibliotecaApp.menu = menu;
        BibliotecaApp.menu.selectOption(this.MENU_OPTION_TWO);

        verify(menu, times(1)).stopRunning();
    }

}
