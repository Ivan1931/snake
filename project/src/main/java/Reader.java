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

    /**
     * This method scans standard output for next game state
     * @return Null if the game is over else it will return the game state as an array containing each line of the state string
     */
    public String[] readNextGameState() {
        String acc = "";
        String line;
        for (int i = 0; i < NUM_LINES; i++) {
            line = reader.nextLine();
            if (line.equals("")) break;
            acc += line + "\n";
        }

        if (acc.equals("Game Over")) return null;

        return acc.split("\n");
    }

    public void close() {
        reader.close();
    }

}
