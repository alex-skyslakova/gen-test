import org.junit.jupiter.api.Test
import kotlin.test.assertArrayEquals

class KroneckerProductTest {

    private fun kroneckerProduct(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> {
        val m = a.size
        val n = a[0].size
        val p = b.size
        val q = b[0].size
        val rtn = m * p
        val ctn = n * q
        val r: Array<IntArray> = Array(rtn) { IntArray(ctn) }
        for (i in 0 until m)
            for (j in 0 until n)
                for (k in 0 until p)
                    for (l in 0 until q)
                        r[p * i + k][q * j + l] = a[i][j] * b[k][l]
        return r
    }


    @Test
    fun testKroneckerProductSample1() {
        val a = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )
        val b = arrayOf(
            intArrayOf(0, 5),
            intArrayOf(6, 7)
        )
        val expected = arrayOf(
            intArrayOf(0, 5, 0, 10),
            intArrayOf(6, 7, 12, 14),
            intArrayOf(0, 15, 0, 20),
            intArrayOf(18, 21, 24, 28)
        )
        val result = kroneckerProduct(a, b)
        assertArrayEquals(expected, result)
    }

    @Test
    fun testKroneckerProductSample2() {
        val a = arrayOf(
            intArrayOf(0, 1, 0),
            intArrayOf(1, 1, 1),
            intArrayOf(0, 1, 0)
        )
        val b = arrayOf(
            intArrayOf(1, 1, 1, 1),
            intArrayOf(1, 0, 0, 1),
            intArrayOf(1, 1, 1, 1)
        )
        val expected = arrayOf(
            intArrayOf(0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0),
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1),
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0)
        )
        val result = kroneckerProduct(a, b)
        assertArrayEquals(expected, result)
    }

}

