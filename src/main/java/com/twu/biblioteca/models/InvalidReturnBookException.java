package com.twu.biblioteca.models;

public class InvalidReturnBookException extends RuntimeException {
    public InvalidReturnBookException() {
        super("That is not a valid book to return\n");
    }

}
