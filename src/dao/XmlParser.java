package Dao;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * This class provide functionalities to read "config.xml".
 */
public class XmlParser {

    private static Document doc = null;

    private static void setDoc() throws SAXException, IOException, ParserConfigurationException {
        File fXmlFile = new File("config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
    }

    /**
     * Get {@code str} field in xml.
     * @param str The name of the field.
     * @return The value of the matching field.
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static String get(String str) throws SAXException, IOException, ParserConfigurationException {
            if (doc == null) {
                setDoc();
            }
            return doc.getElementsByTagName(str).item(0).getTextContent();
    }

}
