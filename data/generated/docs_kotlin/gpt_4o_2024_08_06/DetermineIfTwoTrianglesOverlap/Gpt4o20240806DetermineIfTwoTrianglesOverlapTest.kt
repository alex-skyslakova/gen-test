import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TriangleOverlapTest {

    @Test
    fun testOverlapCase1() {
        val t1 = Triangle(0.0 to 0.0, 5.0 to 0.0, 0.0 to 5.0)
        val t2 = Triangle(0.0 to 0.0, 5.0 to 0.0, 0.0 to 6.0)
        assertTrue(triTri2D(t1, t2), "Triangles should overlap")
    }

    @Test
    fun testOverlapCase2() {
        val t1 = Triangle(0.0 to 0.0, 0.0 to 5.0, 5.0 to 0.0)
        val t2 = Triangle(0.0 to 0.0, 0.0 to 5.0, 5.0 to 0.0)
        assertTrue(triTri2D(t1, t2, 0.0, true), "Triangles should overlap (reversed)")
    }

    @Test
    fun testNoOverlapCase1() {
        val t1 = Triangle(0.0 to 0.0, 5.0 to 0.0, 0.0 to 5.0)
        val t2 = Triangle(-10.0 to 0.0, -5.0 to 0.0, -1.0 to 6.0)
        assertFalse(triTri2D(t1, t2), "Triangles should not overlap")
    }

    @Test
    fun testOverlapCase3() {
        val t1 = Triangle(0.0 to 0.0, 5.0 to 0.0, 2.5 to 5.0)
        val t2 = Triangle(0.0 to 4.0, 2.5 to -1.0, 5.0 to 4.0)
        assertTrue(triTri2D(t1, t2), "Triangles should overlap")
    }

    @Test
    fun testNoOverlapCase2() {
        val t1 = Triangle(0.0 to 0.0, 1.0 to 1.0, 0.0 to 2.0)
        val t2 = Triangle(2.0 to 1.0, 3.0 to 0.0, 3.0 to 2.0)
        assertFalse(triTri2D(t1, t2), "Triangles should not overlap")
    }

    @Test
    fun testNoOverlapCase3() {
        val t1 = Triangle(0.0 to 0.0, 1.0 to 1.0, 0.0 to 2.0)
        val t2 = Triangle(2.0 to 1.0, 3.0 to -2.0, 3.0 to 4.0)
        assertFalse(triTri2D(t1, t2), "Triangles should not overlap")
    }

    @Test
    fun testSingleCornerContactOverlap() {
        val t1 = Triangle(0.0 to 0.0, 1.0 to 0.0, 0.0 to 1.0)
        val t2 = Triangle(1.0 to 0.0, 2.0 to 0.0, 1.0 to 1.0)
        assertTrue(triTri2D(t1, t2), "Triangles should overlap when boundary points collide")
    }

    @Test
    fun testSingleCornerContactNoOverlap() {
        val t1 = Triangle(0.0 to 0.0, 1.0 to 0.0, 0.0 to 1.0)
        val t2 = Triangle(1.0 to 0.0, 2.0 to 0.0, 1.0 to 1.0)
        assertFalse(triTri2D(t1, t2, 0.0, false, false), "Triangles should not overlap when boundary points do not collide")
    }
}
