import junit.framework.TestCase;

public class PointTest extends TestCase {

    public void testPointCreation() {
        assertEquals("When created using comma separation the following should be the same",
                new Point("0,0"),
                new Point(0,0));
    }

    public void testPointsBetween() throws Exception {
        Point[] points = new Point[]{ new Point(0,1), new Point(0,2), new Point(0,3) };
        Point[] innerPoints =  Point.pointsBetween(new Point(0,0), new Point(0,4)).toArray(new Point[3]);
        for (int i = 0; i < 3; i++) {
            assertTrue("All points in array must be equal", points[i].equals(innerPoints[i]));
        }

        points = new Point[]{ new Point(1,0), new Point(2,0), new Point(3,0) };
        innerPoints =  Point.pointsBetween(new Point(0,0), new Point(4,0)).toArray(new Point[3]);
        for (int i = 0; i < 3; i++) {
            assertTrue("All points in array must be equal", points[i].equals(innerPoints[i]));
        }
    }

    public void testEquals() throws Exception {
    }
}