package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import view.menus.AppEditMenu;
import view.menus.AppFileMenu;

public class AppMenuBar extends MenuBar {
    private static final Menu FILE = new AppFileMenu("File");
    private static final Menu EDIT = new AppEditMenu("Edit");

    public AppMenuBar() {
        super(FILE, EDIT);
    }

    public static Menu getFILE() {
        return FILE;
    }

    public static Menu getEDIT() {
        return EDIT;
    }
}
