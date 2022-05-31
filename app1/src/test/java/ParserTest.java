
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {

    @Test
    void parserConstructorTest() {

        assertThrows(FileNotFoundException.class,() ->{
            new Parser("src/main/resources/notExistingFile");
        });

    }

    @Test
    void createPersonTest() {
        try {

            Parser parser = new Parser("src/main/resources/example.csv");
            String personData = "John,Johnson,12345";
            String[] personArray = personData.split(",");

            Person person = parser.createPerson(personArray, 0);

            assertEquals(person.getName(), personArray[0]);
            assertEquals(person.getSurname(), personArray[1]);
            assertEquals(person.getPhone(), personArray[2]);

            String personData1 = "John,Johnson,";
            String[] personArray1 = personData1.split(",");
            assertThrows(IllegalArgumentException.class, () -> {
                parser.createPerson(personArray1, 0);
            });

            String personData2 = ",Johnson,";
            String[] personArray2 = personData2.split(",");
            assertThrows(IllegalArgumentException.class, () -> {
                parser.createPerson(personArray2, 0);
            });

            String personData3 = ",,";
            String[] personArray3 = personData3.split(",");
            assertThrows(IllegalArgumentException.class, () -> {
                parser.createPerson(personArray3, 0);
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }





    @Test
    void parseTest() {
//        try {
//            ObjectMapper mapper = new XmlMapper();
//            InputStream inputStream = new FileInputStream("output.xml");
//            TypeReference<List<Person>> typeReference = new TypeReference<List<Person>>() {};
//            List<Person> persons = mapper.readValue(inputStream, typeReference);
////            mapper.
//
//            Parser parser = new Parser("src/main/resources/example.csv");
//            assertTrue(parser.parseFromCSV());
//
//        } catch(IOException ex) {
//            ex.printStackTrace();
//        }
    }


}
