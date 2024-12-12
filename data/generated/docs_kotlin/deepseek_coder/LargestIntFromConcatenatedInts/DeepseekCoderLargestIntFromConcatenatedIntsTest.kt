import org.junit.Test
import kotlin.test.assertEquals

class LargestIntFromConcatenatedIntsTest {

    @Test
    fun testFindLargestSequence() {
        val comparator = Comparator<Int> { x, y -> "$x$y".compareTo("$y$x") }

        fun findLargestSequence(array: IntArray): String {
            return array.sortedWith(comparator.reversed()).joinToString("") { it.toString() }
        }

        // Test case 1
        val array1 = intArrayOf(1, 34, 3, 98, 9, 76, 45, 4)
        val expected1 = "998764543431"
        assertEquals(expected1, findLargestSequence(array1))

        // Test case 2
        val array2 = intArrayOf(54, 546, 548, 60)
        val expected2 = "6054854654"
        assertEquals(expected2, findLargestSequence(array2))
    }
}
