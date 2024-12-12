import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class EquilibriumIndexTest {

    @Test
    fun emptyArrayTest() {
        val a = intArrayOf()
        val expected = mutableListOf<Int>()
        val actual = equilibriumIndices(a)
        assertEquals(expected, actual)
    }

    @Test
    fun exampleTest() {
        val a = intArrayOf(-7, 1, 5, 2, -4, 3, 0)
        val expected = mutableListOf(3, 6)
        val actual = equilibriumIndices(a)
        assertEquals(expected, actual)
    }

    @Test
    fun singleElementArrayTest() {
        val a = intArrayOf(5)
        val expected = mutableListOf(0)
        val actual = equilibriumIndices(a)
        assertEquals(expected, actual)
    }

    @Test
    fun allZerosTest() {
        val a = intArrayOf(0, 0, 0, 0, 0)
        val expected = mutableListOf(0, 1, 2, 3, 4)
        val actual = equilibriumIndices(a)
        assertEquals(expected, actual)
    }

    @Test
    fun noEquilibriumIndexTest() {
        val a = intArrayOf(1, 2, 3, 4, 5)
        val expected = mutableListOf<Int>()
        val actual = equilibriumIndices(a)
        assertEquals(expected, actual)
    }

    @Test
    fun negativeNumbersTest() {
        val a = intArrayOf(-1, -2, -3, 0, 3, 2, 1)
        val expected = mutableListOf(3)
        val actual = equilibriumIndices(a)
        assertEquals(expected, actual)
    }

    @Test
    fun largeArrayTest() {
        val a = IntArray(10000) { 0 }
        val expected = mutableListOf<Int>()
        for (i in 0 until 10000) expected.add(i)
        val actual = equilibriumIndices(a)
        assertEquals(expected, actual)
    }


}
