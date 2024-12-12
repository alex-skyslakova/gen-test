import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.Random

class DutchNationalFlagTest {

    @Test
    fun testSort_emptyArray() {
        val balls = arrayOf<DutchColors>()
        balls.sort()
        assertTrue(balls.isSorted())
    }

    @Test
    fun testSort_singleElement() {
        val balls = arrayOf(DutchColors.WHITE)
        balls.sort()
        assertTrue(balls.isSorted())
    }

    @Test
    fun testSort_allRed() {
        val balls = arrayOf(DutchColors.RED, DutchColors.RED, DutchColors.RED)
        balls.sort()
        assertTrue(balls.isSorted())
    }


    @Test
    fun testSort_allWhite() {
        val balls = arrayOf(DutchColors.WHITE, DutchColors.WHITE, DutchColors.WHITE)
        balls.sort()
        assertTrue(balls.isSorted())
    }

    @Test
    fun testSort_allBlue() {
        val balls = arrayOf(DutchColors.BLUE, DutchColors.BLUE, DutchColors.BLUE)
        balls.sort()
        assertTrue(balls.isSorted())
    }

    @Test
    fun testSort_mixedColors() {
        val balls = arrayOf(DutchColors.BLUE, DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE, DutchColors.RED, DutchColors.WHITE)
        balls.sort()
        assertTrue(balls.isSorted())
    }

    @Test
    fun testSort_alreadySorted() {
        val balls = arrayOf(DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE)
        balls.sort()
        assertTrue(balls.isSorted())
    }


    @Test
    fun testSort_reverseSorted() {
        val balls = arrayOf(DutchColors.BLUE, DutchColors.WHITE, DutchColors.RED)
        balls.sort()
        assertTrue(balls.isSorted())
    }

    @Test
    fun testSort_randomColors() {
        val r = Random(123) // Fixed seed for reproducibility
        val balls = Array(100) { DutchColors.values()[r.nextInt(3)] }
        balls.sort()
        assertTrue(balls.isSorted())
    }


    @Test
    fun testIsSorted_emptyArray() {
        val balls = arrayOf<DutchColors>()
        assertTrue(balls.isSorted())
    }

    @Test
    fun testIsSorted_sortedArray() {
        val balls = arrayOf(DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE)
        assertTrue(balls.isSorted())
    }

    @Test
    fun testIsSorted_unsortedArray() {
        val balls = arrayOf(DutchColors.WHITE, DutchColors.RED, DutchColors.BLUE)
        assertFalse(balls.isSorted())
    }


    @Test
    fun testSwap() {
        val balls = arrayOf(DutchColors.RED, DutchColors.WHITE)
        balls.swap(0, 1)
        assertArrayEquals(arrayOf(DutchColors.WHITE, DutchColors.RED), balls)
    }



}
