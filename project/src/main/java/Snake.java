import java.util.LinkedList;

/**
 * Created by jonah-hooper on 2014/07/29.
 */
public class Snake {
    private boolean alive;
    private int snakeNumber;
    private LinkedList<Vertex> vertexes;


    public Snake(String vertexData) {
    }

    public boolean isAlive() {
        return alive;
    }

    public int getSnakeNumber () {
        return snakeNumber;
    }
}
