import java.util.LinkedList;

/**
 * Created by jonah-hooper on 2014/08/18.
 */
public class GameController {

    private OpponentModel[] opponentModels;
    private LinkedList<GameState> previousStates;
    private Strategy strategy;

    public GameController() {
        previousStates = new LinkedList<GameState>();
    }

    public void update(GameState nextState) {

    }

    public Direction move() {

    }

    public OpponentModel[] getOpponentModels() {
        return opponentModels;
    }

    public LinkedList<GameState> getPreviousStates() {
        return previousStates;
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
