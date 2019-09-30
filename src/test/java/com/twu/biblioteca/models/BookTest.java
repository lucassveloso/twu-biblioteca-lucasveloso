package com.twu.biblioteca.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookTest {
    private Book book;
    private int id = 1;
    private String title = "Book 1";
    private String author = "Author 1";
    private int year = 2000;

    @Before
    public void setUp() {
        book = new Book(id, title, author, year);
    }

    @Test
    public void shouldHaveAId() {
        assertThat(book.getId(), is(id));
    }

    @Test
    public void shouldHaveATitle() {
        assertThat(book.getTitle(), is(title));
    }

    @Test
    public void shouldHaveAnAuthor() {
        assertThat(book.getAuthor(), is(author));
    }

    @Test
    public void shouldHaveAYear() {
        assertThat(book.getYear(), is(year));
    }
}
