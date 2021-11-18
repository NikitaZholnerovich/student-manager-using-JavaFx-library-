package sample;

import model.Player;
import model.PlayersArticle;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class PlayersXMLParser {

    public List<Player> loadPlayers(String path) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        PlayersHandler playersHandler = new PlayersHandler();

        saxParser.parse(path, playersHandler);

        PlayersArticle result = playersHandler.getPlayers();
        result.Players.forEach(p -> System.out.println(p.name));
        return result.Players;
    }

}
