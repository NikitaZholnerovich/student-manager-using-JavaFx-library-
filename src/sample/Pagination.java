package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Pagination extends HBox {

    public int itemsPerPage = 3;

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }


    public Pagination() {

        Button prefPageButton = new Button(" < ");
        Label pageNumberLabel = new Label("1");
        Button nextPageButton = new Button(" > ");
        this.getChildren().add(prefPageButton);
        this.getChildren().add(pageNumberLabel);
        this.getChildren().add(nextPageButton);
    }
}