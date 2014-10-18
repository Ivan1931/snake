import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by jonah-hooper on 2014/09/18.
 */
public class Serpent extends Strategy {

    private HashSet<Point> possibleFutureHostilePoints(GameState state) {
        Board board = state.getBoard();
        HashSet<Point> futureHostilePoints = new HashSet<Point>();
        for(Snake snake : state.getHostileSnakes()) {
            Point head = snake.getHead();
            Point[] neighbours = head.getAllNeighbours();
            for(Point neighbour : neighbours) {
                if (Board.isOnBoard(neighbour) && !board.isTraversable(neighbour)) {
                    futureHostilePoints.add(neighbour);
                }
            }
        }

        return futureHostilePoints;
    }

    private double averageBorderAge(HashSet<Point> border, int[][] ageBoard) {
        double totalAge = 0.0;
        for(Point point : border) {
            int x = point.getX();
            int y = point.getY();
            totalAge += ageBoard[x][y];
        }
        return totalAge / (double) border.size();
    }

    private Point bestTrappedMove(GameState state, int[][] ageBoard,
                                  HashMap<Point, HashSet<Point>[]> movableData) {
        Board board = state.getBoard();
        Point head = state.getOurSnake().getHead();
        Point[] neighbours = board.getTraverableNeighbours(head);
        double highestAverage = -Double.MAX_VALUE;
        Point bestNeighbour = null;
        for(Point neighbour : neighbours) {
            HashSet<Point> neighbourBorder = movableData.get(neighbour)[1];
            double average = averageBorderAge(neighbourBorder, ageBoard);
            if (highestAverage < average) {
                bestNeighbour = neighbour;
                highestAverage = average;
            }
        }

        return bestNeighbour;
    }

    private int[][] createAgeBoard(GameState state) {
        int[][] ages = new int[Board.BOARD_SIZE][Board.BOARD_SIZE];
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                ages[i][j] = Integer.MIN_VALUE;
            }
        }

        Snake[] snakes = state.getSnakes();
        for(Snake snake : snakes) {
            int age = 0;
            for(Point segment : snake.getBody()) {
                int x = segment.getX();
                int y = segment.getY();
                ages[x][y] = age;
                age++;
            }
        }

        return ages;
    }

    @SuppressWarnings("unchecked")
    private HashSet<Point>[] createMovementZone(GameState state, Point fromPoint) {
        Board board = state.getBoard();
        Point head;
        if (fromPoint == null)
            head = state.getOurSnake().getHead();
        else
            head = fromPoint;
        HashSet<Point> movableZone = new HashSet<Point>();
        HashSet<Point> boarder = new HashSet<Point>();
        LinkedList<Point> frontier = new LinkedList<Point>();
        frontier.add(head);
        while(!frontier.isEmpty()) {
            Point current = frontier.poll();
            Point[] neighbours = current.getAllNeighbours();

            for(Point neighbour : neighbours) {
                if(Board.isOnBoard(neighbour)
                        && board.isTraversable(neighbour)
                        && !movableZone.contains(neighbour)
                        && !head.equals(neighbour)) {
                    frontier.add(neighbour);
                    movableZone.add(neighbour);
                } else {
                    if (Board.isOnBoard(neighbour) && !board.isTraversable(neighbour)) {
                        boarder.add(neighbour);
                    }
                }
            }

        }
        return new HashSet[] {movableZone, boarder};
    }

    private HashMap<Point, HashSet<Point>[]> movableDataForPoints(GameState state, Point[] points) {
        HashMap<Point, HashSet<Point>[]> movablePointData = new HashMap<Point, HashSet<Point>[]>();
        for(Point point : points) {
            boolean inOtherZone = false;

            for(Map.Entry<Point, HashSet<Point>[]> kv : movablePointData.entrySet()) {
                if (kv.getValue()[0].contains(point)) {
                    movablePointData.put(point, kv.getValue());
                    inOtherZone = true;
                    break;
                }
            }

            if (!inOtherZone) {
                movablePointData.put(point, createMovementZone(state, point));
            }
        }
        return movablePointData;
    }

    private boolean movebleRegionContainsHostileEnd(GameState state, HashSet<Point> movableRegion) {
        for(Snake snake : state.getHostileSnakes()) {
            if (movableRegion.contains(snake.getEnd())) return true;
        }
        return false;
    }

    private boolean isTrapped(GameState state,  HashSet<Point>[] sets) {
        Snake ourSnake = state.getOurSnake();
        HashSet<Point> movableRegion = sets[0];
        HashSet<Point> border = sets[1];
        return !(movableRegion.size() > ourSnake.getLength()
                ||  border.contains(ourSnake.getEnd())
                ||  movebleRegionContainsHostileEnd(state, movableRegion));
    }

    private boolean moveByBoarder(GameState state,  HashSet<Point> border, Point potentialMove) {
        Board board = state.getBoard();
        Point[] neighbours = potentialMove.getAllNeighbours();
        for(Point neighbour : neighbours) {
            if (border.contains(neighbour)) return true;
        }
        return false;
    }

    private int ageOfSquare(int[][] ageBoard, Point point) {
        if (!Board.isOnBoard(point)) return Integer.MIN_VALUE;
        int x = point.getX();
        int y = point.getY();
        return ageBoard[x][y];
    }

    private int maxAgeOfAdjacencies(GameState state, int[][] ageBoard, Point point) {
        Board board = state.getBoard();
        if (!Board.isOnBoard(point)) return Integer.MIN_VALUE;
        if (!board.isTraversable(point)) return Integer.MIN_VALUE;
        Point[] neighbours = point.getAllNeighbours();
        int maxAge = Integer.MIN_VALUE;
        for(Point neighbour : neighbours) {
            int neighbourAge = ageOfSquare(ageBoard, neighbour);
            if (maxAge < neighbourAge) {
                maxAge = neighbourAge;
            }
        }

        return maxAge;
    }

    private Point leastDenseReachableSquare(GameState state, HashSet<Point> movableZone) {
        Snake[] snakes = state.getSnakes();
        final int checkInterval = 4;
        int checked = 0;
        Point leastDenseSoFar = null;
        double bestDensity = Double.MAX_VALUE;
        for(Point point : movableZone) {
            if (checked % checkInterval == 0) {
                double density = 0.0;
                for(Snake snake : snakes) {
                    for(Point segment : snake.getBody()) {
                        density += point.gravityDistance(segment);
                    }
                }
                if (density < bestDensity) {
                    bestDensity = density;
                    leastDenseSoFar = point;
                }
            }
            checked++;
        }

        return leastDenseSoFar;
    }

    private Point moveToLeastDenseSquare(GameState state, HashSet<Point> movableRegion) {
        Point reachableLeastDenseSquare = leastDenseReachableSquare(state, movableRegion);
        Point[] pathToLeastDenseSquare = state.getBoard().approximateShortestPath(state.getOurSnake().getHead(), reachableLeastDenseSquare);
        if (pathToLeastDenseSquare != null && pathToLeastDenseSquare.length > 2) return pathToLeastDenseSquare[1];
        return null;
    }

    private Direction directionOfPath(Point[] path) {
        if (path.length > 2) return path[0].directionBetween(path[1]);
        return null;
    }

    private Direction directionToPoint(GameState state, Point point) {
        Board board = state.getBoard();
        Point head = state.getOurSnake().getHead();
        Point[] path = board.approximateShortestPath(head, point);
        if (path.length > 2) {
            return path[0].directionBetween(path[1]);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    /**
     * Gets the path to apple that is closest to us and closer to us than all the other snakes. Returns null if no path matches this description
     * @param state
     * @return a direction to closes apple. Otherwise null
     */
    private Point bestPathToApple(GameState state, HashSet<Point> movableRegion) {
        Board board = state.getBoard();
        Point head = state.getOurSnake().getHead();
        Apple[] apples = state.getApples();
        //
        LinkedList<Point[]> ourPaths = new LinkedList<Point[]>();
        //
        LinkedList<Point> opponentHeads = new LinkedList<Point>();
        for (Snake snake : state.getHostileSnakes()) opponentHeads.add(snake.getHead());
        //
        LinkedList<Point[]>[] hostilePathsToApples = new LinkedList[]{new LinkedList<Point[]>(), new LinkedList<Point[]>()};
        Point[][] shortestHostilePaths = new Point[2][];
        for (int i = 0; i < 2; i++) {
            if (movableRegion.contains(apples[i])) {
                for (Point hostileHead : opponentHeads) {
                    Point[] hostilePath = board.approximateShortestPath(hostileHead, apples[i]);
                    if (hostilePath != null)
                        hostilePathsToApples[i].add(hostilePath);
                }

                if (!hostilePathsToApples[i].isEmpty())
                    shortestHostilePaths[i] = Collections.min(hostilePathsToApples[i], new PathComparator());

                Point[] ourPath = board.approximateShortestPath(head, apples[i]);

                if (ourPath != null) {
                    if (shortestHostilePaths[i] != null) {
                        if (ourPath.length < shortestHostilePaths[i].length) {
                            ourPaths.add(ourPath);
                        }
                    }
                    else {
                        ourPaths.add(ourPath);
                    }
                }
            }
        }
        if (ourPaths.isEmpty()) return null;
        Point[] best = Collections.min(ourPaths, new PathComparator());
        if (best.length < 2) return null;
        return best[1];
    }

    /**
     * gets the moves for our snake which are traversable
     * @param state
     * @return empty list if none exist
     */
    public Point[] availableMoves(GameState state) {
        Point head = state.getOurSnake().getHead();
        Board board = state.getBoard();
        Point[] neighbours = head.getAllNeighbours();
        LinkedList<Point> ret = new LinkedList<Point>();
        for(Point neighbour : neighbours) {
            if (Board.isOnBoard(neighbour) && board.isTraversable(neighbour)) {
                ret.add(neighbour);
            }
        }

        return ret.toArray(new Point[ret.size()]);
    }

    public boolean willCollideWithOtherSnake(GameState state, Point potentialMove) {
        for(Snake snake : state.getHostileSnakes()) {
            Point[] futurePositions = snake.getHead().getAllNeighbours();
            for(Point future : futurePositions) {
                if (potentialMove.equals(future)) return true;
            }
        }

        return false;
    }

    public double percentageMovableRegionReduction(HashSet<Point> movableRegion, HashSet<Point> potentialMovableRegion) {
        return (double) (movableRegion.size() - potentialMovableRegion.size()) / (double) movableRegion.size();
    }

    public double score(boolean willTrap,  boolean isPathToLeastDenseSquare, boolean pathToClosestApple,
                        boolean willCollideWithOtherSnake, double regionSizeReductionPercentage) {
        double score = 0.0;
        if (willTrap) score -= 10.0;
        if (isPathToLeastDenseSquare) score += 1.0;
        if (pathToClosestApple) score += 4.0;
        if (willCollideWithOtherSnake) score += -10.0;
        score -= regionSizeReductionPercentage * 10.0;
        return score;
    }

    public double scoreTrapped(boolean pathToClosestApple, boolean willCollideWithOtherSnake,
                               boolean isBestTrappedMove,
                               double regionSizeReductionPercentage ){
        double score = 0.0;
        if(pathToClosestApple) score += 1.0;
        if(isBestTrappedMove) score += 2.0;
        if(willCollideWithOtherSnake) score -= 10.0;
        score -= regionSizeReductionPercentage * 10.0;
        return score;
    }


    @Override
    public Direction decideMove(GameState state, OpponentModel[] opponentModels, int snakeNumber) {
        Snake snake = state.getOurSnake();
        Point head = snake.getHead();
        HashSet<Point>[] sets = createMovementZone(state, null);
        HashSet<Point> movableZone = sets[0];
        HashSet<Point> zoneBorders = sets[1];
        int[][] ageBoard = createAgeBoard(state);
        Point[] movablePoints = availableMoves(state);
        Point shortestPathToAppleMove = bestPathToApple(state, movableZone);
        Point moveToLeastDenseSquare = moveToLeastDenseSquare(state, movableZone);
        HashMap<Point, HashSet<Point>[]> movableData = movableDataForPoints(state, movablePoints);
        boolean isTrapped = isTrapped(state, sets);
        Point bestSurroundingBorder = null;
        if (isTrapped)
            bestSurroundingBorder = bestTrappedMove(state, ageBoard, movableData);

        double bestScore = -Double.MAX_VALUE;
        Point bestMove = null;
        for(Point potentialMove : movablePoints) {
            Direction best = head.directionBetween(potentialMove);
            boolean isShortestPathToApple = potentialMove.equals(shortestPathToAppleMove);
            double regionSizeReduction = percentageMovableRegionReduction(movableZone, movableData.get(potentialMove)[0]);
            boolean willCollideWithOtherSnake = willCollideWithOtherSnake(state, potentialMove);

            double score = 0.0;
            if (!isTrapped) {
                boolean willTrap = isTrapped(state, movableData.get(potentialMove));
                boolean isPathToLeastDenseSquare = potentialMove.equals(moveToLeastDenseSquare);
                score = score(willTrap, isPathToLeastDenseSquare,
                        isShortestPathToApple, willCollideWithOtherSnake, regionSizeReduction);
            } else {
                boolean isBestTrappedMove = potentialMove.equals(bestSurroundingBorder);
                score = scoreTrapped(isShortestPathToApple, willCollideWithOtherSnake, isBestTrappedMove, regionSizeReduction );
            }
            if (bestScore < score) {
                bestScore = score;
                bestMove = potentialMove;
            }
        }
        return head.directionBetween(bestMove);
    }
}
