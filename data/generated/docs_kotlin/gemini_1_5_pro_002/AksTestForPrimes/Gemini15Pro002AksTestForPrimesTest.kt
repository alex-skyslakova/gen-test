import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.lang.IllegalArgumentException

class AksTestForPrimesTest {

    @Test
    fun testBinomial_negativeInput_throwsException() {
        assertThrows(IllegalArgumentException::class.java) { binomial(-1, 1) }
        assertThrows(IllegalArgumentException::class.java) { binomial(1, -1) }
    }

    @Test
    fun testBinomial_kZero_returnsOne() {
        assertEquals(1, binomial(0, 0))
        assertEquals(1, binomial(5, 0))
        assertEquals(1, binomial(10, 0))
    }

    @Test
    fun testBinomial_kEqualsN_returnsOne() {
        assertEquals(1, binomial(0, 0))
        assertEquals(1, binomial(5, 5))
        assertEquals(1, binomial(10, 10))
    }

    @Test
    fun testBinomial_validInput_returnsCorrectValue() {
        assertEquals(10, binomial(5, 2))
        assertEquals(21, binomial(7, 2))
        assertEquals(252, binomial(10, 5))
    }


    @Test
    fun testIsPrime_lessThanTwo_returnsFalse() {
        assertFalse(isPrime(0))
        assertFalse(isPrime(1))
        assertFalse(isPrime(-1))

    }

    @Test
    fun testIsPrime_primeNumbers_returnsTrue() {
        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertTrue(isPrime(5))
        assertTrue(isPrime(7))
        assertTrue(isPrime(11))
        assertTrue(isPrime(13))
        assertTrue(isPrime(17))
        assertTrue(isPrime(19))
        assertTrue(isPrime(23))
        assertTrue(isPrime(29))
        assertTrue(isPrime(31))
    }

    @Test
    fun testIsPrime_compositeNumbers_returnsFalse() {
        assertFalse(isPrime(4))
        assertFalse(isPrime(6))
        assertFalse(isPrime(8))
        assertFalse(isPrime(9))
        assertFalse(isPrime(10))
        assertFalse(isPrime(12))
        assertFalse(isPrime(14))
        assertFalse(isPrime(15))
        assertFalse(isPrime(16))
    }


    @Test
    fun testPrimesUnder35() {
        val expectedPrimes = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31)
        val actualPrimes = generatePrimesUnder(35)
        assertEquals(expectedPrimes, actualPrimes)
    }
    
    @Test
    fun testPrimesUnder50() {
        val expectedPrimes = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47)
        val actualPrimes = generatePrimesUnder(50)
        assertEquals(expectedPrimes, actualPrimes)
    }


    private fun generatePrimesUnder(limit: Int): List<Int> {
        val primes = mutableListOf<Int>()
        var p = 2
        do {
            if (isPrime(p)) primes.add(p)
            if (p != 2) p += 2 else p = 3
        } while (p < limit)
        return primes
    }

}

