import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CountInFactorsTest {

    @Test
    fun testIsPrime() {
        assertTrue(isPrime(2), "2 is prime")
        assertTrue(isPrime(3), "3 is prime")
        assertFalse(isPrime(4), "4 is not prime")
        assertTrue(isPrime(5), "5 is prime")
        assertFalse(isPrime(6), "6 is not prime")
        assertTrue(isPrime(7), "7 is prime")
        assertFalse(isPrime(8), "8 is not prime")
        assertFalse(isPrime(9), "9 is not prime")
        assertFalse(isPrime(10), "10 is not prime")
        assertTrue(isPrime(11), "11 is prime")
        assertFalse(isPrime(12), "12 is not prime")
        assertTrue(isPrime(13), "13 is prime")
        assertFalse(isPrime(14), "14 is not prime")
        assertFalse(isPrime(15), "15 is not prime")
        assertFalse(isPrime(16), "16 is not prime")
        assertTrue(isPrime(17), "17 is prime")
        assertFalse(isPrime(18), "18 is not prime")
        assertTrue(isPrime(19), "19 is prime")
        assertFalse(isPrime(20), "20 is not prime")
    }

    @Test
    fun testGetPrimeFactors() {
        assertEquals(listOf(1), getPrimeFactors(1), "1 should return [1]")
        assertEquals(listOf(2), getPrimeFactors(2), "2 should return [2]")
        assertEquals(listOf(3), getPrimeFactors(3), "3 should return [3]")
        assertEquals(listOf(2, 2), getPrimeFactors(4), "4 should return [2, 2]")
        assertEquals(listOf(5), getPrimeFactors(5), "5 should return [5]")
        assertEquals(listOf(2, 3), getPrimeFactors(6), "6 should return [2, 3]")
        assertEquals(listOf(7), getPrimeFactors(7), "7 should return [7]")
        assertEquals(listOf(2, 2, 2), getPrimeFactors(8), "8 should return [2, 2, 2]")
        assertEquals(listOf(3, 3), getPrimeFactors(9), "9 should return [3, 3]")
        assertEquals(listOf(2, 5), getPrimeFactors(10), "10 should return [2, 5]")
        assertEquals(listOf(11), getPrimeFactors(11), "11 should return [11]")
        assertEquals(listOf(2, 2, 3), getPrimeFactors(12), "12 should return [2, 2, 3]")
        assertEquals(listOf(13), getPrimeFactors(13), "13 should return [13]")
        assertEquals(listOf(2, 7), getPrimeFactors(14), "14 should return [2, 7]")
        assertEquals(listOf(3, 5), getPrimeFactors(15), "15 should return [3, 5]")
        assertEquals(listOf(2, 2, 2, 2, 2, 67), getPrimeFactors(2144), "2144 should return [2, 2, 2, 2, 2, 67]")
        assertEquals(listOf(2, 3179), getPrimeFactors(6358), "6358 should return [2, 3179]")
    }
}
