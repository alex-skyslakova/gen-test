import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.math.sqrt
import kotlin.math.E
import kotlin.math.PI

class ContinuedFractionTest {

    private val epsilon = 1e-9

    @Test
    fun testSqrt2() {
        val sqrt2Func: Func = { n -> intArrayOf(if (n > 0) 2 else 1, 1) }
        val calculatedSqrt2 = calc(sqrt2Func, 200)
        assertEquals(sqrt(2.0), calculatedSqrt2, epsilon, "Calculated sqrt(2) is not accurate")
    }

    @Test
    fun testNapierConstant() {
        val eFunc: Func = { n -> intArrayOf(if (n > 0) n else 2, if (n > 1) n - 1 else 1) }
        val calculatedE = calc(eFunc, 200)
        assertEquals(E, calculatedE, epsilon, "Calculated Napier's constant (e) is not accurate")
    }

    @Test
    fun testPi() {
        val piFunc: Func = { n -> intArrayOf(if (n > 0) 6 else 3, (2 * n - 1) * (2 * n - 1)) }
        val calculatedPi = calc(piFunc, 200)
        assertEquals(PI, calculatedPi, epsilon, "Calculated Pi is not accurate")
    }
}
