import java.util.LinkedList;

/**
 * Created by jonah-hooper on 2014/07/29.
 */
public class GameState {
    private Snake[] snakes;
    private Apple[] apples;
    private Obstacle[] obstacles;
    private int ourSnakeNumber;

    public GameState(String[] initialisationStrings, Obstacle[] obstacles) {
        assert(initialisationStrings.length > 0);
        final int APPLE1_INDEX = 0;
        final int APPLE2_INDEX = 1;
        final int SNAKE_NUMBER_INDEX = 2;
        final int FIRST_SNAKE_INDEX = 3;

        this.obstacles = obstacles;

        apples = new Apple[] {
                new Apple(initialisationStrings[APPLE1_INDEX]),
                new Apple(initialisationStrings[APPLE2_INDEX])
        };

        ourSnakeNumber = Integer.parseInt(initialisationStrings[SNAKE_NUMBER_INDEX]);

        int numberSnakes = initialisationStrings.length - FIRST_SNAKE_INDEX;
        snakes = new Snake[numberSnakes]; //Subtract one since our snake will not be stored

        for (int i = FIRST_SNAKE_INDEX; i < initialisationStrings.length; i++) {
            snakes[i - FIRST_SNAKE_INDEX] = new Snake(initialisationStrings[i]);
        }
    }

    public Snake[] getSnakes() {
        return snakes;
    }

    public Snake[] getHostileSnakes() {
        LinkedList<Snake> hostiles = new LinkedList<Snake>();
        for (int i = 0; i < snakes.length; i++) {
            if(i != ourSnakeNumber) hostiles.add(snakes[i]);
        }
        return hostiles.toArray(new Snake[hostiles.size()]);
    }

    public int getOurSnakeNumber() {
        return ourSnakeNumber;
    }

    public Apple[] getApples() {
        return apples;
    }

    public Obstacle[] getObstacles() {
        return obstacles;
    }

    public Snake getSnake(int snakeNumber) {
        return snakes[snakeNumber];
    }

    public Snake getOurSnake() {
        return snakes[ourSnakeNumber];
    }
}
