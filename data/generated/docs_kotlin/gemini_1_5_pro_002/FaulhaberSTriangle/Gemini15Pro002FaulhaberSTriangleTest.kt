import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.math.BigInteger

class FaulhaberSTriangleTest {

    @Test
    fun testGcd() {
        assertEquals(5, gcd(10, 5))
        assertEquals(1, gcd(10, 3))
        assertEquals(2, gcd(4, 6))
        assertEquals(17, gcd(17, 51))
        assertEquals(12, gcd(24, 36))
        assertEquals(1, gcd(0,1))
        assertEquals(5, gcd(0,5))
        assertEquals(5, gcd(5,0))
    }

    @Test
    fun testFrac() {
        val frac1 = Frac(1, 2)
        val frac2 = Frac(3, 4)
        assertEquals(Frac(5, 4), frac1 + frac2)
        assertEquals(Frac(-1, 2), -frac1)
        assertEquals(Frac(-1, 4), frac1 - frac2)
        assertEquals(Frac(3, 8), frac1 * frac2)
        assertEquals(Frac(1,2), frac1.abs())
        assertEquals(Frac(-1,2), Frac(-1,2).abs())

        assertEquals(0, Frac(1,2).compareTo(Frac(1,2)))
        assertEquals(-1, Frac(1,2).compareTo(Frac(3,4)))

        assertEquals(Frac(1, 2), Frac(2, 4))
        assertEquals(Frac(0,1), Frac(0,4))
        assertEquals(Frac(0), Frac(0))

        assertEquals(0.5, Frac(1,2).toDouble(), 0.0001)
        assertEquals(0.5.toBigDecimal(), Frac(1,2).toBigDecimal())
    }


    @Test
    fun testBernoulli() {
        assertEquals(Frac(1, 1), bernoulli(0))
        assertEquals(Frac(-1, 2), bernoulli(1))
        assertEquals(Frac(1, 6), bernoulli(2))
        assertEquals(Frac(0, 1), bernoulli(3))
        assertEquals(Frac(-1, 30), bernoulli(4))
    }

    @Test
    fun testBinomial() {
        assertEquals(1, binomial(0, 0))
        assertEquals(1, binomial(5, 0))
        assertEquals(1, binomial(5, 5))
        assertEquals(5, binomial(5, 1))
        assertEquals(10, binomial(5, 2))
        assertEquals(10, binomial(5, 3))

    }

    @Test
    fun testFaulhaberTriangle() {
        assertArrayEquals(arrayOf(Frac(1, 1)), faulhaberTriangle(0))
        assertArrayEquals(arrayOf(Frac(1, 2), Frac(1, 2)), faulhaberTriangle(1))
        assertArrayEquals(arrayOf(Frac(1, 6), Frac(1, 2), Frac(1, 3)), faulhaberTriangle(2))
        assertArrayEquals(arrayOf(Frac(0, 1), Frac(1, 4), Frac(1, 2), Frac(1, 4)), faulhaberTriangle(3))
    }

    @Test
    fun testMainSumCalculation() {
        val k = 17
        val cc = faulhaberTriangle(k)
        val n = 1000
        val nn = BigDecimal(n)
        var np = BigDecimal.ONE
        var sum = BigDecimal.ZERO
        for (c in cc) {
            np *= nn
            sum += np * c.toBigDecimal()
        }
        assertEquals(BigInteger("370371188237525"), sum.toBigInteger())
    }

}
