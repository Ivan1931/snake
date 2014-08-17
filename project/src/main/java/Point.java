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
        String[] points = commaSeperatedString.split(",");
        this.x = Integer.parseInt(points[0]);
        this.y = Integer.parseInt(points[1]);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
}
