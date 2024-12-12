import org.junit.Test;
import static org.junit.Assert.*;
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
            return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
        }

        @Override
        public String toString() {
            return String.format("(%.4f, %.4f)", x, y);
        }
    }

    private static Point[] findCircles(Point p1, Point p2, double r) {
        if (r < 0.0) throw new IllegalArgumentException("the radius can't be negative");
        if (r == 0.0 && !Objects.equals(p1, p2)) throw new IllegalArgumentException("no circles can ever be drawn");
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
    public void testValidCircles() {
        Point p1 = new Point(0.1234, 0.9876);
        Point p2 = new Point(0.8765, 0.2345);
        double r = 2.0;
        Point[] circles = findCircles(p1, p2, r);
        assertNotNull(circles);
        assertEquals(2, circles.length);
    }

    @Test
    public void testDiameterCase() {
        Point p1 = new Point(0.0, 2.0);
        Point p2 = new Point(0.0, 0.0);
        double r = 1.0;
        Point[] circles = findCircles(p1, p2, r);
        assertNotNull(circles);
        assertEquals(2, circles.length);
        assertEquals(circles[0], circles[1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCoincidentPointsWithRadius() {
        Point p1 = new Point(0.1234, 0.9876);
        Point p2 = new Point(0.1234, 0.9876);
        double r = 2.0;
        findCircles(p1, p2, r);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPointsTooFarApart() {
        Point p1 = new Point(0.1234, 0.9876);
        Point p2 = new Point(0.8765, 0.2345);
        double r = 0.5;
        findCircles(p1, p2, r);
    }

    @Test
    public void testCoincidentPointsZeroRadius() {
        Point p1 = new Point(0.1234, 0.9876);
        Point p2 = new Point(0.1234, 0.9876);
        double r = 0.0;
        Point[] circles = findCircles(p1, p2, r);
        assertNotNull(circles);
        assertEquals(2, circles.length);
        assertEquals(circles[0], circles[1]);
        assertEquals(p1, circles[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroRadiusDifferentPoints() {
        Point p1 = new Point(0.1234, 0.9876);
        Point p2 = new Point(0.8765, 0.2345);
        double r = 0.0;
        findCircles(p1, p2, r);
    }
}
