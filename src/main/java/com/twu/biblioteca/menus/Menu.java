package com.twu.biblioteca.menus;

import java.util.List;

public class Menu {
    private List<MenuOption> options;

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
}
