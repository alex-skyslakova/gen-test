import org.junit.Test
import kotlin.test.assertEquals

class FindIntersectionOfTwoLinesTest {

    @Test
    fun testIntersectionOfTwoLines() {
        // Test case 1: Intersection point of the given lines
        val l1 = LineF(PointF(4f, 0f), PointF(6f, 10f))
        val l2 = LineF(PointF(0f, 3f), PointF(10f, 7f))
        val expectedIntersection1 = PointF(5f, 5f)
        val actualIntersection1 = findIntersection(l1, l2)
        assertEquals(expectedIntersection1.x, actualIntersection1.x, 0.0001f)
        assertEquals(expectedIntersection1.y, actualIntersection1.y, 0.0001f)

        // Test case 2: Intersection point of another set of lines
        val l3 = LineF(PointF(0f, 0f), PointF(1f, 1f))
        val l4 = LineF(PointF(1f, 2f), PointF(4f, 5f))
        val expectedIntersection2 = PointF(-1f, -1f)
        val actualIntersection2 = findIntersection(l3, l4)
        assertEquals(expectedIntersection2.x, actualIntersection2.x, 0.0001f)
        assertEquals(expectedIntersection2.y, actualIntersection2.y, 0.0001f)

        // Test case 3: Intersection point of parallel lines (should be infinite)
        val l5 = LineF(PointF(0f, 0f), PointF(1f, 1f))
        val l6 = LineF(PointF(0f, 1f), PointF(1f, 2f))
        val expectedIntersection3 = PointF(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        val actualIntersection3 = findIntersection(l5, l6)
        assertEquals(expectedIntersection3.x, actualIntersection3.x, 0.0001f)
        assertEquals(expectedIntersection3.y, actualIntersection3.y, 0.0001f)

        // Test case 4: Intersection point of coincident lines (should be infinite)
        val l7 = LineF(PointF(0f, 0f), PointF(1f, 1f))
        val l8 = LineF(PointF(0f, 0f), PointF(1f, 1f))
        val expectedIntersection4 = PointF(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        val actualIntersection4 = findIntersection(l7, l8)
        assertEquals(expectedIntersection4.x, actualIntersection4.x, 0.0001f)
        assertEquals(expectedIntersection4.y, actualIntersection4.y, 0.0001f)
    }
}
