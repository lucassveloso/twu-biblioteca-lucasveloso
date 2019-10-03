package com.twu.biblioteca.models;

import java.util.UUID;

public class Product {
    private UUID id;
    private String title;
    private int year;
    private boolean checkedOut;

    public Product(String title, int year) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.year = year;
        this.checkedOut = false;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}
