import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class FractranTest {

    @Test
    fun testFractionMultiplication() {
        val fraction = Fraction(BigInteger.valueOf(3), BigInteger.valueOf(2))
        val result = fraction * BigInteger.valueOf(4)
        assertEquals(Fraction(BigInteger.valueOf(12), BigInteger.valueOf(2)), result)
    }

    @Test
    fun testFractionIsIntegral() {
        val integralFraction = Fraction(BigInteger.valueOf(4), BigInteger.valueOf(2))
        val nonIntegralFraction = Fraction(BigInteger.valueOf(5), BigInteger.valueOf(2))
        assertEquals(true, integralFraction.isIntegral())
        assertEquals(false, nonIntegralFraction.isIntegral())
    }

    @Test
    fun testStringToFraction() {
        val fraction = "3/2".toFraction()
        assertEquals(Fraction(BigInteger.valueOf(3), BigInteger.valueOf(2)), fraction)
    }

    @Test
    fun testBigIntegerIsPowerOfTwo() {
        val powerOfTwo = BigInteger.valueOf(16)
        val notPowerOfTwo = BigInteger.valueOf(15)
        assertEquals(true, powerOfTwo.isPowerOfTwo)
        assertEquals(false, notPowerOfTwo.isPowerOfTwo)
    }

    @Test
    fun testFractranFirstTwentyNumbers() {
        val program = "17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1"
        val expected = listOf(2, 15, 825, 725, 1925, 2275, 425, 390, 330, 290, 770, 910, 110, 130, 15, 170, 190, 210, 230, 250)
        val result = fractran(program, 2, 20, false)
        assertEquals(expected, result)
    }

    @Test
    fun testFractranFirstTwentyPrimes() {
        val program = "17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1"
        val expected = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71)
        val result = fractran(program, 2, 20, true)
        assertEquals(expected, result)
    }

    @Test
    fun testFractranLimitReached() {
        val program = "17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1"
        val result = fractran(program, 2, 5, false)
        assertEquals(5, result.size)
    }

    @Test
    fun testFractranNoValidFraction() {
        val program = "2/3 4/5" // No fraction will produce an integer when multiplied by 2
        val result = fractran(program, 2, 5, false)
        assertEquals(listOf(2), result)
    }
}
