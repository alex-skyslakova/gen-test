import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class AlmostPrimeTest {

    @Test
    fun testKPrime_1() {
        assertTrue(2.k_prime(1))
        assertTrue(3.k_prime(1))
        assertTrue(5.k_prime(1))
        assertFalse(4.k_prime(1))
        assertFalse(6.k_prime(1))
    }

    @Test
    fun testKPrime_2() {
        assertTrue(2.k_prime(2))
        assertTrue(3.k_prime(2))
        assertTrue(5.k_prime(2))
        assertTrue(7.k_prime(2))
        assertFalse(8.k_prime(2))  // 2*2*2 k=3
        assertTrue(4.k_prime(2))   // 2*2
        assertTrue(6.k_prime(2))   // 2*3
        assertTrue(9.k_prime(2))
        assertTrue(10.k_prime(2))
        assertFalse(12.k_prime(2)) // 2*2*3 k=3

    }

    @Test
    fun testKPrime_3() {
        assertTrue(8.k_prime(3))
        assertTrue(12.k_prime(3))
        assertTrue(18.k_prime(3))
        assertFalse(4.k_prime(3))
        assertFalse(6.k_prime(3))
    }


    @Test
    fun testKPrime_EdgeCases() {
        assertTrue(1.k_prime(0))
        assertFalse(2.k_prime(0))
        assertFalse(0.k_prime(1)) // shouldn't loop infinitely
        assertTrue(100.k_prime(2))
        
        assertTrue(16.k_prime(4)) // 2^4

        assertFalse(20.k_prime(3)) // 2*2*5

        assertTrue(32.k_prime(5)) // 2^5
        assertFalse(24.k_prime(4)) // 2^3*3 is 3-prime not 4-prime

    }
    

    @Test
    fun testPrimes_1() {
        assertEquals(listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29), 1.primes(10))
    }


    @Test
    fun testPrimes_2() {
        val expected = listOf(4, 6, 9, 10, 14, 15, 21, 22, 25, 26)
        assertEquals(expected, 2.primes(10))
    }

    @Test
    fun testPrimes_3() {
       val expected = listOf(8, 12, 18, 20, 27, 28, 30, 42, 44, 45)
        assertEquals(expected, 3.primes(10))
    }


    fun Int.k_prime(x: Int): Boolean {
        var n = x
        var f = 0
        var p = 2
        while (f < this && p * p <= n) {
            while (0 == n % p) { n /= p; f++ }
            p++
        }
        return f + (if (n > 1) 1 else 0) == this
    }


    fun Int.primes(n : Int) : List<Int> {
        var i = (if (this == 0) 1 else 2) // handle k=0 which would loop infinitely in the original code otherwise, returning 1 only for k=0 otherwise starting search from 2.
        var list = mutableListOf<Int>()
        while (list.size < n) {
            if (k_prime(i)) list.add(i)
            i++
        }
        return list
    }
}

