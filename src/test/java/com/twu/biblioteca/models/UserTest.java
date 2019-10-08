package com.twu.biblioteca.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {
    private User user;
    private String libraryNumber = "001-0001";
    private String password = "password1";

    @Before
    public void setUp() {
        user = new User(libraryNumber, password);
    }

    @Test
    public void shouldHaveALibraryNumber() {
        assertThat(user.getLibraryNumber(), is(libraryNumber));
    }

    @Test
    public void shouldHaveAPassword() {
        assertThat(user.getPassword(), is(password));
    }
}
