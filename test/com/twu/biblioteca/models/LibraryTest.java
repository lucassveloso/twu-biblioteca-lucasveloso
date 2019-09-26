package com.twu.biblioteca.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {
    @Test
    public void shouldHaveAListOfBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Book 1"));
        books.add(new Book("Book 2"));

        Library library = new Library(books);

        assertThat(library.getBooks(), is(books));
    }
}
