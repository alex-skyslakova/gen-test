import org.junit.Test
import kotlin.test.assertEquals

class EquilibriumIndexTest {

    @Test
    fun testEmptyArray() {
        val result = equilibriumIndices(intArrayOf())
        assertEquals(0, result.size)
    }

    @Test
    fun testNoEquilibriumIndex() {
        val result = equilibriumIndices(intArrayOf(1, 2, 3, 4, 5))
        assertEquals(0, result.size)
    }

    @Test
    fun testSingleEquilibriumIndex() {
        val result = equilibriumIndices(intArrayOf(-7, 1, 5, 2, -4, 3, 0))
        assertEquals(listOf(3, 6), result)
    }

    @Test
    fun testMultipleEquilibriumIndices() {
        val result = equilibriumIndices(intArrayOf(1, 2, 3, 4, 3, 2, 1))
        assertEquals(listOf(3), result)
    }

    @Test
    fun testAllElementsSame() {
        val result = equilibriumIndices(intArrayOf(2, 2, 2, 2))
        assertEquals(listOf(1, 2), result)
    }

    @Test
    fun testNegativeNumbers() {
        val result = equilibriumIndices(intArrayOf(-1, -2, -3, -4, -3, -2, -1))
        assertEquals(listOf(3), result)
    }

    @Test
    fun testMixedNumbers() {
        val result = equilibriumIndices(intArrayOf(-1, 2, -3, 4, -3, 2, -1))
        assertEquals(listOf(3), result)
    }

    @Test
    fun testSingleElementArray() {
        val result = equilibriumIndices(intArrayOf(0))
        assertEquals(listOf(0), result)
    }

    @Test
    fun testZeroSumArray() {
        val result = equilibriumIndices(intArrayOf(0, 0, 0, 0))
        assertEquals(listOf(0, 1, 2, 3), result)
    }

    @Test
    fun testLargeArray() {
        val largeArray = IntArray(100000) { if (it % 2 == 0) 1 else -1 }
        val result = equilibriumIndices(largeArray)
        assertEquals(listOf(50000), result)
    }
}
