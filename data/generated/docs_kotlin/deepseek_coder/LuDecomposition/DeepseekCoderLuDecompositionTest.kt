import org.junit.Assert.assertArrayEquals
import org.junit.Test

class LuDecompositionTest {

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

        assertMatrixEquals("L1", expectedL1, l1, 0.00001)
        assertMatrixEquals("U1", expectedU1, u1, 0.00001)
        assertMatrixEquals("P1", expectedP1, p1, 0.00001)
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

        assertMatrixEquals("L2", expectedL2, l2, 0.00001)
        assertMatrixEquals("U2", expectedU2, u2, 0.00001)
        assertMatrixEquals("P2", expectedP2, p2, 0.00001)
    }

    private fun assertMatrixEquals(message: String, expected: Matrix, actual: Matrix, delta: Double) {
        for (i in expected.indices) {
            assertArrayEquals("$message (row $i)", expected[i], actual[i], delta)
        }
    }
}
