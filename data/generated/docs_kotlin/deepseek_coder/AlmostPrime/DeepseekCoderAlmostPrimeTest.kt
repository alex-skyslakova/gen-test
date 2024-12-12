import org.junit.Test
import kotlin.test.assertEquals

class AlmostPrimeTest {

    @Test
    fun testKPrime() {
        // Test cases for k_prime function
        assertEquals(true, 1.k_prime(2))  // 2 is a 1-almost-prime (prime)
        assertEquals(false, 1.k_prime(4)) // 4 is not a 1-almost-prime
        assertEquals(true, 2.k_prime(4))  // 4 is a 2-almost-prime (semiprime)
        assertEquals(false, 2.k_prime(5)) // 5 is not a 2-almost-prime
        assertEquals(true, 3.k_prime(8))  // 8 is a 3-almost-prime (2*2*2)
        assertEquals(false, 3.k_prime(9)) // 9 is not a 3-almost-prime
        assertEquals(true, 4.k_prime(16)) // 16 is a 4-almost-prime (2*2*2*2)
        assertEquals(false, 4.k_prime(15)) // 15 is not a 4-almost-prime
        assertEquals(true, 5.k_prime(32)) // 32 is a 5-almost-prime (2*2*2*2*2)
        assertEquals(false, 5.k_prime(31)) // 31 is not a 5-almost-prime
    }

    @Test
    fun testPrimes() {
        // Test cases for primes function
        assertEquals(listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29), 1.primes(10)) // 1-almost-primes (primes)
        assertEquals(listOf(4, 6, 9, 10, 14, 15, 21, 22, 25, 26), 2.primes(10)) // 2-almost-primes (semiprimes)
        assertEquals(listOf(8, 12, 18, 20, 27, 28, 30, 42, 44, 45), 3.primes(10)) // 3-almost-primes
        assertEquals(listOf(16, 24, 36, 40, 54, 56, 60, 81, 84, 88), 4.primes(10)) // 4-almost-primes
        assertEquals(listOf(32, 48, 72, 80, 108, 112, 120, 162, 168, 176), 5.primes(10)) // 5-almost-primes
    }
}
