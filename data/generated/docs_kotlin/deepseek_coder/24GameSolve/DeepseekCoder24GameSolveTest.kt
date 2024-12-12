import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TwentyFourGameSolverTest {

    @Test
    fun testSolve24WithSolution() {
        val digits = intArrayOf(6, 6, 6, 6)
        assertTrue(solve24(digits))
    }

    @Test
    fun testSolve24WithoutSolution() {
        val digits = intArrayOf(1, 1, 1, 1)
        assertFalse(solve24(digits))
    }

    @Test
    fun testSolve24WithSingleDigit() {
        val digits = intArrayOf(24, 1, 1, 1)
        assertTrue(solve24(digits))
    }

    @Test
    fun testSolve24WithAllSameDigits() {
        val digits = intArrayOf(8, 8, 8, 8)
        assertTrue(solve24(digits))
    }

    @Test
    fun testSolve24WithRandomDigits() {
        val digits = intArrayOf(3, 8, 3, 8)
        assertTrue(solve24(digits))
    }

    @Test
    fun testSolve24WithLargeNumbers() {
        val digits = intArrayOf(12, 2, 2, 2)
        assertTrue(solve24(digits))
    }

    @Test
    fun testSolve24WithZero() {
        val digits = intArrayOf(0, 0, 0, 0)
        assertFalse(solve24(digits))
    }

    @Test
    fun testSolve24WithOne() {
        val digits = intArrayOf(1, 1, 1, 1)
        assertFalse(solve24(digits))
    }

    @Test
    fun testSolve24WithTwo() {
        val digits = intArrayOf(2, 2, 2, 2)
        assertFalse(solve24(digits))
    }

    @Test
    fun testSolve24WithThree() {
        val digits = intArrayOf(3, 3, 3, 3)
        assertFalse(solve24(digits))
    }
}
