import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleOverlapTest {

    private TriangleOverlap.Triangle createTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        return new TriangleOverlap.Triangle(
                new TriangleOverlap.Pair(x1, y1),
                new TriangleOverlap.Pair(x2, y2),
                new TriangleOverlap.Pair(x3, y3)
        );
    }

    @Test
    void testOverlap1() {
        TriangleOverlap.Triangle t1 = createTriangle(0, 0, 5, 0, 0, 5);
        TriangleOverlap.Triangle t2 = createTriangle(0, 0, 5, 0, 0, 6);
        assertTrue(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    void testOverlap2() {
        TriangleOverlap.Triangle t1 = createTriangle(0, 0, 0, 5, 5, 0);
        TriangleOverlap.Triangle t2 = createTriangle(0, 0, 0, 5, 5, 0);
        assertTrue(TriangleOverlap.triTri2D(t1, t2, 0.0, true));
    }

    @Test
    void testNoOverlap1() {
        TriangleOverlap.Triangle t1 = createTriangle(0, 0, 5, 0, 0, 5);
        TriangleOverlap.Triangle t2 = createTriangle(-10, 0, -5, 0, -1, 6);
        assertFalse(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    void testOverlap3() {
        TriangleOverlap.Triangle t1 = createTriangle(0, 0, 5, 0, 2.5, 5);
        TriangleOverlap.Triangle t2 = createTriangle(0, 4, 2.5, -1, 5, 4);
        assertTrue(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    void testNoOverlap2() {
        TriangleOverlap.Triangle t1 = createTriangle(0, 0, 1, 1, 0, 2);
        TriangleOverlap.Triangle t2 = createTriangle(2, 1, 3, 0, 3, 2);
        assertFalse(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    void testOverlap4() {
        TriangleOverlap.Triangle t1 = createTriangle(0, 0, 1, 1, 0, 2);
        TriangleOverlap.Triangle t2 = createTriangle(2, 1, 3, -2, 3, 4);
        assertTrue(TriangleOverlap.triTri2D(t1, t2));
    }


    @Test
    void testSingleCornerContactOnBoundary() {
        TriangleOverlap.Triangle t1 = createTriangle(0, 0, 1, 0, 0, 1);
        TriangleOverlap.Triangle t2 = createTriangle(1, 0, 2, 0, 1, 1.1);
        assertTrue(TriangleOverlap.triTri2D(t1, t2)); // Overlap if boundary contact counts
    }

    @Test
    void testSingleCornerContactNoBoundary() {
        TriangleOverlap.Triangle t1 = createTriangle(0, 0, 1, 0, 0, 1);
        TriangleOverlap.Triangle t2 = createTriangle(1, 0, 2, 0, 1, 1.1);
        assertFalse(TriangleOverlap.triTri2D(t1, t2, 0.0, false, false)); // No overlap if boundary contact doesn't count
    }



    @Test
    void testWindingException(){
        TriangleOverlap.Triangle t1 = createTriangle(0,0, 0, 5, 5,0);
        TriangleOverlap.Triangle t2 = createTriangle(0,0, 5, 0, 0,5);

        assertThrows(RuntimeException.class, () -> TriangleOverlap.triTri2D(t1, t2));


    }

}
