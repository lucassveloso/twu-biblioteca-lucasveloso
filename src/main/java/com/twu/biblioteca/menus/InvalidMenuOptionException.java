package com.twu.biblioteca.menus;

public class InvalidMenuOptionException extends Exception {
    public InvalidMenuOptionException() {
        super("Please select a valid option!\n");
    }
}
