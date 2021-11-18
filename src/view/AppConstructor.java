package view;

import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import model.Player;

public class AppConstructor extends BorderPane {
    private static final MenuBar MENU_BAR = new AppMenuBar();
    private static final ToolBar TOOL_BAR = new AppToolBar();
    private static final TableView<Player> TABLE_VIEW = new AppTableView();

    public AppConstructor() {
        this.setTop(MENU_BAR);
        this.setLeft(TOOL_BAR);
        this.setCenter(TABLE_VIEW);
    }
}
