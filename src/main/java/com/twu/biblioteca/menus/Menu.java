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

    public void selectOption(int optionNumber) {
        this.options.get(optionNumber - 1).execute();
    }

    public String getMenuPrintable() {
        StringBuilder message = new StringBuilder("Select one of the options below:\n");
        for (int i = 0; i < options.size(); i++) {
            int optionNumber = i+1;
            message.append(optionNumber + " - " + options.get(i).getDescription());
        }
        return message.toString();
    }
}
