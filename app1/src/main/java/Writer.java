import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Writer {

    private Document document;
    private Element root;

    public Writer() {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            this.document = documentBuilder.newDocument();
            this.root = document.createElement("root");
            document.appendChild(root);
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }
    public void addCardToDOM(Person person) {

        Element card = document.createElement("card");
        root.appendChild(card);

        Element name =  document.createElement("name");
        name.setTextContent(person.getName());

        Element surname =document.createElement("surname");
        surname.setTextContent(person.getSurname());

        Element phone =document.createElement("phone");
        phone.setTextContent(person.getPhone());

        card.appendChild(name);
        card.appendChild(surname);
        card.appendChild(phone);
    }


    public void writeDomToFile() {
        StreamResult result = new StreamResult(new File("output.xml"));
        try {

            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.METHOD, "xml");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            tf.transform(source, result);


        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
