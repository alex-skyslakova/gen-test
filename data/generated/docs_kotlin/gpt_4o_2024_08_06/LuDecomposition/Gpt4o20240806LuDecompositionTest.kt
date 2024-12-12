import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LuDecompositionTest {

    private fun assertMatrixEquals(expected: Matrix, actual: Matrix, delta: Double = 1e-5) {
        assertEquals(expected.size, actual.size, "Matrix row size mismatch")
        for (i in expected.indices) {
            assertEquals(expected[i].size, actual[i].size, "Matrix column size mismatch at row $i")
            for (j in expected[i].indices) {
                assertEquals(expected[i][j], actual[i][j], delta, "Matrix value mismatch at position ($i, $j)")
            }
        }
    }

    @Test
    fun testExample1() {
        val a1 = arrayOf(
            doubleArrayOf(1.0, 3.0, 5.0),
            doubleArrayOf(2.0, 4.0, 7.0),
            doubleArrayOf(1.0, 1.0, 0.0)
        )
        val expectedL1 = arrayOf(
            doubleArrayOf(1.0, 0.0, 0.0),
            doubleArrayOf(0.5, 1.0, 0.0),
            doubleArrayOf(0.5, -1.0, 1.0)
        )
        val expectedU1 = arrayOf(
            doubleArrayOf(2.0, 4.0, 7.0),
            doubleArrayOf(0.0, 1.0, 1.5),
            doubleArrayOf(0.0, 0.0, -2.0)
        )
        val expectedP1 = arrayOf(
            doubleArrayOf(0.0, 1.0, 0.0),
            doubleArrayOf(1.0, 0.0, 0.0),
            doubleArrayOf(0.0, 0.0, 1.0)
        )

        val (l1, u1, p1) = lu(a1)

        assertMatrixEquals(expectedL1, l1)
        assertMatrixEquals(expectedU1, u1)
        assertMatrixEquals(expectedP1, p1)
    }

    @Test
    fun testExample2() {
        val a2 = arrayOf(
            doubleArrayOf(11.0, 9.0, 24.0, 2.0),
            doubleArrayOf(1.0, 5.0, 2.0, 6.0),
            doubleArrayOf(3.0, 17.0, 18.0, 1.0),
            doubleArrayOf(2.0, 5.0, 7.0, 1.0)
        )
        val expectedL2 = arrayOf(
            doubleArrayOf(1.0, 0.0, 0.0, 0.0),
            doubleArrayOf(0.27273, 1.0, 0.0, 0.0),
            doubleArrayOf(0.09091, 0.28750, 1.0, 0.0),
            doubleArrayOf(0.18182, 0.23125, 0.00360, 1.0)
        )
        val expectedU2 = arrayOf(
            doubleArrayOf(11.0, 9.0, 24.0, 2.0),
            doubleArrayOf(0.0, 14.54545, 11.45455, 0.45455),
            doubleArrayOf(0.0, 0.0, -3.47500, 5.68750),
            doubleArrayOf(0.0, 0.0, 0.0, 0.51079)
        )
        val expectedP2 = arrayOf(
            doubleArrayOf(1.0, 0.0, 0.0, 0.0),
            doubleArrayOf(0.0, 0.0, 1.0, 0.0),
            doubleArrayOf(0.0, 1.0, 0.0, 0.0),
            doubleArrayOf(0.0, 0.0, 0.0, 1.0)
        )

        val (l2, u2, p2) = lu(a2)

        assertMatrixEquals(expectedL2, l2)
        assertMatrixEquals(expectedU2, u2)
        assertMatrixEquals(expectedP2, p2)
    }

    @Test
    fun testMatrixMultiplication() {
        val m1 = arrayOf(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(3.0, 4.0)
        )
        val m2 = arrayOf(
            doubleArrayOf(5.0, 6.0),
            doubleArrayOf(7.0, 8.0)
        )
        val expected = arrayOf(
            doubleArrayOf(19.0, 22.0),
            doubleArrayOf(43.0, 50.0)
        )

        val result = m1 * m2

        assertMatrixEquals(expected, result)
    }

    @Test
    fun testPivotize() {
        val m = arrayOf(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(3.0, 4.0)
        )
        val expected = arrayOf(
            doubleArrayOf(0.0, 1.0),
            doubleArrayOf(1.0, 0.0)
        )

        val result = pivotize(m)

        assertMatrixEquals(expected, result)
    }
}
