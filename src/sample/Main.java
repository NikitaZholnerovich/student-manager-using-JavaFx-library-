package sample;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Player;
import org.xml.sax.SAXException;
import view.AppConstructor;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main extends Application {

    private Controller controller = new Controller();

    @Override
    public void start(Stage stage) {
        Button buttonSearch = new Button("Search");
        buttonSearch.setPrefSize(70, 50);

        Button buttonDelete = new Button("Delete");
        buttonDelete.setPrefSize(70, 50);

        Button buttonAdd = new Button("Add");
        buttonDelete.setPrefSize(70, 50);

        Button buttonSave = new Button("Save");
        buttonSave.setPrefSize(70, 50);


        Button buttonOpen = new Button("Open file");
        buttonOpen.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File playersFile = fileChooser.showOpenDialog(stage);
            try {
                controller.loadPlayers(playersFile.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        });

        ButtonType buttonDialogOk = new ButtonType("OK");

        Dialog<String> dialogSearch = new Dialog<String>();
        dialogSearch.getDialogPane().getButtonTypes().add(buttonDialogOk);
        dialogSearch.setTitle("Searching");

        dialogSearch.getDialogPane().setContent(controller.getSearchPlayersView());

        buttonSearch.setOnAction(e -> {
            dialogSearch.showAndWait();
        });

        ButtonType buttonDialogDelete = new ButtonType("Delete");

        Dialog<List<Player>> dialogDelete = new Dialog<List<Player>>();
        dialogDelete.getDialogPane().getButtonTypes().add(buttonDialogDelete);
        dialogDelete.setTitle("Deleting");

        SearchViewControl deletePlayersView = controller.getDeletePlayersView();
        dialogDelete.getDialogPane().setContent(deletePlayersView);

        buttonDelete.setOnAction(e -> {
            dialogDelete.showAndWait();
            List<Player> filteredPlayers = deletePlayersView.getFilteredPlayers();
            controller.deletePlayers(filteredPlayers);
        });


        ButtonType buttonDialogAdd = new ButtonType("Add");

        Dialog<String> dialogAdd = new Dialog<String>();
        dialogAdd.getDialogPane().getButtonTypes().add(buttonDialogAdd);
        dialogAdd.setTitle("Adding");
        AddPlayerControl addPlayerView = controller.getAddPlayersView();
        dialogAdd.getDialogPane().setContent(addPlayerView);

        buttonAdd.setOnAction(e -> {
            dialogAdd.showAndWait();
            controller.addPlayer(addPlayerView.getPlayer());

        });

        buttonSave.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();

            //Show save file dialog
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {

                try {
                    controller.write(file.getPath(), controller.Players);
                } catch (TransformerException transformerException) {
                    transformerException.printStackTrace();
                } catch (ParserConfigurationException parserConfigurationException) {
                    parserConfigurationException.printStackTrace();
                }
            }
        });

        TableviewCustomControl table = controller.getMainPlayersView();

        FlowPane root = new FlowPane(Orientation.HORIZONTAL, 10, 50, buttonSearch, buttonDelete, buttonOpen, buttonAdd, buttonSave, table);

        //root.setAlignment(Pos.CENTER);
        //Scene scene = new Scene(new AppConstructor(), 600, 500);
       Scene scene = new Scene(root, 600, 500);

        stage.setScene(scene);
        stage.setTitle("Second lab");
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
