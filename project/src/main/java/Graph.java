import java.security.Key;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by jonah-hooper on 2014/08/11.
 */
public class Graph<T> {
    private LinkedList<Vertex<T>> vertexes;

    public Graph() {
        vertexes = new LinkedList<Vertex<T>>();
    }

    public void removeVertex(Vertex<T> vertex) {
        vertexes.remove(vertex);
        for(Vertex<T> iteratorVertex : vertexes) {
            iteratorVertex.unlink(vertex);
        }
    }

    public void removeKey(T key) {
        Iterator<Vertex<T>> iterator = vertexes.iterator();
        while(iterator.hasNext()) {
            Vertex<T> vertex = iterator.next();
            vertex.unlink(key);
            if (vertex.getKey().equals(key)) {
                iterator.remove();
            }
        }
    }
}
