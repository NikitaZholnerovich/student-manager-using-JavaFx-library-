package view;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class AppToolBar extends ToolBar {
    private static final Button ADD = new Button("Add");
    private static final Button DELETE = new Button("Delete");
    private static final Button SEARCH = new Button("Search");

    public AppToolBar() {
        super(ADD, DELETE, SEARCH);
        this.setOrientation(Orientation.VERTICAL);
    }

    public static Button getADD() {
        return ADD;
    }

    public static Button getDELETE() {
        return DELETE;
    }

    public static Button getSEARCH() {
        return SEARCH;
    }
}
