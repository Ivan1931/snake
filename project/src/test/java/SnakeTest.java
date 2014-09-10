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
        String creationString = "alive 8 2 10,12 15,12 15,10";
        Snake snake = new Snake(creationString);
        assertTrue(snake.isAlive());
        assertEquals(snake.getLength(), 8);
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

        String otherCreationString = "alive 340 14 19,0 19,2 18,2 18,0 16,0 16,11 12,11 12,10 15,10 15,7 14,7 14,9 12,9 12,8 13,8 13,6 12,6 12,7 11,7 11,10 10,10 10,6 11,6 11,4 14,4 14,0 13,0 13,3 10,3 10,5 9,5 9,11 8,11 8,4 9,4 9,2 12,2 12,0 11,0 11,1 10,1 10,0 8,0 8,3 7,3 7,9 4,9 4,2 5,2 5,8 6,8 6,2 7,2 7,1 1,1 1,7 3,7 3,11 7,11 7,12 10,12 10,14 15,14 15,17 21,17 21,16 16,16 16,13 11,13 11,12 17,12 17,15 24,15 24,7 34,7 34,11 37,11 37,9 46,9 46,8 36,8 36,10 35,10 35,6 42,6 42,4 43,4 43,6 46,6 46,5 44,5 44,2 45,2 45,4 47,4 47,15 48,15 48,3 46,3 46,2 48,2 48,0 42,0 42,3 41,3 41,2 40,2 40,0 32,0";
        snake = new Snake(otherCreationString);
        assertEquals(snake.getHead(), new Point("19,0"));
        assertEquals(snake.getLength(), 340);
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