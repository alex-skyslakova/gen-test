import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HumbleNumbersTest {

    @Test
    fun testIsHumble() {
        // Test cases for humble numbers
        assertEquals(true, isHumble(1))
        assertEquals(true, isHumble(2))
        assertEquals(true, isHumble(3))
        assertEquals(true, isHumble(4))
        assertEquals(true, isHumble(5))
        assertEquals(true, isHumble(6))
        assertEquals(true, isHumble(7))
        assertEquals(true, isHumble(8))
        assertEquals(true, isHumble(9))
        assertEquals(true, isHumble(10))
        assertEquals(true, isHumble(14))
        assertEquals(true, isHumble(15))
        assertEquals(true, isHumble(21))
        assertEquals(true, isHumble(35))
        
        // Test cases for non-humble numbers
        assertEquals(false, isHumble(11))
        assertEquals(false, isHumble(13))
        assertEquals(false, isHumble(17))
        assertEquals(false, isHumble(19))
        assertEquals(false, isHumble(23))
        assertEquals(false, isHumble(29))
    }

    @Test
    fun testFirst50HumbleNumbers() {
        val expectedFirst50 = listOf(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            12, 14, 15, 16, 18, 20, 21, 24, 25, 27,
            28, 30, 32, 35, 36, 40, 42, 45, 48, 49,
            50, 54, 56, 60, 63, 64, 70, 72, 75, 80,
            81, 84, 90, 96, 98, 100, 105, 108, 112, 120
        )
        val actualFirst50 = mutableListOf<Int>()
        var count = 0
        var num = 1
        while (count < 50) {
            if (isHumble(num)) {
                actualFirst50.add(num)
                count++
            }
            num++
        }
        assertEquals(expectedFirst50, actualFirst50)
    }

    @Test
    fun testHumbleNumbersDigitCount() {
        val humble = mutableMapOf<Int, Int>()
        var count = 0
        var num = 1
        while (count < 1000) { // Arbitrary limit for testing
            if (isHumble(num)) {
                val str = num.toString()
                val len = str.length
                humble.merge(len, 1) { a, b -> a + b }
                count++
            }
            num++
        }

        // Check some known values
        assertEquals(9, humble[1]) // 1 to 9
        assertEquals(36, humble[2]) // 10 to 99
        assertEquals(155, humble[3]) // 100 to 999
    }
}
