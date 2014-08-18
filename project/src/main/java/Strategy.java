/**
 * Created by jonah-hooper on 2014/08/18.
 */
public abstract class Strategy {
    public abstract Direction decideMove(GameState state, OpponentModel[] opponentModels, int snakeNumber);
}
