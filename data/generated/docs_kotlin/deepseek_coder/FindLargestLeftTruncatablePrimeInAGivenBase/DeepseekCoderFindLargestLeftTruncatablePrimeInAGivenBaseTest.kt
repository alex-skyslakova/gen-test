import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class LeftTruncatablePrimeTest {

    @Test
    fun testNextLeftTruncatablePrimes() {
        // Base 10, starting from 0
        val base10Primes = nextLeftTruncatablePrimes(BigInteger.ZERO, 10, 100)
        val expectedBase10Primes = listOf(
            BigInteger("2"), BigInteger("3"), BigInteger("5"), BigInteger("7"),
            BigInteger("13"), BigInteger("17"), BigInteger("23"), BigInteger("37"),
            BigInteger("43"), BigInteger("47"), BigInteger("53"), BigInteger("67"),
            BigInteger("73"), BigInteger("83"), BigInteger("97")
        )
        assertEquals(expectedBase10Primes, base10Primes)

        // Base 3, starting from 0
        val base3Primes = nextLeftTruncatablePrimes(BigInteger.ZERO, 3, 100)
        val expectedBase3Primes = listOf(BigInteger("2"))
        assertEquals(expectedBase3Primes, base3Primes)
    }

    @Test
    fun testLargestLeftTruncatablePrime() {
        // Base 10
        val largestBase10Prime = largestLeftTruncatablePrime(10, 100)
        assertNotNull(largestBase10Prime)
        assertEquals(BigInteger("357686312646216567629137"), largestBase10Prime)

        // Base 3
        val largestBase3Prime = largestLeftTruncatablePrime(3, 100)
        assertNotNull(largestBase3Prime)
        assertEquals(BigInteger("23"), largestBase3Prime)

        // Base 2 (invalid base, should return null)
        val largestBase2Prime = largestLeftTruncatablePrime(2, 100)
        assertNull(largestBase2Prime)
    }

    @Test
    fun testLargestLeftTruncatablePrimeWithInvalidBase() {
        // Base 1 (invalid base, should return null)
        val largestBase1Prime = largestLeftTruncatablePrime(1, 100)
        assertNull(largestBase1Prime)
    }

    @Test
    fun testLargestLeftTruncatablePrimeWithHighCertainty() {
        // Base 10 with high certainty
        val largestBase10PrimeHighCertainty = largestLeftTruncatablePrime(10, 1000)
        assertNotNull(largestBase10PrimeHighCertainty)
        assertEquals(BigInteger("357686312646216567629137"), largestBase10PrimeHighCertainty)
    }

    @Test
    fun testLargestLeftTruncatablePrimeWithLowCertainty() {
        // Base 10 with low certainty
        val largestBase10PrimeLowCertainty = largestLeftTruncatablePrime(10, 10)
        assertNotNull(largestBase10PrimeLowCertainty)
        assertEquals(BigInteger("357686312646216567629137"), largestBase10PrimeLowCertainty)
    }
}
