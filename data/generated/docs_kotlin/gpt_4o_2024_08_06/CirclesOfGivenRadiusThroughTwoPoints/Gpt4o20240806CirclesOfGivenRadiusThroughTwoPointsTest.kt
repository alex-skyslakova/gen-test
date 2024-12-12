import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CirclesOfGivenRadiusThroughTwoPointsTest {

    @Test
    fun testNormalCase() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = 2.0
        val (c1, c2) = findCircles(p1, p2, r)
        assertEquals(Point(0.5, 1.5), c1)
        assertEquals(Point(0.5, -0.5), c2)
    }

    @Test
    fun testDiameterCase() {
        val p1 = Point(0.0, 2.0)
        val p2 = Point(0.0, 0.0)
        val r = 1.0
        val (c1, c2) = findCircles(p1, p2, r)
        assertEquals(c1, c2)
        assertEquals(Point(0.0, 1.0), c1)
    }

    @Test
    fun testCoincidentPointsWithRadius() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.1234, 0.9876)
        val r = 2.0
        assertFailsWith<IllegalArgumentException> {
            findCircles(p1, p2, r)
        }
    }

    @Test
    fun testPointsTooFarApart() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = 0.5
        assertFailsWith<IllegalArgumentException> {
            findCircles(p1, p2, r)
        }
    }

    @Test
    fun testCoincidentPointsWithZeroRadius() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.1234, 0.9876)
        val r = 0.0
        val (c1, c2) = findCircles(p1, p2, r)
        assertEquals(p1, c1)
        assertEquals(p1, c2)
    }

    @Test
    fun testZeroRadiusDifferentPoints() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = 0.0
        assertFailsWith<IllegalArgumentException> {
            findCircles(p1, p2, r)
        }
    }

    @Test
    fun testNegativeRadius() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = -1.0
        assertFailsWith<IllegalArgumentException> {
            findCircles(p1, p2, r)
        }
    }
}
