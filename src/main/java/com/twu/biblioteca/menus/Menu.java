package com.twu.biblioteca.menus;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<MenuOption> options;
    private boolean keepRunning = true;

    public Menu(List<MenuOption> options) {
        this.options = options;
    }

    public List<MenuOption> getOptions() {
        return options;
    }

    public void selectOption(int optionNumber) throws InvalidMenuOptionException {
        try {
            this.options.get(optionNumber - 1).execute();
        } catch ( IndexOutOfBoundsException e ) {
            throw new InvalidMenuOptionException();
        }
    }

    public String getMenuPrintable() {
        StringBuilder message = new StringBuilder("Select one of the options below:");
        for (int i = 0; i < options.size(); i++) {
            int optionNumber = i+1;
            message.append("\n" + optionNumber + " - " + options.get(i).getDescription());
        }
        return message.toString();
    }

    public void showMenuPrintable() {
        System.out.println(this.getMenuPrintable());
    }

    public void askUserMenuOption() {
        try {
            Scanner in = new Scanner(System.in);
            this.selectOption(in.nextInt());
        } catch (InvalidMenuOptionException | InputMismatchException e) {
            System.out.println(new InvalidMenuOptionException().getMessage());
        }
    }

    public void run() {
        this.keepRunning = true;
        while(keepRunning) {
            this.showMenuPrintable();
            this.askUserMenuOption();
        }
    }

    public void stopRunning() {
        this.keepRunning = false;
    }
}
