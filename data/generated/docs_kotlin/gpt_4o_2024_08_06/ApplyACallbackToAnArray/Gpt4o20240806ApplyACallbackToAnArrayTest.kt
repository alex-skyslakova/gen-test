import kotlin.test.Test
import kotlin.test.assertEquals

class ApplyACallbackToAnArrayTest {

    @Test
    fun testSquareFunction() {
        val array = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expected = listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
        val function = { i: Int -> i * i }
        val result = array.map { function(it) }
        assertEquals(expected, result)
    }

    @Test
    fun testEmptyArray() {
        val array = arrayOf<Int>()
        val expected = listOf<Int>()
        val function = { i: Int -> i * i }
        val result = array.map { function(it) }
        assertEquals(expected, result)
    }

    @Test
    fun testSingleElementArray() {
        val array = arrayOf(5)
        val expected = listOf(25)
        val function = { i: Int -> i * i }
        val result = array.map { function(it) }
        assertEquals(expected, result)
    }

    @Test
    fun testNegativeNumbers() {
        val array = arrayOf(-1, -2, -3)
        val expected = listOf(1, 4, 9)
        val function = { i: Int -> i * i }
        val result = array.map { function(it) }
        assertEquals(expected, result)
    }

    @Test
    fun testZero() {
        val array = arrayOf(0)
        val expected = listOf(0)
        val function = { i: Int -> i * i }
        val result = array.map { function(it) }
        assertEquals(expected, result)
    }
}
