import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AdditivePrimesTest {

    @Test
    fun testIsPrime() {
        // Test small prime numbers
        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertTrue(isPrime(5))
        assertTrue(isPrime(7))
        
        // Test small non-prime numbers
        assertFalse(isPrime(1))
        assertFalse(isPrime(4))
        assertFalse(isPrime(6))
        assertFalse(isPrime(8))
        assertFalse(isPrime(9))
        assertFalse(isPrime(10))
        
        // Test larger prime numbers
        assertTrue(isPrime(11))
        assertTrue(isPrime(13))
        assertTrue(isPrime(17))
        assertTrue(isPrime(19))
        assertTrue(isPrime(23))
        
        // Test larger non-prime numbers
        assertFalse(isPrime(15))
        assertFalse(isPrime(21))
        assertFalse(isPrime(25))
        assertFalse(isPrime(27))
        assertFalse(isPrime(28))
    }

    @Test
    fun testDigitSum() {
        // Test single digit numbers
        assertEquals(0, digitSum(0))
        assertEquals(1, digitSum(1))
        assertEquals(9, digitSum(9))
        
        // Test multiple digit numbers
        assertEquals(10, digitSum(19))  // 1 + 9 = 10
        assertEquals(6, digitSum(123))  // 1 + 2 + 3 = 6
        assertEquals(15, digitSum(555)) // 5 + 5 + 5 = 15
        assertEquals(9, digitSum(234))  // 2 + 3 + 4 = 9
        assertEquals(18, digitSum(99))  // 9 + 9 = 18
    }

    @Test
    fun testAdditivePrimes() {
        val expectedAdditivePrimes = listOf(2, 3, 5, 7, 11, 23, 29, 41, 43, 47, 61, 67, 83, 89)
        val actualAdditivePrimes = mutableListOf<Int>()

        for (i in 2 until 500) {
            if (isPrime(i) && isPrime(digitSum(i))) {
                actualAdditivePrimes.add(i)
            }
        }

        assertEquals(expectedAdditivePrimes, actualAdditivePrimes)
    }
}
