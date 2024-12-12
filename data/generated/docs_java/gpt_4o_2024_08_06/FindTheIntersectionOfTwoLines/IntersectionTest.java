import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntersectionTest {

    @Test
    public void testIntersectionOfGivenLines() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(4, 0), new Intersection.Point(6, 10));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(0, 3), new Intersection.Point(10, 7));
        Intersection.Point intersection = Intersection.findIntersection(l1, l2);
        assertEquals(5.0, intersection.x, 0.0001);
        assertEquals(5.0, intersection.y, 0.0001);
    }

    @Test
    public void testIntersectionOfParallelLines() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(0, 0), new Intersection.Point(1, 1));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(1, 2), new Intersection.Point(2, 3));
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Intersection.findIntersection(l1, l2);
        });
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    public void testIntersectionOfCoincidentLines() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(0, 0), new Intersection.Point(1, 1));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(0, 0), new Intersection.Point(1, 1));
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Intersection.findIntersection(l1, l2);
        });
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    public void testIntersectionOfLinesWithNegativeCoordinates() {
        Intersection.Line l1 = new Intersection.Line(new Intersection.Point(-1, -1), new Intersection.Point(1, 1));
        Intersection.Line l2 = new Intersection.Line(new Intersection.Point(-1, 1), new Intersection.Point(1, -1));
        Intersection.Point intersection = Intersection.findIntersection(l1, l2);
        assertEquals(0.0, intersection.x, 0.0001);
        assertEquals(0.0, intersection.y, 0.0001);
    }
}
