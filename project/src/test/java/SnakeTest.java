import junit.framework.TestCase;

import java.util.Iterator;
import java.util.LinkedList;

public class SnakeTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    public void testCreation() throws Exception {
        String creationString = "alive 26 2 10,12 15,12 15,10";
        Snake snake = new Snake(creationString);
        assertTrue(snake.isAlive());
        assertEquals(snake.getLength(), 26);
        assertEquals(snake.getKills(), 2);
        LinkedList<Point> body = snake.getBody();
        Point[] knownPoints = new Point[]{
                new Point(10, 12), new Point(11, 12),
                new Point(12, 12), new Point(13, 12),
                new Point(14, 12), new Point(15, 12),
                new Point(15, 11), new Point(15, 10)
        };

        Iterator<Point> iterator = body.iterator();
        int count = 0;
        while(iterator.hasNext()) {
            Point point = iterator.next();
            assertTrue("The following points "
                        + point.toString() + ", " + knownPoints[count] + " are equal", point.equals(knownPoints[count]));
            count++;
        }
    }

    public void testEndPoints() throws Exception {
        String creationString = "alive 26 2 10,12 15,12 15,10";
        Snake snake = new Snake(creationString);
        Point testHead = new Point(10, 12);
        Point testLast = new Point(15,10);
        assertTrue("Head of the snake is 10,12", snake.getHead().equals(testHead));
        assertTrue("Last part of the snake is 15,10", snake.getLastPoint().equals(testLast));
    }
}