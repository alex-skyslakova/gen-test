import kotlin.test.Test
import kotlin.test.assertContentEquals

class LongestIncreasingSubsequenceTest {

    @Test
    fun testLongestIncreasingSubsequence_Example1() {
        val input = intArrayOf(3, 2, 6, 4, 5, 1)
        val expected = intArrayOf(2, 4, 5)
        val result = longestIncreasingSubsequence(input)
        assertContentEquals(expected, result, "The longest increasing subsequence is incorrect.")
    }

    @Test
    fun testLongestIncreasingSubsequence_Example2() {
        val input = intArrayOf(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15)
        val expected = intArrayOf(0, 2, 6, 9, 11, 15)
        val result = longestIncreasingSubsequence(input)
        assertContentEquals(expected, result, "The longest increasing subsequence is incorrect.")
    }

    @Test
    fun testLongestIncreasingSubsequence_EmptyArray() {
        val input = intArrayOf()
        val expected = intArrayOf()
        val result = longestIncreasingSubsequence(input)
        assertContentEquals(expected, result, "The longest increasing subsequence of an empty array should be empty.")
    }

    @Test
    fun testLongestIncreasingSubsequence_SingleElement() {
        val input = intArrayOf(42)
        val expected = intArrayOf(42)
        val result = longestIncreasingSubsequence(input)
        assertContentEquals(expected, result, "The longest increasing subsequence of a single element array should be the element itself.")
    }

    @Test
    fun testLongestIncreasingSubsequence_AllDecreasing() {
        val input = intArrayOf(5, 4, 3, 2, 1)
        val expected = intArrayOf(5)
        val result = longestIncreasingSubsequence(input)
        assertContentEquals(expected, result, "The longest increasing subsequence of a decreasing array should be the first element.")
    }

    @Test
    fun testLongestIncreasingSubsequence_AllIncreasing() {
        val input = intArrayOf(1, 2, 3, 4, 5)
        val expected = intArrayOf(1, 2, 3, 4, 5)
        val result = longestIncreasingSubsequence(input)
        assertContentEquals(expected, result, "The longest increasing subsequence of an increasing array should be the array itself.")
    }
}
