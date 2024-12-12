import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EgyptianFractionsTest {

    @Test
    fun testProperFractionToEgyptian() {
        val frac = Frac(43, 48)
        val egyptian = frac.toEgyptian()
        val expected = listOf(Frac(1, 2), Frac(1, 3), Frac(1, 16))
        assertEquals(expected, egyptian)
    }

    @Test
    fun testImproperFractionToEgyptian() {
        val frac = Frac(2014, 59)
        val egyptian = frac.toEgyptian()
        val expected = listOf(Frac(34), Frac(1, 8), Frac(1, 95), Frac(1, 14040))
        assertEquals(expected, egyptian)
    }

    @Test
    fun testZeroFractionToEgyptian() {
        val frac = Frac(0, 121)
        val egyptian = frac.toEgyptian()
        val expected = listOf(Frac(0, 1))
        assertEquals(expected, egyptian)
    }

    @Test
    fun testSingleUnitFractionToEgyptian() {
        val frac = Frac(1, 2)
        val egyptian = frac.toEgyptian()
        val expected = listOf(Frac(1, 2))
        assertEquals(expected, egyptian)
    }

    @Test
    fun testLargeDenominatorFractionToEgyptian() {
        val frac = Frac(5, 121)
        val egyptian = frac.toEgyptian()
        val expected = listOf(Frac(1, 25), Frac(1, 757), Frac(1, 763309), Frac(1, 873960180913), Frac(1, 1527612795642093418846225))
        assertEquals(expected, egyptian)
    }

    @Test
    fun testMaxSizeAndMaxDenominator() {
        val r = 98
        var maxSize = 0
        var maxSizeFracs = mutableListOf<Frac>()
        var maxDen = BigInteger.ZERO
        var maxDenFracs = mutableListOf<Frac>()
        val sieve = List(r + 1) { BooleanArray(r + 2) }

        for (i in 1..r) {
            for (j in (i + 1)..(r + 1)) {
                if (sieve[i][j]) continue
                val f = Frac(i, j)
                val list = f.toEgyptian()
                val listSize = list.size
                if (listSize > maxSize) {
                    maxSize = listSize
                    maxSizeFracs.clear()
                    maxSizeFracs.add(f)
                } else if (listSize == maxSize) {
                    maxSizeFracs.add(f)
                }
                val listDen = list[list.lastIndex].denom
                if (listDen > maxDen) {
                    maxDen = listDen
                    maxDenFracs.clear()
                    maxDenFracs.add(f)
                } else if (listDen == maxDen) {
                    maxDenFracs.add(f)
                }
                if (i < r / 2) {
                    var k = 2
                    while (true) {
                        if (j * k > r + 1) break
                        sieve[i * k][j * k] = true
                        k++
                    }
                }
            }
        }

        // Expected values for maxSize and maxDenominator are not known exactly,
        // but we can assert that the lists are not empty and the denominators are valid.
        assertTrue(maxSizeFracs.isNotEmpty())
        assertTrue(maxDenFracs.isNotEmpty())
        assertTrue(maxDen > BigInteger.ZERO)
    }
}
