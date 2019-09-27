package com.twu.biblioteca.menus;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuOptionTest {
    private MenuOption option;
    private String description = "Option 1";
    private Action action = mock(Action.class);

    @Before
    public void setUp() {
        option = new MenuOption(description, action);
    }

    @Test
    public void shouldHaveADescription() {
        assertThat(option.getDescription(), is(description));
    }

    @Test
    public void shouldHaveAnAction() {
        assertThat(option.getAction(), is(action));
    }

    @Test
    public void shouldCallActionWhenExecuteIsCalled() {
        option.execute();
        verify(action, times(1)).execute();
    }
}
