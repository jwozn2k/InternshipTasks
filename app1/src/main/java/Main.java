import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        try {
            Parser csvParser = new Parser(String.valueOf(args[0]));
            csvParser.readFromCSV();
        }
        catch (IOException | IllegalArgumentException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
}
