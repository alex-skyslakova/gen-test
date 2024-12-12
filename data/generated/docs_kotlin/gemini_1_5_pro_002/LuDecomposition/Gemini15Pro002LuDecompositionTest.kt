import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LUDecompositionTest {

    private fun matrixEquals(m1: Matrix, m2: Matrix, epsilon: Double = 1e-5): Boolean {
        if (m1.size != m2.size || m1[0].size != m2[0].size) return false
        for (i in m1.indices) {
            for (j in m1[0].indices) {
                if (Math.abs(m1[i][j] - m2[i][j]) > epsilon) return false
            }
        }
        return true
    }

    @Test
    fun testLUDecomposition1() {
        val a = arrayOf(
            doubleArrayOf(1.0, 3.0, 5.0),
            doubleArrayOf(2.0, 4.0, 7.0),
            doubleArrayOf(1.0, 1.0, 0.0)
        )
        val expectedL = arrayOf(
            doubleArrayOf(1.0, 0.0, 0.0),
            doubleArrayOf(0.5, 1.0, 0.0),
            doubleArrayOf(0.5, -1.0, 1.0)
        )
        val expectedU = arrayOf(
            doubleArrayOf(2.0, 4.0, 7.0),
            doubleArrayOf(0.0, 1.0, 1.5),
            doubleArrayOf(0.0, 0.0, -2.0)
        )
        val expectedP = arrayOf(
            doubleArrayOf(0.0, 1.0, 0.0),
            doubleArrayOf(1.0, 0.0, 0.0),
            doubleArrayOf(0.0, 0.0, 1.0)
        )

        val (l, u, p) = lu(a)

        assertEquals(true, matrixEquals(expectedL, l))
        assertEquals(true, matrixEquals(expectedU, u))
        assertEquals(true, matrixEquals(expectedP, p))
    }


    @Test
    fun testLUDecomposition2() {
        val a = arrayOf(
            doubleArrayOf(11.0, 9.0, 24.0, 2.0),
            doubleArrayOf(1.0, 5.0, 2.0, 6.0),
            doubleArrayOf(3.0, 17.0, 18.0, 1.0),
            doubleArrayOf(2.0, 5.0, 7.0, 1.0)
        )
        val expectedL = arrayOf(
            doubleArrayOf(1.00000, 0.00000, 0.00000, 0.00000),
            doubleArrayOf(0.27273, 1.00000, 0.00000, 0.00000),
            doubleArrayOf(0.09091, 0.28750, 1.00000, 0.00000),
            doubleArrayOf(0.18182, 0.23125, 0.00360, 1.00000)
        )
        val expectedU = arrayOf(
            doubleArrayOf(11.00000, 9.00000, 24.00000, 2.00000),
            doubleArrayOf(0.00000, 14.54545, 11.45455, 0.45455),
            doubleArrayOf(0.00000, 0.00000, -3.47500, 5.68750),
            doubleArrayOf(0.00000, 0.00000, 0.00000, 0.51079)

        )
        val expectedP = arrayOf(
            doubleArrayOf(1.0, 0.0, 0.0, 0.0),
            doubleArrayOf(0.0, 0.0, 1.0, 0.0),
            doubleArrayOf(0.0, 1.0, 0.0, 0.0),
            doubleArrayOf(0.0, 0.0, 0.0, 1.0)
        )

        val (l, u, p) = lu(a)

        assertEquals(true, matrixEquals(expectedL, l))
        assertEquals(true, matrixEquals(expectedU, u))
        assertEquals(true, matrixEquals(expectedP, p))
    }


    @Test
    fun testSingularMatrix(){
        val a = arrayOf(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(2.0, 4.0)
        )

        val (l, u, p) = lu(a)

        // Add assertions to check for expected behavior with singular matrices
        // For example, check if an exception is thrown or if U has a zero on the diagonal
        assertEquals(0.0, u[1][1], 1e-5) // Check for 0 on the diagonal of U
    }


    @Test
    fun testIdentityMatrix(){
         val a = arrayOf(
            doubleArrayOf(1.0, 0.0),
            doubleArrayOf(0.0, 1.0)
        )
        val expectedL = arrayOf(
            doubleArrayOf(1.0, 0.0),
            doubleArrayOf(0.0, 1.0)
        )
        val expectedU = arrayOf(
            doubleArrayOf(1.0, 0.0),
            doubleArrayOf(0.0, 1.0)
        )
        val expectedP = arrayOf(
            doubleArrayOf(1.0, 0.0),
            doubleArrayOf(0.0, 1.0)
        )

        val (l, u, p) = lu(a)

         assertEquals(true, matrixEquals(expectedL, l))
        assertEquals(true, matrixEquals(expectedU, u))
        assertEquals(true, matrixEquals(expectedP, p))


    }

}
