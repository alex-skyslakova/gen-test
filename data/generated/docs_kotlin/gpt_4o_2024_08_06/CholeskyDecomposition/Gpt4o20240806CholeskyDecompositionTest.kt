import kotlin.math.sqrt
import kotlin.test.assertEquals

fun testCholeskyDecomposition() {
    // Test case 1
    val m1 = doubleArrayOf(
        25.0, 15.0, -5.0,
        15.0, 18.0, 0.0,
        -5.0, 0.0, 11.0
    )
    val expected1 = doubleArrayOf(
        5.0, 0.0, 0.0,
        3.0, 3.0, 0.0,
        -1.0, 1.0, 3.0
    )
    val result1 = cholesky(m1)
    for (i in result1.indices) {
        assertEquals(expected1[i], result1[i], 1e-5)
    }

    // Test case 2
    val m2 = doubleArrayOf(
        18.0, 22.0, 54.0, 42.0,
        22.0, 70.0, 86.0, 62.0,
        54.0, 86.0, 174.0, 134.0,
        42.0, 62.0, 134.0, 106.0
    )
    val expected2 = doubleArrayOf(
        4.24264, 0.0, 0.0, 0.0,
        5.18545, 6.56591, 0.0, 0.0,
        12.72792, 3.04604, 1.64974, 0.0,
        9.89949, 1.62455, 1.84971, 1.39262
    )
    val result2 = cholesky(m2)
    for (i in result2.indices) {
        assertEquals(expected2[i], result2[i], 1e-5)
    }
}

fun main() {
    testCholeskyDecomposition()
    println("All tests passed.")
}
