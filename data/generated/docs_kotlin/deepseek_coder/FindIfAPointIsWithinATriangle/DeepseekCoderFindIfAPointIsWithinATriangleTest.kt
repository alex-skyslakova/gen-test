import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TriangleTest {

    @Test
    fun testPointWithinTriangle() {
        val p1 = Point(1.5, 2.4)
        val p2 = Point(5.1, -3.1)
        val p3 = Point(-3.8, 1.2)
        val triangle = Triangle(p1, p2, p3)

        // Points within the triangle
        assertEquals(true, triangle.within(Point(0.0, 0.0)))
        assertEquals(true, triangle.within(Point(0.0, 1.0)))
        assertEquals(true, triangle.within(Point(3.0, 1.0)))

        // Points outside the triangle
        assertEquals(false, triangle.within(Point(10.0, 10.0)))
        assertEquals(false, triangle.within(Point(-10.0, -10.0)))
        assertEquals(false, triangle.within(Point(6.0, 0.0)))
    }

    @Test
    fun testPointOnTriangleEdge() {
        val p1 = Point(1.0 / 10, 1.0 / 9)
        val p2 = Point(100.0 / 8, 100.0 / 3)
        val p3 = Point(100.0 / 4, 100.0 / 9)
        val triangle = Triangle(p1, p2, p3)

        // Point on the edge of the triangle
        val pt = Point(p1.x + 3.0 / 7 * (p2.x - p1.x), p1.y + 3.0 / 7 * (p2.y - p1.y))
        assertEquals(true, triangle.within(pt))
    }

    @Test
    fun testPointOutsideTriangleBoundingBox() {
        val p1 = Point(1.0 / 10, 1.0 / 9)
        val p2 = Point(100.0 / 8, 100.0 / 3)
        val p3 = Point(-100.0 / 8, 100.0 / 6)
        val triangle = Triangle(p1, p2, p3)

        // Point outside the bounding box of the triangle
        val pt = Point(p1.x + 3.0 / 7 * (p2.x - p1.x), p1.y + 3.0 / 7 * (p2.y - p1.y))
        assertEquals(false, triangle.within(Point(1000.0, 1000.0)))
    }

    @Test
    fun testPointExactlyOnVertex() {
        val p1 = Point(1.5, 2.4)
        val p2 = Point(5.1, -3.1)
        val p3 = Point(-3.8, 1.2)
        val triangle = Triangle(p1, p2, p3)

        // Point exactly on a vertex
        assertEquals(true, triangle.within(p1))
        assertEquals(true, triangle.within(p2))
        assertEquals(true, triangle.within(p3))
    }

    @Test
    fun testPointExactlyOnEdge() {
        val p1 = Point(1.5, 2.4)
        val p2 = Point(5.1, -3.1)
        val p3 = Point(-3.8, 1.2)
        val triangle = Triangle(p1, p2, p3)

        // Point exactly on an edge
        val midPoint12 = Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2)
        val midPoint23 = Point((p2.x + p3.x) / 2, (p2.y + p3.y) / 2)
        val midPoint31 = Point((p3.x + p1.x) / 2, (p3.y + p1.y) / 2)

        assertEquals(true, triangle.within(midPoint12))
        assertEquals(true, triangle.within(midPoint23))
        assertEquals(true, triangle.within(midPoint31))
    }
}
