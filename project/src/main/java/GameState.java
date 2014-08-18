/**
 * Created by jonah-hooper on 2014/07/29.
 */
public class GameState {
    private Snake[] snakes;
    private Snake ourSnake;
    private Apple[] apples;
    private Obstacle[] obstacles;

    public GameState(String[] initialisationStrings) {
        final int APPLE1_INDEX = 0;
        final int APPLE2_INDEX = 1;
        final int SNAKE_NUMBER_INDEX = 2; // This refers to the index of our snake
        final int FIRST_SNAKE_INDEX = 3;

        apples = new Apple[]{ new Apple(initialisationStrings[APPLE1_INDEX]), new Apple(initialisationStrings[APPLE2_INDEX]) };

        int numberSnakes = initialisationStrings.length - FIRST_SNAKE_INDEX;
        snakes = new Snake[numberSnakes - 1]; //Subtract one since our snake will not be stored

        for (int i = FIRST_SNAKE_INDEX; i < initialisationStrings.length; i++) {
            if (i != SNAKE_NUMBER_INDEX) {
                snakes[i - FIRST_SNAKE_INDEX] = new Snake(initialisationStrings[i]);
            } else {
                ourSnake = new Snake(initialisationStrings[i]);
            }
        }
    }

    public Snake[] getSnakes() {
        return snakes;
    }

    public Snake getOurSnake() {
        return ourSnake;
    }

    public Apple[] getApples() {
        return apples;
    }

    public Obstacle[] getObstacles() {
        return obstacles;
    }
}
