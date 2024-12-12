import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CountInFactorsTest {

    @Test
    fun testIsPrime() {
        assertEquals(false, isPrime(1))
        assertEquals(true, isPrime(2))
        assertEquals(true, isPrime(3))
        assertEquals(false, isPrime(4))
        assertEquals(true, isPrime(5))
        assertEquals(false, isPrime(6))
        assertEquals(true, isPrime(7))
        assertEquals(false, isPrime(9))
        assertEquals(true, isPrime(11))
        assertEquals(false, isPrime(12))
        assertEquals(true, isPrime(13))
        assertEquals(false, isPrime(15))
        assertEquals(true, isPrime(17))
        assertEquals(false, isPrime(18))
        assertEquals(true, isPrime(19))
    }

    @Test
    fun testGetPrimeFactors() {
        assertEquals(listOf(1), getPrimeFactors(1))
        assertEquals(listOf(2), getPrimeFactors(2))
        assertEquals(listOf(3), getPrimeFactors(3))
        assertEquals(listOf(2, 2), getPrimeFactors(4))
        assertEquals(listOf(5), getPrimeFactors(5))
        assertEquals(listOf(2, 3), getPrimeFactors(6))
        assertEquals(listOf(7), getPrimeFactors(7))
        assertEquals(listOf(2, 2, 2), getPrimeFactors(8))
        assertEquals(listOf(3, 3), getPrimeFactors(9))
        assertEquals(listOf(2, 5), getPrimeFactors(10))
        assertEquals(listOf(11), getPrimeFactors(11))
        assertEquals(listOf(2, 2, 3), getPrimeFactors(12))
        assertEquals(listOf(13), getPrimeFactors(13))
        assertEquals(listOf(2, 7), getPrimeFactors(14))
        assertEquals(listOf(3, 5), getPrimeFactors(15))
        assertEquals(listOf(2, 2, 2, 2, 2, 67), getPrimeFactors(2144))
        assertEquals(listOf(2, 3, 106), getPrimeFactors(6358))
    }

    @Test
    fun testEdgeCases() {
        assertEquals(emptyList<Int>(), getPrimeFactors(0))
        assertEquals(emptyList<Int>(), getPrimeFactors(-1))
        assertEquals(emptyList<Int>(), getPrimeFactors(-10))
    }
}
