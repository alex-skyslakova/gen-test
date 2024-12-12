import kotlin.test.Test
import kotlin.test.assertEquals

class LoopsWrongRangesTest {

    @Test
    fun testNormalIncrement() {
        val example = Example(-2, 2, 1, "Normal")
        val expected = listOf(-2, -1, 0, 1, 2)
        assertEquals(expected, sequence(example, 10))
    }

    @Test
    fun testZeroIncrement() {
        val example = Example(-2, 2, 0, "Zero increment")
        val expected = List(10) { -2 }
        assertEquals(expected, sequence(example, 10))
    }

    @Test
    fun testNegativeIncrement() {
        val example = Example(-2, 2, -1, "Increments away from stop value")
        val expected = listOf<Int>()  // No numbers generated
        assertEquals(expected, sequence(example, 10))
    }

    @Test
    fun testIncrementBeyondStop() {
        val example = Example(-2, 2, 10, "First increment is beyond stop value")
        val expected = listOf(-2)
        assertEquals(expected, sequence(example, 10))
    }

    @Test
    fun testStartMoreThanStopPositiveIncrement() {
        val example = Example(2, -2, 1, "Start more than stop: positive increment")
        val expected = listOf<Int>()  // No numbers generated
        assertEquals(expected, sequence(example, 10))
    }

    @Test
    fun testStartEqualStopPositiveIncrement() {
        val example = Example(2, 2, 1, "Start equal stop: positive increment")
        val expected = listOf(2)
        assertEquals(expected, sequence(example, 10))
    }

    @Test
    fun testStartEqualStopNegativeIncrement() {
        val example = Example(2, 2, -1, "Start equal stop: negative increment")
        val expected = listOf(2)
        assertEquals(expected, sequence(example, 10))
    }

    @Test
    fun testStartEqualStopZeroIncrement() {
        val example = Example(2, 2, 0, "Start equal stop: zero increment")
        val expected = List(10) { 2 }
        assertEquals(expected, sequence(example, 10))
    }

    @Test
    fun testStartEqualStopZeroZeroIncrement() {
        val example = Example(0, 0, 0, "Start equal stop equal zero: zero increment")
        val expected = List(10) { 0 }
        assertEquals(expected, sequence(example, 10))
    }
}
