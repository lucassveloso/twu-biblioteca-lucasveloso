package com.twu.biblioteca.models;

public class InvalidCheckoutProductException extends RuntimeException {
    public InvalidCheckoutProductException(String typeProduct) {
        super(String.format("Sorry, that %s is not available!\n", typeProduct));
    }

}
