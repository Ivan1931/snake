import java.util.Comparator;

/**
 * Created by jonah-hooper on 2014/08/28.
 */
public class PathComparator implements Comparator<Point[]> {
    @Override
    public int compare(Point[] a, Point[] b) {
        if (a.length > b.length) return 1;
        if (a.length == b.length) return 0;
        return -1;
    }
}
