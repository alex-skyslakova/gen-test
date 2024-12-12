import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvexHullTest {

    @Test
    public void testConvexHullWithGivenPoints() {
        List<ConvexHull.Point> points = Arrays.asList(
                new ConvexHull.Point(16, 3),
                new ConvexHull.Point(12, 17),
                new ConvexHull.Point(0, 6),
                new ConvexHull.Point(-4, -6),
                new ConvexHull.Point(16, 6),
                new ConvexHull.Point(16, -7),
                new ConvexHull.Point(16, -3),
                new ConvexHull.Point(17, -4),
                new ConvexHull.Point(5, 19),
                new ConvexHull.Point(19, -8),
                new ConvexHull.Point(3, 16),
                new ConvexHull.Point(12, 13),
                new ConvexHull.Point(3, -4),
                new ConvexHull.Point(17, 5),
                new ConvexHull.Point(-3, 15),
                new ConvexHull.Point(-3, -9),
                new ConvexHull.Point(0, 11),
                new ConvexHull.Point(-9, -3),
                new ConvexHull.Point(-4, -2),
                new ConvexHull.Point(12, 10)
        );

        List<ConvexHull.Point> expectedHull = Arrays.asList(
                new ConvexHull.Point(-9, -3),
                new ConvexHull.Point(-3, -9),
                new ConvexHull.Point(19, -8),
                new ConvexHull.Point(17, 5),
                new ConvexHull.Point(12, 17),
                new ConvexHull.Point(5, 19),
                new ConvexHull.Point(-3, 15)
        );

        List<ConvexHull.Point> actualHull = ConvexHull.convexHull(points);
        assertEquals(expectedHull, actualHull);
    }

    @Test
    public void testConvexHullWithNoPoints() {
        List<ConvexHull.Point> points = Arrays.asList();
        List<ConvexHull.Point> expectedHull = Arrays.asList();
        List<ConvexHull.Point> actualHull = ConvexHull.convexHull(points);
        assertEquals(expectedHull, actualHull);
    }

    @Test
    public void testConvexHullWithSinglePoint() {
        List<ConvexHull.Point> points = Arrays.asList(new ConvexHull.Point(1, 1));
        List<ConvexHull.Point> expectedHull = Arrays.asList(new ConvexHull.Point(1, 1));
        List<ConvexHull.Point> actualHull = ConvexHull.convexHull(points);
        assertEquals(expectedHull, actualHull);
    }

    @Test
    public void testConvexHullWithTwoPoints() {
        List<ConvexHull.Point> points = Arrays.asList(
                new ConvexHull.Point(1, 1),
                new ConvexHull.Point(2, 2)
        );
        List<ConvexHull.Point> expectedHull = Arrays.asList(
                new ConvexHull.Point(1, 1),
                new ConvexHull.Point(2, 2)
        );
        List<ConvexHull.Point> actualHull = ConvexHull.convexHull(points);
        assertEquals(expectedHull, actualHull);
    }

    @Test
    public void testConvexHullWithCollinearPoints() {
        List<ConvexHull.Point> points = Arrays.asList(
                new ConvexHull.Point(1, 1),
                new ConvexHull.Point(2, 2),
                new ConvexHull.Point(3, 3)
        );
        List<ConvexHull.Point> expectedHull = Arrays.asList(
                new ConvexHull.Point(1, 1),
                new ConvexHull.Point(3, 3)
        );
        List<ConvexHull.Point> actualHull = ConvexHull.convexHull(points);
        assertEquals(expectedHull, actualHull);
    }
}
