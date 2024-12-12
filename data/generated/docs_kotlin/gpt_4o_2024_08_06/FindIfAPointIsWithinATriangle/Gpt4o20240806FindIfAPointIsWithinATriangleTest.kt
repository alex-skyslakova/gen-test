import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class TriangleTest {

    @Test
    fun testPointInsideTriangle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(5.0, 0.0)
        val p3 = Point(0.0, 5.0)
        val triangle = Triangle(p1, p2, p3)
        
        val insidePoint = Point(1.0, 1.0)
        assertTrue(triangle.within(insidePoint), "Point should be inside the triangle")
    }

    @Test
    fun testPointOutsideTriangle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(5.0, 0.0)
        val p3 = Point(0.0, 5.0)
        val triangle = Triangle(p1, p2, p3)
        
        val outsidePoint = Point(6.0, 6.0)
        assertFalse(triangle.within(outsidePoint), "Point should be outside the triangle")
    }

    @Test
    fun testPointOnVertex() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(5.0, 0.0)
        val p3 = Point(0.0, 5.0)
        val triangle = Triangle(p1, p2, p3)
        
        assertTrue(triangle.within(p1), "Point on vertex should be considered inside the triangle")
        assertTrue(triangle.within(p2), "Point on vertex should be considered inside the triangle")
        assertTrue(triangle.within(p3), "Point on vertex should be considered inside the triangle")
    }

    @Test
    fun testPointOnEdge() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(5.0, 0.0)
        val p3 = Point(0.0, 5.0)
        val triangle = Triangle(p1, p2, p3)
        
        val onEdgePoint = Point(2.5, 0.0)
        assertTrue(triangle.within(onEdgePoint), "Point on edge should be considered inside the triangle")
    }

    @Test
    fun testPointNearEdgeWithinEpsilon() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(5.0, 0.0)
        val p3 = Point(0.0, 5.0)
        val triangle = Triangle(p1, p2, p3)
        
        val nearEdgePoint = Point(2.5, 0.001)
        assertTrue(triangle.within(nearEdgePoint), "Point near edge within epsilon should be considered inside the triangle")
    }

    @Test
    fun testPointNearEdgeOutsideEpsilon() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(5.0, 0.0)
        val p3 = Point(0.0, 5.0)
        val triangle = Triangle(p1, p2, p3)
        
        val nearEdgePoint = Point(2.5, 0.002)
        assertFalse(triangle.within(nearEdgePoint), "Point near edge outside epsilon should be considered outside the triangle")
    }
}
