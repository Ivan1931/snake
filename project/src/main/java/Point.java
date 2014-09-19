/**
 * Created by jonah-hooper on 2014/08/17.
 */

import java.util.LinkedList;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.y = y;
        this.x = x;
    }

    public Point(String commaSeperatedString) {
        String[] points = commaSeperatedString.split("(,| )");
        this.x = Integer.parseInt(points[0]);
        this.y = Integer.parseInt(points[1]);
    }

    public Point north() {
        return new Point(this.x, this.y - 1);
    }

    public Point south() {
        return new Point(this.x, this.y + 1);
    }

    public Point east() {
        return new Point(this.x + 1, this.y);
    }

    public Point west() {
        return new Point(this.x - 1, this.y);
    }

    public Point pointInDirection(Direction direction) {
        switch (direction) {
            case NORTH: return this.north();
            case  EAST: return this.east();
            case SOUTH: return this.south();
            default: return this.west();
        }
    }

    public Direction directionBetween(Point that) {
        if (this.north().equals(that)) return Direction.NORTH;
        if (this.south().equals(that)) return Direction.SOUTH;
        if (this.east().equals(that)) return Direction.EAST;
        if (this.west().equals(that)) return Direction.WEST;
        return null;
    }

    public Point[] getAllNeighbours() {
        return new Point[] { this.north(), this.east(), this.west(), this.south() };
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Returns the number of squares two points are away from each other on x-axis
     * Subtracts the argument point from calling points x value
     * @param that
     * @return an Integer
     */
    public int xDiff(Point that) {
        return this.x - that.getX();
    }

    public int yDiff(Point that) {
        return this.y - that.getY();
    }

    public double manhattanDistance(Point that) {
        return Math.abs(this.x - that.getX())  + Math.abs(this.y - that.getY());
    }

    /**
     * Calculates a gravity like distance between points. Another metric of measuring distance between points.
     * @param that This other point
     * @return a positive double. MAX_VALUE if the argument point is equal to this point
     */
    public double gravityDistance(Point that, double G) {
        if(this.equals(that)) return Double.MAX_VALUE;
        return G / Math.sqrt(Math.pow(xDiff(that), 2) + Math.pow(yDiff(that), 2));
    }

    public double gravityDistance(Point that) {
        return this.gravityDistance(that, 10.0);
    }

    @Override
    public String toString() {
        return "X: " + this.x + ", Y: " + this.getY();
    }

    public static LinkedList<Point> pointsBetween(Point a, Point b) {
        assert(!a.equals(b));
        assert(a.getX() == b.getX() || a.getY() == b.getY());

        LinkedList<Point> innerPoints = new LinkedList<Point>();

        if(a.getX() != b.getX()) {
            if (b.getX() < a.getX()) {
                Point temp = b;
                b = a;
                a = temp;
            }
            for (int i = a.getX() + 1; i < b.getX(); i++) {
                innerPoints.add(new Point(i, a.getY()));
            }
        } else {
            if (b.getY() < a.getY()) {
                Point temp = b;
                b = a;
                a = temp;
            }
            for (int i = a.getY() + 1; i < b.getY(); i++) {
                innerPoints.add(new Point(a.getX(), i));
            }
        }
        return innerPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
