import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jonah-hooper on 2014/07/29.
 */
public class Snake {
    private LinkedList<Point> body;
    private boolean alive;

    public Snake(String[] vertexes, boolean alive) {
        this.alive = alive;
        body = new LinkedList<Point>();
        Point[] turningPoints = new Point[vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            turningPoints[i] = new Point(vertexes[i]);
        }

        for (int i = 0; i < turningPoints.length - 1; i++) {
            LinkedList<Point> innerPoints = Point.pointsBetween(turningPoints[i], turningPoints[i + 1]);
            body.add(turningPoints[i]);
            for(Point point : innerPoints) {
                body.addLast(point);
            }
        }

        body.add(turningPoints[turningPoints.length]); // Add last element since it has not been added by the last element of the method above
    }


    public boolean isAlive() {
        return alive;
    }

    public LinkedList<Point> getBody() {
        return this.body;
    }
}
