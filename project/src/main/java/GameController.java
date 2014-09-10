import java.util.HashMap;
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

    public HashMap<String, Double> gameStats() {
        HashMap<String, Double> gameStats = new HashMap<String, Double>();
        int maxLength = Integer.MIN_VALUE;
        int trappedCount = 0;
        long sumOfLivingLength = 0;
        int deathCount = 0;
        for(GameState state : previousStates) {
            Snake ourSnake = state.getOurSnake();
            int length = ourSnake.getLength();
            sumOfLivingLength += length;
            if (maxLength < length) {
                maxLength = length;
            }

            if(ourSnake.isTrapped(state.getBoard())) {
                trappedCount++;
            }

            if(!ourSnake.isAlive()) {
                deathCount++;
            }
        }
        gameStats.put("Mean Length", (double) sumOfLivingLength / (double) previousStates.size());
        gameStats.put("LongestLength", (double)maxLength);
        gameStats.put("Trapped Count", (double)trappedCount);
        gameStats.put("Deaths", (double) deathCount);
        gameStats.put("Game Length", (double) previousStates.size());
        gameStats.put("Total kills", (double) previousStates.getLast().getOurSnake().getKills());

        return gameStats;
    }
}
