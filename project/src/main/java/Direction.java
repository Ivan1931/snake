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

    public static int asInt(Direction d) {
        switch (d) {
            case NORTH: return 0;
            case SOUTH: return 1;
            case WEST: return 2;
            default: return 3;
        }
    }

}
