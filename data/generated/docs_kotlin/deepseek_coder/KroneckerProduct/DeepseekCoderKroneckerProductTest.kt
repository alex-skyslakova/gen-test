import org.junit.Test
import org.junit.Assert.*

class KroneckerProductTest {

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

    private fun assertArrayEquals(expected: Array<IntArray>, actual: Array<IntArray>) {
        assertEquals(expected.size, actual.size)
        for (i in expected.indices) {
            assertArrayEquals(expected[i], actual[i])
        }
    }
}
