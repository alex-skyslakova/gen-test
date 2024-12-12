import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class FactorialTest {

    @Test
    fun `test factorial of zero`() {
        assertEquals(1, facti(0))
        assertEquals(1, factr(0))
    }

    @Test
    fun `test factorial of positive number`() {
        assertEquals(1, facti(1))
        assertEquals(1, factr(1))

        assertEquals(2, facti(2))
        assertEquals(2, factr(2))

        assertEquals(6, facti(3))
        assertEquals(6, factr(3))

        assertEquals(24, facti(4))
        assertEquals(24, factr(4))

        assertEquals(120, facti(5))
        assertEquals(120, factr(5))
    }

    @Test
    fun `test factorial of large number`() {
        assertEquals(2432902008176640000, facti(20))
        assertEquals(2432902008176640000, factr(20))
    }

    @Test
    fun `test factorial of negative number`() {
        assertThrows<IllegalArgumentException> {
            facti(-1)
        }
        assertThrows<IllegalArgumentException> {
            factr(-1)
        }
    }
}
