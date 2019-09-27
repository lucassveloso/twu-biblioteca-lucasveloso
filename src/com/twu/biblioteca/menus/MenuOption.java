package com.twu.biblioteca.menus;

public class MenuOption {
    private String description;
    private Action action;

    public MenuOption(String description, Action action) {
        this.description = description;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public Action getAction() {
        return action;
    }

    public void execute() {
        this.action.execute();
    }
}
