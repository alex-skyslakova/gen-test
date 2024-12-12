import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class DigitalRootTest {

    @Test
    fun testSumDigits() {
        assertEquals(1, sumDigits(1))
        assertEquals(5, sumDigits(14))
        assertEquals(15, sumDigits(267))
        assertEquals(19, sumDigits(8128))
        assertEquals(28, sumDigits(627615))
        assertEquals(21, sumDigits(39390))

    }


    @Test
    fun testSumDigitsNegative() {
        assertThrows<IllegalArgumentException> { sumDigits(-1) }
    }


    @Test
    fun testDigitalRootSingleDigit() {
        assertEquals(Pair(1, 0), digitalRoot(1))
        assertEquals(Pair(5, 1), digitalRoot(14))
        assertEquals(Pair(6, 2), digitalRoot(267))
        assertEquals(Pair(1, 2), digitalRoot(8128))
        assertEquals(Pair(9, 2), digitalRoot(627615))
        assertEquals(Pair(6, 2), digitalRoot(39390))
        assertEquals(Pair(3, 2), digitalRoot(588225))
        assertEquals(Pair(9, 2), digitalRoot(393900588225))
    }

    @Test
    fun testDigitalRootNegative() {
        assertThrows<IllegalArgumentException> { digitalRoot(-1) }
    }


}
