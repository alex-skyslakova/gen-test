import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

class GaussianEliminationTest {

    private val ta = arrayOf(
        doubleArrayOf(1.00, 0.00, 0.00, 0.00, 0.00, 0.00),
        doubleArrayOf(1.00, 0.63, 0.39, 0.25, 0.16, 0.10),
        doubleArrayOf(1.00, 1.26, 1.58, 1.98, 2.49, 3.13),
        doubleArrayOf(1.00, 1.88, 3.55, 6.70, 12.62, 23.80),
        doubleArrayOf(1.00, 2.51, 6.32, 15.88, 39.90, 100.28),
        doubleArrayOf(1.00, 3.14, 9.87, 31.01, 97.41, 306.02)
    )

    private val tb = doubleArrayOf(-0.01, 0.61, 0.91, 0.99, 0.60, 0.02)

    private val tx = doubleArrayOf(
        -0.01, 1.602790394502114, -1.6132030599055613,
        1.2454941213714368, -0.4909897195846576, 0.065760696175232
    )

    private val EPSILON = 1e-14

    @Test
    fun testGaussianElimination() {
        val result = gaussPartial(ta, tb)
        for (i in result.indices) {
            assertTrue(Math.abs(tx[i] - result[i]) <= EPSILON, "Result out of tolerance at index $i")
        }
    }

    @Test
    fun testSingularMatrix() {
        val singularMatrix = arrayOf(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(2.0, 4.0, 6.0),
            doubleArrayOf(3.0, 6.0, 9.0)
        )
        val b = doubleArrayOf(1.0, 2.0, 3.0)
        assertFailsWith<RuntimeException>("Matrix is singular.") {
            gaussPartial(singularMatrix, b)
        }
    }

    @Test
    fun testZeroMatrix() {
        val zeroMatrix = arrayOf(
            doubleArrayOf(0.0, 0.0, 0.0),
            doubleArrayOf(0.0, 0.0, 0.0),
            doubleArrayOf(0.0, 0.0, 0.0)
        )
        val b = doubleArrayOf(0.0, 0.0, 0.0)
        assertFailsWith<RuntimeException>("Matrix is singular.") {
            gaussPartial(zeroMatrix, b)
        }
    }
}
