import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LargestIntFromConcatenatedIntsTest {

    private val comparator = Comparator<Int> { x, y -> "$x$y".compareTo("$y$x") }

    private fun findLargestSequence(array: IntArray): String {
        return array.sortedWith(comparator.reversed()).joinToString("") { it.toString() }
    }

    @Test
    fun testFindLargestSequence_case1() {
        val input = intArrayOf(1, 34, 3, 98, 9, 76, 45, 4)
        val expected = "998764543431"
        val result = findLargestSequence(input)
        assertEquals(expected, result)
    }

    @Test
    fun testFindLargestSequence_case2() {
        val input = intArrayOf(54, 546, 548, 60)
        val expected = "6054854654"
        val result = findLargestSequence(input)
        assertEquals(expected, result)
    }

    @Test
    fun testFindLargestSequence_singleElement() {
        val input = intArrayOf(5)
        val expected = "5"
        val result = findLargestSequence(input)
        assertEquals(expected, result)
    }

    @Test
    fun testFindLargestSequence_identicalElements() {
        val input = intArrayOf(11, 11, 11)
        val expected = "111111"
        val result = findLargestSequence(input)
        assertEquals(expected, result)
    }

    @Test
    fun testFindLargestSequence_largeNumbers() {
        val input = intArrayOf(999, 9999, 99999)
        val expected = "999999999999"
        val result = findLargestSequence(input)
        assertEquals(expected, result)
    }

    @Test
    fun testFindLargestSequence_mixedLengthNumbers() {
        val input = intArrayOf(5, 50, 56)
        val expected = "56550"
        val result = findLargestSequence(input)
        assertEquals(expected, result)
    }
}
