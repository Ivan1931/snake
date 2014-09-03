/**
 * Created by jonah-hooper on 2014/08/27.
 */

public class SimpleAStar extends Strategy {
    @Override
    public Direction decideMove(GameState state, OpponentModel[] opponentModels, int snakeNumber) {
        Direction nextMove = state.getOurSnake().currentDirection();
        Board board = new Board(state);
        Apple[] apples = state.getApples();
        Point head = state.getOurSnake().getHead();
        Point[] pathToAppleOne = board.approximateShortestPath(head, apples[0]);
        Point[] pathToAppleTwo = board.approximateShortestPath(head, apples[1]);
        if (pathToAppleOne != null && pathToAppleTwo != null) {
            if (pathToAppleOne.length > pathToAppleTwo.length) {
                return head.directionBetween(pathToAppleTwo[1]);
            }
            return head.directionBetween(pathToAppleOne[1]);
        } else if (pathToAppleOne != null && pathToAppleTwo == null) {
            return head.directionBetween(pathToAppleOne[1]);
        } else if (pathToAppleOne == null && pathToAppleTwo != null) {
            return head.directionBetween(pathToAppleTwo[1]);
        } else {
            return state.getOurSnake().currentDirection();
        }
    }
}
