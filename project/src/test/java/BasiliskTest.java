import junit.framework.TestCase;

public class BasiliskTest extends TestCase {

    private String[] deathDataFile2_5 = {"21 15", "4 8", "0", "alive 90 3 1,0 1,23 13,23 13,13 9,13 9,10 16,10 16,7 19,7 19,6 10,6 10,0 2,0", "alive 40 1 9,39 24,39 24,30 27,30 27,22 26,22 26,25", "alive 125 3 2,45 5,45 5,39 4,39 4,44 0,44 0,38 23,38 23,26 14,26 14,14 16,14 16,23 17,23 17,14 21,14 21,13 15,13 15,12 10,12 10,11 14,11", "alive 65 0 23,43 22,43 22,40 21,40 21,43 14,43 14,44 24,44 24,49 27,49 27,46 30,46 30,43 28,43 28,44 29,44 29,45 27,45 27,36 26,36 26,40"};
    private String[] deathDataFile3_0 = {"38 26", "10 15", "0", "alive 78 1 30,23 49,23 49,29 39,29 39,28 48,28 48,24 26,24 26,19 25,19", "alive 55 0 28,23 27,23 27,18 26,18 26,16 24,16 24,15 17,15 17,12 9,12 9,4 13,4 13,3 5,3 5,6", "alive 15 0 20,16 23,16 23,18 24,18 24,19 23,19 23,23 24,23 24,22", "alive 15 0 28,17 27,17 27,15 25,15 25,14 18,14 18,13"};
    private String[] deathDataFile4_0 = { "3 44", "13 28", "0", "alive 225 1 27,16 27,13 28,13 28,11 27,11 27,12 26,12 26,19 25,19 25,22 16,22 16,24 15,24 15,21 24,21 24,18 25,18 25,12 24,12 24,17 23,17 23,20 8,20 8,43 11,43 11,45 5,45 5,46 49,46 49,49 0,49 0,48 13,48", "alive 30 0 31,31 31,38 20,38 20,27", "alive 45 0 21,10 26,10 26,11 23,11 23,16 22,16 22,19 5,19 5,24 6,24 6,21", "alive 20 0 28,15 28,14 29,14 29,11 31,11 31,0 32,0" };
    private String[] testDataFilefile5_1 = {"35 21", "30 37", "0", "alive 110 0 49,21 41,21 41,28 40,28 40,15 26,15 26,5 42,5 42,4 35,4 35,3 45,3 45,0 32,0 32,1 36,1", "alive 5 0 22,37 18,37", "alive 30 1 49,22 49,29 40,29 40,36 34,36", "alive 5 2 18,34 18,30" };
    private String[] testDataFile7_txt_3 = {"9 31", "17 10", "0", "alive 270 8 35,38 36,38 36,36 37,36 37,19 38,19 38,18 36,18 36,35 35,35 35,37 34,37 34,46 12,46 12,45 2,45 2,44 0,44 0,41 3,41 3,44 13,44 13,45 14,45 14,41 16,41 16,39 33,39 33,36 34,36 34,34 35,34 35,11 46,11 46,33 45,33 45,40 47,40 47,45 48,45 48,0 40,0", "alive 40 0 47,11 47,10 34,10 34,33 32,33", "alive 5 2 45,22 45,26", "alive 5 1 19,10 23,10" };
    private String[] testDataFile8_0 = {"28 11", "40 18", "0", "alive 215 7 16,13 16,30 15,30 15,29 0,29 0,27 2,27 2,18 8,18 8,12 19,12 19,11 1,11 1,26 0,26 0,0 48,0 48,4 49,4 49,15 48,15 48,19 34,19", "alive 5 1 17,35 13,35", "alive 40 1 28,14 28,21 27,21 27,31 13,31 13,33 8,33", "alive 40 0 38,18 29,18 29,22 28,22 28,32 14,32 14,33" };
    private String[] testTrapDataFile0_0 = {"20 16", "36 29", "0", "alive 60 0 2,26 2,30 1,30 1,31 0,31 0,25 6,25 6,11 23,11 23,8 29,8", "alive 50 0 36,49 25,49 25,41 40,41 40,40 33,40 33,33", "alive 5 0 3,16 3,17 5,17 5,16", "alive 15 1 24,11 24,9 34,9 34,7" };
    private String[] testDataFile10_12 = {"7 12", "37 48", "0", "alive 310 14 0,40 1,40 1,29 2,29 2,47 29,47 29,37 46,37 46,26 45,26 45,36 28,36 28,46 23,46 23,45 27,45 27,30 25,30 25,29 27,29 27,24 26,24 26,25 25,25 25,17 29,17 29,15 37,15 37,5 43,5 43,13 44,13 44,25 45,25 45,22 46,22 46,21 47,21 47,46 48,46 48,12 44,12 44,10 48,10 48,9", "alive 16 2 26,44 21,44 21,36 19,36", "alive 50 3 41,4 36,4 36,14 35,14 35,5 17,5 17,6 22,6", "alive 45 4 24,29 24,32 25,32 25,31 26,31 26,33 21,33 21,16 17,16 17,9 20,9" };
    private String[] testDataFile11_4 = { "48 0", "32 45", "0", "alive 65 5 2,44 5,44 5,45 1,45 1,44 0,44 0,40 9,40 9,48 8,48 8,41 2,41 2,42 7,42 7,48 6,48 6,43 5,43", "alive 20 5 9,13 4,13 4,14 11,14 11,20", "alive 25 1 22,32 19,32 19,30 18,30 18,33 28,33 28,36 30,36", "alive 65 3 12,18 12,26 13,26 13,2 36,2 36,1 43,1" };
    private String[] testDataFile12_0 = {"43 2", "46 1", "0", "alive 340 14 19,0 19,2 18,2 18,0 16,0 16,11 12,11 12,10 15,10 15,7 14,7 14,9 12,9 12,8 13,8 13,6 12,6 12,7 11,7 11,10 10,10 10,6 11,6 11,4 14,4 14,0 13,0 13,3 10,3 10,5 9,5 9,11 8,11 8,4 9,4 9,2 12,2 12,0 11,0 11,1 10,1 10,0 8,0 8,3 7,3 7,9 4,9 4,2 5,2 5,8 6,8 6,2 7,2 7,1 1,1 1,7 3,7 3,11 7,11 7,12 10,12 10,14 15,14 15,17 21,17 21,16 16,16 16,13 11,13 11,12 17,12 17,15 24,15 24,7 34,7 34,11 37,11 37,9 46,9 46,8 36,8 36,10 35,10 35,6 42,6 42,4 43,4 43,6 46,6 46,5 44,5 44,2 45,2 45,4 47,4 47,15 48,15 48,3 46,3 46,2 48,2 48,0 42,0 42,3 41,3 41,2 40,2 40,0 32,0", "alive 15 3 42,16 42,21 43,21 43,13", "alive 5 1 44,17 44,21", "alive 25 1 43,29 43,45 37,45 37,43" };
    private String[] testDataFile12_6 = {"46 25", "14 33", "0", "alive 270 20 0,15 10,15 10,36 11,36 11,40 10,40 10,37 8,37 8,41 24,41 24,42 25,42 25,41 36,41 36,38 39,38 39,37 46,37 46,38 40,38 40,41 38,41 38,42 42,42 42,40 46,40 46,42 44,42 44,43 40,43 40,44 39,44 39,45 45,45 45,43 49,43 49,42 48,42 48,14 47,14 47,20 42,20 42,7 44,7 44,5 42,5 42,4 44,4 44,3 42,3 42,2 45,2 45,12 46,12 46,0 41,0 41,33", "alive 25 9 33,26 40,26 40,25 30,25 30,19", "alive 15 4 49,27 49,13", "alive 5 2 34,27 32,27 32,29" };

    public void testDecideMove() throws Exception {
        String[] stateStrs = ("7 12\n" +
                "8 16\n" +
                "0\n" +
                "alive 26 2 10,12 15,12 15,7 5,7 5,2\n" +
                "dead 6 6 14,13 19,13\n" +
                "alive 2 1 12,13 12,14\n" +
                "alive 10 8 10,2 15,2 15,6 16,6").split("\n");
        testForState(stateStrs, Direction.WEST);
    }


    public void testForState(String[] stateStrings, Direction direction) {
        long a = System.currentTimeMillis();
        GameState state = new GameState(stateStrings, null);
        GameController gameController = new GameController(new Basilisk());
        gameController.update(state);
        Direction move = gameController.move();
        long b = System.currentTimeMillis();
        System.out.println(b - a);
        assertEquals(move, direction);
    }

    public void testDecisionForTestData2_5() throws Exception {
        testForState(deathDataFile2_5, Direction.WEST);
    }

    public void testDecisionForTestData3_0() throws Exception {
        testForState(deathDataFile3_0, Direction.NORTH);
    }

    public void testDecisionForTestData4_0() throws Exception {
        testForState(deathDataFile4_0, Direction.SOUTH);
    }

    public void testDecisionForTestData5_1() throws Exception {
        testForState(testDataFilefile5_1, Direction.NORTH);
    }

    public void testDataFile7_3 () throws Exception {
        testForState(testDataFile7_txt_3, Direction.SOUTH);
    }

    public void testDecisionForTestData8_0() {
        testForState(testDataFile8_0, Direction.EAST);
    }

    public void testDecisionForTrapData0_0() {
        testForState(testTrapDataFile0_0, Direction.EAST);
    }

    public void testDecisionForData10_12() {
        testForState(testDataFile10_12, Direction.NORTH);
    }

    public void testDecisionForData11_4() {
        testForState(testDataFile11_4, Direction.NORTH);
    }

    public void testDecisionForData12_0() {
        testForState(testDataFile12_0, Direction.EAST);
    }

    public void testDecisionForData12_6() {
        testForState(testDataFile12_6, Direction.NORTH);
    }
}
