import com.sun.xml.internal.ws.util.Pool;
import sun.security.x509.CRLDistributionPointsExtension;

import java.util.*;

/**
 * Created by jonah-hooper on 2014/07/29.
 */
public class Board {
    public static final int BOARD_SIZE = 50;
    private BoardSquare[][] board;

    public Board(GameState state) {
        this(state.getSnakes(), state.getApples(), state.getObstacles(), null);
    }

    public Board(GameState state,  Point[] points) {
        this(state.getSnakes(), state.getApples(), state.getObstacles(), points);
    }

    public Board(Snake[] snakes, Apple[] apples, Obstacle[] obstacles, Point[] otherPoints) {
        this.board = Board.emptyBoard();

        if (snakes != null)
            for(Snake snake : snakes) setSnake(snake);

        if (apples != null)
            for(Apple apple : apples) setApple(apple);

        //for(Obstacle obstacle : obstacles) setPoint(obstacle);

        if (otherPoints != null)
            for(Point point : otherPoints) setPoint(point, BoardSquare.ANYTHING);
    }

    private void setSnake(Snake snake) {
        for(Point point : snake.getBody()) {
            setPoint(point, BoardSquare.SNAKE);
        }
    }

    private void setApple(Apple apple) {
        setPoint(apple, BoardSquare.APPLE);
    }

    private void setPoint(Point point, BoardSquare square) {
        board[point.getX()][point.getY()] = square;
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

    //This performs an a-star algorithm using euclidean distance
    public Point[] aproximateShortestPath(Point start, Point end) {
        //Since java has no fucking built in tuples I had to build this. Fuck fuckedy fuck fuck.
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
            path.add(next);
            next = cameFrom.get(next);
        }
        path.add(start);

        return path.toArray(new Point[path.size()]);
    }

    public Point[] possibleNeighbouringPoints(Point p) {
        //Omfg!!! java sucks so fucking much. Why the fuck cant you just have a map function! is it too much to ask!!!!
        if (Board.isOnBoard(p)) {
            Point[] allNeighbours = p.getAllNeighbours();
            LinkedList<Point> possibleNeighbours = new LinkedList<Point>();
            for(Point point : allNeighbours) {
                if (Board.isOnBoard(point) && isEmpty(point)) possibleNeighbours.add(point);
            }
            return possibleNeighbours.toArray(new Point[possibleNeighbours.size()]);
        }
        return null;
    }


    @Override
    public String toString() {
        String acc = "";

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE - 1; j++) {
                acc += board[i][j].toString() + " ";
            }
            acc += board[i][BOARD_SIZE - 1] + "\n";
        }
        acc = acc.substring(0, acc.length() - 1); // This will remove the last newLine
        return acc;
    }
}
