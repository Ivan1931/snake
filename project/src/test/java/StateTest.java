import junit.framework.TestCase;
import org.junit.Test;

public class StateTest extends TestCase {

    @Test
    public void stateCorrectlyCreatedFromExpectedSTDIN() {
        String testData = "20 15\n" +
                "7 12\n" +
                "39 45\n" +
                "1\n" +
                "alive 26 2 10,12 15,12 15,7 5,7 5,2\n" +
                "alive 6 6 14,13 19,13\n" +
                "alive 2 1 12,13 12,14\n" +
                "alive 10 8 10,2 15,2 15,6 16,6\n";


    }

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }
}