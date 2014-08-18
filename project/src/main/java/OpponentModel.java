/**
 * Created by jonah-hooper on 2014/08/18.
 */
public abstract class OpponentModel {

    public abstract void savePredictionHistory(Direction actualDirection);

    public abstract Strategy evaluateStrategyAccuracy(GameState[] previousStates, GameState[] previousMoves, int movesToLookBack);

    public abstract Strategy getStrategy();
}
