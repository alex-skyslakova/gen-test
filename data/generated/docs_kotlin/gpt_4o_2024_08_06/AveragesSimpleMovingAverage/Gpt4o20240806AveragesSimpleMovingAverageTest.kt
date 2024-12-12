import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SimpleMovingAverageTest {

    @Test
    fun testMovingAverageWithValidPeriod() {
        val sma3 = initMovingAverage(3)
        assertEquals(1.0, sma3(1.0), 0.001)
        assertEquals(1.5, sma3(2.0), 0.001)
        assertEquals(2.0, sma3(3.0), 0.001)
        assertEquals(3.0, sma3(4.0), 0.001)
        assertEquals(4.0, sma3(5.0), 0.001)
    }

    @Test
    fun testMovingAverageWithDifferentPeriods() {
        val sma2 = initMovingAverage(2)
        val sma4 = initMovingAverage(4)
        
        assertEquals(1.0, sma2(1.0), 0.001)
        assertEquals(1.5, sma2(2.0), 0.001)
        assertEquals(2.5, sma2(3.0), 0.001)
        
        assertEquals(1.0, sma4(1.0), 0.001)
        assertEquals(1.5, sma4(2.0), 0.001)
        assertEquals(2.0, sma4(3.0), 0.001)
        assertEquals(2.5, sma4(4.0), 0.001)
        assertEquals(3.5, sma4(5.0), 0.001)
    }

    @Test
    fun testMovingAverageWithSingleElementPeriod() {
        val sma1 = initMovingAverage(1)
        assertEquals(1.0, sma1(1.0), 0.001)
        assertEquals(2.0, sma1(2.0), 0.001)
        assertEquals(3.0, sma1(3.0), 0.001)
    }

    @Test
    fun testMovingAverageWithInvalidPeriod() {
        assertFailsWith<IllegalArgumentException> {
            initMovingAverage(0)
        }
        assertFailsWith<IllegalArgumentException> {
            initMovingAverage(-1)
        }
    }

    @Test
    fun testIndependentStreams() {
        val sma3 = initMovingAverage(3)
        val sma5 = initMovingAverage(5)

        assertEquals(1.0, sma3(1.0), 0.001)
        assertEquals(1.0, sma5(1.0), 0.001)

        assertEquals(1.5, sma3(2.0), 0.001)
        assertEquals(1.5, sma5(2.0), 0.001)

        assertEquals(2.0, sma3(3.0), 0.001)
        assertEquals(2.0, sma5(3.0), 0.001)

        assertEquals(3.0, sma3(4.0), 0.001)
        assertEquals(2.5, sma5(4.0), 0.001)
    }
}
