import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TriangleTest {

    @Test
    fun testWithin_insideTriangle() {
        val p1 = Point(1.5, 2.4)
        val p2 = Point(5.1, -3.1)
        val p3 = Point(-3.8, 1.2)
        val tri = Triangle(p1, p2, p3)
        assertEquals(true, tri.within(Point(0.0, 0.0)))
    }

    @Test
    fun testWithin_outsideTriangle() {
        val p1 = Point(1.5, 2.4)
        val p2 = Point(5.1, -3.1)
        val p3 = Point(-3.8, 1.2)
        val tri = Triangle(p1, p2, p3)
        assertEquals(false, tri.within(Point(10.0, 10.0)))
    }

    @Test
    fun testWithin_onEdge() {
        val p1 = Point(1.5, 2.4)
        val p2 = Point(5.1, -3.1)
        val p3 = Point(-3.8, 1.2)
        val tri = Triangle(p1, p2, p3)
        val edgePoint = Point(p1.x + (p2.x - p1.x) / 2, p1.y + (p2.y - p1.y) / 2)
        assertEquals(true, tri.within(edgePoint))
    }

    @Test
    fun testWithin_onVertex() {
        val p1 = Point(1.5, 2.4)
        val p2 = Point(5.1, -3.1)
        val p3 = Point(-3.8, 1.2)
        val tri = Triangle(p1, p2, p3)
        assertEquals(true, tri.within(p1))
        assertEquals(true, tri.within(p2))
        assertEquals(true, tri.within(p3))

    }


    @Test
    fun testWithin_smallTriangle() {
        val p1 = Point(0.1, 0.1)
        val p2 = Point(0.2, 0.1)
        val p3 = Point(0.15, 0.2)
        val tri = Triangle(p1, p2, p3)
        assertEquals(true, tri.within(Point(0.15, 0.15)))
        assertEquals(false, tri.within(Point(0.0, 0.0)))


    }

    @Test
    fun testWithin_largeTriangle() {
        val p1 = Point(100.0, 200.0)
        val p2 = Point(500.0, -300.0)
        val p3 = Point(-300.0, 100.0)
        val tri = Triangle(p1, p2, p3)
        assertEquals(true, tri.within(Point(100.0, 0.0)))
        assertEquals(false, tri.within(Point(1000.0, 1000.0)))
    }

     @Test
    fun testWithin_degenerateTriangle() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 2.0)
        val p3 = Point(3.0, 3.0) // Degenerate triangle - all points on a line
        val tri = Triangle(p1, p2, p3)
        assertEquals(true, tri.within(Point(2.0, 2.0))) // Point on the line
        assertEquals(false, tri.within(Point(1.0, 2.0))) // Point off the line
    }


}
