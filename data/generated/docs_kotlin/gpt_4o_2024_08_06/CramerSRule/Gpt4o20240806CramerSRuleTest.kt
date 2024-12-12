import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CramerSRuleTest {

    @Test
    fun testDeterminant() {
        val matrix1 = arrayOf(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(0.0, 1.0, 4.0),
            doubleArrayOf(5.0, 6.0, 0.0)
        )
        assertEquals(-1.0, determinant(matrix1), 1e-9)

        val matrix2 = arrayOf(
            doubleArrayOf(2.0, -1.0, 5.0, 1.0),
            doubleArrayOf(3.0, 2.0, 2.0, -6.0),
            doubleArrayOf(1.0, 3.0, 3.0, -1.0),
            doubleArrayOf(5.0, -2.0, -3.0, 3.0)
        )
        assertEquals(-418.0, determinant(matrix2), 1e-9)
    }

    @Test
    fun testCramer() {
        val matrix = arrayOf(
            doubleArrayOf(2.0, -1.0, 5.0, 1.0),
            doubleArrayOf(3.0, 2.0, 2.0, -6.0),
            doubleArrayOf(1.0, 3.0, 3.0, -1.0),
            doubleArrayOf(5.0, -2.0, -3.0, 3.0)
        )
        val d = doubleArrayOf(-3.0, -32.0, -47.0, 49.0)
        val result = cramer(matrix, d)
        assertEquals(1.0, result[0], 1e-9) // w
        assertEquals(-5.0, result[1], 1e-9) // x
        assertEquals(3.0, result[2], 1e-9) // y
        assertEquals(4.0, result[3], 1e-9) // z
    }

    @Test
    fun testCramerWithZeroDeterminant() {
        val matrix = arrayOf(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(2.0, 4.0, 6.0),
            doubleArrayOf(3.0, 6.0, 9.0)
        )
        val d = doubleArrayOf(1.0, 2.0, 3.0)
        assertFailsWith<ArithmeticException> {
            cramer(matrix, d)
        }
    }

    @Test
    fun testCramerWithInvalidMatrix() {
        val matrix = arrayOf(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(3.0, 4.0)
        )
        val d = doubleArrayOf(1.0, 2.0)
        assertFailsWith<IllegalArgumentException> {
            cramer(matrix, d)
        }
    }
}
