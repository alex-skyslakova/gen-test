import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FivenumTest {

    @Test
    fun testFivenumWithOddNumberOfElements() {
        val input = doubleArrayOf(15.0, 6.0, 42.0, 41.0, 7.0, 36.0, 49.0, 40.0, 39.0, 47.0, 43.0)
        val expected = doubleArrayOf(6.0, 39.0, 41.0, 43.0, 49.0)
        assertArrayEquals(expected, fivenum(input), 0.0001)
    }

    @Test
    fun testFivenumWithEvenNumberOfElements() {
        val input = doubleArrayOf(36.0, 40.0, 7.0, 39.0, 41.0, 15.0)
        val expected = doubleArrayOf(7.0, 15.0, 37.5, 40.0, 41.0)
        assertArrayEquals(expected, fivenum(input), 0.0001)
    }

    @Test
    fun testFivenumWithNegativeAndPositiveNumbers() {
        val input = doubleArrayOf(
            0.14082834, 0.09748790, 1.73131507, 0.87636009, -1.95059594, 0.73438555,
            -0.03035726, 1.46675970, -0.74621349, -0.72588772, 0.63905160, 0.61501527,
            -0.98983780, -1.00447874, -0.62759469, 0.66206163, 1.04312009, -0.10305385,
            0.75775634, 0.32566578
        )
        val expected = doubleArrayOf(-1.95059594, -0.74621349, 0.14082834, 0.75775634, 1.73131507)
        assertArrayEquals(expected, fivenum(input), 0.0001)
    }

    @Test
    fun testFivenumWithSingleElement() {
        val input = doubleArrayOf(42.0)
        val expected = doubleArrayOf(42.0, 42.0, 42.0, 42.0, 42.0)
        assertArrayEquals(expected, fivenum(input), 0.0001)
    }

    @Test
    fun testFivenumWithTwoElements() {
        val input = doubleArrayOf(15.0, 42.0)
        val expected = doubleArrayOf(15.0, 15.0, 28.5, 42.0, 42.0)
        assertArrayEquals(expected, fivenum(input), 0.0001)
    }

    @Test
    fun testFivenumWithNaN() {
        val input = doubleArrayOf(15.0, Double.NaN, 42.0)
        val exception = assertThrows<IllegalArgumentException> {
            fivenum(input)
        }
        assertEquals("Unable to deal with arrays containing NaN", exception.message)
    }

    @Test
    fun testFivenumWithEmptyArray() {
        val input = doubleArrayOf()
        val exception = assertThrows<IllegalArgumentException> {
            fivenum(input)
        }
        assertEquals("Array slice cannot be empty", exception.message)
    }
}
