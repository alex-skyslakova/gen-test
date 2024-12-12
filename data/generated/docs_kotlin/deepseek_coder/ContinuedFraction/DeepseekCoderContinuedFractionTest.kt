import org.junit.Assert.assertEquals
import org.junit.Test

class ContinuedFractionTest {

    @Test
    fun testSqrt2() {
        val sqrt2Func: (Int) -> IntArray = { n -> intArrayOf(if (n > 0) 2 else 1, 1) }
        val result = calc(sqrt2Func, 200)
        assertEquals(Math.sqrt(2.0), result, 1e-15)
    }

    @Test
    fun testNapier() {
        val napierFunc: (Int) -> IntArray = { n -> intArrayOf(if (n > 0) n else 2, if (n > 1) n - 1 else 1) }
        val result = calc(napierFunc, 200)
        assertEquals(Math.E, result, 1e-15)
    }

    @Test
    fun testPi() {
        val piFunc: (Int) -> IntArray = { n -> intArrayOf(if (n > 0) 6 else 3, (2 * n - 1) * (2 * n - 1)) }
        val result = calc(piFunc, 200)
        assertEquals(Math.PI, result, 1e-15)
    }
}
