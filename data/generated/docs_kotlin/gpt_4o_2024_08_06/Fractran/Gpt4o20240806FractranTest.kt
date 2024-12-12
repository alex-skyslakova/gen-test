import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class FractranTest {

    @Test
    fun `test fraction multiplication`() {
        val fraction = Fraction(BigInteger.valueOf(17), BigInteger.valueOf(91))
        val result = fraction * BigInteger.valueOf(2)
        assertEquals(BigInteger.valueOf(34), result.num)
        assertEquals(BigInteger.valueOf(91), result.denom)
    }

    @Test
    fun `test is integral`() {
        val fraction = Fraction(BigInteger.valueOf(34), BigInteger.valueOf(17))
        assertEquals(true, fraction.isIntegral())

        val nonIntegralFraction = Fraction(BigInteger.valueOf(35), BigInteger.valueOf(17))
        assertEquals(false, nonIntegralFraction.isIntegral())
    }

    @Test
    fun `test string to fraction conversion`() {
        val fraction = "17/91".toFraction()
        assertEquals(BigInteger.valueOf(17), fraction.num)
        assertEquals(BigInteger.valueOf(91), fraction.denom)
    }

    @Test
    fun `test is power of two`() {
        assertEquals(true, BigInteger.valueOf(4).isPowerOfTwo)
        assertEquals(false, BigInteger.valueOf(5).isPowerOfTwo)
    }

    @Test
    fun `test fractran program with non-prime output`() {
        val program = "17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1"
        val result = fractran(program, 2, 10, false)
        val expected = listOf(2, 15, 825, 725, 1925, 2275, 425, 390, 330, 290)
        assertEquals(expected, result)
    }

    @Test
    fun `test fractran program with prime output`() {
        val program = "17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1"
        val result = fractran(program, 2, 10, true)
        val expected = listOf(2, 3, 5, 7, 11, 13, 17, 19)
        assertEquals(expected, result)
    }

    @Test
    fun `test fractran program with limit exceeding sequence`() {
        val program = "17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1"
        val result = fractran(program, 2, 20, false)
        assertEquals(20, result.size)
    }

    @Test
    fun `test fractran program with limit on primes`() {
        val program = "17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1"
        val result = fractran(program, 2, 20, true)
        assertEquals(8, result.size) // Only 8 primes in the first 20 steps
    }
}
