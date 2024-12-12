import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GaussianEliminationTest {

    @Test
    fun testProvidedExample() {
        val ta = arrayOf(
            doubleArrayOf(1.00, 0.00, 0.00, 0.00, 0.00, 0.00),
            doubleArrayOf(1.00, 0.63, 0.39, 0.25, 0.16, 0.10),
            doubleArrayOf(1.00, 1.26, 1.58, 1.98, 2.49, 3.13),
            doubleArrayOf(1.00, 1.88, 3.55, 6.70, 12.62, 23.80),
            doubleArrayOf(1.00, 2.51, 6.32, 15.88, 39.90, 100.28),
            doubleArrayOf(1.00, 3.14, 9.87, 31.01, 97.41, 306.02)
        )

        val tb = doubleArrayOf(-0.01, 0.61, 0.91, 0.99, 0.60, 0.02)

        val tx = doubleArrayOf(
            -0.01, 1.602790394502114, -1.6132030599055613,
            1.2454941213714368, -0.4909897195846576, 0.065760696175232
        )

        val x = gaussPartial(ta, tb)

        for ((i, xi) in x.withIndex()) {
            assertEquals(tx[i], xi, EPSILON, "Element at index $i is not within tolerance.")
        }
    }


    @Test
    fun testSingularMatrix() {
        val a = arrayOf(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(2.0, 4.0, 6.0),
            doubleArrayOf(3.0, 6.0, 9.0)
        )
        val b = doubleArrayOf(1.0, 2.0, 3.0)

        assertThrows<RuntimeException> {
            gaussPartial(a, b)
        }
    }

    @Test
    fun test2x2Matrix(){
        val a = arrayOf(
            doubleArrayOf(2.0, 1.0),
            doubleArrayOf(1.0, 2.0)
        )
        val b = doubleArrayOf(3.0, 3.0)
        val expectedX = doubleArrayOf(1.0, 1.0)
        val x = gaussPartial(a,b)

        for ((i, xi) in x.withIndex()) {
            assertEquals(expectedX[i], xi, EPSILON, "Element at index $i is not within tolerance.")
        }
    }

    @Test
    fun test3x3Matrix(){
        val a = arrayOf(
            doubleArrayOf(3.0, 2.0, -1.0),
            doubleArrayOf(2.0, -2.0, 4.0),
            doubleArrayOf(-1.0, 0.5, -1.0)
        )
        val b = doubleArrayOf(1.0, -2.0, 0.0)
        val expectedX = doubleArrayOf(1.0, -2.0, -2.0)
        val x = gaussPartial(a,b)

        for ((i, xi) in x.withIndex()) {
            assertEquals(expectedX[i], xi, EPSILON, "Element at index $i is not within tolerance.")
        }
    }



    companion object {
        const val EPSILON = 1e-14
    }
}
