import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AlmostPrimeTest {

    // Helper function to check if a number is k-almost prime
    private fun Int.k_prime(x: Int): Boolean {
        var n = x
        var f = 0
        var p = 2
        while (f < this && p * p <= n) {
            while (0 == n % p) {
                n /= p
                f++
            }
            p++
        }
        return f + (if (n > 1) 1 else 0) == this
    }

    // Helper function to generate the first n k-almost primes
    private fun Int.primes(n: Int): List<Int> {
        var i = 2
        val list = mutableListOf<Int>()
        while (list.size < n) {
            if (this.k_prime(i)) list.add(i)
            i++
        }
        return list
    }

    @Test
    fun test1AlmostPrimes() {
        val expected = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
        assertEquals(expected, 1.primes(10))
    }

    @Test
    fun test2AlmostPrimes() {
        val expected = listOf(4, 6, 9, 10, 14, 15, 21, 22, 25, 26)
        assertEquals(expected, 2.primes(10))
    }

    @Test
    fun test3AlmostPrimes() {
        val expected = listOf(8, 12, 18, 20, 27, 28, 30, 32, 36, 40)
        assertEquals(expected, 3.primes(10))
    }

    @Test
    fun test4AlmostPrimes() {
        val expected = listOf(16, 24, 30, 36, 40, 42, 45, 48, 50, 54)
        assertEquals(expected, 4.primes(10))
    }

    @Test
    fun test5AlmostPrimes() {
        val expected = listOf(32, 48, 60, 72, 80, 84, 90, 96, 100, 108)
        assertEquals(expected, 5.primes(10))
    }
}
