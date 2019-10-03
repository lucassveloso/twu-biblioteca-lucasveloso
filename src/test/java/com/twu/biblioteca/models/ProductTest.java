package com.twu.biblioteca.models;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ProductTest {
    private Product product;
    private String title = "Product 1";
    private int year = 2000;

    @Before
    public void setUp() {
        product = new Product(title, year);
    }

    @Test
    public void shouldHaveAIdAsUUID() {
        assertThat(product.getId(), isA(UUID.class));
    }

    @Test
    public void shouldHaveATitle() {
        assertThat(product.getTitle(), is(title));
    }

    @Test
    public void shouldHaveAYear() {
        assertThat(product.getYear(), is(year));
    }

    @Test
    public void shouldCheckedOutBeFalseWhenInstantiateAnObject() {
        assertFalse(product.isCheckedOut());
    }
}
