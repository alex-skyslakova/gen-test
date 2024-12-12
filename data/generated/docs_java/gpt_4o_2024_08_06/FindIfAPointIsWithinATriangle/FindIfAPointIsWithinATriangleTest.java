import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FindTriangleTest {

    @Test
    void testPointInsideTriangle() {
        FindTriangle.Point p1 = new FindTriangle.Point(0, 0);
        FindTriangle.Point p2 = new FindTriangle.Point(5, 0);
        FindTriangle.Point p3 = new FindTriangle.Point(0, 5);
        FindTriangle.Triangle triangle = new FindTriangle.Triangle(p1, p2, p3);

        FindTriangle.Point insidePoint = new FindTriangle.Point(1, 1);
        assertTrue(triangle.within(insidePoint), "Point should be inside the triangle");
    }

    @Test
    void testPointOutsideTriangle() {
        FindTriangle.Point p1 = new FindTriangle.Point(0, 0);
        FindTriangle.Point p2 = new FindTriangle.Point(5, 0);
        FindTriangle.Point p3 = new FindTriangle.Point(0, 5);
        FindTriangle.Triangle triangle = new FindTriangle.Triangle(p1, p2, p3);

        FindTriangle.Point outsidePoint = new FindTriangle.Point(6, 6);
        assertFalse(triangle.within(outsidePoint), "Point should be outside the triangle");
    }

    @Test
    void testPointOnEdgeOfTriangle() {
        FindTriangle.Point p1 = new FindTriangle.Point(0, 0);
        FindTriangle.Point p2 = new FindTriangle.Point(5, 0);
        FindTriangle.Point p3 = new FindTriangle.Point(0, 5);
        FindTriangle.Triangle triangle = new FindTriangle.Triangle(p1, p2, p3);

        FindTriangle.Point edgePoint = new FindTriangle.Point(2.5, 0);
        assertTrue(triangle.within(edgePoint), "Point should be on the edge of the triangle");
    }

    @Test
    void testPointOnVertexOfTriangle() {
        FindTriangle.Point p1 = new FindTriangle.Point(0, 0);
        FindTriangle.Point p2 = new FindTriangle.Point(5, 0);
        FindTriangle.Point p3 = new FindTriangle.Point(0, 5);
        FindTriangle.Triangle triangle = new FindTriangle.Triangle(p1, p2, p3);

        assertTrue(triangle.within(p1), "Point should be on the vertex of the triangle");
        assertTrue(triangle.within(p2), "Point should be on the vertex of the triangle");
        assertTrue(triangle.within(p3), "Point should be on the vertex of the triangle");
    }

    @Test
    void testPointNearEdgeWithinEpsilon() {
        FindTriangle.Point p1 = new FindTriangle.Point(0, 0);
        FindTriangle.Point p2 = new FindTriangle.Point(5, 0);
        FindTriangle.Point p3 = new FindTriangle.Point(0, 5);
        FindTriangle.Triangle triangle = new FindTriangle.Triangle(p1, p2, p3);

        FindTriangle.Point nearEdgePoint = new FindTriangle.Point(2.5, 0.0005);
        assertTrue(triangle.within(nearEdgePoint), "Point should be near the edge within epsilon");
    }

    @Test
    void testDegenerateTriangle() {
        FindTriangle.Point p1 = new FindTriangle.Point(0, 0);
        FindTriangle.Point p2 = new FindTriangle.Point(0, 0);
        FindTriangle.Point p3 = new FindTriangle.Point(0, 0);
        FindTriangle.Triangle triangle = new FindTriangle.Triangle(p1, p2, p3);

        FindTriangle.Point point = new FindTriangle.Point(0, 0);
        assertTrue(triangle.within(point), "Point should be within the degenerate triangle");
    }
}
