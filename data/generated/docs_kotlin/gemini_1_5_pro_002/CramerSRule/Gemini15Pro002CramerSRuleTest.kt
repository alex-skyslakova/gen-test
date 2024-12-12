import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.math.abs

internal class CramerSRuleKtTest {

    private fun approximatelyEqual(a: Double, b: Double, epsilon: Double = 1e-9): Boolean {
        return abs(a - b) < epsilon
    }

    private fun assertVectorsApproximatelyEqual(expected: Vector, actual: Vector, epsilon: Double = 1e-9) {
        assertEquals(expected.size, actual.size)
        for (i in expected.indices) {
            assertTrue(approximatelyEqual(expected[i], actual[i], epsilon))
        }
    }


    @Test
    fun determinantTest1() {
        val m = arrayOf(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(3.0, 4.0)
        )
        assertEquals(-2.0, determinant(m))
    }

    @Test
    fun determinantTest2() {
        val m = arrayOf(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0),
            doubleArrayOf(7.0, 8.0, 9.0)
        )
        assertEquals(0.0, determinant(m))
    }
    
    @Test
    fun determinantTest3() {
        val m = arrayOf(
            doubleArrayOf(2.0, -1.0,  5.0,  1.0),
            doubleArrayOf(3.0,  2.0,  2.0, -6.0),
            doubleArrayOf(1.0,  3.0,  3.0, -1.0),
            doubleArrayOf(5.0, -2.0, -3.0,  3.0)
        )
        assertEquals(-418.0, determinant(m))

    }


    @Test
    fun cramerTest1() {
        val m = arrayOf(
            doubleArrayOf(2.0, -1.0,  5.0,  1.0),
            doubleArrayOf(3.0,  2.0,  2.0, -6.0),
            doubleArrayOf(1.0,  3.0,  3.0, -1.0),
            doubleArrayOf(5.0, -2.0, -3.0,  3.0)
        )
        val d = doubleArrayOf(-3.0, -32.0, -47.0, 49.0)
        val expected = doubleArrayOf(2.0, 3.0, -5.0, 7.0)
        val result = cramer(m, d)
        assertVectorsApproximatelyEqual(expected, result)
    }

    @Test
    fun cramerTest2() {
        val m = arrayOf(
            doubleArrayOf(1.0, 1.0),
            doubleArrayOf(2.0, 1.0)
        )
        val d = doubleArrayOf(3.0, 4.0)
        val expected = doubleArrayOf(1.0, 2.0)
        val result = cramer(m, d)
        assertVectorsApproximatelyEqual(expected, result)

    }
    @Test
    fun cramerTest3() {
        val m = arrayOf(
            doubleArrayOf(3.0, 2.0),
            doubleArrayOf(5.0, 4.0)
        )
        val d = doubleArrayOf(11.0, 19.0)
        val expected = doubleArrayOf(3.0, 1.0)
        val result = cramer(m, d)
        assertVectorsApproximatelyEqual(expected, result)
    }
}
