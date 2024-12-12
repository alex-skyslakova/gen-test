import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class FactorialTest {

    @Test
    fun testFacti_zero() {
        assertEquals(1L, facti(0))
    }

    @Test
    fun testFacti_positive() {
        assertEquals(120L, facti(5))
        assertEquals(720L, facti(6))
        assertEquals(2432902008176640000L, facti(20))
    }

    @Test
    fun testFacti_negative() {
        assertThrows<IllegalArgumentException> { facti(-1) }
    }


    @Test
    fun testFactr_zero() {
        assertEquals(1L, factr(0))
    }

    @Test
    fun testFactr_one() {
        assertEquals(1L, factr(1))
    }

    @Test
    fun testFactr_positive() {
        assertEquals(120L, factr(5))
        assertEquals(720L, factr(6))
        assertEquals(2432902008176640000L, factr(20))

    }

    @Test
    fun testFactr_negative() {
        assertThrows<IllegalArgumentException> { factr(-1) }
    }
}
