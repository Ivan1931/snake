import java.util.*;

/**
 * Created by jonah-hooper on 2014/08/28.
 */
public class Basilisk extends Strategy  {

    private Point[] shortestPathForApple(Apple apple, Board board, Snake snakes[]) {
        Point[] minPath = null;

        for(Snake snake : snakes) {
            if(snake.isAlive()) {
                Point[] path = board.aproximateShortestPath(snake.getHead(), apple);
                if (path != null && (minPath == null || minPath.length > path.length)) {
                    minPath = path;
                }
            }
        }

        return minPath;
    }

    /**
     * This method will return the best possible path towards an apple if any
     * It performs A* for all known apples for each snake. It will then return our shortest path if our path is shorter than all the other snakes
     * If not it will return null
     * @param state State of the game
     * @param board Board derived from state
     * @return null if our possible paths are not the shortest. Otherwise our shortest path
     */
    private Point[] getOurBestPath(GameState state, Board board) {
        Snake[] hostileSnakes = state.getHostileSnakes();
        Apple[] apples = state.getApples();
        Snake ourSnake = state.getOurSnake();
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

        if (possibleShortestPaths.isEmpty()) return null;
        return Collections.min(possibleShortestPaths, pathComparator);
    }

    /**
     * This gets the path to the least dense point that is accessible to us
     * @param state
     * @param board
     * @return Path to the least dense point if it exists or null if there is no least dense point we can travel too
     */
    private Point[] getPathToLeastDenseSquare(GameState state,  Board board) {
        PriorityQueue<Board.Tuple> leastDensePoints = board.getMostEmptySpace();
        Point leastDensePoint;
        Point[] pathToLeastDensePoint;
        Point head = state.getOurSnake().getHead();
        do {
            leastDensePoint = leastDensePoints.poll().point;
            pathToLeastDensePoint = board.aproximateShortestPath(head, leastDensePoint);
        } while (!leastDensePoints.isEmpty() && pathToLeastDensePoint == null);

        return pathToLeastDensePoint;
    }

    /**
     * This method returns a score value based on whether or not the next move kills our snake
     * @param direction direction to move
     * @param board board to evaluate move
     * @param state state of game
     * @return 0.0 if moving in this direction does not kill us. Some negative number if it does
     */
    private double moveDeathScore(Direction direction, Board board, GameState state) {
        Point head = state.getOurSnake().getHead();
        Point nextPosition = head.pointInDirection(direction);
        final double DEATH_POINTS = -10000.0;
        if(!Board.isOnBoard(nextPosition)) return DEATH_POINTS; // Return negative death points if this move will kill us
        if(!board.isEmpty(nextPosition) && !board.isApple(nextPosition)) return DEATH_POINTS;
        return 0.0; //Nothing killed us
    }

    /**
     * This checks how many empty squares or apples our snake can reach from its current position on the board if the gamestate where to remain constant whilst our snake moved
     * @param board the board to eval
     * @param state the gamestate
     * @return non-negative integer
     */
    private int accessibleSquares(Board board, GameState state) {
        boolean[][] accessed = new boolean[Board.BOARD_SIZE][Board.BOARD_SIZE];
        LinkedList<Point> frontier = new LinkedList<Point>();
        frontier.add(state.getOurSnake().getHead());
        Point current;
        Point[] neighbours;
        int reached = 0;
        while(!frontier.isEmpty()) {
            current = frontier.pollLast();
            neighbours = current.getAllNeighbours();
            for(Point neighbour : neighbours) {
                if (Board.isOnBoard(neighbour)
                    && (board.isEmpty(neighbour) || board.isApple(neighbour))
                    && !accessed[neighbour.getX()][neighbour.getY()]) {

                    reached ++;
                    accessed[neighbour.getX()][neighbour.getY()] = true;
                    frontier.add(neighbour);
                }
            }
        }

        return reached;
    }

    /**
     * This method will return a negative score if our snake and another snake could possibly move into the same square in the next move
     * @param board board to evaluate move
     * @param state state of game
     * @param move move to check
     * @return 0 if no collision can occur otherwise a very low score
     */
    private double scoreCollosionWithHostile(Board board, GameState state, Direction move) {
        final double SNAKE_ABOUT_TO_CHOW = -7000.0;
        Point potentialMove = state.getOurSnake().getHead().pointInDirection(move);
        for(Snake hostile : state.getHostileSnakes()) {
            for(Point futurePoint : hostile.futureAllowedPoints()) {
                if (futurePoint.equals(potentialMove)) return SNAKE_ABOUT_TO_CHOW;
            }
        }
        return 500.0;
    }

    private boolean isTrapped(Board board, int reachedSquares) {
        final int totalSquares = Board.BOARD_SIZE * Board.BOARD_SIZE;
        final int totalEmptySquares = totalSquares - board.numberNonEmptySquares();
        return reachedSquares != totalEmptySquares;
    }

    @Override
    public Direction decideMove(GameState state, OpponentModel[] opponentModels, int snakeNumber) {
        final double EAT_APPLE_SCORE = 5000.0;
        final double LEAST_DENSE_SQUARE_SCORE = 1000.0;
        Snake ourSnake = state.getOurSnake();
        Board board = new Board(state);
        Point head = ourSnake.getHead();
        Direction[] allowedDirections = ourSnake.currentDirection().oppositDirection().otherDirections();

        Point[] shortestOfOurPaths = getOurBestPath(state, board);
        Direction shortestPath = null;
        if(shortestOfOurPaths != null) {
            shortestPath = head.directionBetween(shortestOfOurPaths[1]);
        }

        Point[] pathToLeastDenseSquare = getPathToLeastDenseSquare(state, board);
        Direction directionToLeastDense = head.directionBetween(pathToLeastDenseSquare[1]);
        double bestDirectionScore = Double.MIN_VALUE;
        Direction bestDirection = null;
        for(Direction direction : allowedDirections) {
            double currentDirectionScore = moveDeathScore(direction, board, state);
            if (shortestPath != null && shortestPath == direction) currentDirectionScore += EAT_APPLE_SCORE;

            if (directionToLeastDense == direction) currentDirectionScore += LEAST_DENSE_SQUARE_SCORE;

            if (shortestOfOurPaths != null && shortestOfOurPaths.length == 1) currentDirectionScore += scoreCollosionWithHostile(board, state, direction);

            if (currentDirectionScore > bestDirectionScore) {
                bestDirectionScore = currentDirectionScore;
                bestDirection = direction;
            }
        }

        if (bestDirection == null) return ourSnake.currentDirection();
        return bestDirection;
    }
}