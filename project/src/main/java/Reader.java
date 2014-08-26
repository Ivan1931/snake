import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jonah-hooper on 2014/08/11.
 */
public class Reader {
    private BufferedReader reader;

    final private int NUM_LINES = 7;

    public Reader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readInitialState() throws IOException {
        return reader.readLine();
    }

    public String[] readNextGameState() {
        String acc = "";
        String line;

        System.out.println("log " + "Reading next game");
        try {
            while ((line = reader.readLine()) != null) {
                line = line.concat("\n");
            }
        } catch (IOException e) {
            System.out.println("log We have a failure!!!");
        }

        return acc.split("\n");
    }

}
