import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class EgyptianDivisionTest {

    @Test
    fun testDivideByZero() {
        assertThrows(IllegalArgumentException::class.java) {
            egyptianDivide(10, 0)
        }
    }

    @Test
    fun testDividendLessThanDivisor() {
        val result = egyptianDivide(10, 20)
        assertEquals(0, result.quotient)
        assertEquals(10, result.remainder)
    }

    @Test
    fun testExactDivision() {
        val result = egyptianDivide(100, 10)
        assertEquals(10, result.quotient)
        assertEquals(0, result.remainder)
    }

    @Test
    fun testDivisionWithRemainder() {
        val result = egyptianDivide(580, 34)
        assertEquals(17, result.quotient)
        assertEquals(2, result.remainder)
    }

    @Test
    fun testLargeNumbers() {
        val result = egyptianDivide(1000000, 3456)
        assertEquals(289, result.quotient)
        assertEquals(1336, result.remainder)
    }

    @Test
    fun testNegativeDividend() {
        assertThrows(IllegalArgumentException::class.java) {
            egyptianDivide(-10, 5)
        }
    }

    @Test
    fun testZeroDividend() {
        val result = egyptianDivide(0, 5)
        assertEquals(0, result.quotient)
        assertEquals(0, result.remainder)
    }

    @Test
    fun testSingleStepDivision() {
        val result = egyptianDivide(7, 7)
        assertEquals(1, result.quotient)
        assertEquals(0, result.remainder)
    }

    @Test
    fun testMultipleStepsDivision() {
        val result = egyptianDivide(1023, 3)
        assertEquals(341, result.quotient)
        assertEquals(0, result.remainder)
    }

    @Test
    fun testLargeDividendSmallDivisor() {
        val result = egyptianDivide(1000000, 1)
        assertEquals(1000000, result.quotient)
        assertEquals(0, result.remainder)
    }

    @Test
    fun testLargeDivisorSmallDividend() {
        val result = egyptianDivide(10, 1000000)
        assertEquals(0, result.quotient)
        assertEquals(10, result.remainder)
    }
}
