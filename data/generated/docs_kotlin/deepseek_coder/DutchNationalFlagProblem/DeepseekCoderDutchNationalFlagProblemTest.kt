import org.junit.Assert.*
import org.junit.Test

class DutchNationalFlagProblemTest {

    @Test
    fun testSwap() {
        val balls = arrayOf(DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE)
        balls.swap(0, 2)
        assertArrayEquals(arrayOf(DutchColors.BLUE, DutchColors.WHITE, DutchColors.RED), balls)
    }

    @Test
    fun testIsSorted() {
        val sortedBalls = arrayOf(DutchColors.RED, DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE, DutchColors.BLUE)
        val unsortedBalls = arrayOf(DutchColors.BLUE, DutchColors.RED, DutchColors.WHITE, DutchColors.RED, DutchColors.BLUE)

        assertTrue(sortedBalls.isSorted())
        assertFalse(unsortedBalls.isSorted())
    }

    @Test
    fun testSort() {
        val balls = arrayOf(DutchColors.BLUE, DutchColors.RED, DutchColors.WHITE, DutchColors.RED, DutchColors.BLUE)
        balls.sort()
        assertArrayEquals(arrayOf(DutchColors.RED, DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE, DutchColors.BLUE), balls)
    }

    @Test
    fun testSortWithAllColors() {
        val balls = arrayOf(DutchColors.BLUE, DutchColors.WHITE, DutchColors.RED)
        balls.sort()
        assertArrayEquals(arrayOf(DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE), balls)
    }

    @Test
    fun testSortWithSingleColor() {
        val balls = arrayOf(DutchColors.RED, DutchColors.RED, DutchColors.RED)
        balls.sort()
        assertArrayEquals(arrayOf(DutchColors.RED, DutchColors.RED, DutchColors.RED), balls)
    }

    @Test
    fun testSortWithTwoColors() {
        val balls = arrayOf(DutchColors.BLUE, DutchColors.RED, DutchColors.BLUE)
        balls.sort()
        assertArrayEquals(arrayOf(DutchColors.RED, DutchColors.BLUE, DutchColors.BLUE), balls)
    }

    @Test
    fun testSortWithAlreadySorted() {
        val balls = arrayOf(DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE)
        balls.sort()
        assertArrayEquals(arrayOf(DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE), balls)
    }

    @Test
    fun testSortWithReverseOrder() {
        val balls = arrayOf(DutchColors.BLUE, DutchColors.WHITE, DutchColors.RED)
        balls.sort()
        assertArrayEquals(arrayOf(DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE), balls)
    }

    @Test
    fun testSortWithRandomOrder() {
        val balls = arrayOf(DutchColors.WHITE, DutchColors.BLUE, DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE)
        balls.sort()
        assertArrayEquals(arrayOf(DutchColors.RED, DutchColors.WHITE, DutchColors.WHITE, DutchColors.BLUE, DutchColors.BLUE), balls)
    }
}
