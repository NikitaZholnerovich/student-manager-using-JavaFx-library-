package view.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class AppFileMenu extends Menu {
    private static final MenuItem OPEN = new MenuItem("Open");
    private static final MenuItem SAVE = new MenuItem("Save");

    public AppFileMenu(String s) {
        super(s, null, OPEN, SAVE);
    }

    public static MenuItem getOPEN() {
        return OPEN;
    }

    public static MenuItem getSAVE() {
        return SAVE;
    }
}
