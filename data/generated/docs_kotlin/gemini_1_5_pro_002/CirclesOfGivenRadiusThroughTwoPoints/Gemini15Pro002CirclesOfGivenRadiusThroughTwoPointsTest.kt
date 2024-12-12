import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class FindCirclesTest {

    @Test
    fun testDistinctPointsValidRadius() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = 2.0
        val (c1, c2) = findCircles(p1, p2, r)
        assertEquals(Point(-0.2417, 1.3563), c1)
        assertEquals(Point(1.2417, -0.1218), c2)
    }

    @Test
    fun testCoincidentPointsRadiusOne() {
        val p1 = Point(0.0, 2.0)
        val p2 = Point(0.0, 2.0)
        val r = 1.0
        assertThrows<IllegalArgumentException> { findCircles(p1, p2, r) }
    }


    @Test
    fun testCoincidentPointsRadiusZero() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.1234, 0.9876)
        val r = 0.0
        val (c1, c2) = findCircles(p1, p2, r)
        assertEquals(p1, c1)
        assertEquals(p2, c2)
    }

    @Test
    fun testPointsTooFarApart() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = 0.5
        assertThrows<IllegalArgumentException> { findCircles(p1, p2, r) }
    }

    @Test
    fun testNegativeRadius() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = -1.0
        assertThrows<IllegalArgumentException> { findCircles(p1, p2, r) }
    }
    
    @Test
    fun testZeroRadiusDistinctPoints() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = 0.0
        assertThrows<IllegalArgumentException> { findCircles(p1, p2, r) }
    }

    @Test
    fun testPointsFormDiameter() {
        val p1 = Point(0.0, 1.0)
        val p2 = Point(0.0, -1.0)
        val r = 1.0
        val (c1, c2) = findCircles(p1, p2, r)
        assertEquals(Point(0.0, 0.0), c1)
        assertEquals(Point(0.0, 0.0), c2)

    }


}
