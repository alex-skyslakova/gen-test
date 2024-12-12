import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class SimpleMovingAverageTest {

    @Test
    fun testInvalidPeriod() {
        assertThrows<IllegalArgumentException> { initMovingAverage(0) }
        assertThrows<IllegalArgumentException> { initMovingAverage(-1) }
    }

    @Test
    fun testSinglePeriod() {
        val sma1 = initMovingAverage(1)
        assertEquals(1.0, sma1(1.0))
        assertEquals(2.0, sma1(2.0))
        assertEquals(3.0, sma1(3.0))
    }

    @Test
    fun testMultiplePeriods() {
        val sma3 = initMovingAverage(3)
        assertEquals(1.0, sma3(1.0))
        assertEquals(1.5, sma3(2.0))
        assertEquals(2.0, sma3(3.0))
        assertEquals(2.6666666666666665, sma3(4.0)) // Floating point precision issue, handled below
        assertEquals(3.3333333333333335, sma3(5.0))
    }


    @Test
    fun testExampleFromMain() {
        val sma4 = initMovingAverage(4)
        val sma5 = initMovingAverage(5)
        val numbers = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 5.0, 4.0, 3.0, 2.0, 1.0)
        val expectedSma4 = listOf(1.0, 1.5, 2.0, 2.5, 3.5, 4.0, 4.0, 3.5, 3.0, 2.0)
        val expectedSma5 = listOf(1.0, 1.5, 2.0, 2.5, 3.0, 3.6, 4.0, 4.0, 3.6, 3.0)

        for (i in numbers.indices) {
            assertEquals(expectedSma4[i], sma4(numbers[i]))
            assertEquals(expectedSma5[i], sma5(numbers[i]))

        }
    }

    @Test
    fun testEmptyInput() {
        val sma2 = initMovingAverage(2)
        assertEquals(0.0, sma2(1.0))  //Initial call with list size 0 is defined to be 0.0 in PseudoCode
        assertEquals(0.5, sma2(0.0))
    }

    @Test
    fun testFloatingPointPrecision() {
        val sma3 = initMovingAverage(3)
        assertEquals(2.6666666666666665, sma3(4.0), 0.000001) // Using delta for comparison
    }

    @Test
    fun testIndependentInstances() {
        val sma2a = initMovingAverage(2)
        val sma2b = initMovingAverage(2)

        sma2a(1.0)
        sma2a(2.0)

        sma2b(3.0)
        sma2b(4.0)

        assertEquals(1.5, sma2a(3.0))
        assertEquals(3.5, sma2b(5.0))
    }


}
