import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CirclesTest {

    @Test
    public void testValidCircles() {
        Circles.Point p1 = new Circles.Point(0.1234, 0.9876);
        Circles.Point p2 = new Circles.Point(0.8765, 0.2345);
        double radius = 2.0;

        Circles.Point[] centers = Circles.findCircles(p1, p2, radius);
        assertNotNull(centers);
        assertEquals(2, centers.length);
        assertNotEquals(centers[0], centers[1]);
    }

    @Test
    public void testCoincidentPointsWithRadius() {
        Circles.Point p1 = new Circles.Point(0.0000, 2.0000);
        Circles.Point p2 = new Circles.Point(0.0000, 0.0000);
        double radius = 1.0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Circles.findCircles(p1, p2, radius);
        });
        assertEquals("an infinite number of circles can be drawn", exception.getMessage());
    }

    @Test
    public void testCoincidentPointsWithoutRadius() {
        Circles.Point p1 = new Circles.Point(0.1234, 0.9876);
        Circles.Point p2 = new Circles.Point(0.1234, 0.9876);
        double radius = 2.0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Circles.findCircles(p1, p2, radius);
        });
        assertEquals("an infinite number of circles can be drawn", exception.getMessage());
    }

    @Test
    public void testZeroRadiusWithDifferentPoints() {
        Circles.Point p1 = new Circles.Point(0.1234, 0.9876);
        Circles.Point p2 = new Circles.Point(0.8765, 0.2345);
        double radius = 0.0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Circles.findCircles(p1, p2, radius);
        });
        assertEquals("no circles can ever be drawn", exception.getMessage());
    }

    @Test
    public void testZeroRadiusWithCoincidentPoints() {
        Circles.Point p1 = new Circles.Point(0.1234, 0.9876);
        Circles.Point p2 = new Circles.Point(0.1234, 0.9876);
        double radius = 0.0;

        Circles.Point[] centers = Circles.findCircles(p1, p2, radius);
        assertNotNull(centers);
        assertEquals(2, centers.length);
        assertEquals(centers[0], centers[1]);
    }

    @Test
    public void testPointsFormingDiameter() {
        Circles.Point p1 = new Circles.Point(0.1234, 0.9876);
        Circles.Point p2 = new Circles.Point(0.8765, 0.2345);
        double radius = 0.5;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Circles.findCircles(p1, p2, radius);
        });
        assertEquals("the points are too far apart to draw a circle", exception.getMessage());
    }

    @Test
    public void testNegativeRadius() {
        Circles.Point p1 = new Circles.Point(0.1234, 0.9876);
        Circles.Point p2 = new Circles.Point(0.8765, 0.2345);
        double radius = -1.0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Circles.findCircles(p1, p2, radius);
        });
        assertEquals("the radius can't be negative", exception.getMessage());
    }
}
