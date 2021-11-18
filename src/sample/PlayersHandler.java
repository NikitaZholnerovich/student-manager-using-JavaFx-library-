package sample;

import model.Player;
import model.PlayersArticle;
import model.SquadEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;

public class PlayersHandler extends DefaultHandler {
    private static final String PlAYERS = "players";
    private static final String PLAYER = "player";
    private static final String NAME = "name";
    private static final String DATE_OF_BIRTH = "dateofbirth";
    private static final String FOOTBALL_TEAM = "footballteam";
    private static final String HOMETOWN = "hometown";
    private static final String SQUAD = "squad";
    private static final String POSITION = "position";

    private PlayersArticle playersArticle;

    private StringBuilder elementValue;

    @Override
    public void characters(char[] ch, int start, int length) {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() {
        playersArticle = new PlayersArticle();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        switch (qName) {
            case PlAYERS:
                playersArticle.Players = new ArrayList<>();
                break;
            case PLAYER:
                playersArticle.Players.add(new Player());
                break;
            case NAME:
                elementValue = new StringBuilder();
                break;
            case DATE_OF_BIRTH:
                elementValue = new StringBuilder();
                break;
            case FOOTBALL_TEAM:
                elementValue = new StringBuilder();
                break;
            case HOMETOWN:
                elementValue = new StringBuilder();
                break;
            case SQUAD:
                elementValue = new StringBuilder();
                break;
            case POSITION:
                elementValue = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case NAME:
                latestPlayer().name = elementValue.toString();
                break;
            case DATE_OF_BIRTH:
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
                try {
                    Date date = format.parse(elementValue.toString());
                    latestPlayer().dateOfBirth = date;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;
            case FOOTBALL_TEAM:
                latestPlayer().footballTeam = elementValue.toString();
                break;
            case HOMETOWN:
                latestPlayer().hometown = elementValue.toString();
                break;
            case SQUAD:
                latestPlayer().squad = SquadEnum.valueOf(elementValue.toString());
                break;
            case POSITION:
                latestPlayer().position = elementValue.toString();
                break;
        }
    }

    private Player latestPlayer() {
        List<Player> playerList = playersArticle.Players;
        int latestArticleIndex = playerList.size() - 1;
        return playerList.get(latestArticleIndex);
    }

    public PlayersArticle getPlayers() {
        return playersArticle;
    }
}
