package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TableviewCustomControl extends VBox {
    public int itemsPerPage = 10;
    public int currentPage = 1;
    public ObservableList<Player> playerDataSource = FXCollections.observableArrayList();
    public List<Player> Players = new ArrayList<>();
    public int pageCount;
    public Label pageNumberLabel = new Label("1");
    public Label entriesPerPageLabel = new Label("Entries per page");


    public TableviewCustomControl() {

        TableView<Player> table = new TableView<>(playerDataSource);
        TableviewCustomControl.<String>createColumn(table, "Name", "Name");
        TableviewCustomControl.createDateColumn(table, "Date Of Birth", "DateOfBirth");
        TableviewCustomControl.<String>createColumn(table, "Football Team", "FootballTeam");
        TableviewCustomControl.<String>createColumn(table, "Hometown", "Hometown");
        TableviewCustomControl.<String>createColumn(table, "Squad", "Squad");
        TableviewCustomControl.<String>createColumn(table, "Position", "Position");

        table.setPrefHeight(120);
        this.getChildren().add(table);

        HBox pageControls = new HBox();
        //HBox.setMargin(pageControls,new Insets(50,50,50,50));
        //  pageControls.setSpacing(20);

        Button prefPageButton = new Button(" < ");

        Button nextPageButton = new Button(" > ");

        Button firstPageButton = new Button("First page");

        Button lastPageButton = new Button("Last page");


        ObservableList<Integer> entryPerPageCount = FXCollections.observableArrayList(5, 10, 15);
        ComboBox<Integer> entriesPerPage = new ComboBox<>(entryPerPageCount);
        entriesPerPage.setValue(10);

        entriesPerPage.setOnAction(actionEvent -> {
            itemsPerPage = entriesPerPage.getValue();
            currentPage = 1;
            updateTable();
        });

        firstPageButton.setOnAction(ActionEvent -> {
            currentPage = 1;
            updateTable();

        });

        lastPageButton.setOnAction(ActionEvent -> {
            currentPage = Players.size() / itemsPerPage + 1;
            updateTable();

        });

        prefPageButton.setOnAction(actionEvent -> {
            if (currentPage > 1) {
                currentPage -= 1;
                updateTable();
            }
        });

        nextPageButton.setOnAction(actionEvent -> {
            if(currentPage<pageCount) {
                currentPage += 1;
                updateTable();
            }
        });

        pageControls.getChildren().add(firstPageButton);
        pageControls.getChildren().add(prefPageButton);
        pageControls.getChildren().add(pageNumberLabel);
        pageControls.getChildren().add(nextPageButton);
        pageControls.getChildren().add(lastPageButton);
        pageControls.getChildren().add(entriesPerPageLabel);
        pageControls.getChildren().add(entriesPerPage);

        this.getChildren().add(pageControls);
        updateTable();
    }

    //dateColumn.setCellValueFactory(new FormattedDateValueFactory<InvoiceTableEntry>("date", "dd/MM/yyyy"));


    private static <T> void createColumn(TableView<Player> table, String columnName, String propertyName) {
        TableColumn<Player, T> fullNameColumn = new TableColumn<>(columnName);
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<Player, T>(propertyName));
        fullNameColumn.setPrefWidth(150);
        table.getColumns().add(fullNameColumn);
    }


    private static void createDateColumn(TableView<Player> table, String columnName, String propertyName) {
        TableColumn<Player, Date> columnDate = new TableColumn<>(columnName);

        columnDate.setCellFactory(column -> {
            TableCell<Player, Date> cell = new TableCell<Player, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        this.setText(format.format(item));

                    }
                }
            };

            return cell;
        });
        columnDate.setCellValueFactory(new PropertyValueFactory<Player, Date>(propertyName));
        columnDate.setPrefWidth(150);
        table.getColumns().add(columnDate);
    }

    public void updateTable() {
        List<Player> playersOnPage = Players.stream().skip((currentPage - 1) * itemsPerPage).limit(itemsPerPage).collect(Collectors.toList());
        playerDataSource.setAll(playersOnPage);
        pageCount = Players.size() / itemsPerPage + 1;
        pageNumberLabel.setText(String.valueOf(currentPage) + " ( of " + String.valueOf(pageCount) + " )");
    }

    public void setContent(List<Player> players) {
        Players = players;
        updateTable();
    }

    public List<Player> GetPlayers() {
        return Players;
    }
}
