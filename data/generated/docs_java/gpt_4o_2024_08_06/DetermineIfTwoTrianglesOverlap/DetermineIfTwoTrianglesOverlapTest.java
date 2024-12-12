import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleOverlapTest {

    @Test
    void testOverlapCase1() {
        TriangleOverlap.Triangle t1 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(0.0, 0.0),
                new TriangleOverlap.Pair(5.0, 0.0),
                new TriangleOverlap.Pair(0.0, 5.0)
        );
        TriangleOverlap.Triangle t2 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(0.0, 0.0),
                new TriangleOverlap.Pair(5.0, 0.0),
                new TriangleOverlap.Pair(0.0, 6.0)
        );
        assertTrue(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    void testOverlapCase2() {
        TriangleOverlap.Triangle t1 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(0.0, 0.0),
                new TriangleOverlap.Pair(0.0, 5.0),
                new TriangleOverlap.Pair(5.0, 0.0)
        );
        TriangleOverlap.Triangle t2 = t1;
        assertTrue(TriangleOverlap.triTri2D(t1, t2, 0.0, true));
    }

    @Test
    void testNoOverlapCase1() {
        TriangleOverlap.Triangle t1 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(0.0, 0.0),
                new TriangleOverlap.Pair(5.0, 0.0),
                new TriangleOverlap.Pair(0.0, 5.0)
        );
        TriangleOverlap.Triangle t2 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(-10.0, 0.0),
                new TriangleOverlap.Pair(-5.0, 0.0),
                new TriangleOverlap.Pair(-1.0, 6.0)
        );
        assertFalse(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    void testOverlapCase3() {
        TriangleOverlap.Triangle t1 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(0.0, 0.0),
                new TriangleOverlap.Pair(5.0, 0.0),
                new TriangleOverlap.Pair(2.5, 5.0)
        );
        TriangleOverlap.Triangle t2 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(0.0, 4.0),
                new TriangleOverlap.Pair(2.5, -1.0),
                new TriangleOverlap.Pair(5.0, 4.0)
        );
        assertTrue(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    void testNoOverlapCase2() {
        TriangleOverlap.Triangle t1 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(0.0, 0.0),
                new TriangleOverlap.Pair(1.0, 1.0),
                new TriangleOverlap.Pair(0.0, 2.0)
        );
        TriangleOverlap.Triangle t2 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(2.0, 1.0),
                new TriangleOverlap.Pair(3.0, 0.0),
                new TriangleOverlap.Pair(3.0, 2.0)
        );
        assertFalse(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    void testNoOverlapCase3() {
        TriangleOverlap.Triangle t1 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(0.0, 0.0),
                new TriangleOverlap.Pair(1.0, 1.0),
                new TriangleOverlap.Pair(0.0, 2.0)
        );
        TriangleOverlap.Triangle t2 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(2.0, 1.0),
                new TriangleOverlap.Pair(3.0, -2.0),
                new TriangleOverlap.Pair(3.0, 4.0)
        );
        assertFalse(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    void testSingleCornerContactOverlap() {
        TriangleOverlap.Triangle t1 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(0.0, 0.0),
                new TriangleOverlap.Pair(1.0, 0.0),
                new TriangleOverlap.Pair(0.0, 1.0)
        );
        TriangleOverlap.Triangle t2 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(1.0, 0.0),
                new TriangleOverlap.Pair(2.0, 0.0),
                new TriangleOverlap.Pair(1.0, 1.0)
        );
        assertTrue(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    void testSingleCornerContactNoOverlap() {
        TriangleOverlap.Triangle t1 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(0.0, 0.0),
                new TriangleOverlap.Pair(1.0, 0.0),
                new TriangleOverlap.Pair(0.0, 1.0)
        );
        TriangleOverlap.Triangle t2 = new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(1.0, 0.0),
                new TriangleOverlap.Pair(2.0, 0.0),
                new TriangleOverlap.Pair(1.0, 1.0)
        );
        assertFalse(TriangleOverlap.triTri2D(t1, t2, 0.0, false, false));
    }
}
