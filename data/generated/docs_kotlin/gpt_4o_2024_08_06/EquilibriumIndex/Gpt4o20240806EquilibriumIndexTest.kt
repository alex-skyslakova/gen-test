import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EquilibriumIndexTest {

    @Test
    fun testEquilibriumIndicesWithExampleArray() {
        val a = intArrayOf(-7, 1, 5, 2, -4, 3, 0)
        val expected = listOf(3, 6)
        assertEquals(expected, equilibriumIndices(a))
    }

    @Test
    fun testEquilibriumIndicesWithNoEquilibrium() {
        val a = intArrayOf(1, 2, 3)
        val expected = emptyList<Int>()
        assertEquals(expected, equilibriumIndices(a))
    }

    @Test
    fun testEquilibriumIndicesWithSingleElement() {
        val a = intArrayOf(5)
        val expected = listOf(0)
        assertEquals(expected, equilibriumIndices(a))
    }

    @Test
    fun testEquilibriumIndicesWithTwoElements() {
        val a = intArrayOf(1, -1)
        val expected = emptyList<Int>()
        assertEquals(expected, equilibriumIndices(a))
    }

    @Test
    fun testEquilibriumIndicesWithAllZeros() {
        val a = intArrayOf(0, 0, 0, 0)
        val expected = listOf(0, 1, 2, 3)
        assertEquals(expected, equilibriumIndices(a))
    }

    @Test
    fun testEquilibriumIndicesWithNegativeNumbers() {
        val a = intArrayOf(-1, -2, -3, -4, -5, -6)
        val expected = emptyList<Int>()
        assertEquals(expected, equilibriumIndices(a))
    }

    @Test
    fun testEquilibriumIndicesWithEmptyArray() {
        val a = intArrayOf()
        val expected = emptyList<Int>()
        assertEquals(expected, equilibriumIndices(a))
    }

    @Test
    fun testEquilibriumIndicesWithLargeNumbers() {
        val a = intArrayOf(Int.MAX_VALUE, Int.MIN_VALUE, 0, Int.MAX_VALUE, Int.MIN_VALUE)
        val expected = listOf(2)
        assertEquals(expected, equilibriumIndices(a))
    }
}
