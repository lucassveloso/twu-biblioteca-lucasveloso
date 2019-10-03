package com.twu.biblioteca.models;

public class Movie extends Product {
    private String director;
    private Double rating;

    public Movie(String title, String director, int year, Double rating) {
        super(title, year);
        this.director = director;
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public Double getRating() {
        return rating;
    }
}
