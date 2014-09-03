import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by jonah-hooper on 2014/08/28.
 */
public class Basilisk extends Strategy  {

    private Point[] shortestPathForApple(Apple apple, Board board, Snake snakes[]) {
        Point[] minPath = null;

        for(Snake snake : snakes) {
            if(snake.isAlive()) {
                Point[] path = board.approximateShortestPath(snake.getHead(), apple);
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
                board.approximateShortestPath(head, apples[0]),
                board.approximateShortestPath(head, apples[1])
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
            pathToLeastDensePoint = board.approximateShortestPath(head, leastDensePoint);
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
        final double DEATH_POINTS = -20000.0;
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
        final double SNAKE_ABOUT_TO_CHOW = -10000.0;
        Point potentialMove = state.getOurSnake().getHead().pointInDirection(move);
        Snake[] hostileSnakes = state.getHostileSnakes();
        for(Snake hostile : hostileSnakes) {
            Point[] hostileFuturePoints = hostile.futureAllowedPoints();
            for(Point futurePoint : hostileFuturePoints) {
                if (futurePoint.equals(potentialMove)) {
                    return SNAKE_ABOUT_TO_CHOW;
                }
            }
        }
        return 0.0;
    }

    private int movesUntilSnakeUntrapped(boolean[][] trappedRegion, Snake snake) {
        LinkedList<Point> body = snake.getBody();
        int count = 0;
        boolean foundTrapped;
        /*for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                System.out.print((trappedRegion[j][i] ? 1 : 0) + " ");
            }
            System.out.print("\n");
        }*/
        for(Point segment : body) {
            Point[] adjacentBlocks = segment.getAllNeighbours();
            foundTrapped = false;
            for(Point neighbour : adjacentBlocks) {
                if (Board.isOnBoard(neighbour) && trappedRegion[neighbour.getX()][neighbour.getY()]) {
                    foundTrapped = true;
                }
            }
            if(!foundTrapped) {
                return snake.getLength() - count + 1;
            }
            count++;
        }
        return snake.getLength();
    }

    private double scoreTrapped(Board board, GameState state, Direction move) {
        final double TRAPPED_TO_DEATH = -10000.0;
        Snake snake = state.getOurSnake() ;
        Point head = snake.getHead();
        Point nextHead = head.pointInDirection(move);
        if (!Board.isOnBoard(nextHead)) return 0.0;
        if (snake.moveWouldTrap(move, board)) {
            Object[] emenance = board.moveableRegion(nextHead, null);
            boolean[][] movableRegion = (boolean[][]) emenance[0];
            int squaresInRegion = (int) emenance[1];
            int movesUntilUntrapped = movesUntilSnakeUntrapped(movableRegion, snake);
            if (movesUntilUntrapped < squaresInRegion) {
                return (1.0 - movesUntilUntrapped / squaresInRegion) * TRAPPED_TO_DEATH;
            } else {
                return TRAPPED_TO_DEATH;
            }
        }
        return 0.0;
    }

    private Direction normalDecision(Board board, GameState state,  OpponentModel[] opponentModels, int snakeNumber) {
        final double EAT_APPLE_SCORE = 5000.0;
        final double LEAST_DENSE_SQUARE_SCORE = 1000.0;
        Snake ourSnake = state.getOurSnake();
        Point head = ourSnake.getHead();
        Direction[] allowedDirections = ourSnake.currentDirection().oppositDirection().otherDirections();

        Point[] shortestOfOurPaths = getOurBestPath(state, board);
        Direction shortestPath = null;
        if(shortestOfOurPaths != null) {
            shortestPath = head.directionBetween(shortestOfOurPaths[1]);
        }

        Point[] pathToLeastDenseSquare = getPathToLeastDenseSquare(state, board);
        Direction directionToLeastDense = head.directionBetween(pathToLeastDenseSquare[1]);
        double bestDirectionScore = -10000000.0;
        Direction bestDirection = null;
        for(Direction direction : allowedDirections) {
            double currentDirectionScore = moveDeathScore(direction, board, state);
            if (shortestPath != null && shortestPath == direction) currentDirectionScore += EAT_APPLE_SCORE;

            if (directionToLeastDense == direction) currentDirectionScore += LEAST_DENSE_SQUARE_SCORE;

            currentDirectionScore += scoreCollosionWithHostile(board, state, direction);

            currentDirectionScore += scoreTrapped(board, state, direction);

            if (currentDirectionScore > bestDirectionScore) {
                bestDirectionScore = currentDirectionScore;
                bestDirection = direction;
            }
        }

        if (bestDirection == null) return ourSnake.currentDirection();
        return bestDirection;
    }

    private Direction trappedDecision(Board board, GameState state, OpponentModel[] opponentModels, int snakeNumber) {
        Snake ourSnake = state.getOurSnake();
        Point head = ourSnake.getHead();
        Point thirdPoint = ourSnake.getBody().get(2);
        Point[] pathToThird = board.approximateShortestPath(head, thirdPoint);
        Direction directionToThird = head.directionBetween(pathToThird[1]);
        Direction[] possibleDirections = ourSnake.currentDirection().otherDirections();
        Direction bestDirection = directionToThird;
        double bestDirectionScore = -1000000;
        for(Direction direction : possibleDirections) {
            double currentDirectionScore = moveDeathScore(direction, board, state);
            if(direction == directionToThird) currentDirectionScore += 500.0;

            if (currentDirectionScore > bestDirectionScore) {
                bestDirectionScore = currentDirectionScore;
                bestDirection = direction;
            }
        }
        return bestDirection;
    }

    @Override
    public Direction decideMove(GameState state, OpponentModel[] opponentModels, int snakeNumber) {
       Board board = state.getBoard();
       return normalDecision(board, state, opponentModels, snakeNumber);
    }

}
