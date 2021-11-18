package sample;

import model.Player;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class Controller {

    public List<Player> Players;
    public TableviewCustomControl MainPlayersView = new TableviewCustomControl();
    public SearchViewControl SearchPlayersView = new SearchViewControl();
    public SearchViewControl DeletePlayersView = new SearchViewControl();
    public AddPlayerControl AddPlayersView = new AddPlayerControl();


    public Controller() {

    }

    public void loadPlayers(String path) throws IOException, SAXException, ParserConfigurationException {

        PlayersXMLParser loader = new PlayersXMLParser();
        Players = loader.loadPlayers(path);
        MainPlayersView.setContent(Players);
        SearchPlayersView.setData(Players);
        DeletePlayersView.setData(Players);
    }

    public TableviewCustomControl getMainPlayersView() {
        return MainPlayersView;
    }

    public SearchViewControl getSearchPlayersView() {
        return SearchPlayersView;
    }

    public SearchViewControl getDeletePlayersView() {
        return DeletePlayersView;
    }

    public AddPlayerControl getAddPlayersView() {
        return AddPlayersView;
    }

    public void deletePlayers(List<Player> playersToDelete) {
        for (Player player : playersToDelete) {
            Players.remove(player);
        }
        MainPlayersView.setContent(Players);
        SearchPlayersView.setData(Players);
        DeletePlayersView.setData(Players);
    }

    public void addPlayer(Player player) {
        Players.add(player);
        MainPlayersView.setContent(Players);
        SearchPlayersView.setData(Players);
        DeletePlayersView.setData(Players);
    }

    public void write(String pathToFile, List<Player> playerList) throws TransformerException, ParserConfigurationException {

        PlayersXMLWriter writer = new PlayersXMLWriter();
        writer.write(pathToFile, playerList);
    }
}
