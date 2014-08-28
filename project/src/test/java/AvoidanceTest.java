import junit.framework.TestCase;

public class AvoidanceTest extends TestCase {

    public void testDecideMove() throws Exception {
        String[] stateStrs = ("7 12\n" +
                "8 16\n" +
                "0\n" +
                "alive 26 2 10,12 15,12 15,7 5,7 5,2\n" +
                "dead 6 6 14,13 19,13\n" +
                "alive 2 1 12,13 12,14\n" +
                "alive 10 8 10,2 15,2 15,6 16,6").split("\n");
        GameState state = new GameState(stateStrs, null);
        GameController gameController = new GameController(new Avoidance());
        gameController.update(state);
        assert(gameController.move() == Direction.WEST);
    }
}