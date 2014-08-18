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

    public String readInitialState() throws Exception{
        return reader.readLine();
    }

    public String[] readNextGameState() throws IOException {
        String[] stateRepresentation = new String[NUM_LINES];

        for (int i = 0; i < NUM_LINES; i++) {
            stateRepresentation[i] = reader.readLine();
        }

        return stateRepresentation;
    }

}
