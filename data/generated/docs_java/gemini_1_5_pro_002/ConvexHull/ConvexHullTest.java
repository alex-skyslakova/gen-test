import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ConvexHullTest {

    private static ConvexHull.Point p(int x, int y) {
        return new ConvexHull.Point(x, y);
    }

    @Test
    void testEmptyInput() {
        List<ConvexHull.Point> points = new ArrayList<>();
        List<ConvexHull.Point> hull = ConvexHull.convexHull(points);
        assertTrue(hull.isEmpty());
    }

    @Test
    void testSinglePoint() {
        List<ConvexHull.Point> points = Arrays.asList(p(1, 1));
        List<ConvexHull.Point> hull = ConvexHull.convexHull(points);
        assertEquals(points, hull);
    }

    @Test
    void testTwoPoints() {
        List<ConvexHull.Point> points = Arrays.asList(p(1, 1), p(2, 2));
        List<ConvexHull.Point> hull = ConvexHull.convexHull(points);
        assertEquals(points, hull);
    }

    @Test
    void testThreePointsCollinear() {
        List<ConvexHull.Point> points = Arrays.asList(p(1, 1), p(2, 2), p(3, 3));
        List<ConvexHull.Point> hull = ConvexHull.convexHull(points);
        assertEquals(Arrays.asList(p(1, 1), p(3, 3)), hull);

    }

    @Test
    void testThreePointsNonCollinear() {
        List<ConvexHull.Point> points = Arrays.asList(p(1, 1), p(2, 3), p(3, 2));
        List<ConvexHull.Point> hull = ConvexHull.convexHull(points);
        assertEquals(points, hull);

    }


    @Test
    void testExampleCase() {
        List<ConvexHull.Point> points = Arrays.asList(p(16, 3), p(12, 17), p(0, 6), p(-4, -6), p(16, 6),
                p(16, -7), p(16, -3), p(17, -4), p(5, 19), p(19, -8),
                p(3, 16), p(12, 13), p(3, -4), p(17, 5), p(-3, 15),
                p(-3, -9), p(0, 11), p(-9, -3), p(-4, -2), p(12, 10));

        List<ConvexHull.Point> expectedHull = Arrays.asList(p(-9, -3), p(-3, -9), p(19, -8), p(17, 5), p(12, 17), p(5, 19), p(-3, 15));
        List<ConvexHull.Point> hull = ConvexHull.convexHull(points);

        assertEquals(expectedHull.size(), hull.size());
        assertTrue(hull.containsAll(expectedHull));

    }


    @Test
    void testSquare() {
        List<ConvexHull.Point> points = Arrays.asList(p(0, 0), p(0, 1), p(1, 0), p(1, 1));
        List<ConvexHull.Point> expectedHull = Arrays.asList(p(0, 0), p(1, 0), p(1, 1), p(0, 1));
        List<ConvexHull.Point> hull = ConvexHull.convexHull(points);

        assertEquals(expectedHull.size(), hull.size());
        assertTrue(hull.containsAll(expectedHull));

    }


}
