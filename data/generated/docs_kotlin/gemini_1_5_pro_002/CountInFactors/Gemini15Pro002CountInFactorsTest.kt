import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CountInFactorsTest {

    @Test
    fun testIsPrime() {
        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertTrue(isPrime(5))
        assertTrue(isPrime(7))
        assertTrue(isPrime(11))
        assertTrue(isPrime(67))
        assertFalse(isPrime(1))
        assertFalse(isPrime(4))
        assertFalse(isPrime(6))
        assertFalse(isPrime(8))
        assertFalse(isPrime(9))
        assertFalse(isPrime(10))
    }

    @Test
    fun testGetPrimeFactors() {
        assertEquals(listOf(1), getPrimeFactors(1))
        assertEquals(listOf(2), getPrimeFactors(2))
        assertEquals(listOf(3), getPrimeFactors(3))
        assertEquals(listOf(2, 2), getPrimeFactors(4))
        assertEquals(listOf(2, 3), getPrimeFactors(6))
        assertEquals(listOf(2, 2, 2), getPrimeFactors(8))
        assertEquals(listOf(3, 3), getPrimeFactors(9))
        assertEquals(listOf(2, 5), getPrimeFactors(10))
        assertEquals(listOf(2, 2, 2, 2, 2, 67), getPrimeFactors(2144))
        assertEquals(listOf(2, 3179), getPrimeFactors(6358))
        assertEquals(listOf<Int>(), getPrimeFactors(0))
        assertEquals(listOf<Int>(), getPrimeFactors(-1))
        assertEquals(listOf(17), getPrimeFactors(17))


    }


}
