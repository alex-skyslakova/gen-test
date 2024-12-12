import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows

class DigitalRootTest {

    @Test
    fun testSumDigits() {
        assertEquals(0, sumDigits(0))
        assertEquals(1, sumDigits(1))
        assertEquals(5, sumDigits(5))
        assertEquals(6, sumDigits(123))
        assertEquals(15, sumDigits(12345))
        assertEquals(27, sumDigits(999))
        assertThrows(IllegalArgumentException::class.java) { sumDigits(-123) }
    }

    @Test
    fun testDigitalRoot() {
        assertEquals(Pair(1, 0), digitalRoot(1))
        assertEquals(Pair(5, 1), digitalRoot(14))
        assertEquals(Pair(6, 2), digitalRoot(267))
        assertEquals(Pair(1, 1), digitalRoot(10))
        assertEquals(Pair(7, 1), digitalRoot(16))
        assertEquals(Pair(9, 2), digitalRoot(627615))
        assertEquals(Pair(6, 2), digitalRoot(39390))
        assertEquals(Pair(3, 2), digitalRoot(588225))
        assertEquals(Pair(9, 2), digitalRoot(393900588225))
        assertThrows(IllegalArgumentException::class.java) { digitalRoot(-123) }
    }
}
