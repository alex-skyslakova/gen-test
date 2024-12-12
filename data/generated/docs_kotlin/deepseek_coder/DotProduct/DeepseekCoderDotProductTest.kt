import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DotProductTest {

    @Test
    fun `test dot product with positive numbers`() {
        val v1 = arrayOf(1.0, 3.0, 5.0)
        val v2 = arrayOf(2.0, 4.0, 6.0)
        val result = dot(v1, v2)
        assertEquals(44.0, result)
    }

    @Test
    fun `test dot product with negative numbers`() {
        val v1 = arrayOf(-1.0, -3.0, -5.0)
        val v2 = arrayOf(-2.0, -4.0, -6.0)
        val result = dot(v1, v2)
        assertEquals(44.0, result)
    }

    @Test
    fun `test dot product with mixed numbers`() {
        val v1 = arrayOf(1.0, -3.0, 5.0)
        val v2 = arrayOf(-2.0, 4.0, -6.0)
        val result = dot(v1, v2)
        assertEquals(-44.0, result)
    }

    @Test
    fun `test dot product with zero vectors`() {
        val v1 = arrayOf(0.0, 0.0, 0.0)
        val v2 = arrayOf(0.0, 0.0, 0.0)
        val result = dot(v1, v2)
        assertEquals(0.0, result)
    }

    @Test
    fun `test dot product with one zero vector`() {
        val v1 = arrayOf(1.0, 2.0, 3.0)
        val v2 = arrayOf(0.0, 0.0, 0.0)
        val result = dot(v1, v2)
        assertEquals(0.0, result)
    }

    @Test
    fun `test dot product with different lengths`() {
        val v1 = arrayOf(1.0, 2.0, 3.0)
        val v2 = arrayOf(4.0, 5.0)
        assertThrows(IllegalArgumentException::class.java) {
            dot(v1, v2)
        }
    }

    @Test
    fun `test dot product with empty vectors`() {
        val v1 = emptyArray<Double>()
        val v2 = emptyArray<Double>()
        val result = dot(v1, v2)
        assertEquals(0.0, result)
    }

    @Test
    fun `test dot product with single element vectors`() {
        val v1 = arrayOf(2.0)
        val v2 = arrayOf(3.0)
        val result = dot(v1, v2)
        assertEquals(6.0, result)
    }

    @Test
    fun `test dot product with large vectors`() {
        val v1 = Array(1000) { it.toDouble() }
        val v2 = Array(1000) { it.toDouble() }
        val result = dot(v1, v2)
        assertEquals(166167000.0, result)
    }

    @Test
    fun `test dot product with floating point precision`() {
        val v1 = arrayOf(0.1, 0.2, 0.3)
        val v2 = arrayOf(0.4, 0.5, 0.6)
        val result = dot(v1, v2)
        assertEquals(0.32, result, 0.000001)
    }
}
