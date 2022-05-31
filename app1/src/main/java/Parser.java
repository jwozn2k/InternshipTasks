import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;


public class Parser {

    private String path;
    private static int expectedNumOfWords = 3;
    private BufferedReader reader;
    private Document document;
    private Element root;

    public Parser(String path) throws FileNotFoundException {

        this.path = path;
        reader = new BufferedReader(new FileReader(path));


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


    public Person createPerson(String[] splittedLine, int row) {

        //throws exception if there is lack of information
        if (splittedLine.length < expectedNumOfWords) {
            throw new IllegalArgumentException("NULL VALUE: COL: " +
                    (expectedNumOfWords - 1) + " ROW: " + row);
        }
        else {
            for (int i = 0; i < splittedLine.length; i++) {
                if (splittedLine[i].length() == 0) {
                    throw new IllegalArgumentException("NULL VALUE: COL: " +
                            i + " ROW: " + row);
                }
            }
        }

        return new Person(splittedLine[0], splittedLine[1], splittedLine[2]);
    }


    public boolean readFromCSV() throws IOException {

        String line = reader.readLine();
        int row = 1;

        while (line != null) {
            String[] splittedLine = line.split(",");

            addCardToDOM(createPerson(splittedLine, row));

            row++;
            line = reader.readLine();
            }

        writeDomToFile();
        reader.close();
        return true;
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
