import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by jonah-hooper on 2014/08/28.
 */
public class Avoidance extends Strategy  {

    @Override
    public Direction decideMove(GameState state, OpponentModel[] opponentModels, int snakeNumber) {
        long a = System.currentTimeMillis();
        Snake[] hostileSnakes = state.getHostileSnakes();
        Point[] paths = new Point[hostileSnakes.length];
        Apple[] apples = state.getApples();
        Snake ourSnake = state.getOurSnake();
        Board board = new Board(state);
        Point head = ourSnake.getHead();
        PathComparator pathComparator = new PathComparator();

        Point[][] ourPathsToApples = new Point[][] {
                board.aproximateShortestPath(head, apples[0]),
                board.aproximateShortestPath(head, apples[1])
        };

        Point[][] shortestHostilePathsToApples = new Point[][] {
                shortestPathForApple(apples[0], board, hostileSnakes),
                shortestPathForApple(apples[1], board, hostileSnakes)
        };

        LinkedList<Point[]> possibleShortestPaths = new LinkedList<Point[]>();
        for (int i = 0; i < ourPathsToApples.length; i++) {
            if (ourPathsToApples[i] != null
                    && (shortestHostilePathsToApples[i] == null
                    || pathComparator.compare(ourPathsToApples[i], shortestHostilePathsToApples[i]) == -1)) {
                possibleShortestPaths.add(ourPathsToApples[i]);
            }
        }

        if (!possibleShortestPaths.isEmpty()) {
            Point[] shortestOfOurPaths = Collections.min(possibleShortestPaths, pathComparator);
            return head.directionBetween(shortestOfOurPaths[1]);
        } else {
            PriorityQueue<Board.Tuple> leastDensePoints = board.getMostEmptySpace();
            Point leastDensePoint;
            Point[] pathToLeastDensePoint;
            do {
                leastDensePoint = leastDensePoints.poll().point;
                pathToLeastDensePoint = board.aproximateShortestPath(head, leastDensePoint);
            } while (!leastDensePoints.isEmpty() && pathToLeastDensePoint == null);

            if(pathToLeastDensePoint == null || pathToLeastDensePoint.length < 2) return state.getOurSnake().currentDirection();
            long b = System.currentTimeMillis();
            System.out.println("log Execution time: " + (b - a));
            return head.directionBetween(pathToLeastDensePoint[1]);
        }
    }

    private Point[] shortestPathForApple(Apple apple, Board board, Snake snakes[]) {
        Point[] minPath = null;

        for(Snake snake : snakes) {
            Point[] path = board.aproximateShortestPath(snake.getHead(), apple);
            if (path != null && (minPath == null || minPath.length > path.length)) {
                minPath = path;
            }
        }

        return minPath;
    }

}
