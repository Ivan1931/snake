import java.util.LinkedList;
import java.util.Random;

/**
 * Created by jonah-hooper on 2014/08/18.
 */
public class RandomStrategy extends Strategy {
    @Override
    public Direction decideMove(GameState state, OpponentModel[] opponentModels, int snakeNumber) {
        LinkedList<Direction> directions = new LinkedList<Direction>();
        Direction impossibleDirection = Direction.oppositDirection(state.getSnake(snakeNumber).currentDirection());
        for(Direction direction : Direction.values()) {
            if (direction != impossibleDirection) directions.add(direction);
        }

        Random random = new Random();
        int chosenDirection = random.nextInt(directions.size());
        return directions.get(chosenDirection);
    }
}
