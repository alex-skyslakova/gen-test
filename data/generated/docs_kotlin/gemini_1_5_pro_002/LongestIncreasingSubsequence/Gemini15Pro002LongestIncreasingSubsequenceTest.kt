import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class LongestIncreasingSubsequenceTest {

    @Test
    fun testEmptyArray() {
        val input = intArrayOf()
        val expected = intArrayOf()
        assertContentEquals(expected, longestIncreasingSubsequence(input))
    }

    @Test
    fun testSingleElementArray() {
        val input = intArrayOf(5)
        val expected = intArrayOf(5)
        assertContentEquals(expected, longestIncreasingSubsequence(input))
    }

    @Test
    fun testExample1() {
        val input = intArrayOf(3, 2, 6, 4, 5, 1)
        val expected = intArrayOf(2, 4, 5)
        assertContentEquals(expected, longestIncreasingSubsequence(input))
    }

    @Test
    fun testExample2() {
        val input = intArrayOf(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15)
        val expected = intArrayOf(0, 2, 6, 9, 11, 15)
        assertContentEquals(expected, longestIncreasingSubsequence(input))
    }

    @Test
    fun testDecreasingSequence() {
        val input = intArrayOf(5, 4, 3, 2, 1)
        val expected = intArrayOf(1)
        assertContentEquals(expected, longestIncreasingSubsequence(input))
    }

    @Test
    fun testDuplicateElements() {
        val input = intArrayOf(1, 2, 2, 3, 3, 3, 4)
        val expected = intArrayOf(1, 2, 3, 4)
        assertContentEquals(expected, longestIncreasingSubsequence(input))

    }


    @Test
    fun testAllSameElements(){
        val input = intArrayOf(5,5,5,5,5)
        val expected = intArrayOf(5)
        assertContentEquals(expected, longestIncreasingSubsequence(input))
    }

    @Test
    fun testNegativeNumbers(){
        val input = intArrayOf(-5, -2, 0, 3, 1)
        val expected = intArrayOf(-5, -2, 0, 3)
        assertContentEquals(expected, longestIncreasingSubsequence(input))
    }

}
