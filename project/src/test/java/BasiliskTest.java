import junit.framework.TestCase;

public class BasiliskTest extends TestCase {

    private String[] deathDataFile2_5 = {"21 15", "4 8", "0", "alive 90 3 1,0 1,23 13,23 13,13 9,13 9,10 16,10 16,7 19,7 19,6 10,6 10,0 2,0", "alive 40 1 9,39 24,39 24,30 27,30 27,22 26,22 26,25", "alive 125 3 2,45 5,45 5,39 4,39 4,44 0,44 0,38 23,38 23,26 14,26 14,14 16,14 16,23 17,23 17,14 21,14 21,13 15,13 15,12 10,12 10,11 14,11", "alive 65 0 23,43 22,43 22,40 21,40 21,43 14,43 14,44 24,44 24,49 27,49 27,46 30,46 30,43 28,43 28,44 29,44 29,45 27,45 27,36 26,36 26,40"};

    public void testDecideMove() throws Exception {
        String[] stateStrs = ("7 12\n" +
                "8 16\n" +
                "0\n" +
                "alive 26 2 10,12 15,12 15,7 5,7 5,2\n" +
                "dead 6 6 14,13 19,13\n" +
                "alive 2 1 12,13 12,14\n" +
                "alive 10 8 10,2 15,2 15,6 16,6").split("\n");
        GameState state = new GameState(stateStrs, null);
        GameController gameController = new GameController(new Basilisk());
        gameController.update(state);
        assert(gameController.move() == Direction.WEST);
    }

    public voi
}