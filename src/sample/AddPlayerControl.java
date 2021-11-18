package sample;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Player;
import model.SquadEnum;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddPlayerControl extends HBox {

     public TextField name =new TextField();

     public TextField footBallTeam =new TextField();

     public TextField hometown =new TextField();

     public ComboBox squad =new ComboBox();

     public TextField position =new TextField();

     public DatePicker dateOfBirth = new DatePicker();




    public AddPlayerControl() {
        VBox addPlayerControl=new VBox();

          Label nameLabel=new Label("Name");
          Label dateOfBirthLabel=new Label("Date of birth");
          Label footBallTeamLabel=new Label("Football team");
          Label homeTownLabel=new Label("HomeTown");
          Label squadLabel=new Label("HomeTown");
          Label positionLabel=new Label("Position");


          squad.setItems(FXCollections.observableArrayList( SquadEnum.values()));


         addPlayerControl.getChildren().add(nameLabel);
         addPlayerControl.getChildren().add(name);
         addPlayerControl.getChildren().add(dateOfBirthLabel);
         addPlayerControl.getChildren().add(dateOfBirth);
         addPlayerControl.getChildren().add(footBallTeamLabel);
         addPlayerControl.getChildren().add(footBallTeam);
         addPlayerControl.getChildren().add(homeTownLabel);
         addPlayerControl.getChildren().add(hometown);
         addPlayerControl.getChildren().add(squadLabel);
         addPlayerControl.getChildren().add(squad);
         addPlayerControl.getChildren().add(positionLabel);
         addPlayerControl.getChildren().add(position);

         this.getChildren().add(addPlayerControl);


    }

    public Player getPlayer(){
        Player player = new Player();
        player.name = name.getText();
        LocalDate localDate = dateOfBirth.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        player.dateOfBirth = date;
        player.footballTeam = footBallTeam.getText();
        player.hometown = hometown.getText();
        player.squad = (SquadEnum) squad.getValue();
        player.position = position.getText();
        return player;
    }
}
