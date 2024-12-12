import org.junit.Assert.*
import org.junit.Test

class KnightTourTest {

    @Test
    fun testKnightTourFromTopLeft() {
        val start = Square(1, 1)
        val tour = knightTourFrom(start)
        assertEquals(64, tour.size)
        assertEquals(start, tour.first())
        assertTrue(tour.distinct().size == 64)
    }

    @Test
    fun testKnightTourFromBottomRight() {
        val start = Square(8, 8)
        val tour = knightTourFrom(start)
        assertEquals(64, tour.size)
        assertEquals(start, tour.first())
        assertTrue(tour.distinct().size == 64)
    }

    @Test
    fun testKnightTourFromMiddle() {
        val start = Square(4, 4)
        val tour = knightTourFrom(start)
        assertEquals(64, tour.size)
        assertEquals(start, tour.first())
        assertTrue(tour.distinct().size == 64)
    }

    @Test
    fun testKnightTourFromEdge() {
        val start = Square(1, 4)
        val tour = knightTourFrom(start)
        assertEquals(64, tour.size)
        assertEquals(start, tour.first())
        assertTrue(tour.distinct().size == 64)
    }

    @Test
    fun testKnightTourFromCorner() {
        val start = Square(1, 8)
        val tour = knightTourFrom(start)
        assertEquals(64, tour.size)
        assertEquals(start, tour.first())
        assertTrue(tour.distinct().size == 64)
    }

    @Test
    fun testKnightTourFromRandom() {
        val start = Square(3, 5)
        val tour = knightTourFrom(start)
        assertEquals(64, tour.size)
        assertEquals(start, tour.first())
        assertTrue(tour.distinct().size == 64)
    }

    @Test
    fun testKnightTourVisitsAllSquares() {
        val start = Square(2, 3)
        val tour = knightTourFrom(start)
        val allSquares = board.toSet()
        val visitedSquares = tour.toSet()
        assertEquals(allSquares, visitedSquares)
    }

    @Test
    fun testKnightTourNoDuplicates() {
        val start = Square(5, 6)
        val tour = knightTourFrom(start)
        val visitedSquares = tour.toSet()
        assertEquals(64, visitedSquares.size)
    }
}
