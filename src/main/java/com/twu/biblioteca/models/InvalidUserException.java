package com.twu.biblioteca.models;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException() {
        super("You have entered an invalid library number or password.\n");
    }
}
