import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LongPrimesTest {

    @Test
    fun testSieve() {
        assertEquals(listOf(3, 5, 7, 11, 13, 17, 19, 23, 29), sieve(30))
        assertEquals(listOf<Int>(), sieve(1))
        assertEquals(listOf<Int>(), sieve(0)) //Edge case: zero
        assertEquals(listOf<Int>(), sieve(-1))  //Edge case: negative number

        // Check for larger numbers (without complete enumeration)
        val largePrimes = sieve(1000)
        assertTrue(largePrimes.isNotEmpty())
        assertTrue(largePrimes.all { it > 1 && isPrime(it)}) //Helper function isPrime below
    }


    @Test
    fun testFindPeriod() {
        assertEquals(6, findPeriod(7))
        assertEquals(1, findPeriod(2)) //Non-cyclic prime
        assertEquals(2, findPeriod(3)) //Non-cyclic prime
        assertEquals(16, findPeriod(17))
        assertEquals(18, findPeriod(19))
        assertEquals(22, findPeriod(23))
        assertEquals(1, findPeriod(1)) //Edge case: 1 (not prime but testing for robustness)
    }

    @Test
    fun testLongPrimesIntegration() {
        val limit = 500
        val primes = sieve(limit)
        val actualLongPrimes = primes.filter { findPeriod(it) == it -1 }

        val expectedLongPrimesBelow500 = listOf(7, 17, 19, 23, 29, 47, 59, 61, 97, 109, 113, 131, 149, 167, 179, 181, 193, 223, 229, 233, 257, 263, 269, 313, 337, 367, 379, 383, 389, 419, 433, 461, 487, 491, 499)

        assertEquals(expectedLongPrimesBelow500, actualLongPrimes)

    }

    // Helper function for primality check (not optimized, but sufficient for testing)
    private fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }

}


