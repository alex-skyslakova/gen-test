import org.junit.Test
import kotlin.test.assertEquals

class ConvexHullTest {

    @Test
    fun testConvexHull() {
        val points = arrayOf(
            Point(16,  3), Point(12, 17), Point( 0,  6), Point(-4, -6), Point(16,  6),
            Point(16, -7), Point(16, -3), Point(17, -4), Point( 5, 19), Point(19, -8),
            Point( 3, 16), Point(12, 13), Point( 3, -4), Point(17,  5), Point(-3, 15),
            Point(-3, -9), Point( 0, 11), Point(-9, -3), Point(-4, -2), Point(12, 10)
        )

        val expectedHull = listOf(
            Point(-9, -3), Point(-3, -9), Point(19, -8), Point(17, 5),
            Point(12, 17), Point(5, 19), Point(-3, 15)
        )

        val hull = convexHull(points)
        assertEquals(expectedHull.sorted(), hull.sorted())
    }

    @Test
    fun testConvexHullEmptyInput() {
        val points = emptyArray<Point>()
        val expectedHull = emptyList<Point>()

        val hull = convexHull(points)
        assertEquals(expectedHull, hull)
    }

    @Test
    fun testConvexHullSinglePoint() {
        val points = arrayOf(Point(1, 1))
        val expectedHull = listOf(Point(1, 1))

        val hull = convexHull(points)
        assertEquals(expectedHull, hull)
    }

    @Test
    fun testConvexHullTwoPoints() {
        val points = arrayOf(Point(1, 1), Point(2, 2))
        val expectedHull = listOf(Point(1, 1), Point(2, 2))

        val hull = convexHull(points)
        assertEquals(expectedHull.sorted(), hull.sorted())
    }

    @Test
    fun testConvexHullCollinearPoints() {
        val points = arrayOf(
            Point(0, 0), Point(1, 1), Point(2, 2), Point(3, 3), Point(4, 4)
        )
        val expectedHull = listOf(Point(0, 0), Point(4, 4))

        val hull = convexHull(points)
        assertEquals(expectedHull.sorted(), hull.sorted())
    }

    @Test
    fun testConvexHullComplexCase() {
        val points = arrayOf(
            Point(0, 0), Point(0, 1), Point(1, 0), Point(1, 1),
            Point(0, 2), Point(2, 0), Point(2, 2), Point(1, 2),
            Point(2, 1), Point(3, 3), Point(3, 0), Point(0, 3)
        )
        val expectedHull = listOf(
            Point(0, 0), Point(3, 0), Point(3, 3), Point(0, 3)
        )

        val hull = convexHull(points)
        assertEquals(expectedHull.sorted(), hull.sorted())
    }
}
