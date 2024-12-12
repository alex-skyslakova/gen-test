import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LucasLehmerTest {

    @Test
    fun testIsPrime() {
        // Test small prime numbers
        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertTrue(isPrime(5))
        assertTrue(isPrime(7))
        assertTrue(isPrime(11))
        assertTrue(isPrime(13))

        // Test small composite numbers
        assertFalse(isPrime(1))
        assertFalse(isPrime(4))
        assertFalse(isPrime(6))
        assertFalse(isPrime(8))
        assertFalse(isPrime(9))
        assertFalse(isPrime(10))
        assertFalse(isPrime(12))
        assertFalse(isPrime(14))
        assertFalse(isPrime(15))

        // Test larger prime numbers
        assertTrue(isPrime(17))
        assertTrue(isPrime(19))
        assertTrue(isPrime(23))
        assertTrue(isPrime(29))
        assertTrue(isPrime(31))
        assertTrue(isPrime(37))

        // Test larger composite numbers
        assertFalse(isPrime(20))
        assertFalse(isPrime(21))
        assertFalse(isPrime(22))
        assertFalse(isPrime(24))
        assertFalse(isPrime(25))
        assertFalse(isPrime(26))
        assertFalse(isPrime(27))
        assertFalse(isPrime(28))
        assertFalse(isPrime(30))
        assertFalse(isPrime(32))
        assertFalse(isPrime(33))
        assertFalse(isPrime(34))
        assertFalse(isPrime(35))
        assertFalse(isPrime(36))
        assertFalse(isPrime(38))
        assertFalse(isPrime(39))
    }

    @Test
    fun testLucasLehmerSequence() {
        // Test the Lucas-Lehmer sequence for known Mersenne primes
        val p = 3
        val m = bigTwo.shiftLeft(p - 1) - BigInteger.ONE
        var s = bigFour
        for (i in 1..p - 2) s = (s * s - bigTwo) % m
        assertEquals(BigInteger.ZERO, s)

        val p2 = 5
        val m2 = bigTwo.shiftLeft(p2 - 1) - BigInteger.ONE
        var s2 = bigFour
        for (i in 1..p2 - 2) s2 = (s2 * s2 - bigTwo) % m2
        assertEquals(BigInteger.ZERO, s2)

        val p3 = 7
        val m3 = bigTwo.shiftLeft(p3 - 1) - BigInteger.ONE
        var s3 = bigFour
        for (i in 1..p3 - 2) s3 = (s3 * s3 - bigTwo) % m3
        assertEquals(BigInteger.ZERO, s3)
    }

    @Test
    fun testMainFunction() {
        // Test the main function to ensure it prints the correct number of Mersenne primes
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        main(arrayOf())

        val output = outputStream.toString().trim()
        val expectedOutput = "M3 M5 M7 M13 M17 M19 M31 M61 M89 M107 M127 M521 M607 M1279 M2203 M2281 M3217 M4253 M4423"
        assertEquals(expectedOutput, output)
    }
}
