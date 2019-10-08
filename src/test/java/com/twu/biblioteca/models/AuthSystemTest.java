package com.twu.biblioteca.models;

import com.twu.biblioteca.HelperIO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AuthSystemTest {

    private List<User> users;
    private User user;
    private AuthSystem authSystem;
    private String libraryNumber = "001-0001";
    private String password = "001-0001";
    private String wrongPassword = "wrong-passworg";
    private HelperIO helperIO;


    @Before
    public void setUp() {
        helperIO = new HelperIO();
        users = new ArrayList<User>();
        user = new User(libraryNumber, password);
        users.add(user);
        users.add(new User( "002-0002", "password2"));
        authSystem = new AuthSystem(users);
    }

    @After
    public void tearDown() {
        helperIO.restoreIO();
    }

    @Test
    public void shouldHaveAListOfUsers() {
        assertThat(authSystem.getUsers(), is(users));
    }

    @Test(expected = InvalidUserException.class)
    public void shouldThrowWhenGetUserIsCalledWithInvalidCredentials() throws InvalidUserException {
        authSystem.getUser(libraryNumber, wrongPassword);
    }

    @Test
    public void shouldReturnTheUserWhenGetUserIsCalledWithCorrectCredentials() {
        User returnedUser = authSystem.getUser(libraryNumber, password);
        assertThat(returnedUser, is(user));
    }

    @Test
    public void shouldPrintASuccessMessageWhenUserHaveSuccessfullyLoggedIn() {
        helperIO.setIn(String.format("%s\n%s", libraryNumber, password));
        authSystem.login();
        assertThat(helperIO.getOutContent(), containsString("You are successfully logged in"));
    }

    @Test
    public void shouldSaveUserLoggedWhenUserHaveSuccessfullyLoggedIn() {
        helperIO.setIn(String.format("%s\n%s", libraryNumber, password));
        authSystem.login();
        assertThat(authSystem.getUserLogged(), is(authSystem.getUser(libraryNumber, password)));
    }

    @Test
    public void shouldPrintAUnsuccessMessageWhenUserLoginFailed() {
        helperIO.setIn(String.format("%s\n%s", libraryNumber, wrongPassword));
        authSystem.login();
        assertThat(helperIO.getOutContent(), containsString(new InvalidUserException().getMessage()));
    }
}
