import java.util.LinkedList;

/**
 * Created by jonah-hooper on 2014/07/29.
 */
public class Snake {
    private LinkedList<Point> body;
    private boolean alive;
    private int kills;
    private int length;

    public Snake(String statusString) {
        String[] status = breakUpStatusString(statusString);
        assert(!statusString.isEmpty());

        this.alive = parseLifeStatus(status);
        this.kills = parseKills(status);
        this.length = parseLength(status);
        this.body = parseBody(status);
    }

    private String[] breakUpStatusString(String statusString) {
        return statusString.split(" ");
    }

    private boolean parseLifeStatus(String[] status) {
        final int LIFE_INDEX = 0;
        return status[LIFE_INDEX].equals("alive");
    }

    private int parseLength(String[] status) {
        final int LENGTH_INDEX = 1;
        return Integer.parseInt(status[LENGTH_INDEX]);
    }

    private int parseKills(String[] status) {
        final int KILLS_INDEX = 2;
        return Integer.parseInt(status[KILLS_INDEX]);
    }

    private LinkedList<Point> parseBody(String[] status) {
        final int VERTEX_LISTING_OFFSET_INDEX = 3;

        LinkedList<Point> result = new LinkedList<Point>();

        Point[] turningPoints = new Point[status.length - VERTEX_LISTING_OFFSET_INDEX];
        for (int i = VERTEX_LISTING_OFFSET_INDEX; i < status.length; i++) {
            turningPoints[i - VERTEX_LISTING_OFFSET_INDEX] = new Point(status[i]);
        }

        for (int i = 0; i < turningPoints.length - 1; i++) {
            LinkedList<Point> innerPoints = Point.pointsBetween(turningPoints[i], turningPoints[i + 1]);
            result.add(turningPoints[i]);
            for(Point point : innerPoints) {
                result.addLast(point);
            }
        }

        result.add(turningPoints[turningPoints.length - 1]); // Add last element since it has not been added by the last element of the method above

        return result;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public int getKills() {
        return this.kills;
    }

    public int getLength() {
        return this.length;
    }

    public LinkedList<Point> getBody() {
        return this.body;
    }
}
