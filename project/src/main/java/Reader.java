import java.util.*;
import java.io.IOException;

/**
 * Created by jonah-hooper on 2014/08/11.
 */
public class Reader {
    private Scanner reader;

    final private int NUM_LINES = 7;

    public Reader() {
        reader = new Scanner(System.in);
    }

    public String readInitialState() throws IOException {
        return reader.nextLine();
    }

    public String[] readNextGameState() {
        String acc = "";
        String line;
        System.out.println("log " + "Reading next game");

        for (int i = 0; i < NUM_LINES; i++) {
            acc += (reader.nextLine() + "\n");
        }

        return acc.split("\n");
    }

}
