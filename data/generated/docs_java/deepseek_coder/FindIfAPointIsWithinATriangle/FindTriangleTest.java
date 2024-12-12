import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FindTriangleTest {

    @Test
    public void testPointWithinTriangle() {
        FindTriangle.Point p1 = new FindTriangle.Point(1.5, 2.4);
        FindTriangle.Point p2 = new FindTriangle.Point(5.1, -3.1);
        FindTriangle.Point p3 = new FindTriangle.Point(-3.8, 1.2);
        FindTriangle.Triangle tri = new FindTriangle.Triangle(p1, p2, p3);

        // Point within the triangle
        FindTriangle.Point p = new FindTriangle.Point(0, 0);
        assertTrue(tri.within(p));
    }

    @Test
    public void testPointOutsideTriangle() {
        FindTriangle.Point p1 = new FindTriangle.Point(1.5, 2.4);
        FindTriangle.Point p2 = new FindTriangle.Point(5.1, -3.1);
        FindTriangle.Point p3 = new FindTriangle.Point(-3.8, 1.2);
        FindTriangle.Triangle tri = new FindTriangle.Triangle(p1, p2, p3);

        // Point outside the triangle
        FindTriangle.Point p = new FindTriangle.Point(3, 1);
        assertFalse(tri.within(p));
    }

    @Test
    public void testPointOnTriangleEdge() {
        FindTriangle.Point p1 = new FindTriangle.Point(1.0 / 10, 1.0 / 9);
        FindTriangle.Point p2 = new FindTriangle.Point(100.0 / 8, 100.0 / 3);
        FindTriangle.Point p3 = new FindTriangle.Point(100.0 / 4, 100.0 / 9);
        FindTriangle.Triangle tri = new FindTriangle.Triangle(p1, p2, p3);

        // Point on the edge of the triangle
        FindTriangle.Point pt = new FindTriangle.Point(p1.getX() + (3.0 / 7) * (p2.getX() - p1.getX()), p1.getY() + (3.0 / 7) * (p2.getY() - p1.getY()));
        assertTrue(tri.within(pt));
    }

    @Test
    public void testPointOutsideTriangleBoundingBox() {
        FindTriangle.Point p1 = new FindTriangle.Point(1.0 / 10, 1.0 / 9);
        FindTriangle.Point p2 = new FindTriangle.Point(100.0 / 8, 100.0 / 3);
        FindTriangle.Point p3 = new FindTriangle.Point(-100.0 / 8, 100.0 / 6);
        FindTriangle.Triangle tri = new FindTriangle.Triangle(p1, p2, p3);

        // Point outside the bounding box of the triangle
        FindTriangle.Point pt = new FindTriangle.Point(p1.getX() + (3.0 / 7) * (p2.getX() - p1.getX()), p1.getY() + (3.0 / 7) * (p2.getY() - p1.getY()));
        assertFalse(tri.within(pt));
    }

    @Test
    public void testNullPoint() {
        FindTriangle.Point p1 = new FindTriangle.Point(1.5, 2.4);
        FindTriangle.Point p2 = new FindTriangle.Point(5.1, -3.1);
        FindTriangle.Point p3 = new FindTriangle.Point(-3.8, 1.2);
        FindTriangle.Triangle tri = new FindTriangle.Triangle(p1, p2, p3);

        // Null point should throw NullPointerException
        assertThrows(NullPointerException.class, () -> tri.within(null));
    }

    @Test
    public void testNullTrianglePoints() {
        // Null points in triangle should throw NullPointerException
        assertThrows(NullPointerException.class, () -> new FindTriangle.Triangle(null, new FindTriangle.Point(1, 1), new FindTriangle.Point(2, 2)));
        assertThrows(NullPointerException.class, () -> new FindTriangle.Triangle(new FindTriangle.Point(1, 1), null, new FindTriangle.Point(2, 2)));
        assertThrows(NullPointerException.class, () -> new FindTriangle.Triangle(new FindTriangle.Point(1, 1), new FindTriangle.Point(2, 2), null));
    }
}
