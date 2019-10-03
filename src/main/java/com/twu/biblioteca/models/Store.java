package com.twu.biblioteca.models;

import com.twu.biblioteca.menus.Menu;
import com.twu.biblioteca.menus.MenuOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class Store {
    private List<? extends Product> products;
    private String typeOfProducts;
    private Menu menu;

    public Store(List<? extends Product> products, String typeOfProducts) {
        this.products = products;
        this.typeOfProducts = typeOfProducts;
        this.populateMenu();
    }

    public List<? extends Product> getProducts() {
        return this.products;
    }

    public List<? extends Product> getProductsAvailable() {
        return products.stream().filter(product -> !product.isCheckedOut()).collect(Collectors.toList());
    }

    public void checkoutProductById(UUID id) {
        Product product = this.getProductAvailableById(id);
        product.setCheckedOut(true);
    }

    public void returnProductById(UUID id) {
        Product product = this.getProductCheckedOutById(id);
        product.setCheckedOut(false);
    }

    public Product getProductAvailableById(UUID id) {
        return this.products.stream().filter((product) ->  product.getId().equals(id) && !product.isCheckedOut()).findAny().orElse(null);
    }

    public Product getProductCheckedOutById(UUID id) {
        return this.products.stream().filter((product) ->  product.getId().equals(id) && product.isCheckedOut()).findAny().orElse(null);
    }

    public UUID askProductId() {
        Scanner in = new Scanner(System.in);
        return UUID.fromString(in.next());
    }

    public void startCheckoutProcess() {
        System.out.println("Write the ID to checkout:");
        try {
            UUID id = this.askProductId();
            this.checkoutProductById(id);
            System.out.printf("Thank you! Enjoy the %s\n\n", this.typeOfProducts);
            this.menu.stopRunning();
        } catch (Exception e) {
            System.out.println(new InvalidCheckoutProductException(this.typeOfProducts).getMessage());
        }
    }

    public void startReturnProcess() {
        System.out.println("Write the ID to return:");
        try {
            UUID id = this.askProductId();
            this.returnProductById(id);
            System.out.printf("Thank you for returning the %s\n\n", this.typeOfProducts);
            this.menu.stopRunning();
        } catch (Exception e) {
            System.out.println(new InvalidReturnProductException(this.typeOfProducts).getMessage());
        }
    }


    public void populateMenu() {
        this.menu = new Menu(this.getMenuOptions());
    }

    public List<MenuOption> getMenuOptions() {
        List<MenuOption> options = new ArrayList<MenuOption>();
        options.add(new MenuOption(String.format("Checkout a %s", this.typeOfProducts), () -> {
            this.startCheckoutProcess();
        })); options.add(new MenuOption(String.format("Return a %s", this.typeOfProducts), () -> {
            this.startReturnProcess();
        }));
        options.add(new MenuOption("Back", () -> { menu.stopRunning(); }));
        return options;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
