import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TriangleOverlapTest {

    @Test
    fun testTrianglesOverlap() {
        val t1 = Triangle(0.0 to 0.0, 5.0 to 0.0, 0.0 to 5.0)
        val t2 = Triangle(0.0 to 0.0, 5.0 to 0.0, 0.0 to 6.0)
        assertTrue(triTri2D(t1, t2))
    }

    @Test
    fun testTrianglesIdentical() {
        val t1 = Triangle(0.0 to 0.0, 0.0 to 5.0, 5.0 to 0.0)
        val t2 = Triangle(0.0 to 0.0, 0.0 to 5.0, 5.0 to 0.0)
        assertTrue(triTri2D(t1, t2, 0.0, true))
    }

    @Test
    fun testTrianglesNonOverlap() {
        val t1 = Triangle(0.0 to 0.0, 5.0 to 0.0, 0.0 to 5.0)
        val t2 = Triangle(-10.0 to 0.0, -5.0 to 0.0, -1.0 to 6.0)
        assertFalse(triTri2D(t1, t2))
    }

    @Test
    fun testTrianglesOverlapWithDifferentPoints() {
        val t1 = Triangle(0.0 to 0.0, 5.0 to 0.0, 2.5 to 5.0)
        val t2 = Triangle(0.0 to 4.0, 2.5 to -1.0, 5.0 to 4.0)
        assertTrue(triTri2D(t1, t2))
    }

    @Test
    fun testTrianglesNonOverlapWithDifferentPoints() {
        val t1 = Triangle(0.0 to 0.0, 1.0 to 1.0, 0.0 to 2.0)
        val t2 = Triangle(2.0 to 1.0, 3.0 to 0.0, 3.0 to 2.0)
        assertFalse(triTri2D(t1, t2))
    }

    @Test
    fun testTrianglesOverlapWithBoundaryPoints() {
        val t1 = Triangle(0.0 to 0.0, 1.0 to 1.0, 0.0 to 2.0)
        val t2 = Triangle(2.0 to 1.0, 3.0 to -2.0, 3.0 to 4.0)
        assertTrue(triTri2D(t1, t2))
    }

    @Test
    fun testTrianglesOverlapWithSingleCornerContact() {
        val t1 = Triangle(0.0 to 0.0, 1.0 to 0.0, 0.0 to 1.0)
        val t2 = Triangle(1.0 to 0.0, 2.0 to 0.0, 1.0 to 1.1)
        assertTrue(triTri2D(t1, t2))
    }

    @Test
    fun testTrianglesNonOverlapWithSingleCornerContact() {
        val t1 = Triangle(0.0 to 0.0, 1.0 to 0.0, 0.0 to 1.0)
        val t2 = Triangle(1.0 to 0.0, 2.0 to 0.0, 1.0 to 1.1)
        assertFalse(triTri2D(t1, t2, 0.0, false, false))
    }
}
