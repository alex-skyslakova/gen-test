import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.math.BigInteger

class EgyptianFractionsTest {

    @Test
    fun testSpecificFractions() {
        assertEquals(listOf(Frac(1, 2), Frac(1, 3), Frac(1, 16)), Frac(43, 48).toEgyptian())
        assertEquals(listOf(Frac(1, 25), Frac(1, 305)), Frac(5, 121).toEgyptian())
        assertEquals(listOf(Frac(34, 1), Frac(1, 6), Frac(1, 354)), Frac(2014, 59).toEgyptian())
    }

    @Test
    fun testZeroFraction() {
        assertEquals(listOf(Frac(0, 1)), Frac(0, 5).toEgyptian())
    }

    @Test
    fun testWholeNumberFraction() {
        assertEquals(listOf(Frac(5, 1)), Frac(5, 1).toEgyptian())
    }

    @Test
    fun testNegativeFractions() {
        assertEquals(listOf(Frac(-1, 2), Frac(-1, 3), Frac(-1, 16)), Frac(-43, 48).toEgyptian())
        assertEquals(listOf(Frac(1, 2), Frac(1, 3), Frac(1, 16)), Frac(43, -48).toEgyptian())
        assertEquals(listOf(Frac(-1, 2), Frac(-1, 3), Frac(-1, 16)), Frac(-43, -48).toEgyptian())

    }


    @Test
    fun testImproperFraction() {
        assertEquals(listOf(Frac(2, 1), Frac(1, 2)), Frac(5, 2).toEgyptian())
        assertEquals(listOf(Frac(1, 1), Frac(1, 2)), Frac(3, 2).toEgyptian())

    }


    @Test
    fun testGcd() {
        assertEquals(BigInteger.valueOf(5), gcd(BigInteger.valueOf(10), BigInteger.valueOf(5)))
        assertEquals(BigInteger.valueOf(1), gcd(BigInteger.valueOf(17), BigInteger.valueOf(5)))
        assertEquals(BigInteger.valueOf(2), gcd(BigInteger.valueOf(4), BigInteger.valueOf(-6)))
    }

    @Test
    fun testFracEquals() {
        assertTrue(Frac(1, 2) == Frac(2, 4))
        assertFalse(Frac(1, 2) == Frac(1, 3))
        assertFalse(Frac(1, 2) == null)
        assertFalse(Frac(1,2).equals(Any()))
    }


    @Test
    fun testFracCompareTo() {
        assertEquals(0, Frac(1, 2).compareTo(Frac(2, 4)))
        assertEquals(1, Frac(2, 3).compareTo(Frac(1, 2)))
        assertEquals(-1, Frac(1, 3).compareTo(Frac(1, 2)))
    }

    @Test
    fun testFracPlus() {
       assertEquals(Frac(1, 1), Frac(1, 2) + Frac(1, 2))
       assertEquals(Frac(7, 6), Frac(1, 2) + Frac(2, 3))
    }


    @Test
    fun testFracMinus() {
        assertEquals(Frac(0, 1), Frac(1, 2) - Frac(1, 2))
        assertEquals(Frac(-1, 6), Frac(1, 2) - Frac(2, 3))
    }

    @Test
    fun testUnaryMinus() {
        assertEquals(Frac(-1, 2), -Frac(1, 2))
    }

    @Test
    fun testBigDecimalConversion() {

        val expected = 0.895833333333333352663490146707904338836669921875.toBigDecimal()
        assertEquals(expected, Frac(43, 48).toBigDecimal())


    }
}
