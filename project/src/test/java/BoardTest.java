import junit.framework.TestCase;

public class BoardTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testAproximateShortestPath() throws Exception {
        String[] stateStrs = ("7 12\n" +
        "8 16\n" +
        "0\n" +
        "alive 26 2 10,12 15,12 15,7 5,7 5,2\n" +
        "dead 6 6 14,13 19,13\n" +
        "alive 2 1 12,13 12,14\n" +
        "alive 10 8 10,2 15,2 15,6 16,6").split("\n");

        GameState gameState = new GameState(stateStrs, null);
        Board board = new Board(gameState);
        //System.out.println(board.toString());
        Point[] path = board.aproximateShortestPath(new Point(10, 12), new Point(8,16));
        Board boardWithPath = new Board(gameState, path);
        System.out.println("");
        for (int i = 0; i < Board.BOARD_SIZE - 1; i++) {
            System.out.print(i % 10 + " ");
        }
        System.out.println("0");
        System.out.println(boardWithPath);
    }
}