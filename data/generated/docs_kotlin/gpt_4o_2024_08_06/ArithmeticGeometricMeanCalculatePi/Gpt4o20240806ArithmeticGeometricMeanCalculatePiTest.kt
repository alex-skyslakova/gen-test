import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.MathContext

class ArithmeticGeometricMeanCalculatePiTest {

    private val con1024 = MathContext(1024)
    private val bigTwo = BigDecimal(2)

    @Test
    fun testBigSqrt() {
        val value = BigDecimal(2)
        val expected = BigDecimal(Math.sqrt(2.0), con1024)
        val result = bigSqrt(value, con1024)
        assertEquals(expected, result, "The square root calculation is incorrect")
    }

    @Test
    fun testPiCalculation() {
        val expectedPi = BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679", con1024)
        val calculatedPi = calculatePi()
        assertEquals(expectedPi, calculatedPi.setScale(100, BigDecimal.ROUND_HALF_UP), "The π calculation is incorrect")
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
        return (bigTwo * bigTwo * a * a).divide(BigDecimal.ONE - sum, con1024)
    }
}
