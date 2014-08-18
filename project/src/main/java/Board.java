import sun.security.x509.CRLDistributionPointsExtension;

import java.util.ArrayList;

/**
 * Created by jonah-hooper on 2014/07/29.
 */
public class Board {
    public static final int BOARD_SIZE = 50;
    private boolean[][] board;
    public Board(Snake[] snakes, Apple[] apples, Obstacle[] obstacles) {
        this.board = Board.emptyBoard();

        for(Snake snake : snakes) setSnake(snake);

        for(Apple apple : apples) setPoint(apple);

        for(Obstacle obstacle : obstacles) setPoint(obstacle);
    }

    private void setSnake(Snake snake) {
        for(Point point : snake.getBody()) {
            setPoint(point);
        }
    }

    private void setPoint(Point point) {
        board[point.getX()][point.getY()] = true;
    }

    public static boolean isOnBoard(Point point) {
        return point.getX() >= 0 && point.getY() >= 0 &&
               point.getX() < BOARD_SIZE && point.getY() < BOARD_SIZE;
    }

    public boolean isEmpty(Point point) {
        return !board[point.getX()][point.getY()];
    }

    public static boolean[][] emptyBoard() {
        return new boolean[BOARD_SIZE][BOARD_SIZE];
    }

    public boolean[][] getBoard() {
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

}
