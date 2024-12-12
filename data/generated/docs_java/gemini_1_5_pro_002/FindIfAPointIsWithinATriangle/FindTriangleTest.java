import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FindTriangleTest {

    @Test
    void within_pointInsideTriangle_returnsTrue() {
        FindTriangle.Point p1 = new FindTriangle.Point(1.5, 2.4);
        FindTriangle.Point p2 = new FindTriangle.Point(5.1, -3.1);
        FindTriangle.Point p3 = new FindTriangle.Point(-3.8, 1.2);
        FindTriangle.Triangle tri = new FindTriangle.Triangle(p1, p2, p3);
        FindTriangle.Point p = new FindTriangle.Point(0, 0);

        assertTrue(tri.within(p));
    }

    @Test
    void within_pointOutsideTriangle_returnsFalse() {
        FindTriangle.Point p1 = new FindTriangle.Point(1.5, 2.4);
        FindTriangle.Point p2 = new FindTriangle.Point(5.1, -3.1);
        FindTriangle.Point p3 = new FindTriangle.Point(-3.8, 1.2);
        FindTriangle.Triangle tri = new FindTriangle.Triangle(p1, p2, p3);
        FindTriangle.Point p = new FindTriangle.Point(10, 10);

        assertFalse(tri.within(p));
    }

    @Test
    void within_pointOnTriangleEdge_returnsTrue() {
        FindTriangle.Point p1 = new FindTriangle.Point(0, 0);
        FindTriangle.Point p2 = new FindTriangle.Point(2, 0);
        FindTriangle.Point p3 = new FindTriangle.Point(1, 2);
        FindTriangle.Triangle tri = new FindTriangle.Triangle(p1, p2, p3);
        FindTriangle.Point p = new FindTriangle.Point(1, 0);

        assertTrue(tri.within(p));
    }

    @Test
    void within_pointOnTriangleVertex_returnsTrue() {
        FindTriangle.Point p1 = new FindTriangle.Point(0, 0);
        FindTriangle.Point p2 = new FindTriangle.Point(2, 0);
        FindTriangle.Point p3 = new FindTriangle.Point(1, 2);
        FindTriangle.Triangle tri = new FindTriangle.Triangle(p1, p2, p3);
        FindTriangle.Point p = new FindTriangle.Point(0, 0);

        assertTrue(tri.within(p));

    }
    @Test
    void within_pointSlightlyOutsideEdge_returnsFalse() {
        FindTriangle.Point p1 = new FindTriangle.Point(0, 0);
        FindTriangle.Point p2 = new FindTriangle.Point(2, 0);
        FindTriangle.Point p3 = new FindTriangle.Point(1, 2);
        FindTriangle.Triangle tri = new FindTriangle.Triangle(p1, p2, p3);
        FindTriangle.Point p = new FindTriangle.Point(1, -0.0001);


        assertFalse(tri.within(p));
    }

    @Test
    void within_pointSlightlyInsideEdge_returnsTrue() {
        FindTriangle.Point p1 = new FindTriangle.Point(0, 0);
        FindTriangle.Point p2 = new FindTriangle.Point(2, 0);
        FindTriangle.Point p3 = new FindTriangle.Point(1, 2);
        FindTriangle.Triangle tri = new FindTriangle.Triangle(p1, p2, p3);
        FindTriangle.Point p = new FindTriangle.Point(1, 0.0001);


        assertTrue(tri.within(p));
    }



}
