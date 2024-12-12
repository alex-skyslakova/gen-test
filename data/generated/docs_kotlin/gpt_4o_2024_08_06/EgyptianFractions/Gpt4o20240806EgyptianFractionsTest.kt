import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class EgyptianFractionsTest {

    @Test
    fun testEgyptianFraction_43_48() {
        val fraction = Frac(43, 48)
        val expected = listOf(Frac(1, 2), Frac(1, 3), Frac(1, 16))
        assertEquals(expected, fraction.toEgyptian())
    }

    @Test
    fun testEgyptianFraction_5_121() {
        val fraction = Frac(5, 121)
        val expected = listOf(Frac(1, 25), Frac(1, 757), Frac(1, 763309))
        assertEquals(expected, fraction.toEgyptian())
    }

    @Test
    fun testEgyptianFraction_2014_59() {
        val fraction = Frac(2014, 59)
        val expected = listOf(Frac(34, 1), Frac(1, 2), Frac(1, 3), Frac(1, 13), Frac(1, 4738))
        assertEquals(expected, fraction.toEgyptian())
    }

    @Test
    fun testProperFractionLargestNumberOfTerms() {
        val fraction = Frac(8, 11)
        val expected = listOf(Frac(1, 2), Frac(1, 6), Frac(1, 66))
        assertEquals(expected, fraction.toEgyptian())
    }

    @Test
    fun testProperFractionLargestDenominator() {
        val fraction = Frac(3, 7)
        val expected = listOf(Frac(1, 3), Frac(1, 11), Frac(1, 231))
        assertEquals(expected, fraction.toEgyptian())
    }

    @Test
    fun testImproperFraction() {
        val fraction = Frac(9, 4)
        val expected = listOf(Frac(2, 1), Frac(1, 4))
        assertEquals(expected, fraction.toEgyptian())
    }

    @Test
    fun testZeroNumerator() {
        val fraction = Frac(0, 5)
        val expected = listOf(Frac(0, 1))
        assertEquals(expected, fraction.toEgyptian())
    }

    @Test
    fun testNegativeFraction() {
        val fraction = Frac(-3, 7)
        val expected = listOf(Frac(-1, 3), Frac(-1, 11), Frac(-1, 231))
        assertEquals(expected, fraction.toEgyptian())
    }
}
