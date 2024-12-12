import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class PythagoreanMeansTest {

    @Test
    fun testGeometricMeanEmptyList() {
        val list = emptyList<Double>()
        assertEquals(Double.NaN, list.geometricMean())
    }

    @Test
    fun testGeometricMeanSingleElement() {
        val list = listOf(5.0)
        assertEquals(5.0, list.geometricMean())
    }

    @Test
    fun testGeometricMeanMultipleElements() {
        val list = listOf(2.0, 4.0, 8.0)
        assertEquals(4.0, list.geometricMean())
    }


    @Test
    fun testHarmonicMeanEmptyList() {
        val list = emptyList<Double>()
        assertEquals(Double.NaN, list.harmonicMean())
    }

    @Test
    fun testHarmonicMeanSingleElement() {
        val list = listOf(5.0)
        assertEquals(5.0, list.harmonicMean())
    }

    @Test
    fun testHarmonicMeanMultipleElements() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        assertEquals(2.4489795918367346 , list.harmonicMean(), 0.000001) // Using a delta for comparison
    }

    @Test
    fun testHarmonicMeanWithZero() {
        val list = listOf(1.0, 2.0, 0.0, 4.0)
        assertEquals(Double.NaN, list.harmonicMean())
    }

    @Test
    fun testToFixed() {
        val num = 1.23456789
        assertEquals(1.234568, num.toFixed())
        assertEquals(1.23457, num.toFixed(5))
        assertEquals(1.23, num.toFixed(2))
    }

    @Test
    fun testExampleCase() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val a = list.average()
        val g = list.geometricMean()
        val h = list.harmonicMean()

        assertTrue(a >= g)
        assertTrue(g >= h)
    }
}
