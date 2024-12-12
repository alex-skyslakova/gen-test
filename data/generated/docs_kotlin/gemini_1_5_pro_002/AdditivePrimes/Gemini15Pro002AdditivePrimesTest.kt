import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class AdditivePrimesTest {

    @Test
    fun testIsPrime() {
        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertTrue(isPrime(5))
        assertTrue(isPrime(7))
        assertTrue(isPrime(11))
        assertTrue(isPrime(13))
        assertFalse(isPrime(1))
        assertFalse(isPrime(4))
        assertFalse(isPrime(6))
        assertFalse(isPrime(8))
        assertFalse(isPrime(9))
        assertFalse(isPrime(10))
        assertTrue(isPrime(31))
        assertFalse(isPrime(91))
    }

    @Test
    fun testDigitSum() {
        assertEquals(0, digitSum(0))
        assertEquals(1, digitSum(1))
        assertEquals(5, digitSum(5))
        assertEquals(6, digitSum(15))
        assertEquals(6, digitSum(6))
        assertEquals(12, digitSum(39))
        assertEquals(1, digitSum(100))
        assertEquals(10, digitSum(127))


    }


    @Test
    fun testAdditivePrimesCount() {
        var count = 0
        for (i in 2 until 500) {
            if (isPrime(i) and isPrime(digitSum(i))) {
               count++
            }
        }
        assertEquals(141, count)
    }


    fun isPrime(n: Int): Boolean {
        if (n <= 3) return n > 1
        if (n % 2 == 0 || n % 3 == 0) return false
        var i = 5
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false
            i += 6
        }
        return true
    }

    fun digitSum(n: Int): Int {
        var sum = 0
        var num = n
        while (num > 0) {
            sum += num % 10
            num /= 10
        }
        return sum
    }
}
