package com.twu.biblioteca.menus;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {
    private Menu menu;
    private List<MenuOption> options;

    @Before
    public void setUp() {
        options = new ArrayList<MenuOption>();
        options.add(new MenuOption("Option 1", () -> {}));
        options.add(new MenuOption("Option 2", () -> {}));
        menu = new Menu(options);
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
}
