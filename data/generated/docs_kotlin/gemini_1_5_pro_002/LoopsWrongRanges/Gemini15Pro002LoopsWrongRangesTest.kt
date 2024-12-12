import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ExampleTest {

    @Test
    fun testNormalRange() {
        val ex = Example(-2, 2, 1, "Normal")
        assertEquals(listOf(-2, -1, 0, 1, 2), sequence(ex, 10))
    }

    @Test
    fun testZeroIncrement() {
        val ex = Example(-2, 2, 0, "Zero increment")
        assertEquals(listOf(-2, -2, -2, -2, -2, -2, -2, -2, -2, -2), sequence(ex, 10))
    }

    @Test
    fun testIncrementsAwayFromStopValue() {
        val ex = Example(-2, 2, -1, "Increments away from stop value")
        assertEquals(listOf(-2), sequence(ex, 10))
    }

    @Test
    fun testFirstIncrementBeyondStopValue() {
        val ex = Example(-2, 2, 10, "First increment is beyond stop value")
        assertEquals(listOf(-2), sequence(ex, 10))
    }

    @Test
    fun testStartMoreThanStopPositiveIncrement() {
        val ex = Example(2, -2, 1, "Start more than stop: positive increment")
        assertEquals(listOf(2), sequence(ex, 10))
    }

    @Test
    fun testStartEqualStopPositiveIncrement() {
        val ex = Example(2, 2, 1, "Start equal stop: positive increment")
        assertEquals(listOf(2), sequence(ex, 10))
    }

    @Test
    fun testStartEqualStopNegativeIncrement() {
        val ex = Example(2, 2, -1, "Start equal stop: negative increment")
        assertEquals(listOf(2), sequence(ex, 10))
    }


    @Test
    fun testStartEqualStopZeroIncrement() {
        val ex = Example(2, 2, 0, "Start equal stop: zero increment")
        assertEquals(listOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2), sequence(ex, 10))
    }

    @Test
    fun testStartEqualStopEqualZeroZeroIncrement() {
        val ex = Example(0, 0, 0, "Start equal stop equal zero: zero increment")
        assertEquals(listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), sequence(ex, 10))
    }


}


data class Example(val start: Int, val stop: Int, val incr: Int, val comment: String)


