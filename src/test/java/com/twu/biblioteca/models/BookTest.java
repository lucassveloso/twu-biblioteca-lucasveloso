package com.twu.biblioteca.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookTest {
    private Book book;
    private String title = "Book 1";
    private String author = "Author 1";
    private int year = 2000;

    @Before
    public void setUp() {
        book = new Book(title, author, year);
    }

    @Test
    public void shouldExtendsProduct() {
        assertThat(book, isA(Product.class));
    }

    @Test
    public void shouldHaveAnAuthor() {
        assertThat(book.getAuthor(), is(author));
    }
}
