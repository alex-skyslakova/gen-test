import kotlin.test.Test
import kotlin.test.assertEquals

class LongPrimesTest {

    @Test
    fun testSieve() {
        val primesUpTo10 = sieve(10)
        assertEquals(listOf(3, 5, 7), primesUpTo10)

        val primesUpTo20 = sieve(20)
        assertEquals(listOf(3, 5, 7, 11, 13, 17, 19), primesUpTo20)

        val primesUpTo50 = sieve(50)
        assertEquals(listOf(3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47), primesUpTo50)
    }

    @Test
    fun testFindPeriod() {
        assertEquals(6, findPeriod(7))  // 1/7 = 0.142857 (period 6)
        assertEquals(16, findPeriod(17)) // 1/17 = 0.0588235294117647 (period 16)
        assertEquals(18, findPeriod(19)) // 1/19 = 0.052631578947368421 (period 18)
    }

    @Test
    fun testLongPrimesUpTo500() {
        val primes = sieve(500)
        val longPrimes = primes.filter { findPeriod(it) == it - 1 }
        assertEquals(listOf(7, 17, 19, 23, 29, 47, 59, 61, 97, 109, 113, 131, 149, 167, 179, 181, 193, 223, 229, 233, 257, 263, 269, 313, 337, 367, 389, 419, 421, 433, 461, 463, 487), longPrimes)
    }

    @Test
    fun testNumberOfLongPrimes() {
        val primes = sieve(64000)
        val longPrimes = primes.filter { findPeriod(it) == it - 1 }

        val numbers = listOf(500, 1000, 2000, 4000, 8000, 16000, 32000, 64000)
        val expectedCounts = listOf(33, 60, 108, 199, 367, 671, 1223, 2310)

        var index = 0
        var count = 0
        val totals = IntArray(numbers.size)
        for (longPrime in longPrimes) {
            if (longPrime > numbers[index]) {
                totals[index++] = count
            }
            count++
        }
        totals[numbers.lastIndex] = count

        assertEquals(expectedCounts, totals.toList())
    }
}
