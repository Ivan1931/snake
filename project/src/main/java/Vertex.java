import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by jonah-hooper on 2014/08/11.
 */
public class Vertex<T> {
    private T key;
    private LinkedList<Vertex<T>> edges;

    public Vertex(T key) {
        this.key = key;
    }

    public void addEdge(Vertex<T> vertex) {
        edges.add(vertex);
    }

    public T getKey() {
        return key;
    }

    public void unlink(Vertex<T> vertex) {
        edges.remove(vertex);
    }

    public void unlink(T pkey) {
        Iterator<Vertex<T>> iterator = edges.iterator();
        while(iterator.hasNext()) {
            Vertex vertex = iterator.next();
            if (vertex.getKey().equals(pkey)) {
                iterator.remove();
            }
        }
    }

}
