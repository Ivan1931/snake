/**
 * Created by jonah-hooper on 2014/08/17.
 */
public enum Direction {
    NORTH, SOUTH, WEST, EAST;

    public Direction oppositDirection() {
        if (this == NORTH) return SOUTH;
        if (this == SOUTH) return NORTH;
        if (this == EAST) return WEST;
        return EAST;
    }

    public Direction[] otherDirections() {
        switch (this) {
            case NORTH: return new Direction[] { SOUTH, WEST, EAST };
            case SOUTH: return new Direction[] { WEST, EAST, NORTH };
            case EAST: return new Direction[] { WEST, NORTH, SOUTH };
            default: return new Direction[] { NORTH, SOUTH, EAST };
        }
    }

    public static int asInt(Direction d) {
        switch (d) {
            case NORTH: return 0;
            case SOUTH: return 1;
            case WEST: return 2;
            default: return 3;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case NORTH: return "north";
            case SOUTH: return "south";
            case WEST: return "west";
            default: EAST: return "east";
        }
    }
}
