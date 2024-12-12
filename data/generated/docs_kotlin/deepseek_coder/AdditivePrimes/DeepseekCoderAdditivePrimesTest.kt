import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class AdditivePrimesTest {

    @Test
    fun testIsPrime() {
        // Test prime numbers
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

        // Test non-prime numbers
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
    }

    @Test
    fun testDigitSum() {
        assertEquals(1, digitSum(1))
        assertEquals(2, digitSum(2))
        assertEquals(3, digitSum(3))
        assertEquals(4, digitSum(4))
        assertEquals(5, digitSum(5))
        assertEquals(6, digitSum(6))
        assertEquals(7, digitSum(7))
        assertEquals(8, digitSum(8))
        assertEquals(9, digitSum(9))
        assertEquals(10, digitSum(19))
        assertEquals(11, digitSum(29))
        assertEquals(12, digitSum(39))
        assertEquals(13, digitSum(49))
        assertEquals(14, digitSum(59))
        assertEquals(15, digitSum(69))
        assertEquals(16, digitSum(79))
        assertEquals(17, digitSum(89))
        assertEquals(18, digitSum(99))
        assertEquals(19, digitSum(199))
        assertEquals(20, digitSum(299))
        assertEquals(21, digitSum(399))
        assertEquals(22, digitSum(499))
    }

    @Test
    fun testAdditivePrimesCount() {
        val additivePrimes = mutableListOf<Int>()
        for (i in 2 until 500) {
            if (isPrime(i) && isPrime(digitSum(i))) {
                additivePrimes.add(i)
            }
        }
        assertEquals(31, additivePrimes.size)
    }

    @Test
    fun testAdditivePrimesList() {
        val expectedAdditivePrimes = listOf(
            2, 3, 5, 7, 11, 23, 29, 41, 43, 47, 61, 67, 83, 89, 101, 113, 131, 137, 139, 151, 157, 173, 179, 191, 193, 197, 199, 223, 227, 229, 241
        )
        val additivePrimes = mutableListOf<Int>()
        for (i in 2 until 500) {
            if (isPrime(i) && isPrime(digitSum(i))) {
                additivePrimes.add(i)
            }
        }
        assertEquals(expectedAdditivePrimes, additivePrimes)
    }
}
