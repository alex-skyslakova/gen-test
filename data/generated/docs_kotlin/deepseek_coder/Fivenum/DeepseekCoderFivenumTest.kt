import org.junit.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals

class FivenumTest {

    @Test
    fun testFivenumWithSortedArray() {
        val input = doubleArrayOf(6.0, 7.0, 15.0, 36.0, 39.0, 40.0, 41.0)
        val expected = doubleArrayOf(6.0, 15.0, 37.5, 40.0, 41.0)
        val result = fivenum(input)
        assertEquals(expected.asList(), result.asList())
    }

    @Test
    fun testFivenumWithUnsortedArray() {
        val input = doubleArrayOf(15.0, 6.0, 42.0, 41.0, 7.0, 36.0, 49.0, 40.0, 39.0, 47.0, 43.0)
        val expected = doubleArrayOf(6.0, 25.5, 40.0, 42.5, 49.0)
        val result = fivenum(input)
        assertEquals(expected.asList(), result.asList())
    }

    @Test
    fun testFivenumWithEvenNumberOfElements() {
        val input = doubleArrayOf(36.0, 40.0, 7.0, 39.0, 41.0, 15.0)
        val expected = doubleArrayOf(7.0, 15.0, 37.5, 40.0, 41.0)
        val result = fivenum(input)
        assertEquals(expected.asList(), result.asList())
    }

    @Test
    fun testFivenumWithOddNumberOfElements() {
        val input = doubleArrayOf(0.1, 0.2, 0.3, 0.4, 0.5)
        val expected = doubleArrayOf(0.1, 0.2, 0.3, 0.4, 0.5)
        val result = fivenum(input)
        assertEquals(expected.asList(), result.asList())
    }

    @Test
    fun testFivenumWithNegativeNumbers() {
        val input = doubleArrayOf(-5.0, -4.0, -3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0)
        val expected = doubleArrayOf(-5.0, -2.0, 0.0, 2.0, 5.0)
        val result = fivenum(input)
        assertEquals(expected.asList(), result.asList())
    }

    @Test
    fun testFivenumWithSingleElement() {
        val input = doubleArrayOf(42.0)
        val expected = doubleArrayOf(42.0, 42.0, 42.0, 42.0, 42.0)
        val result = fivenum(input)
        assertEquals(expected.asList(), result.asList())
    }

    @Test
    fun testFivenumWithEmptyArray() {
        val input = doubleArrayOf()
        assertFailsWith<IllegalArgumentException> {
            fivenum(input)
        }
    }

    @Test
    fun testFivenumWithNaN() {
        val input = doubleArrayOf(1.0, 2.0, Double.NaN, 4.0, 5.0)
        assertFailsWith<IllegalArgumentException> {
            fivenum(input)
        }
    }
}
