package com.twu.biblioteca.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MovieTest {
    private Movie movie;
    private String title = "Movie 1";
    private String director = "Director 1";
    private int year = 2000;
    private Double rating = 9.8;

    @Before
    public void setUp() {
        movie = new Movie(title, director, year, rating);
    }

    @Test
    public void shouldExtendsProduct() {
        assertThat(movie, isA(Product.class));
    }

    @Test
    public void shouldHaveADirector() {
        assertThat(movie.getDirector(), is(director));
    }

    @Test
    public void shouldHaveARating() {
        assertThat(movie.getRating(), is(rating));
    }
}
