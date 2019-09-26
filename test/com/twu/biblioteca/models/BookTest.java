package com.twu.biblioteca.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookTest {
    @Test
    public void shouldHaveATitle() {
        String title = "Book 1";
        Book book = new Book(title);
        assertThat(book.getTitle(), is(title));
    }

    @Test
    public void shouldToStringReturnTitle() {
        String title = "Book 1";
        Book book = new Book(title);
        assertThat(book.toString(), is(title));
    }
}
