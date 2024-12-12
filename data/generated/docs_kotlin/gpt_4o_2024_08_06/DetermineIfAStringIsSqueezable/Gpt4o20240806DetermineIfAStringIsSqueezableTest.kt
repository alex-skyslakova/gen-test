import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SqueezeStringTest {

    @Test
    fun testEmptyString() {
        val input = ""
        val charToSqueeze = ' '
        val expected = ""
        assertEquals(expected, squeeze(input, charToSqueeze))
    }

    @Test
    fun testStringWithDash() {
        val input = "\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln "
        val charToSqueeze = '-'
        val expected = "\"If I were two-faced, would I be wearing this one?\" - Abraham Lincoln "
        assertEquals(expected, squeeze(input, charToSqueeze))
    }

    @Test
    fun testStringWithSeven() {
        val input = "..1111111111111111111111111111111111111111111111111111111111111117777888"
        val charToSqueeze = '7'
        val expected = "..1111111111111111111111111111111111111111111111111111111111111117888"
        assertEquals(expected, squeeze(input, charToSqueeze))
    }

    @Test
    fun testStringWithPeriod() {
        val input = "I never give 'em hell, I just tell the truth, and they think it's hell. "
        val charToSqueeze = '.'
        val expected = "I never give 'em hell, I just tell the truth, and they think it's hell "
        assertEquals(expected, squeeze(input, charToSqueeze))
    }

    @Test
    fun testStringWithRepeatedBlanks() {
        val input = "                                                    --- Harry S Truman  "
        val charToSqueeze = ' '
        val expected = " --- Harry S Truman "
        assertEquals(expected, squeeze(input, charToSqueeze))
    }

    @Test
    fun testStringWithRepeatedMinus() {
        val input = "                                                    --- Harry S Truman  "
        val charToSqueeze = '-'
        val expected = "                                                    - Harry S Truman  "
        assertEquals(expected, squeeze(input, charToSqueeze))
    }

    @Test
    fun testStringWithRepeatedR() {
        val input = "                                                    --- Harry S Truman  "
        val charToSqueeze = 'r'
        val expected = "                                                    --- Hary S Truman  "
        assertEquals(expected, squeeze(input, charToSqueeze))
    }

    @Test
    fun testStringWithFive() {
        val input = "122333444455555666666777777788888888999999999"
        val charToSqueeze = '5'
        val expected = "122333444455566666677777778888888999999999"
        assertEquals(expected, squeeze(input, charToSqueeze))
    }

    @Test
    fun testStringWithE() {
        val input = "The better the 4-wheel drive, the further you'll be from help when ya get stuck!"
        val charToSqueeze = 'e'
        val expected = "The beter the 4-whel drive, the further you'll be from help when ya get stuck!"
        assertEquals(expected, squeeze(input, charToSqueeze))
    }

    @Test
    fun testStringWithS() {
        val input = "headmistressship"
        val charToSqueeze = 's'
        val expected = "headmistreship"
        assertEquals(expected, squeeze(input, charToSqueeze))
    }
}
