package view.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class AppEditMenu extends Menu {
    private static final MenuItem ADD = new MenuItem("Add");
    private static final MenuItem DELETE = new MenuItem("Delete");
    private static final MenuItem SEARCH = new MenuItem("Search");

    public AppEditMenu(String s) {
        super(s, null, ADD, DELETE, SEARCH);
    }

    public static MenuItem getADD() {
        return ADD;
    }

    public static MenuItem getDELETE() {
        return DELETE;
    }

    public static MenuItem getSEARCH() {
        return SEARCH;
    }
}
