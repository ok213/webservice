package loc.example.util;

import loc.example.dto.RequestDto;
import loc.example.model.Client;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;
import java.io.StringReader;

public class StringToXMLParser {

    public static RequestDto parse(String strXml) {

        Document doc = convertStringToXMLDocument(strXml);
        doc.getDocumentElement().normalize();

        RequestDto requestDto = new RequestDto();
        requestDto.setTypeRequest(doc.getElementsByTagName("request-type").item(0).getTextContent().toUpperCase());

        Client client = new Client();
        NodeList nodeList = doc.getElementsByTagName("extra");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if ("login".equals(element.getAttribute("name"))) {
                    client.setLogin(element.getTextContent());
                }
                if ("password".equals(element.getAttribute("name"))) {
                    client.setPassword(element.getTextContent());
                }
            }
        }
        requestDto.setClient(client);
        return requestDto;
    }

    private static Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlString)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
