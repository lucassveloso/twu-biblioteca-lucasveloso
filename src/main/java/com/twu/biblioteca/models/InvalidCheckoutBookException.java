package com.twu.biblioteca.models;

public class InvalidCheckoutBookException extends RuntimeException {
    public InvalidCheckoutBookException() {
        super("Sorry, that book is not available!\n");
    }

}
