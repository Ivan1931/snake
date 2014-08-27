import java.util.LinkedList;

/**
 * Created by jonah-hooper on 2014/08/18.
 */
public class GameController {

    private OpponentModel[] opponentModels;
    private LinkedList<GameState> previousStates;
    private Strategy strategy;
    private int ourSnake;

    public GameController() {
        previousStates = new LinkedList<GameState>();
        strategy = new SimpleAStar();
    }

    public GameController(Strategy strategy) {
        this.strategy = strategy;
        previousStates = new LinkedList<GameState>();
    }

    public void update(GameState nextState) {
        previousStates.addFirst(nextState);
    }

    public Direction move() {
        return strategy.decideMove(previousStates.getFirst(), opponentModels, ourSnake);
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
