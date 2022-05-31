package restAPI.Model;

import org.springframework.stereotype.Service;
import restAPI.serivce.Filter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private final List<Person> persons = new ArrayList<>();
    private final int expectedNumOfWords = 3;
    private final BufferedReader reader;
    private final String path;
    private String mainPath = "src/main/resources/example.csv";


    public Parser() throws FileNotFoundException {
        this.path = mainPath;
        this.reader = new BufferedReader(new FileReader(path));
        try {
            this.readFromCSV();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public Parser(String path) throws FileNotFoundException {
        this.path = path;
        this.reader = new BufferedReader(new FileReader(path));
        try {
            this.readFromCSV();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createPerson(String[] splittedLine, int row) {

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
        persons.add(new Person(splittedLine[0], splittedLine[1], splittedLine[2]));
    }


    public void readFromCSV() throws IOException {

        String line = reader.readLine();
        int row = 1;

        while (line != null) {
            String[] splittedLine = line.split(",");
            try {
                createPerson(splittedLine, row);
            }
            catch (IllegalArgumentException ex) {
                ex.printStackTrace();
                row++;
                continue;
            }
            row++;
            line = reader.readLine();
        }

        reader.close();

    }

    public List<Person> getPersons() {
        return this.persons;
    }
}
