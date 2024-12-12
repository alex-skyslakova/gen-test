import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleOverlapTest {

    @Test
    public void testTrianglesOverlap() {
        Triangle t1 = new Triangle(new Pair(0.0, 0.0), new Pair(5.0, 0.0), new Pair(0.0, 5.0));
        Triangle t2 = new Triangle(new Pair(0.0, 0.0), new Pair(5.0, 0.0), new Pair(0.0, 6.0));
        assertTrue(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    public void testTrianglesIdentical() {
        Triangle t1 = new Triangle(new Pair(0.0, 0.0), new Pair(0.0, 5.0), new Pair(5.0, 0.0));
        Triangle t2 = new Triangle(new Pair(0.0, 0.0), new Pair(0.0, 5.0), new Pair(5.0, 0.0));
        assertTrue(TriangleOverlap.triTri2D(t1, t2, 0.0, true));
    }

    @Test
    public void testTrianglesNonOverlap() {
        Triangle t1 = new Triangle(new Pair(0.0, 0.0), new Pair(5.0, 0.0), new Pair(0.0, 5.0));
        Triangle t2 = new Triangle(new Pair(-10.0, 0.0), new Pair(-5.0, 0.0), new Pair(-1.0, 6.0));
        assertFalse(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    public void testTrianglesOverlapComplex() {
        Triangle t1 = new Triangle(new Pair(0.0, 0.0), new Pair(5.0, 0.0), new Pair(2.5, 5.0));
        Triangle t2 = new Triangle(new Pair(0.0, 4.0), new Pair(2.5, -1.0), new Pair(5.0, 4.0));
        assertTrue(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    public void testTrianglesNonOverlapComplex() {
        Triangle t1 = new Triangle(new Pair(0.0, 0.0), new Pair(1.0, 1.0), new Pair(0.0, 2.0));
        Triangle t2 = new Triangle(new Pair(2.0, 1.0), new Pair(3.0, 0.0), new Pair(3.0, 2.0));
        assertFalse(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    public void testTrianglesOverlapBoundary() {
        Triangle t1 = new Triangle(new Pair(0.0, 0.0), new Pair(1.0, 1.0), new Pair(0.0, 2.0));
        Triangle t2 = new Triangle(new Pair(2.0, 1.0), new Pair(3.0, -2.0), new Pair(3.0, 4.0));
        assertTrue(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    public void testTrianglesSingleCornerContact() {
        Triangle t1 = new Triangle(new Pair(0.0, 0.0), new Pair(1.0, 0.0), new Pair(0.0, 1.0));
        Triangle t2 = new Triangle(new Pair(1.0, 0.0), new Pair(2.0, 0.0), new Pair(1.0, 1.0));
        assertTrue(TriangleOverlap.triTri2D(t1, t2));
    }

    @Test
    public void testTrianglesSingleCornerContactNoBoundary() {
        Triangle t1 = new Triangle(new Pair(0.0, 0.0), new Pair(1.0, 0.0), new Pair(0.0, 1.0));
        Triangle t2 = new Triangle(new Pair(1.0, 0.0), new Pair(2.0, 0.0), new Pair(1.0, 1.0));
        assertFalse(TriangleOverlap.triTri2D(t1, t2, 0.0, false, false));
    }
}
