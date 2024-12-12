import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ApplyCallbackToArrayTest {

    @Test
    fun testApplyCallbackToArray_emptyArray() {
        val array = arrayOf<Int>()
        val function = { i: Int -> i * i }
        val list = array.map { function(it) }
        assertEquals(emptyList<Int>(), list)
    }

    @Test
    fun testApplyCallbackToArray_nonEmptyArray() {
        val array = arrayOf(1, 2, 3, 4, 5)
        val function = { i: Int -> i * i }
        val expected = listOf(1, 4, 9, 16, 25)
        val list = array.map { function(it) }
        assertEquals(expected, list)
    }

    @Test
    fun testApplyCallbackToArray_differentFunction() {
        val array = arrayOf(1, 2, 3, 4, 5)
        val function = { i: Int -> i * 2 }
        val expected = listOf(2, 4, 6, 8, 10)
        val list = array.map { function(it) }
        assertEquals(expected, list)
    }


    @Test
    fun testApplyCallbackToArray_negativeNumbers() {
        val array = arrayOf(-1, -2, -3, -4, -5)
        val function = { i: Int -> i * i }
        val expected = listOf(1, 4, 9, 16, 25)
        val list = array.map { function(it) }
        assertEquals(expected, list)
    }

    @Test
    fun testApplyCallbackToArray_mixedNumbers() {
        val array = arrayOf(-1, 2, -3, 4, -5)
        val function = { i: Int -> i * i }
        val expected = listOf(1, 4, 9, 16, 25)
        val list = array.map { function(it) }
        assertEquals(expected, list)
    }


}
