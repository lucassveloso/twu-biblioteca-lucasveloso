package com.twu.biblioteca.models;

public class Movie extends Product {
    private String director;
    private Double rating;

    public Movie(String title, String director, int year) {
        super(title, year);
        this.director = director;
        this.rating = 0.0;
    }

    public Movie(String title, String director, int year, Double rating) {
        super(title, year);
        this.director = director;
        this.setRating(rating);
    }

    public String getDirector() {
        return director;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating > 10 ? 10 : rating;
    }

    public String getRatingPrintable() {
        return this.rating < 1 ? "unrated" : this.rating.toString();
    }
}
