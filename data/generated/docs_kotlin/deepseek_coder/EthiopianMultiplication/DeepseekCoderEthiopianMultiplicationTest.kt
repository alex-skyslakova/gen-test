import org.junit.Test
import org.junit.Assert.*

class EthiopianMultiplicationTest {

    @Test
    fun testHalve() {
        assertEquals(5, halve(10))
        assertEquals(0, halve(1))
        assertEquals(1, halve(3))
        assertEquals(2, halve(5))
        assertEquals(0, halve(0))
    }

    @Test
    fun testDouble() {
        assertEquals(20, double(10))
        assertEquals(2, double(1))
        assertEquals(6, double(3))
        assertEquals(10, double(5))
        assertEquals(0, double(0))
    }

    @Test
    fun testIsEven() {
        assertTrue(isEven(10))
        assertFalse(isEven(1))
        assertFalse(isEven(3))
        assertTrue(isEven(4))
        assertTrue(isEven(0))
    }

    @Test
    fun testEthiopianMultiply() {
        assertEquals(578, ethiopianMultiply(17, 34))
        assertEquals(9801, ethiopianMultiply(99, 99))
        assertEquals(0, ethiopianMultiply(0, 5))
        assertEquals(0, ethiopianMultiply(5, 0))
        assertEquals(10, ethiopianMultiply(1, 10))
        assertEquals(10, ethiopianMultiply(10, 1))
        assertEquals(12, ethiopianMultiply(3, 4))
        assertEquals(24, ethiopianMultiply(6, 4))
        assertEquals(21, ethiopianMultiply(7, 3))
    }
}
