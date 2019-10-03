package com.twu.biblioteca.models;

import com.twu.biblioteca.HelperIO;
import com.twu.biblioteca.menus.Menu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class StoreTest {
    private final int MENU_OPTION_ONE = 1;
    private final int MENU_OPTION_TWO = 2;
    private final int MENU_OPTION_THREE = 3;
    private final String TYPE_PRODUCT = "PRODUCT-X";

    List<Product> products;
    Store store;
    private HelperIO helperIO;

    @Before
    public void setUp() {
        helperIO = new HelperIO();
        products = new ArrayList<Product>();
        products.add(new Product("Product 1",  2000));
        products.add(new Product( "Product 2",  2010));
        store = new Store(products, this.TYPE_PRODUCT);
    }

    @After
    public void tearDown() {
        helperIO.restoreIO();
    }

    @Test
    public void shouldHaveAListOfProducts() {
        assertThat(store.getProducts(), is(products));
    }


    @Test
    public void shouldCheckoutAProductWhenCheckoutProductIsCalled() {
        Product product =  store.getProducts().get(0);
        UUID productId = product.getId();
        store.checkoutProductById(productId);
        assertTrue(product.isCheckedOut());
    }

    @Test
    public void shouldNotListCheckedOutProductsWhenGetProductsAvailableIsCalled() {
        UUID productId = store.getProducts().get(0).getId();
        store.checkoutProductById(productId);
        List<Product> productsAvailable = (List<Product>) store.getProductsAvailable();
        assertThat(productsAvailable.get(0).getId(), not(is(productId)));
    }


    @Test
    public void shouldHaveAMenuWithOptionsWhenPopulateMenuIsCalled() {
        store.populateMenu();
        assertTrue(store.getMenu().getOptions().size() > 0);
    }


    @Test
    public void shouldCallStartCheckoutProcessWhenOptionOneWasChosenFromMenu() {
        List<Product> products = (List<Product>) store.getProducts();
        Store storeMock = spy(new Store(products, "product"));
        storeMock.populateMenu();
        storeMock.getMenu().selectOption(this.MENU_OPTION_ONE);

        verify(storeMock, times(1)).startCheckoutProcess();
    }

    @Test
    public void shouldPrintASuccessMessageWhenAnAvailableProductIdIsCheckedOut() {
        UUID id = store.getProductsAvailable().get(0).getId();
        helperIO.setIn(id.toString());
        store.startCheckoutProcess();
        assertThat(helperIO.getOutContent(), containsString(String.format("Thank you! Enjoy the %s", this.TYPE_PRODUCT)));
    }

    @Test
    public void shouldPrintAUnsuccessMessageWhenAUnavailableProductIdTryToBeCheckedOut() {
        UUID id = store.getProductsAvailable().get(0).getId();
        store.checkoutProductById(id);
        helperIO.setIn(id.toString());
        store.startCheckoutProcess();
        assertThat(helperIO.getOutContent(), containsString(String.format("Sorry, that %s is not available", this.TYPE_PRODUCT)));
    }

    @Test
    public void shouldPrintAUnsuccessMessageWhenANonexistentProductIdTryToBeCheckedOut() {
        UUID id = UUID.randomUUID();
        helperIO.setIn(id.toString());
        store.startCheckoutProcess();
        assertThat(helperIO.getOutContent(), containsString(String.format("Sorry, that %s is not available", this.TYPE_PRODUCT)));
    }

    @Test
    public void shouldCallStartReturnProcessWhenOptionTwoWasChosenFromMenu() {
        List<Product> products = (List<Product>) store.getProducts();
        Store storeMock = spy(new Store(products, this.TYPE_PRODUCT));
        storeMock.populateMenu();
        storeMock.getMenu().selectOption(this.MENU_OPTION_TWO);

        verify(storeMock, times(1)).startReturnProcess();
    }

    @Test
    public void shouldPrintASuccessMessageWhenAnAvailableProductIdIsReturned() {
        UUID id = store.getProductsAvailable().get(0).getId();
        store.checkoutProductById(id);
        helperIO.setIn(id.toString());
        store.startReturnProcess();
        assertThat(helperIO.getOutContent(), containsString(String.format("Thank you for returning the %s", this.TYPE_PRODUCT)));
    }

    @Test
    public void shouldPrintAUnsuccessMessageWhenAUnavailableProductIdTryToBeReturned() {
        UUID id = store.getProductsAvailable().get(0).getId();
        helperIO.setIn(id.toString());
        store.startReturnProcess();
        assertThat(helperIO.getOutContent(), containsString(String.format("That is not a valid %s to return", this.TYPE_PRODUCT)));
    }

    @Test
    public void shouldPrintAUnsuccessMessageWhenANonexistentProductIdTryToBeReturned() {
        UUID id = UUID.randomUUID();
        helperIO.setIn(id.toString());
        store.startReturnProcess();
        assertThat(helperIO.getOutContent(), containsString(String.format("That is not a valid %s to return", this.TYPE_PRODUCT)));
    }

    @Test
    public void shouldCallStopRunningWhenOptionThreeWasChosenFromMenu() {
        store.populateMenu();
        Menu menu = spy(new Menu(store.getMenu().getOptions()));

        store.setMenu(menu);
        store.getMenu().selectOption(this.MENU_OPTION_THREE);

        verify(menu, times(1)).stopRunning();
    }
}
