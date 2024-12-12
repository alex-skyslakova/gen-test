import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AksTestForPrimesTest {

    @Test
    fun testBinomial() {
        // Test edge cases
        assertEquals(1L, binomial(0, 0))
        assertEquals(1L, binomial(1, 0))
        assertEquals(1L, binomial(1, 1))
        assertEquals(1L, binomial(5, 0))
        assertEquals(1L, binomial(5, 5))

        // Test typical cases
        assertEquals(10L, binomial(5, 2))
        assertEquals(20L, binomial(6, 3))
        assertEquals(35L, binomial(7, 3))

        // Test large values
        assertEquals(137846528820L, binomial(40, 20))
    }

    @Test
    fun testIsPrime() {
        // Test edge cases
        assertFalse(isPrime(0))
        assertFalse(isPrime(1))
        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertFalse(isPrime(4))

        // Test typical cases
        assertTrue(isPrime(5))
        assertFalse(isPrime(6))
        assertTrue(isPrime(7))
        assertFalse(isPrime(8))
        assertFalse(isPrime(9))
        assertFalse(isPrime(10))

        // Test large prime and composite numbers
        assertTrue(isPrime(31))
        assertFalse(isPrime(33))
        assertTrue(isPrime(37))
        assertFalse(isPrime(49))
    }

    @Test
    fun testGeneratePrimesUnder35() {
        val primes = mutableListOf<Int>()
        for (p in 2..34) {
            if (isPrime(p)) primes.add(p)
        }
        val expectedPrimes = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31)
        assertEquals(expectedPrimes, primes)
    }

    @Test
    fun testGeneratePrimesUnder50() {
        val primes = mutableListOf<Int>()
        for (p in 2..49) {
            if (isPrime(p)) primes.add(p)
        }
        val expectedPrimes = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47)
        assertEquals(expectedPrimes, primes)
    }
}
