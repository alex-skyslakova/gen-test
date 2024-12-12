import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KnightTourTest {

    @Test
    fun testKnightMoves() {
        val start = Square(1, 1)
        val expectedMoves = listOf(
            Square(2, 3), Square(3, 2)
        )
        val actualMoves = knightMoves(start)
        assertEquals(expectedMoves.size, actualMoves.size)
        assertTrue(actualMoves.containsAll(expectedMoves))
    }

    @Test
    fun testKnightTourFromStart() {
        val start = Square(1, 1)
        val tour = knightTourFrom(start)
        assertEquals(64, tour.size)
        assertTrue(tour.contains(start))
        assertTrue(tour.distinct().size == 64) // Ensure all squares are unique
    }

    @Test
    fun testKnightTourCompleteness() {
        val start = Square(4, 4)
        val tour = knightTourFrom(start)
        assertEquals(64, tour.size)
        assertTrue(tour.distinct().size == 64) // Ensure all squares are unique
    }

    @Test
    fun testKnightTourDifferentStart() {
        val start = Square(8, 8)
        val tour = knightTourFrom(start)
        assertEquals(64, tour.size)
        assertTrue(tour.contains(start))
        assertTrue(tour.distinct().size == 64) // Ensure all squares are unique
    }

    @Test
    fun testKnightTourEdgeCase() {
        val start = Square(1, 8)
        val tour = knightTourFrom(start)
        assertEquals(64, tour.size)
        assertTrue(tour.contains(start))
        assertTrue(tour.distinct().size == 64) // Ensure all squares are unique
    }
}
