import org.junit.Test;
import static org.junit.Assert.*;

public class IntersectionTest {

    @Test
    public void testFindIntersection_ParallelLines() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(0, 0), new Intersection.Point(1, 1));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(1, 0), new Intersection.Point(2, 1));
        
        // Parallel lines do not intersect, but the method should not crash
        try {
            Intersection.findIntersection(l1, l2);
        } catch (ArithmeticException e) {
            // Expected exception due to division by zero
            return;
        }
        fail("Expected ArithmeticException was not thrown");
    }

    @Test
    public void testFindIntersection_SameLine() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(0, 0), new Intersection.Point(1, 1));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(2, 2), new Intersection.Point(3, 3));
        
        // Same line should intersect at any point on the line
        Intersection.Point intersection = Intersection.findIntersection(l1, l2);
        assertTrue(isPointOnLine(intersection, l1));
        assertTrue(isPointOnLine(intersection, l2));
    }

    @Test
    public void testFindIntersection_IntersectingLines() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(4, 0), new Intersection.Point(6, 10));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(0, 3), new Intersection.Point(10, 7));
        
        Intersection.Point intersection = Intersection.findIntersection(l1, l2);
        assertEquals(5.0, intersection.x, 0.0001);
        assertEquals(5.0, intersection.y, 0.0001);
    }

    @Test
    public void testFindIntersection_VerticalAndHorizontalLines() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(2, 0), new Intersection.Point(2, 10));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(0, 5), new Intersection.Point(10, 5));
        
        Intersection.Point intersection = Intersection.findIntersection(l1, l2);
        assertEquals(2.0, intersection.x, 0.0001);
        assertEquals(5.0, intersection.y, 0.0001);
    }

    @Test
    public void testFindIntersection_NonIntersectingLines() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(0, 0), new Intersection.Point(1, 1));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(2, 0), new Intersection.Point(3, 1));
        
        // Non-intersecting lines should not intersect
        Intersection.Point intersection = Intersection.findIntersection(l1, l2);
        assertFalse(isPointOnLine(intersection, l1));
        assertFalse(isPointOnLine(intersection, l2));
    }

    private boolean isPointOnLine(Intersection.Point p, Intersection.Line l) {
        double minX = Math.min(l.s.x, l.e.x);
        double maxX = Math.max(l.s.x, l.e.x);
        double minY = Math.min(l.s.y, l.e.y);
        double maxY = Math.max(l.s.y, l.e.y);
        
        return p.x >= minX && p.x <= maxX && p.y >= minY && p.y <= maxY;
    }
}
