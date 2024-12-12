import org.junit.Assert.assertArrayEquals
import org.junit.Test
import kotlin.math.abs

class GaussianEliminationTest {

    @Test
    fun testGaussPartial() {
        val expected = doubleArrayOf(
            -0.01, 1.602790394502114, -1.6132030599055613,
            1.2454941213714368, -0.4909897195846576, 0.065760696175232
        )
        val result = gaussPartial(ta, tb)

        assertArrayEquals(expected, result, EPSILON)
    }

    @Test(expected = RuntimeException::class)
    fun testGaussPartialWithSingularMatrix() {
        val singularMatrix = arrayOf(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0),
            doubleArrayOf(7.0, 8.0, 9.0)
        )
        val b = doubleArrayOf(1.0, 2.0, 3.0)

        gaussPartial(singularMatrix, b)
    }

    @Test
    fun testGaussPartialWithIdentityMatrix() {
        val identityMatrix = arrayOf(
            doubleArrayOf(1.0, 0.0, 0.0),
            doubleArrayOf(0.0, 1.0, 0.0),
            doubleArrayOf(0.0, 0.0, 1.0)
        )
        val b = doubleArrayOf(1.0, 2.0, 3.0)
        val expected = doubleArrayOf(1.0, 2.0, 3.0)

        val result = gaussPartial(identityMatrix, b)

        assertArrayEquals(expected, result, EPSILON)
    }

    @Test
    fun testGaussPartialWithDiagonalMatrix() {
        val diagonalMatrix = arrayOf(
            doubleArrayOf(2.0, 0.0, 0.0),
            doubleArrayOf(0.0, 3.0, 0.0),
            doubleArrayOf(0.0, 0.0, 4.0)
        )
        val b = doubleArrayOf(2.0, 6.0, 12.0)
        val expected = doubleArrayOf(1.0, 2.0, 3.0)

        val result = gaussPartial(diagonalMatrix, b)

        assertArrayEquals(expected, result, EPSILON)
    }

    @Test
    fun testGaussPartialWithRandomMatrix() {
        val randomMatrix = arrayOf(
            doubleArrayOf(3.0, 2.0, -4.0),
            doubleArrayOf(2.0, 3.0, 3.0),
            doubleArrayOf(5.0, -3.0, 1.0)
        )
        val b = doubleArrayOf(3.0, 15.0, 14.0)
        val expected = doubleArrayOf(3.0, 1.0, 2.0)

        val result = gaussPartial(randomMatrix, b)

        assertArrayEquals(expected, result, EPSILON)
    }

    companion object {
        const val EPSILON = 1e-14  // tolerance required
    }
}
