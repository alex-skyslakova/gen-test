import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FindTheIntersectionOfTwoLinesTest {

    @Test
    fun testIntersectionOfGivenLines() {
        val l1 = LineF(PointF(4f, 0f), PointF(6f, 10f))
        val l2 = LineF(PointF(0f, 3f), PointF(10f, 7f))
        val intersection = findIntersection(l1, l2)
        assertEquals(PointF(5f, 5f), intersection)
    }

    @Test
    fun testIntersectionOfParallelLines() {
        val l1 = LineF(PointF(0f, 0f), PointF(1f, 1f))
        val l2 = LineF(PointF(1f, 1f), PointF(2f, 2f))
        val intersection = findIntersection(l1, l2)
        assertTrue(intersection.x.isInfinite() && intersection.y.isInfinite())
    }

    @Test
    fun testIntersectionOfCoincidentLines() {
        val l1 = LineF(PointF(0f, 0f), PointF(1f, 1f))
        val l2 = LineF(PointF(0f, 0f), PointF(1f, 1f))
        val intersection = findIntersection(l1, l2)
        assertTrue(intersection.x.isInfinite() && intersection.y.isInfinite())
    }

    @Test
    fun testIntersectionOfPerpendicularLines() {
        val l1 = LineF(PointF(0f, 0f), PointF(0f, 1f))
        val l2 = LineF(PointF(-1f, 0.5f), PointF(1f, 0.5f))
        val intersection = findIntersection(l1, l2)
        assertEquals(PointF(0f, 0.5f), intersection)
    }

    @Test
    fun testIntersectionOfLinesWithNegativeSlope() {
        val l1 = LineF(PointF(0f, 0f), PointF(1f, -1f))
        val l2 = LineF(PointF(0f, -1f), PointF(1f, 0f))
        val intersection = findIntersection(l1, l2)
        assertEquals(PointF(0.5f, -0.5f), intersection)
    }
}
