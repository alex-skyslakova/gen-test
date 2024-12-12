import java.math.BigDecimal
import java.math.MathContext
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ArithmeticGeometricMeanCalculatePiTest {

    private val con1024 = MathContext(1024)
    private val bigTwo = BigDecimal(2)
    private val bigFour = bigTwo * bigTwo

    @Test
    fun testBigSqrt() {
        val input = BigDecimal(2)
        val expected = BigDecimal(Math.sqrt(2.0))
        val result = bigSqrt(input, con1024)
        assertEquals(expected.setScale(10, BigDecimal.ROUND_HALF_UP), result.setScale(10, BigDecimal.ROUND_HALF_UP))
    }

    @Test
    fun testMain() {
        val piString = "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679"
        val expectedPi = BigDecimal(piString).setScale(100, BigDecimal.ROUND_HALF_UP)

        val pi = calculatePi()
        assertEquals(expectedPi, pi.setScale(100, BigDecimal.ROUND_HALF_UP))
    }

    @Test
    fun testConvergence() {
        var a = BigDecimal.ONE
        var g = a.divide(bigSqrt(bigTwo, con1024), con1024)
        var t: BigDecimal
        var sum = BigDecimal.ZERO
        var pow = bigTwo
        var iterations = 0

        while (a != g && iterations < 1000) {
            t = (a + g).divide(bigTwo, con1024)
            g = bigSqrt(a * g, con1024)
            a = t
            pow *= bigTwo
            sum += (a * a - g * g) * pow
            iterations++
        }

        assertNotEquals(0, iterations, "The algorithm did not converge within 1000 iterations")
    }

    private fun calculatePi(): BigDecimal {
        var a = BigDecimal.ONE
        var g = a.divide(bigSqrt(bigTwo, con1024), con1024)
        var t: BigDecimal
        var sum = BigDecimal.ZERO
        var pow = bigTwo

        while (a != g) {
            t = (a + g).divide(bigTwo, con1024)
            g = bigSqrt(a * g, con1024)
            a = t
            pow *= bigTwo
            sum += (a * a - g * g) * pow
        }

        return (bigFour * a * a).divide(BigDecimal.ONE - sum, con1024)
    }
}
