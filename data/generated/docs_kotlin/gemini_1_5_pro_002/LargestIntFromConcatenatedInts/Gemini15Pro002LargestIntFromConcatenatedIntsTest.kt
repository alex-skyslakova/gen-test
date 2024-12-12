import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LargestIntFromConcatenatedIntsTest {

    private fun findLargestSequence(array: IntArray): String {
        val comparator = Comparator<Int> { x, y -> "$x$y".compareTo("$y$x") }
        return array.sortedWith(comparator.reversed()).joinToString("") { it.toString() }
    }

    @Test
    fun testExample1() {
        val input = intArrayOf(1, 34, 3, 98, 9, 76, 45, 4)
        val expected = "998764543431"
        assertEquals(expected, findLargestSequence(input))
    }

    @Test
    fun testExample2() {
        val input = intArrayOf(54, 546, 548, 60)
        val expected = "6054854654"
        assertEquals(expected, findLargestSequence(input))
    }

    @Test
    fun testSingleElement() {
        val input = intArrayOf(1)
        val expected = "1"
        assertEquals(expected, findLargestSequence(input))
    }

    @Test
    fun testEmptyArray() {
        val input = intArrayOf()
        val expected = ""
        assertEquals(expected, findLargestSequence(input))
    }

    @Test
    fun testDuplicateNumbers() {
        val input = intArrayOf(54, 546, 54, 60)
        val expected = "605454546"
        assertEquals(expected, findLargestSequence(input))
    }

    @Test
    fun testWithZeros() {
        val input = intArrayOf(0, 10, 1)
        val expected = "1100"
        assertEquals(expected, findLargestSequence(input))

    }


}
