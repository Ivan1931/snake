/**
 * Created by jonah-hooper on 2014/08/17.
 */
public enum Direction {
    NORTH, SOUTH, WEST, EAST;

    public static Direction oppositDirection(Direction d) {
        if (d == NORTH) return SOUTH;
        if (d == SOUTH) return NORTH;
        if (d == EAST) return WEST;
        return EAST;
    }

}
