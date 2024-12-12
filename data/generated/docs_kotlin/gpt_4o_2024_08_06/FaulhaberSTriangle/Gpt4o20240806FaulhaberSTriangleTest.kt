import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class FaulhaberSTriangleTest {

    @Test
    fun testFracInitialization() {
        val frac1 = Frac(1, 2)
        assertEquals(1, frac1.num)
        assertEquals(2, frac1.denom)

        val frac2 = Frac(2, 4)
        assertEquals(1, frac2.num)
        assertEquals(2, frac2.denom)

        val frac3 = Frac(-2, -4)
        assertEquals(1, frac3.num)
        assertEquals(2, frac3.denom)

        val frac4 = Frac(-2, 4)
        assertEquals(-1, frac4.num)
        assertEquals(2, frac4.denom)

        val frac5 = Frac(0, 4)
        assertEquals(0, frac5.num)
        assertEquals(1, frac5.denom)
    }

    @Test
    fun testFracOperations() {
        val frac1 = Frac(1, 2)
        val frac2 = Frac(1, 3)

        assertEquals(Frac(5, 6), frac1 + frac2)
        assertEquals(Frac(1, 6), frac1 - frac2)
        assertEquals(Frac(1, 6), frac1 * frac2)
        assertEquals(Frac(-1, 2), -frac1)
    }

    @Test
    fun testFracComparison() {
        val frac1 = Frac(1, 2)
        val frac2 = Frac(2, 4)
        val frac3 = Frac(1, 3)

        assertTrue(frac1 == frac2)
        assertTrue(frac1 > frac3)
        assertTrue(frac3 < frac1)
    }

    @Test
    fun testBernoulliNumbers() {
        assertEquals(Frac(1, 1), bernoulli(0))
        assertEquals(Frac(-1, 2), bernoulli(1))
        assertEquals(Frac(1, 6), bernoulli(2))
        assertEquals(Frac(0, 1), bernoulli(3))
        assertEquals(Frac(-1, 30), bernoulli(4))
    }

    @Test
    fun testBinomialCoefficients() {
        assertEquals(1, binomial(0, 0))
        assertEquals(1, binomial(5, 0))
        assertEquals(5, binomial(5, 1))
        assertEquals(10, binomial(5, 2))
        assertEquals(10, binomial(5, 3))
        assertEquals(5, binomial(5, 4))
        assertEquals(1, binomial(5, 5))
    }

    @Test
    fun testFaulhaberTriangle() {
        val firstRow = faulhaberTriangle(0)
        assertArrayEquals(arrayOf(Frac(1, 1)), firstRow)

        val secondRow = faulhaberTriangle(1)
        assertArrayEquals(arrayOf(Frac(1, 2), Frac(1, 2)), secondRow)

        val thirdRow = faulhaberTriangle(2)
        assertArrayEquals(arrayOf(Frac(1, 6), Frac(1, 2), Frac(1, 3)), thirdRow)
    }

    @Test
    fun testSumOfPowers() {
        val k = 17
        val n = 1000
        val cc = faulhaberTriangle(k)
        val nn = BigDecimal(n)
        var np = BigDecimal.ONE
        var sum = BigDecimal.ZERO
        for (c in cc) {
            np *= nn
            sum += np * c.toBigDecimal()
        }
        assertEquals(BigDecimal("500500250166417000"), sum.toBigInteger())
    }
}
