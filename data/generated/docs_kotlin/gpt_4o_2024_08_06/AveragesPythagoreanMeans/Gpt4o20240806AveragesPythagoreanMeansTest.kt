import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.math.pow
import kotlin.math.round

class AveragesPythagoreanMeansTest {

    private fun Collection<Double>.geometricMean(): Double =
        if (isEmpty()) Double.NaN
        else (reduce { n1, n2 -> n1 * n2 }).pow(1.0 / size)

    private fun Collection<Double>.harmonicMean(): Double =
        if (isEmpty() || contains(0.0)) Double.NaN
        else size / fold(0.0) { n1, n2 -> n1 + 1.0 / n2 }

    private fun Double.toFixed(len: Int = 6): Double =
        round(this * 10.0.pow(len)) / 10.0.pow(len)

    @Test
    fun testArithmeticMean() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val arithmeticMean = list.average()
        assertEquals(5.5, arithmeticMean, "Arithmetic mean should be 5.5")
    }

    @Test
    fun testGeometricMean() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val geometricMean = list.geometricMean().toFixed()
        assertEquals(4.528729, geometricMean, "Geometric mean should be approximately 4.528729")
    }

    @Test
    fun testHarmonicMean() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val harmonicMean = list.harmonicMean().toFixed()
        assertEquals(3.414171, harmonicMean, "Harmonic mean should be approximately 3.414171")
    }

    @Test
    fun testInequality() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val a = list.average()
        val g = list.geometricMean()
        val h = list.harmonicMean()

        assertTrue(a >= g, "Arithmetic mean should be greater than or equal to Geometric mean")
        assertTrue(g >= h, "Geometric mean should be greater than or equal to Harmonic mean")
    }
}
