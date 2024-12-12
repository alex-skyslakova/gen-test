import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EthiopianMultiplicationTest {

    @Test
    fun testHalve() {
        assertEquals(8, halve(17))
        assertEquals(4, halve(8))
        assertEquals(2, halve(4))
        assertEquals(1, halve(2))
        assertEquals(0, halve(1))
    }

    @Test
    fun testDouble() {
        assertEquals(68, double(34))
        assertEquals(136, double(68))
        assertEquals(272, double(136))
        assertEquals(544, double(272))
    }

    @Test
    fun testIsEven() {
        assertEquals(false, isEven(17))
        assertEquals(true, isEven(8))
        assertEquals(true, isEven(4))
        assertEquals(true, isEven(2))
        assertEquals(false, isEven(1))
    }

    @Test
    fun testEthiopianMultiply() {
        assertEquals(578, ethiopianMultiply(17, 34))
        assertEquals(9801, ethiopianMultiply(99, 99))
        assertEquals(0, ethiopianMultiply(0, 99))
        assertEquals(0, ethiopianMultiply(99, 0))
        assertEquals(1, ethiopianMultiply(1, 1))
        assertEquals(100, ethiopianMultiply(10, 10))
    }
}
