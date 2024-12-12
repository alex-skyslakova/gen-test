import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.Random

class DutchNationalFlagProblemTest {

    @Test
    fun testSwapFunction() {
        val balls = arrayOf(DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE)
        balls.swap(0, 2)
        assertArrayEquals(arrayOf(DutchColors.BLUE, DutchColors.WHITE, DutchColors.RED), balls)
    }

    @Test
    fun testSortFunction() {
        val balls = arrayOf(DutchColors.BLUE, DutchColors.RED, DutchColors.WHITE, DutchColors.RED, DutchColors.BLUE)
        balls.sort()
        assertArrayEquals(arrayOf(DutchColors.RED, DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE, DutchColors.BLUE), balls)
    }

    @Test
    fun testIsSortedFunction() {
        val sortedBalls = arrayOf(DutchColors.RED, DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE, DutchColors.BLUE)
        assertTrue(sortedBalls.isSorted())

        val unsortedBalls = arrayOf(DutchColors.BLUE, DutchColors.RED, DutchColors.WHITE)
        assertFalse(unsortedBalls.isSorted())
    }

    @Test
    fun testRandomizedBallsNotSortedInitially() {
        val r = Random()
        val balls = Array(NUM_BALLS) { DutchColors.RED }
        val colors = DutchColors.values()

        // Ensure balls are not sorted initially
        do {
            for (i in 0 until NUM_BALLS) balls[i] = colors[r.nextInt(3)]
        } while (balls.isSorted())

        assertFalse(balls.isSorted())
    }

    @Test
    fun testSortingRandomizedBalls() {
        val r = Random()
        val balls = Array(NUM_BALLS) { DutchColors.RED }
        val colors = DutchColors.values()

        // Randomize balls ensuring they're not sorted
        do {
            for (i in 0 until NUM_BALLS) balls[i] = colors[r.nextInt(3)]
        } while (balls.isSorted())

        // Sort the balls
        balls.sort()

        // Check if sorted
        assertTrue(balls.isSorted())
    }
}
