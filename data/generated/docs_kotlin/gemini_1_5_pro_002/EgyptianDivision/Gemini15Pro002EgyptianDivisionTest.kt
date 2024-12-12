import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class EgyptianDivisionTest {

    @Test
    fun `test egyptianDivide with 580 and 34`() {
        val (quotient, remainder) = egyptianDivide(580, 34)
        assertEquals(17, quotient)
        assertEquals(2, remainder)
    }

    @Test
    fun `test egyptianDivide with dividend less than divisor`() {
        val (quotient, remainder) = egyptianDivide(10, 20)
        assertEquals(0, quotient)
        assertEquals(10, remainder)
    }

    @Test
    fun `test egyptianDivide with dividend equal to divisor`() {
        val (quotient, remainder) = egyptianDivide(20, 20)
        assertEquals(1, quotient)
        assertEquals(0, remainder)
    }

    @Test
    fun `test egyptianDivide with dividend zero`() {
        val (quotient, remainder) = egyptianDivide(0, 20)
        assertEquals(0, quotient)
        assertEquals(0, remainder)
    }

    @Test
    fun `test egyptianDivide with large numbers`() {
        val (quotient, remainder) = egyptianDivide(1234567, 7)
        assertEquals(176366, quotient)
        assertEquals(5, remainder)
    }

    @Test
    fun `test egyptianDivide with divisor zero`() {
        assertThrows<IllegalArgumentException> { egyptianDivide(580, 0) }
    }

    @Test
    fun `test egyptianDivide with negative dividend`() {
        assertThrows<IllegalArgumentException> { egyptianDivide(-580, 34) }
    }

    @Test
    fun `test egyptianDivide with exact division`() {
        val (quotient, remainder) = egyptianDivide(68, 34)
        assertEquals(2, quotient)
        assertEquals(0, remainder)
    }

    @Test
    fun `test egyptianDivide with small divisor`() {
        val (quotient, remainder) = egyptianDivide(580, 1)
        assertEquals(580, quotient)
        assertEquals(0, remainder)

    }

}
