import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ConvexHullTest {

    @Test
    fun testEmptyInput() {
        val points = arrayOf<Point>()
        val hull = convexHull(points)
        assertTrue(hull.isEmpty())
    }

    @Test
    fun testSinglePoint() {
        val points = arrayOf(Point(0, 0))
        val hull = convexHull(points)
        assertEquals(listOf(Point(0, 0)), hull)
    }

    @Test
    fun testTwoPoints() {
        val points = arrayOf(Point(0, 0), Point(1, 1))
        val hull = convexHull(points)
        assertEquals(listOf(Point(0, 0), Point(1, 1)), hull)
    }

    @Test
    fun testThreePointsCollinear() {
        val points = arrayOf(Point(0, 0), Point(1, 1), Point(2, 2))
        val hull = convexHull(points)
        assertEquals(listOf(Point(0, 0), Point(2, 2)), hull)
    }

    @Test
    fun testThreePointsCounterClockwise() {
        val points = arrayOf(Point(0, 0), Point(1, 1), Point(0, 1))
        val hull = convexHull(points)
        assertEquals(listOf(Point(0, 0), Point(1, 1), Point(0, 1)), hull)

    }


    @Test
    fun testThreePointsClockwise() {
        val points = arrayOf(Point(0, 0), Point(0, 1), Point(1,1))
        val hull = convexHull(points)
        assertEquals(listOf(Point(0, 0), Point(1, 1), Point(0,1)), hull)
    }

    @Test
    fun testExampleInput() {
        val points = arrayOf(
            Point(16,  3), Point(12, 17), Point( 0,  6), Point(-4, -6), Point(16,  6),
            Point(16, -7), Point(16, -3), Point(17, -4), Point( 5, 19), Point(19, -8),
            Point( 3, 16), Point(12, 13), Point( 3, -4), Point(17,  5), Point(-3, 15),
            Point(-3, -9), Point( 0, 11), Point(-9, -3), Point(-4, -2), Point(12, 10)
        )
        val hull = convexHull(points)
        val expectedHull = listOf(Point(-9, -3), Point(-3, -9), Point(19, -8), Point(17, 5), Point(12, 17), Point(5, 19), Point(-3, 15))
        assertEquals(expectedHull.toSet(), hull.toSet()) // Using Set for order independence


    }

    @Test
    fun testSquare() {
        val points = arrayOf(Point(0, 0), Point(0, 1), Point(1, 1), Point(1, 0))
        val hull = convexHull(points)
        val expectedHull = listOf(Point(0, 0), Point(1, 0), Point(1, 1), Point(0, 1))
        assertEquals(expectedHull.toSet(), hull.toSet())

    }


}
