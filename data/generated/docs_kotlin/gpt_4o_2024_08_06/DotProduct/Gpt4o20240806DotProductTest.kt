import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DotProductTest {

    @Test
    fun `test dot product of positive and negative numbers`() {
        val v1 = arrayOf(1.0, 3.0, -5.0)
        val v2 = arrayOf(4.0, -2.0, -1.0)
        val result = dot(v1, v2)
        assertEquals(3.0, result)
    }

    @Test
    fun `test dot product of zero vectors`() {
        val v1 = arrayOf(0.0, 0.0, 0.0)
        val v2 = arrayOf(0.0, 0.0, 0.0)
        val result = dot(v1, v2)
        assertEquals(0.0, result)
    }

    @Test
    fun `test dot product of vectors with different lengths`() {
        val v1 = arrayOf(1.0, 2.0)
        val v2 = arrayOf(3.0, 4.0, 5.0)
        assertThrows<IllegalArgumentException> {
            dot(v1, v2)
        }
    }

    @Test
    fun `test dot product of empty vectors`() {
        val v1 = arrayOf<Double>()
        val v2 = arrayOf<Double>()
        val result = dot(v1, v2)
        assertEquals(0.0, result)
    }

    @Test
    fun `test dot product of single element vectors`() {
        val v1 = arrayOf(2.0)
        val v2 = arrayOf(3.0)
        val result = dot(v1, v2)
        assertEquals(6.0, result)
    }

    @Test
    fun `test dot product of vectors with large numbers`() {
        val v1 = arrayOf(1e10, 2e10, 3e10)
        val v2 = arrayOf(4e10, 5e10, 6e10)
        val result = dot(v1, v2)
        assertEquals(3.2e21, result)
    }
}
