package com.twu.biblioteca.menus;

public class InvalidMenuOptionException extends RuntimeException {
    public InvalidMenuOptionException() {
        super("Please select a valid option!\n");
    }
}
