import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class EthiopianMultiplicationTest {

    @Test
    fun testHalve() {
        assertEquals(8, halve(16))
        assertEquals(5, halve(10))
        assertEquals(1, halve(3)) 
    }

    @Test
    fun testDouble() {
        assertEquals(32, double(16))
        assertEquals(20, double(10))
        assertEquals(6, double(3))
    }

    @Test
    fun testIsEven() {
        assertTrue(isEven(16))
        assertTrue(isEven(10))
        assertFalse(isEven(3))
        assertTrue(isEven(0))
    }

    @Test
    fun testEthiopianMultiply() {
        assertEquals(578, ethiopianMultiply(17, 34))
        assertEquals(9801, ethiopianMultiply(99, 99))
        assertEquals(0, ethiopianMultiply(0, 10))
        assertEquals(0, ethiopianMultiply(10, 0))
        assertEquals(21, ethiopianMultiply(7,3))
        assertEquals(1, ethiopianMultiply(1,1))

    }
}
