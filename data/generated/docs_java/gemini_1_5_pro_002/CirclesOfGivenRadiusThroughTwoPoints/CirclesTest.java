import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Objects;

public class CirclesTest {

    private static class Point {
        private final double x, y;

        public Point(Double x, Double y) {
            this.x = x;
            this.y = y;
        }

        public double distanceFrom(Point other) {
            double dx = x - other.x;
            double dy = y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Point point = (Point) other;
            return Math.abs(x - point.x) < 1e-6 && Math.abs(y - point.y) < 1e-6; // Use delta for comparison
        }


    }


    private static Point[] findCircles(Point p1, Point p2, double r) {
         if (r < 0.0) throw new IllegalArgumentException("the radius can't be negative");
        if (r == 0.0 && !p1.equals(p2)) throw new IllegalArgumentException("no circles can ever be drawn");
        if (r == 0.0) return new Point[]{p1, p1};
        if (Objects.equals(p1, p2)) throw new IllegalArgumentException("an infinite number of circles can be drawn");
        double distance = p1.distanceFrom(p2);
        double diameter = 2.0 * r;
        if (distance > diameter) throw new IllegalArgumentException("the points are too far apart to draw a circle");
        Point center = new Point((p1.x + p2.x) / 2.0, (p1.y + p2.y) / 2.0);
        if (distance == diameter) return new Point[]{center, center};
        double mirrorDistance = Math.sqrt(r * r - distance * distance / 4.0);
        double dx = (p2.x - p1.x) * mirrorDistance / distance;
        double dy = (p2.y - p1.y) * mirrorDistance / distance;
        return new Point[]{
            new Point(center.x - dy, center.y + dx),
            new Point(center.x + dy, center.y - dx)
        };
    }



    @Test
    void testDistinctPointsValidRadius() {
        Point p1 = new Point(0.1234, 0.9876);
        Point p2 = new Point(0.8765, 0.2345);
        double r = 2.0;
        Point[] circles = findCircles(p1, p2, r);
        assertNotNull(circles);
        assertEquals(2, circles.length);
    }

    @Test
    void testCoincidentPointsRadiusOne() {
         assertThrows(IllegalArgumentException.class, () -> findCircles(new Point(0.0, 2.0), new Point(0.0, 2.0), 1.0));

    }


    @Test
    void testCoincidentPointsNonZeroRadius() {
        assertThrows(IllegalArgumentException.class, () ->  findCircles(new Point(0.1234, 0.9876), new Point(0.1234, 0.9876), 2.0));
    }



    @Test
    void testPointsTooFarApart() {
       assertThrows(IllegalArgumentException.class, () -> findCircles(new Point(0.1234, 0.9876), new Point(0.8765, 0.2345), 0.5));
    }

    @Test
    void testCoincidentPointsZeroRadius() {
        Point p1 = new Point(0.1234, 0.9876);
        Point[] circles = findCircles(p1, p1, 0.0);
        assertNotNull(circles);
        assertEquals(2, circles.length);
        assertEquals(p1, circles[0]);
        assertEquals(p1, circles[1]);


    }
    @Test
    void testNegativeRadius(){
        assertThrows(IllegalArgumentException.class, () -> findCircles(new Point(0.0, 0.0), new Point(1.0, 1.0), -1.0));
    }

    @Test
    void testZeroRadiusDifferentPoints(){
        assertThrows(IllegalArgumentException.class, () -> findCircles(new Point(0.0, 0.0), new Point(1.0, 1.0), 0.0));

    }

    @Test
    void testDiameterPoints() {
        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(0.0, 2.0);
        double r = 1.0;
        Point[] circles = findCircles(p1, p2, r);
        assertNotNull(circles);
        assertEquals(2, circles.length);
        assertEquals(circles[0], circles[1]); // Expecting identical circles

    }



}
