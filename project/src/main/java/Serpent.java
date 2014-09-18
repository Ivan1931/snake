import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by jonah-hooper on 2014/09/18.
 */
public class Serpent extends Strategy {

    private int[][] createAgeBoard(GameState state) {
        int[][] ages = new int[Board.BOARD_SIZE][Board.BOARD_SIZE];
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                ages[i][j] = Integer.MAX_VALUE;
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
        boolean isBoarder = false;
        while(!frontier.isEmpty()) {
            Point current = frontier.poll();
            Point[] neighbours = current.getAllNeighbours();

            for(Point neighbour : neighbours) {
                if(Board.isOnBoard(neighbour) && board.isTraversable(neighbour) && !movableZone.contains(neighbour) && !head.equals(neighbour)) {
                    frontier.add(neighbour);
                    movableZone.add(neighbour);
                } else {
                    isBoarder = (Board.isOnBoard(neighbour) && !board.isTraversable(neighbour));
                }
            }

            if(isBoarder) boarder.add(current);
            isBoarder = false;

        }
        return new HashSet[] {movableZone, boarder};
    }

    private boolean isTrapped(GameState state,  HashSet<Point>[] sets,  Point potentialMove) {
        Snake ourSnake = state.getOurSnake();
        HashSet<Point> movableRegion = sets[0];
        HashSet<Point> boarder = sets[1];
        if (movableRegion.size() > ourSnake.getLength() && movableRegion.contains(ourSnake.getEnd())) return false;

        return true;
    }

    private boolean isTrapped(GameState state,  HashSet<Point> movableZone) {
        Point tail = state.getOurSnake().getEnd();
        return movableZone.size() < state.getOurSnake().getLength() || movableZone.contains(tail);
    }

    private Point leastDenseArea(GameState state, HashSet<Point> movableZone) {
        Snake[] snakes = state.getSnakes();
        final int checkInterval = 5;
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

    private Direction directionToPoint(GameState state, Point point) {
        Board board = state.getBoard();
        Point head = state.getOurSnake().getHead();
        Point[] path = board.approximateShortestPath(head, point);
        if (path.length > 2) {
            return path[0].directionBetween(path[1]);
        }
        return null;
    }

    @Override
    public Direction decideMove(GameState state, OpponentModel[] opponentModels, int snakeNumber) {
        return null;
    }
}
