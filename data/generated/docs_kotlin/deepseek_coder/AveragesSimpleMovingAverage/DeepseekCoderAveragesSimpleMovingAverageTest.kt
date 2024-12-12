import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows

class SimpleMovingAverageTest {

    private lateinit var sma4: (Double) -> Double
    private lateinit var sma5: (Double) -> Double

    @BeforeEach
    fun setUp() {
        sma4 = initMovingAverage(4)
        sma5 = initMovingAverage(5)
    }

    @Test
    fun `test period must be positive`() {
        assertThrows<IllegalArgumentException> {
            initMovingAverage(0)
        }
        assertThrows<IllegalArgumentException> {
            initMovingAverage(-1)
        }
    }

    @Test
    fun `test sma4 with initial numbers`() {
        assertEquals(1.0, sma4(1.0))
        assertEquals(1.5, sma4(2.0))
        assertEquals(2.0, sma4(3.0))
        assertEquals(2.5, sma4(4.0))
    }

    @Test
    fun `test sma4 with sliding window`() {
        sma4(1.0)
        sma4(2.0)
        sma4(3.0)
        sma4(4.0)
        assertEquals(3.5, sma4(5.0))
        assertEquals(4.0, sma4(5.0))
        assertEquals(4.5, sma4(4.0))
        assertEquals(4.0, sma4(3.0))
        assertEquals(3.0, sma4(2.0))
        assertEquals(2.0, sma4(1.0))
    }

    @Test
    fun `test sma5 with initial numbers`() {
        assertEquals(1.0, sma5(1.0))
        assertEquals(1.5, sma5(2.0))
        assertEquals(2.0, sma5(3.0))
        assertEquals(2.5, sma5(4.0))
        assertEquals(3.0, sma5(5.0))
    }

    @Test
    fun `test sma5 with sliding window`() {
        sma5(1.0)
        sma5(2.0)
        sma5(3.0)
        sma5(4.0)
        sma5(5.0)
        assertEquals(4.0, sma5(5.0))
        assertEquals(4.2, sma5(4.0))
        assertEquals(4.0, sma5(3.0))
        assertEquals(3.4, sma5(2.0))
        assertEquals(2.6, sma5(1.0))
    }

    @Test
    fun `test sma4 and sma5 independence`() {
        assertEquals(1.0, sma4(1.0))
        assertEquals(1.0, sma5(1.0))
        assertEquals(1.5, sma4(2.0))
        assertEquals(1.5, sma5(2.0))
        assertEquals(2.0, sma4(3.0))
        assertEquals(2.0, sma5(3.0))
        assertEquals(2.5, sma4(4.0))
        assertEquals(2.5, sma5(4.0))
        assertEquals(3.5, sma4(5.0))
        assertEquals(3.0, sma5(5.0))
    }
}
