import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class DotProductTest {

    @Test
    fun `dot product of two vectors with same length`() {
        val v1 = arrayOf(1.0, 3.0, -5.0)
        val v2 = arrayOf(4.0, -2.0, -1.0)
        assertEquals(3.0, dot(v1, v2))
    }

    @Test
    fun `dot product of two vectors with different lengths throws exception`() {
        val v1 = arrayOf(1.0, 3.0)
        val v2 = arrayOf(4.0, -2.0, -1.0)
        assertThrows<IllegalArgumentException> { dot(v1, v2) }
    }


    @Test
    fun `dot product of two empty vectors`() {
        val v1 = arrayOf<Double>()
        val v2 = arrayOf<Double>()
        assertThrows<IllegalArgumentException> { dot(v1, v2) }
    }

    @Test
    fun `dot product of vector with itself`() {
        val v = arrayOf(2.0, 3.0, 4.0)
        assertEquals(29.0, dot(v,v))

    }

    @Test
    fun `dot product with zero vector`() {
        val v1 = arrayOf(1.0, 2.0, 3.0)
        val v2 = arrayOf(0.0, 0.0, 0.0)
        assertEquals(0.0, dot(v1, v2))
    }

    @Test
    fun `dot product with one element vector`() {
        val v1 = arrayOf(5.0)
        val v2 = arrayOf(2.0)
        assertEquals(10.0, dot(v1,v2))
    }

}
