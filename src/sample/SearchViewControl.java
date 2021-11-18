package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Player;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchViewControl extends VBox {

    public TableviewCustomControl searchPlayersTable = new TableviewCustomControl();
    public List<Player> Players = new ArrayList<>();
    public ComboBox positionComboBox = new ComboBox();
    public TextField nameTextField = new TextField();
    public ComboBox squadComboBox=new ComboBox();
    public TextField footballTeamTextField = new TextField();
    public TextField hometownTextField = new TextField();
    public DatePicker datePicker=new DatePicker();



    public SearchViewControl() {
        HBox searchControls = new HBox();

        Label nameLabel = new Label("Name");

        Label positionLabel = new Label("Position");

        Label squadLabel=new Label("Squad");

        Label footballTeamLabel=new Label("Football team");

        Label hometownLabel=new Label("Hometown");

        Button updateButton = new Button("Search");
        updateButton.setOnAction(ActionEvent -> {
            searchItems();
        });

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(ActionEvent -> {
            refreshSearch();
        });

        searchControls.getChildren().add(nameLabel);
        searchControls.getChildren().add(nameTextField);
        searchControls.getChildren().add(positionLabel);
        searchControls.getChildren().add(positionComboBox);
        searchControls.getChildren().add(squadLabel);
        searchControls.getChildren().add(squadComboBox);
        searchControls.getChildren().add(footballTeamLabel);
        searchControls.getChildren().add(footballTeamTextField);
        searchControls.getChildren().add(hometownLabel);
        searchControls.getChildren().add(hometownTextField);
        searchControls.getChildren().add(datePicker);
        searchControls.getChildren().add(updateButton);
        searchControls.getChildren().add(clearButton);

        this.getChildren().add(searchControls);
        this.getChildren().add(searchPlayersTable);
    }

    public void setData(List<Player> players) {
        Players = players;
        updateSearchView();
    }

    public void updateSearchView() {
        searchPlayersTable.setContent(Players);

        Stream<String> positions = Players.stream().map(p -> p.getPosition()).distinct();
        ObservableList<String> resultPositions = FXCollections.observableArrayList(positions.collect(Collectors.toList()));
        positionComboBox.setItems(resultPositions);

        Stream<String> squads = Players.stream().map(s -> s.getSquad().toString()).distinct();
        ObservableList<String> resultSquad = FXCollections.observableArrayList(squads.collect(Collectors.toList()));
        squadComboBox.setItems(resultSquad);

    }

    public void searchItems() {
        List<Player> filteredPlayers = FXCollections.observableArrayList(Players);

        String nameText = nameTextField.getText().toLowerCase();
        if (!nameText.isBlank()) {
            filteredPlayers = filteredPlayers.stream().filter(player -> player.getName().toLowerCase().contains(nameText)).collect(Collectors.toList());
        }

        String footballTeamText = footballTeamTextField.getText().toLowerCase();
        if (!footballTeamText.isBlank()) {
            filteredPlayers = filteredPlayers.stream().filter(player -> player.getFootballTeam().toLowerCase().contains(footballTeamText)).collect(Collectors.toList());
        }

        String hometownText = hometownTextField.getText().toLowerCase();
        if (!hometownText.isBlank()) {
            filteredPlayers = filteredPlayers.stream().filter(player -> player.getHometown().toLowerCase().contains(hometownText)).collect(Collectors.toList());
        }

        LocalDate localDate = datePicker.getValue();
        if(localDate!=null){
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            filteredPlayers = filteredPlayers.stream().filter(player -> player.getDateOfBirth().compareTo(date)==0).collect(Collectors.toList());

        }

        Object positionObject=positionComboBox.getValue();
        if (positionObject!=null) {
            String positionText = positionObject.toString();
            filteredPlayers = filteredPlayers.stream().filter(player -> player.getPosition().contains(positionText)).collect(Collectors.toList());
        }

        Object squadObject=squadComboBox.getValue();
        if (squadObject!=null) {
            String squadText = squadObject.toString();
            filteredPlayers = filteredPlayers.stream().filter(player -> player.getSquad().toString().contains(squadText)).collect(Collectors.toList());
        }

        searchPlayersTable.setContent(filteredPlayers);
    }

    public void refreshSearch(){
        nameTextField.clear();
        positionComboBox.getSelectionModel().clearSelection();
        squadComboBox.getSelectionModel().clearSelection();
        footballTeamTextField.clear();
        hometownTextField.clear();
        datePicker.getEditor().clear();
        datePicker.setValue(null);
        searchPlayersTable.setContent(Players);
    }

    public List<Player> getFilteredPlayers() {
        return searchPlayersTable.GetPlayers();
    }
}
