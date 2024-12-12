import java.math.BigInteger
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LucasLehmerTestKtTest {

    val bigTwo  = BigInteger.valueOf(2L)
    val bigFour = bigTwo * bigTwo

    fun isPrime(n: Int): Boolean { // Copied from the original code for testing purposes
        if (n < 2) return false
        if (n % 2 == 0) return n == 2
        if (n % 3 == 0) return n == 3
        var d : Int = 5
        while (d * d <= n) {
            if (n % d == 0) return false
            d += 2
            if (n % d == 0) return false
            d += 4
        }
        return true
    }


    @Test
    fun testIsPrime() {
        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertTrue(isPrime(5))
        assertTrue(isPrime(7))
        assertTrue(isPrime(13))
        assertFalse(isPrime(1))
        assertFalse(isPrime(4))
        assertFalse(isPrime(6))
        assertFalse(isPrime(9))
        assertFalse(isPrime(15))
    }


    @Test
    fun testLucasLehmerSmallPrimes() {
        assertTrue(isMersennePrime(2))
        assertTrue(isMersennePrime(3))
        assertTrue(isMersennePrime(5))
        assertTrue(isMersennePrime(7))
        assertFalse(isMersennePrime(11)) // Important to test a failing case
    }
    


    fun isMersennePrime(p: Int): Boolean { // Helper function to isolate the Mersenne prime logic
        val m = bigTwo.shiftLeft(p - 1) - BigInteger.ONE
        var s = bigFour
        for (i in 1..p - 2) s = (s * s - bigTwo) % m
        return s == BigInteger.ZERO
    }

   @Test
   fun testLucasLehmerLargePrimes() { //test some larger primes
       assertTrue(isMersennePrime(13))
       assertTrue(isMersennePrime(17))
       assertTrue(isMersennePrime(19))
       assertTrue(isMersennePrime(31))
       assertFalse(isMersennePrime(23)) // Another failing case
   }


}
