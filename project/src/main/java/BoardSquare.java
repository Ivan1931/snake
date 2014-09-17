/**
 * Created by jonah-hooper on 2014/08/27.
 */
public enum BoardSquare {
    ANYTHING, APPLE, SNAKE, EMPTY;

    @Override
    public String toString() {
        switch (this) {
            case APPLE: return "A";
            case SNAKE: return "S";
            case ANYTHING: return "X";
            default: return "0";
        }
    }

}
