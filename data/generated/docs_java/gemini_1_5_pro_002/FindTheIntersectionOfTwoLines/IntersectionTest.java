import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntersectionTest {

    private static final double DELTA = 0.0001; // Tolerance for double comparisons

    @Test
    void testFindIntersection1() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(4, 0), new Intersection.Point(6, 10));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(0, 3), new Intersection.Point(10, 7));
        Intersection.Point intersection = Intersection.findIntersection(l1, l2);
        assertEquals(5.0, intersection.x, DELTA);
        assertEquals(5.0, intersection.y, DELTA);
    }

    @Test
    void testFindIntersection2() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(0, 0), new Intersection.Point(1, 1));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(1, 2), new Intersection.Point(4, 5));
        Intersection.Point intersection = Intersection.findIntersection(l1, l2);
        assertEquals(-1.0, intersection.x, DELTA);
        assertEquals(-1.0, intersection.y, DELTA);

    }
    @Test
    void testParallelLines(){
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(0, 0), new Intersection.Point(1, 1));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(1, 1), new Intersection.Point(2, 2));

        assertThrows(ArithmeticException.class, () ->  Intersection.findIntersection(l1,l2));
    }



    @Test
    void testVerticalLine1() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(2, 0), new Intersection.Point(2, 5));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(0, 2), new Intersection.Point(5, 2));
        Intersection.Point intersection = Intersection.findIntersection(l1, l2);

        assertEquals(2.0, intersection.x, DELTA);
        assertEquals(2.0, intersection.y, DELTA);
    }
    @Test
    void testVerticalLine2() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(2, 0), new Intersection.Point(2, 5));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(0, 3), new Intersection.Point(10, 7));
        Intersection.Point intersection = Intersection.findIntersection(l1, l2);
        assertEquals(2.0, intersection.x, DELTA);
        assertEquals(3.8, intersection.y, DELTA);
    }



}
