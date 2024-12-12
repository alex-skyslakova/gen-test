import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class CirclesOfGivenRadiusThroughTwoPointsTest {

    @Test
    fun testValidCircles() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = 2.0

        val (c1, c2) = findCircles(p1, p2, r)

        // Expected centers based on manual calculation or reference
        val expectedC1 = Point(1.8631, 1.9742)
        val expectedC2 = Point(-0.8632, -0.7521)

        assertEquals(expectedC1.x, c1.x, 0.0001)
        assertEquals(expectedC1.y, c1.y, 0.0001)
        assertEquals(expectedC2.x, c2.x, 0.0001)
        assertEquals(expectedC2.y, c2.y, 0.0001)
    }

    @Test
    fun testCoincidentPointsWithNonZeroRadius() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.1234, 0.9876)
        val r = 2.0

        assertThrows<IllegalArgumentException> {
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
    fun testPointsFormingDiameter() {
        val p1 = Point(0.0000, 2.0000)
        val p2 = Point(0.0000, 0.0000)
        val r = 1.0

        val (c1, c2) = findCircles(p1, p2, r)

        val expectedCenter = Point(0.0000, 1.0000)

        assertEquals(expectedCenter, c1)
        assertEquals(expectedCenter, c2)
    }

    @Test
    fun testPointsTooFarApart() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = 0.5

        assertThrows<IllegalArgumentException> {
            findCircles(p1, p2, r)
        }
    }

    @Test
    fun testNegativeRadius() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = -1.0

        assertThrows<IllegalArgumentException> {
            findCircles(p1, p2, r)
        }
    }

    @Test
    fun testZeroRadiusWithDifferentPoints() {
        val p1 = Point(0.1234, 0.9876)
        val p2 = Point(0.8765, 0.2345)
        val r = 0.0

        assertThrows<IllegalArgumentException> {
            findCircles(p1, p2, r)
        }
    }
}
