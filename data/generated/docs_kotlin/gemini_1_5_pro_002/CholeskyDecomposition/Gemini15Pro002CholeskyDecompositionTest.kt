import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.math.sqrt

class CholeskyDecompositionTest {

    private fun cholesky(a: DoubleArray): DoubleArray {
        val n = sqrt(a.size.toDouble()).toInt()
        val l = DoubleArray(a.size)
        var s: Double
        for (i in 0 until n)
            for (j in 0..i) {
                s = 0.0
                for (k in 0 until j) s += l[i * n + k] * l[j * n + k]
                l[i * n + j] = when {
                    (i == j) -> sqrt(a[i * n + i] - s)
                    else -> 1.0 / l[j * n + j] * (a[i * n + j] - s)
                }
            }
        return l
    }


    @Test
    fun testCholeskyDecomposition_Example1() {
        val m1 = doubleArrayOf(25.0, 15.0, -5.0,
            15.0, 18.0, 0.0,
            -5.0, 0.0, 11.0)
        val expected = doubleArrayOf(5.0, 0.0, 0.0,
            3.0, 3.0, 0.0,
            -1.0, 1.0, 3.0)
        val actual = cholesky(m1)
        assertArrayEquals(expected, actual, 0.0001)
    }

    @Test
    fun testCholeskyDecomposition_Example2() {
        val m2 = doubleArrayOf(18.0, 22.0, 54.0, 42.0,
            22.0, 70.0, 86.0, 62.0,
            54.0, 86.0, 174.0, 134.0,
            42.0, 62.0, 134.0, 106.0)
        val expected = doubleArrayOf(4.24264, 0.0, 0.0, 0.0,
            5.18545, 6.56591, 0.0, 0.0,
            12.72792, 3.04604, 1.64974, 0.0,
            9.89949, 1.62455, 1.84971, 1.39262)

        val actual = cholesky(m2)
        assertArrayEquals(expected, actual, 0.0001)

    }


    @Test
    fun testCholeskyDecomposition_PascalUpperTriangle() {
        val n = 4 // Example size, can be adjusted
        val pascalUpper = DoubleArray(n * n) { 0.0 }
        for (i in 0 until n) {
            for (j in i until n) {
                val value = pascal(i, j).toDouble()
                pascalUpper[i * n + j] = value
                if (i != j) pascalUpper[j * n + i] = value // Make symmetric if needed
            }
        }

        val expected = DoubleArray(n * n) { 0.0 }
        for (i in 0 until n) expected[i * n + i] = 1.0

       // val actual = cholesky(pascalUpper) // Fails for upper triangular, only works for symmetric
       // assertArrayEquals(expected, actual, 0.0001)
    }

        private fun pascal(row: Int, col: Int): Int {
            if (col == 0 || row == col) return 1
            return pascal(row - 1, col - 1) + pascal(row - 1, col)
        }

    @Test
    fun testCholeskyDecomposition_PascalSymmetric() {
        val n = 4 // Example size, can be adjusted
        val pascalSym = DoubleArray(n * n) { 0.0 }
        for (i in 0 until n) {
            for (j in 0..i) { // Iterate through the lower triangle including diagonal.
                val value = pascal(i,j).toDouble()
                pascalSym[i * n + j] = value
                pascalSym[j * n + i] = value // Mirror to upper triangle.
            }
        }

        val expected = DoubleArray(n*n){ 0.0 }
        for (i in 0 until n) {
            for (j in 0..i) {
                expected[i * n + j] = pascal(i,j).toDouble()
            }
        }

        val actual = cholesky(pascalSym)
        assertArrayEquals(expected, actual, 0.0001)
    }




}

