import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by jonah-hooper on 2014/08/11.
 */
public class Vertex<T> {
    private T key;
    private LinkedList<Vertex<T>> adjacentVertexes;

    public Vertex(T key) {
        adjacentVertexes = new LinkedList<Vertex<T>>();
        this.key = key;
    }

    public void addEdge(Vertex<T> vertex) {
        adjacentVertexes.add(vertex);
    }

    public T getKey() {
        return key;
    }

    public void unlink(Vertex<T> vertex) {
        adjacentVertexes.remove(vertex);
    }

    public void unlink(T pkey) {
        Iterator<Vertex<T>> iterator = adjacentVertexes.iterator();
        while(iterator.hasNext()) {
            Vertex vertex = iterator.next();
            if (vertex.getKey().equals(pkey)) {
                iterator.remove();
            }
        }
    }

    public LinkedList<Vertex<T>> getAdjacentVertexes() {
        return adjacentVertexes;
    }

    public boolean isAdjacent(T key) {
        for(Vertex<T> vertex : adjacentVertexes) {
            if (vertex.getKey().equals(key)) return true;
        }

        return false;
    }

    public boolean isAdjacent(Vertex<T> vertex) {
        return adjacentVertexes.contains(vertex);
    }
}
