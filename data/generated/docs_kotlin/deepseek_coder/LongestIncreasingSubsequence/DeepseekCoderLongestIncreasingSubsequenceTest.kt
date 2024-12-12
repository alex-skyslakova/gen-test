import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LongestIncreasingSubsequenceTest {

    @Test
    fun testEmptyList() {
        val result = longestIncreasingSubsequence(intArrayOf())
        assertEquals(0, result.size)
    }

    @Test
    fun testSingleElementList() {
        val result = longestIncreasingSubsequence(intArrayOf(5))
        assertEquals(1, result.size)
        assertEquals(5, result[0])
    }

    @Test
    fun testExampleList1() {
        val result = longestIncreasingSubsequence(intArrayOf(3, 2, 6, 4, 5, 1))
        assertEquals(3, result.size)
        assertTrue(result.contentEquals(intArrayOf(2, 4, 5)) || result.contentEquals(intArrayOf(3, 4, 5)))
    }

    @Test
    fun testExampleList2() {
        val result = longestIncreasingSubsequence(intArrayOf(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15))
        assertEquals(6, result.size)
        assertTrue(result.contentEquals(intArrayOf(0, 2, 6, 9, 11, 15)) || result.contentEquals(intArrayOf(0, 4, 6, 9, 11, 15)))
    }

    @Test
    fun testAllIncreasingElements() {
        val result = longestIncreasingSubsequence(intArrayOf(1, 2, 3, 4, 5))
        assertEquals(5, result.size)
        assertTrue(result.contentEquals(intArrayOf(1, 2, 3, 4, 5)))
    }

    @Test
    fun testAllDecreasingElements() {
        val result = longestIncreasingSubsequence(intArrayOf(5, 4, 3, 2, 1))
        assertEquals(1, result.size)
        assertTrue(result.contentEquals(intArrayOf(5)) || result.contentEquals(intArrayOf(4)) || result.contentEquals(intArrayOf(3)) || result.contentEquals(intArrayOf(2)) || result.contentEquals(intArrayOf(1)))
    }

    @Test
    fun testMixedElements() {
        val result = longestIncreasingSubsequence(intArrayOf(10, 22, 9, 33, 21, 50, 41, 60, 80))
        assertEquals(6, result.size)
        assertTrue(result.contentEquals(intArrayOf(10, 22, 33, 41, 60, 80)))
    }

    @Test
    fun testDuplicateElements() {
        val result = longestIncreasingSubsequence(intArrayOf(1, 3, 5, 3, 5, 7))
        assertEquals(4, result.size)
        assertTrue(result.contentEquals(intArrayOf(1, 3, 5, 7)) || result.contentEquals(intArrayOf(1, 3, 5, 7)))
    }

    @Test
    fun testNegativeElements() {
        val result = longestIncreasingSubsequence(intArrayOf(-2, -1, -3, -4, -1, -5))
        assertEquals(2, result.size)
        assertTrue(result.contentEquals(intArrayOf(-2, -1)) || result.contentEquals(intArrayOf(-3, -1)) || result.contentEquals(intArrayOf(-4, -1)))
    }
}
