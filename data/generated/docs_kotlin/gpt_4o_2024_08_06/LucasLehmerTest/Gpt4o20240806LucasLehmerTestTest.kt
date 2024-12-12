import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class LucasLehmerTest {

    @Test
    fun testIsPrime() {
        assertEquals(false, isPrime(0))
        assertEquals(false, isPrime(1))
        assertEquals(true, isPrime(2))
        assertEquals(true, isPrime(3))
        assertEquals(false, isPrime(4))
        assertEquals(true, isPrime(5))
        assertEquals(false, isPrime(6))
        assertEquals(true, isPrime(7))
        assertEquals(false, isPrime(9))
        assertEquals(true, isPrime(11))
        assertEquals(false, isPrime(15))
        assertEquals(true, isPrime(17))
        assertEquals(false, isPrime(18))
        assertEquals(true, isPrime(19))
    }

    @Test
    fun testLucasLehmer() {
        val expectedMersennePrimes = listOf(3, 5, 7, 13, 17, 19, 31, 61, 89, 107, 127, 521, 607, 1279, 2203, 2281, 3217, 4253, 4423)
        val actualMersennePrimes = mutableListOf<Int>()
        var count = 0
        var p = 3
        var s: BigInteger
        var m: BigInteger
        while (true) {
            m = bigTwo.shiftLeft(p - 1) - BigInteger.ONE
            s = bigFour
            for (i in 1..p - 2) s = (s * s - bigTwo) % m
            if (s == BigInteger.ZERO) {
                count += 1
                actualMersennePrimes.add(p)
                if (count == MAX) {
                    break
                }
            }
            while (true) {
                p += 2
                if (isPrime(p)) break
            }
        }
        assertEquals(expectedMersennePrimes.subList(0, MAX), actualMersennePrimes)
    }
}
