import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

class AveragesPythagoreanMeansTest {

    @Test
    fun testArithmeticMean() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val expected = 5.5
        val actual = list.average()
        assertEquals(expected, actual, 0.000001)
    }

    @Test
    fun testGeometricMean() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val expected = 4.528728688116765
        val actual = list.geometricMean()
        assertEquals(expected, actual, 0.000001)
    }

    @Test
    fun testHarmonicMean() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val expected = 3.414171521474055
        val actual = list.harmonicMean()
        assertEquals(expected, actual, 0.000001)
    }

    @Test
    fun testGeometricMeanWithEmptyList() {
        val list = emptyList<Double>()
        val expected = Double.NaN
        val actual = list.geometricMean()
        assertEquals(expected, actual)
    }

    @Test
    fun testHarmonicMeanWithEmptyList() {
        val list = emptyList<Double>()
        val expected = Double.NaN
        val actual = list.harmonicMean()
        assertEquals(expected, actual)
    }

    @Test
    fun testHarmonicMeanWithZero() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 0.0)
        val expected = Double.NaN
        val actual = list.harmonicMean()
        assertEquals(expected, actual)
    }

    @Test
    fun testFixedPointPrecision() {
        val number = 123.456789
        val expected = 123.456789
        val actual = number.toFixed(6)
        assertEquals(expected, actual, 0.000001)
    }

    @Test
    fun testMainFunction() {
        assertFailsWith<AssertionError> {
            main()
        }
    }

    @Test
    fun testMainFunctionWithValidList() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val a = list.average()
        val g = list.geometricMean()
        val h = list.harmonicMean()
        assertTrue(a >= g)
        assertTrue(g >= h)
    }
}
