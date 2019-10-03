package com.twu.biblioteca.models;

public class InvalidReturnProductException extends RuntimeException {
    public InvalidReturnProductException(String typeProduct) {
        super(String.format("That is not a valid %s to return\n", typeProduct));
    }
}
