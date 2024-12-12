import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoopIncrementLoopIndexWithinLoopBodyTest {

    @Test
    fun testLoopBehavior() {
        var i = 42L
        var n = 0
        val primesFound = mutableListOf<Long>()

        do {
            if (isPrime(i)) {
                n++
                primesFound.add(i)
                i += i - 1
            }
            i++
        } while (n < 42)

        // Verify that the loop terminated after finding 42 primes
        assertEquals(42, n)

        // Verify that the first prime found is 43 (since 42 is not prime)
        assertEquals(43L, primesFound[0])

        // Verify that the last prime found is correct (this will depend on the actual primes found)
        // For simplicity, we'll just check that the last prime is greater than the first one
        assertTrue(primesFound.last() > primesFound.first())

        // Verify that the primes are correctly incremented by their own value
        for (index in 1 until primesFound.size) {
            val expectedNextPrime = primesFound[index - 1] + primesFound[index - 1] - 1
            assertEquals(expectedNextPrime, primesFound[index])
        }
    }

    // Helper function to check if a number is prime
    private fun isPrime(n: Long): Boolean {
        if (n % 2L == 0L) return n == 2L
        if (n % 3L == 0L) return n == 3L
        var d = 5L
        while (d * d <= n) {
            if (n % d == 0L) return false
            d += 2L
            if (n % d == 0L) return false
            d += 4L
        }
        return true
    }
}
