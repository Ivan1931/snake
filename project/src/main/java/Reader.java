import java.io.BufferedReader;
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

    public State readNextState() throws Exception {
        String[] stateLines = new String[NUM_LINES];
        for(String line : stateLines) {
            line = reader.readLine();
        }
        return new State(stateLines);
    }
}
