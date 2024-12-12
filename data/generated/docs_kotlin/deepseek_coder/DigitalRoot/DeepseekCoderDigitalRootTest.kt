import org.junit.Test
import org.junit.Assert.*

class DigitalRootTest {

    @Test
    fun testSumDigits() {
        assertEquals(1, sumDigits(1))
        assertEquals(5, sumDigits(14))
        assertEquals(15, sumDigits(267))
        assertEquals(19, sumDigits(8128))
        assertEquals(27, sumDigits(627615))
        assertEquals(24, sumDigits(39390))
        assertEquals(30, sumDigits(588225))
        assertEquals(55, sumDigits(393900588225))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testSumDigitsNegative() {
        sumDigits(-1)
    }

    @Test
    fun testDigitalRoot() {
        assertEquals(Pair(1, 0), digitalRoot(1))
        assertEquals(Pair(5, 1), digitalRoot(14))
        assertEquals(Pair(6, 2), digitalRoot(267))
        assertEquals(Pair(1, 3), digitalRoot(8128))
        assertEquals(Pair(9, 2), digitalRoot(627615))
        assertEquals(Pair(6, 2), digitalRoot(39390))
        assertEquals(Pair(3, 2), digitalRoot(588225))
        assertEquals(Pair(9, 2), digitalRoot(393900588225))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDigitalRootNegative() {
        digitalRoot(-1)
    }
}
