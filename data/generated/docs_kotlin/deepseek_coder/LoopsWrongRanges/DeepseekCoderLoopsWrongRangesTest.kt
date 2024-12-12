import org.junit.Test
import kotlin.test.assertEquals

class LoopsWrongRangesTest {

    @Test
    fun testNormal() {
        val ex = Example(-2, 2, 1, "Normal")
        val result = sequence(ex, 10)
        assertEquals(listOf(-2, -1, 0, 1, 2), result)
    }

    @Test
    fun testZeroIncrement() {
        val ex = Example(-2, 2, 0, "Zero increment")
        val result = sequence(ex, 10)
        assertEquals(List(10) { -2 }, result)
    }

    @Test
    fun testIncrementsAwayFromStopValue() {
        val ex = Example(-2, 2, -1, "Increments away from stop value")
        val result = sequence(ex, 10)
        assertEquals(listOf(-2), result)
    }

    @Test
    fun testFirstIncrementIsBeyondStopValue() {
        val ex = Example(-2, 2, 10, "First increment is beyond stop value")
        val result = sequence(ex, 10)
        assertEquals(listOf(-2), result)
    }

    @Test
    fun testStartMoreThanStopPositiveIncrement() {
        val ex = Example(2, -2, 1, "Start more than stop: positive increment")
        val result = sequence(ex, 10)
        assertEquals(listOf(2), result)
    }

    @Test
    fun testStartEqualStopPositiveIncrement() {
        val ex = Example(2, 2, 1, "Start equal stop: positive increment")
        val result = sequence(ex, 10)
        assertEquals(listOf(2), result)
    }

    @Test
    fun testStartEqualStopNegativeIncrement() {
        val ex = Example(2, 2, -1, "Start equal stop: negative increment")
        val result = sequence(ex, 10)
        assertEquals(listOf(2), result)
    }

    @Test
    fun testStartEqualStopZeroIncrement() {
        val ex = Example(2, 2, 0, "Start equal stop: zero increment")
        val result = sequence(ex, 10)
        assertEquals(List(10) { 2 }, result)
    }

    @Test
    fun testStartEqualStopEqualZeroZeroIncrement() {
        val ex = Example(0, 0, 0, "Start equal stop equal zero: zero increment")
        val result = sequence(ex, 10)
        assertEquals(List(10) { 0 }, result)
    }
}
