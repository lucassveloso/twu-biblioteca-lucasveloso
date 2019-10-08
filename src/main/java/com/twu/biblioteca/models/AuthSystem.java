package com.twu.biblioteca.models;

import java.util.List;
import java.util.Scanner;

public class AuthSystem {
    private List<User> users;
    private User userLogged;

    public AuthSystem(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserLogged() {
        return userLogged;
    }

    public User getUser(String libraryNumber, String password) {
        return this.users.stream().filter((user) -> {
            return user.getLibraryNumber().equals(libraryNumber) && user.getPassword().equals(password);
        }).findAny().orElseThrow(() -> new InvalidUserException());
    }

    private String askUserLibraryNumber(Scanner in) {
        System.out.println("Library number:");
        return in.nextLine();
    }

    private String askUserPassword(Scanner in) {
        System.out.println("Password:");
        return in.nextLine();
    }

    public void login() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("To login inform the information below:");
            String libraryNumber = this.askUserLibraryNumber(in);
            String password = this.askUserPassword(in);
            this.userLogged =  this.getUser(libraryNumber, password);
            System.out.println("You are successfully logged in\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
