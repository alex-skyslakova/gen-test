import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ApplyACallbackToAnArrayTest {

    @Test
    fun `test applying square function to array`() {
        // Arrange
        val array = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val function = { i: Int -> i * i }

        // Act
        val result = array.map { function(it) }

        // Assert
        val expected = listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
        assertEquals(expected, result)
    }

    @Test
    fun `test applying square function to empty array`() {
        // Arrange
        val array = arrayOf<Int>()
        val function = { i: Int -> i * i }

        // Act
        val result = array.map { function(it) }

        // Assert
        val expected = emptyList<Int>()
        assertEquals(expected, result)
    }

    @Test
    fun `test applying square function to array with negative numbers`() {
        // Arrange
        val array = arrayOf(-3, -2, -1, 0, 1, 2, 3)
        val function = { i: Int -> i * i }

        // Act
        val result = array.map { function(it) }

        // Assert
        val expected = listOf(9, 4, 1, 0, 1, 4, 9)
        assertEquals(expected, result)
    }

    @Test
    fun `test applying square function to array with mixed numbers`() {
        // Arrange
        val array = arrayOf(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5)
        val function = { i: Int -> i * i }

        // Act
        val result = array.map { function(it) }

        // Assert
        val expected = listOf(25, 16, 9, 4, 1, 0, 1, 4, 9, 16, 25)
        assertEquals(expected, result)
    }
}
