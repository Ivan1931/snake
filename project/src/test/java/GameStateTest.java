import junit.framework.TestCase;

import java.util.LinkedList;

public class GameStateTest extends TestCase {
    private String[] testDataFile19_3 = {"26 4", "41 18", "0", "alive 440 6 14,11 8,11 8,10 14,10 14,9 8,9 8,8 14,8 14,7 7,7 7,10 6,10 6,7 0,7 0,6 15,6 15,11 18,11 18,7 17,7 17,10 16,10 16,6 17,6 17,5 0,5 0,4 1,4 1,3 0,3 0,2 11,2 11,1 0,1 0,0 49,0 49,32 48,32 48,21 47,21 47,14 42,14 42,12 21,12 21,11 24,11 24,10 21,10 21,9 25,9 25,11 43,11 43,12 46,12 46,11 47,11 47,10 45,10 45,11 44,11 44,10 26,10 26,9 47,9 47,8 20,8 20,22 19,22 19,6 18,6 18,5 19,5 19,4 2,4 2,3 13,3", "alive 65 9 0,49 26,49 26,45 23,45 23,46 25,46 25,48 17,48 17,45 8,45 8,41 7,41 7,40", "alive 10 3 40,21 39,21 39,29", "alive 25 1 37,14 37,13 39,13 39,15 25,15 25,13 24,13 24,15" };

    public void testCreation() throws Exception {
        GameState state = new GameState(testDataFile19_3, null);
        Board board = state.getBoard();
        LinkedList<Point> accessible = new LinkedList<Point>();
        Point head = state.getOurSnake().getHead();
        for(Point n : head.getAllNeighbours()) {
            if (Board.isOnBoard(n) && board.isTraversable(n)) accessible.add(n);
        }
        assertEquals(accessible.size(), 1);
    }
}