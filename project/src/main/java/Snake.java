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

    public Point getHead() {
        return this.body.getFirst();
    }

    public Point getEnd() {
        return this.body.getLast();
    }

    public Point getSecondPoint() {
        if (this.body.size() > 1) {
            return this.body.get(1);
        }

        return null;
    }

    public Point getLastPoint() {
        return this.body.getLast();
    }

    public Direction currentDirection () {
        Point head = this.getHead();
        Point second = this.getSecondPoint();
        if (second != null) {
            if (head.getX() > second.getX())
                return Direction.EAST;
            else if (head.getX() < second.getX())
                return Direction.WEST;
            else if (head.getY() > second.getY())
                return Direction.SOUTH;
            else
                return Direction.NORTH;
        } else {
            return null;
        }
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

    /**
     * Checks whether the snake in its current state is trapped
     * @param board
     * @return
     */
    public boolean isTrapped(Board board) {
        return board.approximateShortestPath(getHead(), getEnd()) == null;
    }

    /**
     * Decides whether a move will result this snake being trapped. A snake is trapped when it cannot reach its tail
     * @param direction Direction to move the snake
     * @param board The board on which to evaluate whether the snake will be trapped
     * @return True if the snake will be trapped
     */
    public boolean moveWouldTrap(Direction direction, Board board) {
        Point nextPoint = this.getHead().pointInDirection(direction);
        return pointsWouldTrap(board, new Point[]{ nextPoint } );
    }

    /**
     * Determines if whether ensuring that, on a board, setting the squares corresponding at the specified points to non-traversable would result in this snake being trapped.
     * @param board The board on which to check check if snake is trapped
     * @param points Points to set to non-empty. Assumes these are all on the board.
     * @return True if the snake is trapped when specified points are non-traversable
     */
    public boolean pointsWouldTrap(Board board, Point[] points) {
        for(Point point : points) assert(Board.isOnBoard(point)); //Ensures that all points are on the board

        return board.approximateShortestPath(this.getHead(), this.getEnd(), points) == null;
    }

    /**
     * This method finds the possible allowed moved that a snake can make. These are moves those that are not in the same direction of the snake and are in the board
     * @return array with the next squares the snake can move too
     */
    public Point[] futureAllowedPoints() {
        LinkedList<Point> points = new LinkedList<Point>();
        Point head = getHead();
        Direction oppositDirection = currentDirection().oppositDirection();
        for(Direction direction : Direction.values()) {
            Point possibility = head.pointInDirection(direction);
            if(Board.isOnBoard(possibility) && direction != oppositDirection) points.add(possibility);
        }
        return points.toArray(new Point[points.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Snake)) return false;

        Snake snake = (Snake) o;

        if (alive != snake.alive) return false;
        if (kills != snake.kills) return false;
        if (length != snake.length) return false;
        if (body != null ? !body.equals(snake.body) : snake.body != null) return false;

        return true;
    }

}

