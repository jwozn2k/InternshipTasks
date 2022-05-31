
import java.io.*;


public class Parser {

    private String path;
    private static int expectedNumOfWords = 3;
    private final BufferedReader reader;
    private final Writer writer;

    public Parser(String path) throws FileNotFoundException {

        this.path = path;
        reader = new BufferedReader(new FileReader(path));
        this.writer = new Writer();
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

            writer.addCardToDOM(createPerson(splittedLine, row));

            row++;
            line = reader.readLine();
            }

        writer.writeDomToFile();
        reader.close();
        return true;
    }

}
