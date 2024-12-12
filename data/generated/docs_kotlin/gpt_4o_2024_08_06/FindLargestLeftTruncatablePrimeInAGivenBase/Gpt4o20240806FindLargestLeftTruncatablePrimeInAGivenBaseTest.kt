import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger

class LeftTruncatablePrimeTest {

    @Test
    fun testNextLeftTruncatablePrimes_Base10() {
        val result = nextLeftTruncatablePrimes(BigInteger.ZERO, 10, 10)
        val expected = listOf(BigInteger("2"), BigInteger("3"), BigInteger("5"), BigInteger("7"))
        assertEquals(expected, result)
    }

    @Test
    fun testNextLeftTruncatablePrimes_Base3() {
        val result = nextLeftTruncatablePrimes(BigInteger.ZERO, 3, 10)
        val expected = listOf(BigInteger("2"))
        assertEquals(expected, result)
    }

    @Test
    fun testLargestLeftTruncatablePrime_Base10() {
        val result = largestLeftTruncatablePrime(10, 10)
        val expected = BigInteger("739397")
        assertEquals(expected, result)
    }

    @Test
    fun testLargestLeftTruncatablePrime_Base3() {
        val result = largestLeftTruncatablePrime(3, 10)
        val expected = BigInteger("23")
        assertEquals(expected, result)
    }

    @Test
    fun testLargestLeftTruncatablePrime_Base2() {
        val result = largestLeftTruncatablePrime(2, 10)
        assertNull(result)
    }

    @Test
    fun testLargestLeftTruncatablePrime_Base4() {
        val result = largestLeftTruncatablePrime(4, 10)
        val expected = BigInteger("47")
        assertEquals(expected, result)
    }

    @Test
    fun testLargestLeftTruncatablePrime_Base5() {
        val result = largestLeftTruncatablePrime(5, 10)
        val expected = BigInteger("313")
        assertEquals(expected, result)
    }

    @Test
    fun testLargestLeftTruncatablePrime_InvalidBase() {
        val result = largestLeftTruncatablePrime(1, 10)
        assertNull(result)
    }

    @Test
    fun testLargestLeftTruncatablePrime_HighCertainty() {
        val result = largestLeftTruncatablePrime(10, 100)
        val expected = BigInteger("739397")
        assertEquals(expected, result)
    }
}
