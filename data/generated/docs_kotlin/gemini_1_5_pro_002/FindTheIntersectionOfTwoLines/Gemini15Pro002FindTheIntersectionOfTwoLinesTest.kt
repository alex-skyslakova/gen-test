import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class FindTheIntersectionOfTwoLinesKtTest {

    @Test
    fun findIntersectionTest1() {
        val l1 = LineF(PointF(4f, 0f), PointF(6f, 10f))
        val l2 = LineF(PointF(0f, 3f), PointF(10f, 7f))
        val expected = PointF(5f, 5f)
        val actual = findIntersection(l1, l2)
        assertEquals(expected.x, actual.x)
        assertEquals(expected.y, actual.y)
    }

    @Test
    fun findIntersectionTest2() {
        val l1 = LineF(PointF(0f, 0f), PointF(1f, 1f))
        val l2 = LineF(PointF(1f, 2f), PointF(4f, 5f))
        val expected = PointF(-1f, -1f)
        val actual = findIntersection(l1, l2)
        assertEquals(expected.x, actual.x)
        assertEquals(expected.y, actual.y)

    }

    @Test
    fun findIntersectionTestParallelLines() {
        val l1 = LineF(PointF(0f, 0f), PointF(1f, 1f))
        val l2 = LineF(PointF(0f, 1f), PointF(1f, 2f))
        // Expecting Infinity for parallel lines.  Note, this test will fail if NaN values are produced.  Adjust as needed based on the actual behavior/requirements.
        val intersection = findIntersection(l1, l2)
        assertTrue(intersection.x.isInfinite())
        assertTrue(intersection.y.isInfinite())
    }


    @Test
    fun findIntersectionTestVerticalLine1(){
        val l1 = LineF(PointF(2f, 0f), PointF(2f, 5f))
        val l2 = LineF(PointF(0f, 2f), PointF(4f, 2f))

        val expected = PointF(2f, 2f)
        val actual = findIntersection(l1, l2)
        assertEquals(expected.x, actual.x)
        assertEquals(expected.y, actual.y)
    }

    @Test
    fun findIntersectionTestVerticalLine2(){
        val l1 = LineF(PointF(0f, 2f), PointF(4f, 2f))
        val l2 = LineF(PointF(2f, 0f), PointF(2f, 5f))
        val expected = PointF(2f, 2f)
        val actual = findIntersection(l1, l2)
        assertEquals(expected.x, actual.x)
        assertEquals(expected.y, actual.y)
    }


}

