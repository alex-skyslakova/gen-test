import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PrimeGeneratorTest {

    private val primeGenerator = generatePrimes()

    @Test
    fun testFirstTwentyPrimes() {
        val expectedFirstTwentyPrimes = listOf(
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
            31, 37, 41, 43, 47, 53, 59, 61, 67, 71
        )
        val actualFirstTwentyPrimes = primeGenerator.take(20).toList()
        assertEquals(expectedFirstTwentyPrimes, actualFirstTwentyPrimes)
    }

    @Test
    fun testPrimesBetween100And150() {
        val expectedPrimes = listOf(101, 103, 107, 109, 113, 127, 131, 137, 139, 149)
        val actualPrimes = primeGenerator.filter { it in 100..150 }.toList()
        assertEquals(expectedPrimes, actualPrimes)
    }

    @Test
    fun testNumberOfPrimesBetween7700And8000() {
        val expectedCount = 21 // Known number of primes between 7700 and 8000
        val actualCount = primeGenerator.filter { it in 7700..8000 }.count()
        assertEquals(expectedCount, actualCount)
    }

    @Test
    fun testTenThousandthPrime() {
        val tenThousandthPrime = 104729 // Known 10,000th prime
        val actualTenThousandthPrime = primeGenerator.take(10000).last()
        assertEquals(tenThousandthPrime, actualTenThousandthPrime)
    }

    @Test
    fun testIsPrimeFunction() {
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
    }
}
