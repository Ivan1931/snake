import junit.framework.TestCase;

public class SerpentTest extends TestCase {
    private String[] deathDataFile2_5 = {"21 15", "4 8", "0", "alive 90 3 1,0 1,23 13,23 13,13 9,13 9,10 16,10 16,7 19,7 19,6 10,6 10,0 2,0", "alive 40 1 9,39 24,39 24,30 27,30 27,22 26,22 26,25", "alive 125 3 2,45 5,45 5,39 4,39 4,44 0,44 0,38 23,38 23,26 14,26 14,14 16,14 16,23 17,23 17,14 21,14 21,13 15,13 15,12 10,12 10,11 14,11", "alive 65 0 23,43 22,43 22,40 21,40 21,43 14,43 14,44 24,44 24,49 27,49 27,46 30,46 30,43 28,43 28,44 29,44 29,45 27,45 27,36 26,36 26,40"};
    private String[] deathDataFile3_0 = {"38 26", "10 15", "0", "alive 78 1 30,23 49,23 49,29 39,29 39,28 48,28 48,24 26,24 26,19 25,19", "alive 55 0 28,23 27,23 27,18 26,18 26,16 24,16 24,15 17,15 17,12 9,12 9,4 13,4 13,3 5,3 5,6", "alive 15 0 20,16 23,16 23,18 24,18 24,19 23,19 23,23 24,23 24,22", "alive 15 0 28,17 27,17 27,15 25,15 25,14 18,14 18,13"};
    private String[] deathDataFile4_0 = {"3 44", "13 28", "0", "alive 225 1 27,16 27,13 28,13 28,11 27,11 27,12 26,12 26,19 25,19 25,22 16,22 16,24 15,24 15,21 24,21 24,18 25,18 25,12 24,12 24,17 23,17 23,20 8,20 8,43 11,43 11,45 5,45 5,46 49,46 49,49 0,49 0,48 13,48", "alive 30 0 31,31 31,38 20,38 20,27", "alive 45 0 21,10 26,10 26,11 23,11 23,16 22,16 22,19 5,19 5,24 6,24 6,21", "alive 20 0 28,15 28,14 29,14 29,11 31,11 31,0 32,0"};
    private String[] testDataFilefile5_1 = {"35 21", "30 37", "0", "alive 110 0 49,21 41,21 41,28 40,28 40,15 26,15 26,5 42,5 42,4 35,4 35,3 45,3 45,0 32,0 32,1 36,1", "alive 5 0 22,37 18,37", "alive 30 1 49,22 49,29 40,29 40,36 34,36", "alive 5 2 18,34 18,30"};
    private String[] testDataFile7_txt_3 = {"9 31", "17 10", "0", "alive 270 8 35,38 36,38 36,36 37,36 37,19 38,19 38,18 36,18 36,35 35,35 35,37 34,37 34,46 12,46 12,45 2,45 2,44 0,44 0,41 3,41 3,44 13,44 13,45 14,45 14,41 16,41 16,39 33,39 33,36 34,36 34,34 35,34 35,11 46,11 46,33 45,33 45,40 47,40 47,45 48,45 48,0 40,0", "alive 40 0 47,11 47,10 34,10 34,33 32,33", "alive 5 2 45,22 45,26", "alive 5 1 19,10 23,10"};
    private String[] testDataFile8_0 = {"28 11", "40 18", "0", "alive 215 7 16,13 16,30 15,30 15,29 0,29 0,27 2,27 2,18 8,18 8,12 19,12 19,11 1,11 1,26 0,26 0,0 48,0 48,4 49,4 49,15 48,15 48,19 34,19", "alive 5 1 17,35 13,35", "alive 40 1 28,14 28,21 27,21 27,31 13,31 13,33 8,33", "alive 40 0 38,18 29,18 29,22 28,22 28,32 14,32 14,33"};
    private String[] testTrapDataFile0_0 = {"20 16", "36 29", "0", "alive 60 0 2,26 2,30 1,30 1,31 0,31 0,25 6,25 6,11 23,11 23,8 29,8", "alive 50 0 36,49 25,49 25,41 40,41 40,40 33,40 33,33", "alive 5 0 3,16 3,17 5,17 5,16", "alive 15 1 24,11 24,9 34,9 34,7"};
    private String[] testDataFile10_12 = {"7 12", "37 48", "0", "alive 310 14 0,40 1,40 1,29 2,29 2,47 29,47 29,37 46,37 46,26 45,26 45,36 28,36 28,46 23,46 23,45 27,45 27,30 25,30 25,29 27,29 27,24 26,24 26,25 25,25 25,17 29,17 29,15 37,15 37,5 43,5 43,13 44,13 44,25 45,25 45,22 46,22 46,21 47,21 47,46 48,46 48,12 44,12 44,10 48,10 48,9", "alive 16 2 26,44 21,44 21,36 19,36", "alive 50 3 41,4 36,4 36,14 35,14 35,5 17,5 17,6 22,6", "alive 45 4 24,29 24,32 25,32 25,31 26,31 26,33 21,33 21,16 17,16 17,9 20,9"};
    private String[] testDataFile11_4 = {"48 0", "32 45", "0", "alive 65 5 2,44 5,44 5,45 1,45 1,44 0,44 0,40 9,40 9,48 8,48 8,41 2,41 2,42 7,42 7,48 6,48 6,43 5,43", "alive 20 5 9,13 4,13 4,14 11,14 11,20", "alive 25 1 22,32 19,32 19,30 18,30 18,33 28,33 28,36 30,36", "alive 65 3 12,18 12,26 13,26 13,2 36,2 36,1 43,1"};
    private String[] testDataFile12_0 = {"43 2", "46 1", "0", "alive 340 14 19,0 19,2 18,2 18,0 16,0 16,11 12,11 12,10 15,10 15,7 14,7 14,9 12,9 12,8 13,8 13,6 12,6 12,7 11,7 11,10 10,10 10,6 11,6 11,4 14,4 14,0 13,0 13,3 10,3 10,5 9,5 9,11 8,11 8,4 9,4 9,2 12,2 12,0 11,0 11,1 10,1 10,0 8,0 8,3 7,3 7,9 4,9 4,2 5,2 5,8 6,8 6,2 7,2 7,1 1,1 1,7 3,7 3,11 7,11 7,12 10,12 10,14 15,14 15,17 21,17 21,16 16,16 16,13 11,13 11,12 17,12 17,15 24,15 24,7 34,7 34,11 37,11 37,9 46,9 46,8 36,8 36,10 35,10 35,6 42,6 42,4 43,4 43,6 46,6 46,5 44,5 44,2 45,2 45,4 47,4 47,15 48,15 48,3 46,3 46,2 48,2 48,0 42,0 42,3 41,3 41,2 40,2 40,0 32,0", "alive 15 3 42,16 42,21 43,21 43,13", "alive 5 1 44,17 44,21", "alive 25 1 43,29 43,45 37,45 37,43"};
    private String[] testDataFile12_6 = {"46 25", "14 33", "0", "alive 270 20 0,15 10,15 10,36 11,36 11,40 10,40 10,37 8,37 8,41 24,41 24,42 25,42 25,41 36,41 36,38 39,38 39,37 46,37 46,38 40,38 40,41 38,41 38,42 42,42 42,40 46,40 46,42 44,42 44,43 40,43 40,44 39,44 39,45 45,45 45,43 49,43 49,42 48,42 48,14 47,14 47,20 42,20 42,7 44,7 44,5 42,5 42,4 44,4 44,3 42,3 42,2 45,2 45,12 46,12 46,0 41,0 41,33", "alive 25 9 33,26 40,26 40,25 30,25 30,19", "alive 15 4 49,27 49,13", "alive 5 2 34,27 32,27 32,29"};
    private String[] testDataFile13_2 = {"27 33", "29 2", "0", "alive 161 9 0,2 0,3 18,3 18,6 19,6 19,10 18,10 18,7 10,7 10,25 9,25 9,6 6,6 6,5 0,5 0,6 5,6 5,9 4,9 4,7 1,7 1,8 0,8 0,9 1,9 1,10 0,10 0,12 1,12 1,11 7,11 7,12 2,12 2,26 3,26 3,28 2,28 2,31 0,31 0,32 3,32 3,34 10,34", "alive 210 2 44,5 44,2 45,2 45,4 46,4 46,2 47,2 47,13 46,13 46,19 36,19 36,18 35,18 35,48 42,48 42,47 36,47 36,44 46,44 46,46 44,46 44,48 48,48 48,0 37,0 37,18 45,18 45,16 42,16 42,13 40,13 40,7 39,7", "alive 55 0 19,2 19,3 32,3 32,5 34,5 34,28 21,28", "alive 36 3 12,44 11,44 11,48 0,48 0,33 2,33 2,35"};
    private String[] testDataFile13_6 = {"7 19", "0 18", "0", "alive 235 13 0,7 1,7 1,8 0,8 0,9 1,9 1,10 0,10 0,11 1,11 1,12 0,12 0,13 6,13 6,14 5,14 5,15 16,15 16,13 37,13 37,12 36,12 36,11 35,11 35,9 27,9 27,3 21,3 21,2 26,2 26,1 37,1 37,2 41,2 41,11 40,11 40,10 38,10 38,7 39,7 39,9 40,9 40,6 33,6 33,5 34,5 34,4 38,4 38,5 39,5 39,3 28,3 28,8 36,8 36,10 37,10 37,11 38,11 38,18 39,18 39,19 41,19 41,25 44,25 44,16 43,16 43,11 42,11 42,1 38,1 38,0 29,0", "alive 5 5 21,4 21,8", "alive 5 2 7,30 7,34", "alive 20 4 32,28 32,44 33,44 33,42"};
    private String[] testDataFile13_12 = {"34 19", "47 17", "0", "alive 165 18 20,16 18,16 18,14 19,14 19,15 22,15 22,12 21,12 21,14 20,14 20,13 18,13 18,10 19,10 19,12 20,12 20,8 21,8 21,11 24,11 24,8 23,8 23,10 22,10 22,7 14,7 14,17 28,17 28,2 33,2 33,0 21,0 21,2 22,2 22,1 26,1 26,2 23,2 23,6 13,6 13,18 12,18 12,6", "alive 10 9 45,24 45,32 46,32", "alive 10 2 10,19 5,19 5,15", "alive 40 5 18,25 4,25 4,26 6,26 6,31 22,31 22,32"};
    private String[] testDataFile14_0 = {"4 0", "29 49", "0", "alive 79 0 46,10 49,10 49,9 45,9 45,13 30,13 30,12 44,12 44,9 32,9 32,6 14,6", "alive 70 0 2,48 0,48 0,44 4,44 4,43 12,43 12,44 5,44 5,45 33,45 33,46 21,46", "alive 11 0 33,44 29,44 29,38", "alive 10 1 46,14 46,13 49,13 49,17 48,17"};
    private String[] testDataFile14_4 = {"9 37", "28 24", "0", "alive 60 14 1,0 1,7 0,7 0,8 4,8 4,9 12,9 12,8 17,8 17,6 10,6 10,3 7,3 7,0 6,0 6,1 4,1 4,2 6,2 6,3 4,3 4,4 6,4", "alive 40 5 38,38 32,38 32,19 41,19 41,24", "alive 50 1 28,21 28,18 32,18 32,7 43,7 43,6 24,6", "alive 5 4 24,38 24,39 25,39 25,37"};
    private String[] testDataFile15_12 = {"14 0", "5 48", "0", "alive 90 9 0,0 0,3 1,3 1,2 2,2 2,3 4,3 4,4 3,4 3,5 9,5 9,6 10,6 10,14 11,14 11,17 14,17 14,14 25,14 25,13 13,13 13,16 12,16 12,13 11,13 11,0 5,0", "alive 185 4 9,27 31,27 31,23 27,23 27,13 26,13 26,12 16,12 16,4 28,4 28,0 29,0 29,21 28,21 28,22 34,22 34,26 33,26 33,30 34,30 34,29 42,29 42,30 49,30 49,49 37,49 37,44 38,44 38,48 42,48 42,45 45,45", "alive 20 4 29,31 35,31 35,30 41,30 41,31 45,31 45,32", "alive 45 0 43,14 43,22 44,22 44,16 49,16 49,17 45,17 45,18 49,18 49,19 45,19 45,20 49,20 49,21 46,21"};
    private String[] testDataFile16_1 = {"4 19", "0 21", "0", "alive 175 2 47,49 47,0 0,0 0,4 1,4 1,7 4,7 4,4 3,4 3,6 2,6 2,3 1,3 1,1 39,1 39,2 25,2 25,3", "alive 5 0 23,23 23,24 26,24", "alive 10 1 26,33 26,41 27,41", "alive 41 0 6,23 6,24 7,24 7,49 20,49"};
    private String[] testDataFile18_1 = {"15 1", "24 6", "0", "alive 195 6 17,37 17,45 16,45 16,46 15,46 15,45 14,45 14,46 13,46 13,47 21,47 21,48 13,48 13,49 10,49 10,48 9,48 9,49 8,49 8,48 7,48 7,49 6,49 6,48 5,48 5,49 4,49 4,48 3,48 3,49 2,49 2,48 1,48 1,49 0,49 0,47 11,47 11,48 12,48 12,44 16,44 16,40 9,40 9,43 6,43 6,42 5,42 5,41 4,41 4,38 1,38 1,40 0,40 0,17 5,17 5,40 6,40 6,41 7,41 7,42 8,42 8,14", "alive 5 2 47,1 49,1 49,0 48,0", "alive 65 1 28,8 28,33 20,33 20,32 19,32 19,33 9,33 9,36 19,36 19,35 15,35", "alive 10 2 16,1 16,10"};
    private String[] testDataFile19_2 = {"36 3", "25 36", "0", "alive 27 2 47,2 45,2 45,1 49,1 49,0 40,0 40,6 41,6 41,4", "alive 10 8 8,11 6,11 6,7 9,7", "alive 15 1 48,3 48,17", "alive 40 1 49,11 49,22 48,22 48,27 41,27 41,28 39,28 39,25 32,25 32,23" };
    private String[] testDataFile19_3 = {"26 4", "41 18", "0", "alive 440 6 14,11 8,11 8,10 14,10 14,9 8,9 8,8 14,8 14,7 7,7 7,10 6,10 6,7 0,7 0,6 15,6 15,11 18,11 18,7 17,7 17,10 16,10 16,6 17,6 17,5 0,5 0,4 1,4 1,3 0,3 0,2 11,2 11,1 0,1 0,0 49,0 49,32 48,32 48,21 47,21 47,14 42,14 42,12 21,12 21,11 24,11 24,10 21,10 21,9 25,9 25,11 43,11 43,12 46,12 46,11 47,11 47,10 45,10 45,11 44,11 44,10 26,10 26,9 47,9 47,8 20,8 20,22 19,22 19,6 18,6 18,5 19,5 19,4 2,4 2,3 13,3", "alive 65 9 0,49 26,49 26,45 23,45 23,46 25,46 25,48 17,48 17,45 8,45 8,41 7,41 7,40", "alive 10 3 40,21 39,21 39,29", "alive 25 1 37,14 37,13 39,13 39,15 25,15 25,13 24,13 24,15" };

    public void testForState(String[] stateStrings, Direction direction) {
        long a = System.currentTimeMillis();
        GameState state = new GameState(stateStrings, null);
        GameController gameController = new GameController(new Serpent());
        gameController.update(state);
        Direction move = gameController.move();
        long b = System.currentTimeMillis();
        System.out.println(b - a);
        assertEquals(direction, move);
    }

    public void testNotMoveForState(String[] stateStrings, Direction direction) {
        long a = System.currentTimeMillis();
        GameState state = new GameState(stateStrings, null);
        GameController gameController = new GameController(new Serpent());
        gameController.update(state);
        Direction move = gameController.move();
        long b = System.currentTimeMillis();
        System.out.println(b - a);
        assert(direction != move);
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

    public void testDataFile7_3() throws Exception {
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

    public void testDecisionForData13_2() {
        testNotMoveForState(testDataFile13_2, Direction.EAST);
    }

    public void testDecisionForData13_6() {
        testForState(testDataFile13_6, Direction.NORTH);
    }

    public void testDecisionForData13_12() {
        testForState(testDataFile13_12, Direction.EAST);
    }

    public void testDecisionForData14_0() {
        testForState(testDataFile14_0, Direction.SOUTH);
    }

    public void testDecisionForData14_4() {
        testForState(testDataFile14_4, Direction.EAST);
    }

    public void testDecisionForData15_12() {
        testForState(testDataFile15_12, Direction.EAST);
    }

    public void testDecisionForData16_1() {
        testForState(testDataFile16_1, Direction.WEST);
    }

    public void testDecisionForData18_1() {
        testForState(testDataFile18_1, Direction.EAST);
    }

    public void testDecisionForData19_2() {
        testForState(testDataFile19_2, Direction.SOUTH);
    }

    public void testDecisionForData19_3() {
        testForState(testDataFile19_3, Direction.SOUTH);
    }

}