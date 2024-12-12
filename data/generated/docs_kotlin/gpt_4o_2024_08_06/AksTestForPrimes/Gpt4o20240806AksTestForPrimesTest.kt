import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class AksTestForPrimesTest {

    @Test
    fun testBinomial() {
        assertEquals(1L, binomial(0, 0))
        assertEquals(1L, binomial(5, 0))
        assertEquals(1L, binomial(5, 5))
        assertEquals(5L, binomial(5, 1))
        assertEquals(10L, binomial(5, 2))
        assertEquals(10L, binomial(5, 3))
        assertEquals(5L, binomial(5, 4))
    }

    @Test
    fun testIsPrime() {
        assertFalse(isPrime(0))
        assertFalse(isPrime(1))
        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertFalse(isPrime(4))
        assertTrue(isPrime(5))
        assertFalse(isPrime(6))
        assertTrue(isPrime(7))
        assertFalse(isPrime(8))
        assertFalse(isPrime(9))
        assertFalse(isPrime(10))
        assertTrue(isPrime(11))
        assertFalse(isPrime(12))
        assertTrue(isPrime(13))
        assertFalse(isPrime(14))
        assertFalse(isPrime(15))
        assertFalse(isPrime(16))
        assertTrue(isPrime(17))
        assertFalse(isPrime(18))
        assertTrue(isPrime(19))
        assertFalse(isPrime(20))
        assertFalse(isPrime(21))
        assertFalse(isPrime(22))
        assertTrue(isPrime(23))
        assertFalse(isPrime(24))
        assertFalse(isPrime(25))
        assertFalse(isPrime(26))
        assertFalse(isPrime(27))
        assertFalse(isPrime(28))
        assertTrue(isPrime(29))
        assertFalse(isPrime(30))
        assertTrue(isPrime(31))
        assertFalse(isPrime(32))
        assertFalse(isPrime(33))
        assertFalse(isPrime(34))
    }

    @Test
    fun testGeneratePrimesUnder35() {
        val expectedPrimes = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31)
        val actualPrimes = generatePrimesUnder(35)
        assertEquals(expectedPrimes, actualPrimes)
    }

    @Test
    fun testGeneratePrimesUnder50() {
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
