/**
 * Created by jonah-hooper on 2014/09/11.
 */
public class Tuple implements Comparable<Tuple> {
    public Point point;
    public float priority;

    public Tuple(Point point, float priority) {
        this.point = point;
        this.priority = priority;
    }

    @Override
    public int compareTo(Tuple that) {
        if(this.priority > that.priority) return 1;
        if(this.priority < that.priority) return -1;
        return 0;
    }
}
