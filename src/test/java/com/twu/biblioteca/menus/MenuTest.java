package com.twu.biblioteca.menus;

import com.twu.biblioteca.HelperIO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuTest {
    private final String INVALID_MENU_OPTION = "100";
    private final String INVALID_MENU_OPTION_WITH_LETTERS = "ABC";

    private Menu menu;
    private List<MenuOption> options;
    private Action actionMocked = mock(Action.class);
    private HelperIO helperIO;

    @Before
    public void setUp() {
        helperIO = new HelperIO();
        options = new ArrayList<MenuOption>();
        options.add(new MenuOption("Option 1", actionMocked));
        options.add(new MenuOption("Option 2", () -> {}));
        menu = new Menu(options);
    }

    @After
    public void tearDown() {
        helperIO.restoreIO();
    }

    @Test
    public void shouldHaveAListOfMenuOptions() {
        assertThat(menu.getOptions(), is(options));
    }

    @Test
    public void shouldReturnMenuFormattedToPrintWhenGetMenuPrintableIsCalled() {
        String menuPrintable = menu.getMenuPrintable();
        assertThat(menuPrintable, containsString("Select one of the options below"));
        assertThat(menuPrintable, containsString("1 - Option 1"));
        assertThat(menuPrintable, containsString("2 - Option 2"));
    }

    @Test
    public void shouldExecuteFirstOptionWhenSelectOptionIsCalledPassingNumberOne() throws InvalidMenuOptionException {
        menu.selectOption(1);
        verify(actionMocked, times(1)).execute();
    }

    @Test(expected = InvalidMenuOptionException.class)
    public void shouldThrowWhenSelectOptionIsCalledWithInvalidOption() throws InvalidMenuOptionException {
        menu.selectOption(10);
    }

    @Test
    public void shouldPrintMenuWhenShowMenuPrintableIsCalled() {
        menu.showMenuPrintable();
        assertThat(helperIO.getOutContent(), containsString(menu.getMenuPrintable()));
    }

    @Test
    public void shouldPrintInvalidErrorWhenUserInputInvalidMenuOption() {
        menu.showMenuPrintable();
        helperIO.setIn(this.INVALID_MENU_OPTION);
        menu.askUserMenuOption();
        assertThat(helperIO.getOutContent(), containsString(new InvalidMenuOptionException().getMessage()));
    }

    @Test
    public void shouldPrintInvalidErrorWhenUserInputInvalidMenuOptionWithLetters() {
        menu.showMenuPrintable();
        helperIO.setIn(this.INVALID_MENU_OPTION_WITH_LETTERS);
        menu.askUserMenuOption();
        assertThat(helperIO.getOutContent(), containsString(new InvalidMenuOptionException().getMessage()));
    }
}
