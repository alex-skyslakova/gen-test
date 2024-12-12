import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class EgyptianDivisionTest {

    @Test
    fun testBasicDivision() {
        val result = egyptianDivide(580, 34)
        assertEquals(17, result.quotient)
        assertEquals(2, result.remainder)
    }

    @Test
    fun testExactDivision() {
        val result = egyptianDivide(100, 10)
        assertEquals(10, result.quotient)
        assertEquals(0, result.remainder)
    }

    @Test
    fun testDividendLessThanDivisor() {
        val result = egyptianDivide(5, 10)
        assertEquals(0, result.quotient)
        assertEquals(5, result.remainder)
    }

    @Test
    fun testDividendEqualToDivisor() {
        val result = egyptianDivide(10, 10)
        assertEquals(1, result.quotient)
        assertEquals(0, result.remainder)
    }

    @Test
    fun testLargeNumbers() {
        val result = egyptianDivide(1000000, 123)
        assertEquals(8130, result.quotient)
        assertEquals(40, result.remainder)
    }

    @Test
    fun testZeroDividend() {
        val result = egyptianDivide(0, 5)
        assertEquals(0, result.quotient)
        assertEquals(0, result.remainder)
    }

    @Test
    fun testNegativeDivisor() {
        assertFailsWith<IllegalArgumentException> {
            egyptianDivide(10, -5)
        }
    }

    @Test
    fun testZeroDivisor() {
        assertFailsWith<IllegalArgumentException> {
            egyptianDivide(10, 0)
        }
    }

    @Test
    fun testNegativeDividend() {
        assertFailsWith<IllegalArgumentException> {
            egyptianDivide(-10, 5)
        }
    }
}
