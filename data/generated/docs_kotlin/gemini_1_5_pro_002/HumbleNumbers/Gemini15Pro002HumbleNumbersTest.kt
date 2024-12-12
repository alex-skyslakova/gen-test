import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class HumbleNumbersTest {

    @Test
    fun testIsHumble() {
        assertTrue(isHumble(1))
        assertTrue(isHumble(2))
        assertTrue(isHumble(3))
        assertTrue(isHumble(4))
        assertTrue(isHumble(5))
        assertTrue(isHumble(6))
        assertTrue(isHumble(7))
        assertTrue(isHumble(8))
        assertTrue(isHumble(9))
        assertTrue(isHumble(10))
        assertTrue(isHumble(12))
        assertTrue(isHumble(14))
        assertTrue(isHumble(15))
        assertTrue(isHumble(16))
        assertTrue(isHumble(18))
        assertTrue(isHumble(20))
        assertTrue(isHumble(21))
        assertTrue(isHumble(24))
        assertTrue(isHumble(25))
        assertTrue(isHumble(27))
        assertTrue(isHumble(28))
        assertTrue(isHumble(30))

        assertFalse(isHumble(11))
        assertFalse(isHumble(13))
        assertFalse(isHumble(17))
        assertFalse(isHumble(19))
        assertFalse(isHumble(22))
        assertFalse(isHumble(23))
        assertFalse(isHumble(26))
        assertFalse(isHumble(29))
        assertFalse(isHumble(31))
        assertFalse(isHumble(121))
        assertFalse(isHumble(59))

        // Edge cases
        assertTrue(isHumble(0)) // According to the definition, 0 should also be humble.

        // Larger humble numbers
        assertTrue(isHumble(2*2*2*3*3*5*7))
        assertTrue(isHumble(2*2*3*3*5*5*7*7))

        // Non-humble numbers with prime factors greater than 7
        assertFalse(isHumble(11*2))
        assertFalse(isHumble(13*3))
        assertFalse(isHumble(17*5))
        assertFalse(isHumble(19*7))
    }



}
