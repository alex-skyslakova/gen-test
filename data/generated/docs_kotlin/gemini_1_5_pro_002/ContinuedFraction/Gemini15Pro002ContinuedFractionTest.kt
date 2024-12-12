import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ContinuedFractionTest {

    private fun calc(f: Func, n: Int): Double {
        var temp = 0.0
        for (i in n downTo 1) {
            val p = f(i)
            temp = p[1] / (p[0] + temp)
        }
        return f(0)[0] + temp
    }

    @Test
    fun testSqrt2() {
        val sqrt2Func: Func = { n -> intArrayOf(if (n > 0) 2 else 1, 1) }
        val result = calc(sqrt2Func, 200)
        assertEquals(1.4142135623730951, result, 1e-15) // delta for double comparison
    }

    @Test
    fun testE() {
        val eFunc: Func = { n -> intArrayOf(if (n > 0) n else 2, if (n > 1) n - 1 else 1) }
        val result = calc(eFunc, 200)
        assertEquals(2.718281828459045, result, 1e-15)
    }

    @Test
    fun testPi() {
        val piFunc: Func = { n -> intArrayOf(if (n > 0) 6 else 3, (2 * n - 1) * (2 * n - 1)) }
        val result = calc(piFunc, 200)
        assertEquals(3.141592653589793, result, 1e-15)

    }


    @Test
    fun testCalcZeroIterations() {
        val sqrt2Func: Func = { n -> intArrayOf(if (n > 0) 2 else 1, 1) }
        val result = calc(sqrt2Func, 0)
        assertEquals(1.0, result)
    }

    @Test
    fun testCalcOneIteration() {
        val sqrt2Func: Func = { n -> intArrayOf(if (n > 0) 2 else 1, 1) }
        val result = calc(sqrt2Func, 1)
        assertEquals(1.5, result)
    }



}
