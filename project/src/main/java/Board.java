import com.sun.xml.internal.ws.util.Pool;
import sun.security.x509.CRLDistributionPointsExtension;

import java.util.*;

/**
 * Created by jonah-hooper on 2014/07/29.
 */
public class Board {
    public static final int BOARD_SIZE = 50;
    private BoardSquare[][] board;
    private LinkedList<Point> nonEmptySpaces;

    public Board(GameState state) {
        this(state.getSnakes(), state.getApples(), state.getObstacles(), null);
    }

    public Board(GameState state,  Point[] points) {
        this(state.getSnakes(), state.getApples(), state.getObstacles(), points);
    }

    public Board(Snake[] snakes, Apple[] apples, Obstacle[] obstacles, Point[] otherPoints) {
        this.nonEmptySpaces = new LinkedList<Point>();

        this.board = Board.emptyBoard();

        if (snakes != null)
            for(Snake snake : snakes) setSnake(snake);

        if (apples != null)
            for(Apple apple : apples) setApple(apple);

        //for(Obstacle obstacle : obstacles) setPoint(obstacle);

        if (otherPoints != null)
            for(Point point : otherPoints) setPoint(point, BoardSquare.ANYTHING);
    }

    public BoardSquare getPoint(Point point) {
        return board[point.getX()][point.getY()];
    }

    private void setPoint(Point point, BoardSquare square) {
        if (square != BoardSquare.EMPTY) nonEmptySpaces.add(point);
        board[point.getX()][point.getY()] = square;
    }

    private void setSnake(Snake snake) {
        for(Point point : snake.getBody()) {
            setPoint(point, BoardSquare.SNAKE);
        }
    }

    private void setApple(Apple apple) {
        setPoint(apple, BoardSquare.APPLE);
    }


    public static boolean isOnBoard(Point point) {
        return point.getX() >= 0 && point.getY() >= 0 &&
               point.getX() < BOARD_SIZE && point.getY() < BOARD_SIZE;
    }

    public boolean isEmpty(Point point) {
        return board[point.getX()][point.getY()] == BoardSquare.EMPTY;
    }

    public static BoardSquare[][] emptyBoard() {
        BoardSquare[][] newBoard = new BoardSquare[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                newBoard[i][j] = BoardSquare.EMPTY;
            }
        }

        return newBoard;
    }

    public BoardSquare[][] getBoard() {
        return this.board;
    }

    public Point[] adjacentEmptyPoints(Point point) {
        Point[] points = new Point[] { point.north(), point.south(), point.east(), point.west() };
        ArrayList<Point> ret = new ArrayList<Point>(4);

        for(Point p : points) {
            if (Board.isOnBoard(p) && this.isEmpty(p)) {
                ret.add(p);
            }
        }

        return ret.toArray(new Point[ret.size()]);
    }

    public boolean isApple(Point point) {
        return getPoint(point) == BoardSquare.APPLE;
    }

    //This performs an a-star algorithm using euclidean distance

    /**
     * Performs an A* search to find an aproximate shortest path from one end to another
     * @param start Must be inside the graph
     * @param end Must be an empty square or an apple
     * @return An array of points from the starting point to the end point which forms a path to be taken between the points
     */
    public Point[] aproximateShortestPath(Point start, Point end) {
        PriorityQueue<Tuple> frontier = new PriorityQueue<Tuple>();
        frontier.add(new Tuple(start, 0.0));
        HashMap<Point, Point> cameFrom = new HashMap<Point, Point>();
        HashMap<Point, Double> costSoFar = new HashMap<Point, Double>();
        cameFrom.put(start, null);
        costSoFar.put(start, 0.0);
        Point current;
        do {
            current = frontier.poll().point;
            Point[] neighbours = possibleNeighbouringPoints(current);
            for(Point next : neighbours) {
                double newCost = costSoFar.get(current) + 1; // 1 is the cost of moving to that square since all parts are neighbours
                if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {
                    costSoFar.put(next, newCost);
                    double priority = newCost + end.manhattanDistance(next);
                    frontier.add(new Tuple(next, priority));
                    cameFrom.put(next, current);
                }
            }
        } while (!frontier.isEmpty() && !current.equals(end));

        if (!current.equals(end)) return null; // Return nothing if there is no path out, IE trapped

        Point next = current;
        LinkedList<Point> path = new LinkedList<Point>();
        while(!next.equals(start)) {
            path.addFirst(next);
            next = cameFrom.get(next);
        }
        path.addFirst(start);

        return path.toArray(new Point[path.size()]);
    }

    public Point[] possibleNeighbouringPoints(Point p) {
        //Omfg!!! java sucks so fucking much. Why the fuck cant you just have a map function! is it too much to ask!!!!
        if (Board.isOnBoard(p)) {
            Point[] allNeighbours = p.getAllNeighbours();
            LinkedList<Point> possibleNeighbours = new LinkedList<Point>();
            for(Point point : allNeighbours) {
                if (Board.isOnBoard(point) && (isEmpty(point) || isApple(point))) possibleNeighbours.add(point);
            }
            return possibleNeighbours.toArray(new Point[possibleNeighbours.size()]);
        }
        return null;
    }

    public boolean isFull() {
        return nonEmptySpaces.size() == (BOARD_SIZE * BOARD_SIZE);
    }

    /**
     * This method finds the point which has the farthest away from all other points
     * This is measured by finding the empty point which is least affected by the density between all other non-empty points
     * This is measured by a formula that finds the radial distance between two points and uses that in the gravity formula
     * @return the point that should be in the most empty section of the board
     */
    public PriorityQueue<Tuple> getMostEmptySpace() {
        PriorityQueue<Tuple> orderedEmptySpaces = new PriorityQueue<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Point point = new Point(i, j);
                if(isEmpty(point)) {
                    double totalDensity = 0.0;
                    for(Point nonEmptyPoint : nonEmptySpaces) {
                        totalDensity += point.gravityDistance(nonEmptyPoint);
                    }
                    orderedEmptySpaces.add(new Tuple(point, totalDensity));
                }
            }
        }

        return orderedEmptySpaces;
    }


    @Override
    public String toString() {
        String acc = "";

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE - 1; j++) {
                acc += board[j][i].toString() + " ";
            }
            acc += board[BOARD_SIZE - 1][i] + "\n";
        }
        acc = acc.substring(0, acc.length() - 1); // This will remove the last newLine
        return acc;
    }

    class Tuple implements Comparable<Tuple> {
        public Point point;
        public double priority;

        public Tuple(Point point, double priority) {
            this.point = point;
            this.priority = priority;
        }

        @Override
        public int compareTo(Tuple that) {
            if(this.priority > that.priority) return 1;
            if(this.priority < that.priority) return -1;
            return 0;
        }
    }
}
