package sample;

import model.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class PlayersXMLWriter {
    public void write(String pathToFile, List<Player> playersList) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        // root element
        Element root = document.createElement("players");
        document.appendChild(root);

        for (Player p : playersList) {
            // employee element
            Element player = document.createElement("player");

            root.appendChild(player);


            // firstname element
            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(p.getName()));
            player.appendChild(name);

            // lastname element
            Element dateOfBirth = document.createElement("dateOfBirth");
            dateOfBirth.appendChild(document.createTextNode(p.getDateOfBirth().toString()));
            player.appendChild(dateOfBirth);

            // email element
            Element footballTeam = document.createElement("footballTeam");
            footballTeam.appendChild(document.createTextNode(p.getFootballTeam()));
            player.appendChild(footballTeam);

            // department elements
            Element homeTown = document.createElement("hometown");
            homeTown.appendChild(document.createTextNode(p.getHometown()));
            player.appendChild(homeTown);

            Element squad = document.createElement("squad");
            homeTown.appendChild(document.createTextNode(p.getSquad().toString()));
            player.appendChild(squad);

            Element position = document.createElement("position");
            position.appendChild(document.createTextNode(p.getPosition()));
            player.appendChild(position);
        }

        // create the xml file
        //transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(pathToFile));

        // If you use
        // StreamResult result = new StreamResult(System.out);
        // the output will be pushed to the standard output ...
        // You can use that for debugging

        transformer.transform(domSource, streamResult);

        System.out.println("Done creating XML File");
    }
}
