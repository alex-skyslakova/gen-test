import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PrimeGeneratorTest {

    @Test
    fun `test first twenty primes`() {
        val expectedPrimes = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71)
        val actualPrimes = generatePrimes().take(20).toList()
        assertEquals(expectedPrimes, actualPrimes)
    }

    @Test
    fun `test primes between 100 and 150`() {
        val expectedPrimes = listOf(101, 103, 107, 109, 113, 127, 131, 137, 139, 149)
        val actualPrimes = generatePrimes().filter { it in 100..150 }.toList()
        assertEquals(expectedPrimes, actualPrimes)
    }

    @Test
    fun `test number of primes between 7700 and 8000`() {
        val expectedCount = 30
        val actualCount = generatePrimes().filter { it in 7700..8000 }.count()
        assertEquals(expectedCount, actualCount)
    }

    @Test
    fun `test 10,000th prime`() {
        val expectedPrime = 104729
        val actualPrime = generatePrimes().take(10000).last()
        assertEquals(expectedPrime, actualPrime)
    }

    @Test
    fun `test isPrime function`() {
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
        assertTrue(isPrime(37))
        assertTrue(isPrime(41))
        assertTrue(isPrime(43))
        assertTrue(isPrime(47))
        assertTrue(isPrime(53))
        assertTrue(isPrime(59))
        assertTrue(isPrime(61))
        assertTrue(isPrime(67))
        assertTrue(isPrime(71))
        assertTrue(isPrime(73))
        assertTrue(isPrime(79))
        assertTrue(isPrime(83))
        assertTrue(isPrime(89))
        assertTrue(isPrime(97))

        assertFalse(isPrime(1))
        assertFalse(isPrime(4))
        assertFalse(isPrime(6))
        assertFalse(isPrime(8))
        assertFalse(isPrime(9))
        assertFalse(isPrime(10))
        assertFalse(isPrime(12))
        assertFalse(isPrime(14))
        assertFalse(isPrime(15))
        assertFalse(isPrime(16))
        assertFalse(isPrime(18))
        assertFalse(isPrime(20))
        assertFalse(isPrime(21))
        assertFalse(isPrime(22))
        assertFalse(isPrime(24))
        assertFalse(isPrime(25))
        assertFalse(isPrime(26))
        assertFalse(isPrime(27))
        assertFalse(isPrime(28))
        assertFalse(isPrime(30))
        assertFalse(isPrime(32))
        assertFalse(isPrime(33))
        assertFalse(isPrime(34))
        assertFalse(isPrime(35))
        assertFalse(isPrime(36))
        assertFalse(isPrime(38))
        assertFalse(isPrime(39))
        assertFalse(isPrime(40))
        assertFalse(isPrime(42))
        assertFalse(isPrime(44))
        assertFalse(isPrime(45))
        assertFalse(isPrime(46))
        assertFalse(isPrime(48))
        assertFalse(isPrime(49))
        assertFalse(isPrime(50))
    }
}
