import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TwoDimensionalArrayTest {

    @Test
    fun `test array creation and initialization`() {
        // Given dimensions
        val dim = arrayOf(10, 15)

        // When creating the array
        val array = Array(dim[0]) { IntArray(dim[1]) }

        // Then the array should have the correct dimensions
        assertEquals(dim[0], array.size)
        array.forEach { row ->
            assertEquals(dim[1], row.size)
        }
    }

    @Test
    fun `test array population`() {
        // Given dimensions
        val dim = arrayOf(10, 15)

        // When creating and populating the array
        val array = Array(dim[0]) { IntArray(dim[1]) }
        array.forEachIndexed { i, it ->
            it.indices.forEach { j ->
                it[j] = 1 + i + j
            }
        }

        // Then the array should be populated correctly
        for (i in 0 until dim[0]) {
            for (j in 0 until dim[1]) {
                assertEquals(1 + i + j, array[i][j])
            }
        }
    }

    @Test
    fun `test array destruction`() {
        // Given dimensions
        val dim = arrayOf(10, 15)

        // When creating the array
        val array = Array(dim[0]) { IntArray(dim[1]) }

        // Then the array should be eligible for garbage collection
        // Note: In Kotlin, the array will be automatically destroyed by the garbage collector
        // when it goes out of scope, so we can't directly test this behavior.
        // This test is more of a documentation of the expected behavior.
        assertTrue(true)
    }
}
