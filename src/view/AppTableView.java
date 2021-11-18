package view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.SquadEnum;

import java.util.Date;

public class AppTableView<S> extends TableView<S> {
    private static final TableColumn NAME = new TableColumn("Name");
    private static final TableColumn DATE_OF_BIRTH = new TableColumn("Date of birth");
    private static final TableColumn FOOTBALL_TEAM = new TableColumn("Football team");
    private static final TableColumn HOMETOWN = new TableColumn("Hometown");
    private static final TableColumn SQUAD = new TableColumn("Squad");
    private static final TableColumn POSITION = new TableColumn("Position");

    public AppTableView() {
        super();
        this.getColumns().addAll(NAME, DATE_OF_BIRTH, FOOTBALL_TEAM, HOMETOWN, SQUAD, POSITION);
    }
}
