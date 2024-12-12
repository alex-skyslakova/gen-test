import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CollapseStringTest {

    @Test
    fun testEmptyString() {
        val input = ""
        val expected = ""
        assertEquals(expected, collapse(input))
    }

    @Test
    fun testNoRepeatedCharacters() {
        val input = "abcdefg"
        val expected = "abcdefg"
        assertEquals(expected, collapse(input))
    }

    @Test
    fun testSingleRepeatedCharacter() {
        val input = "aaabbbccc"
        val expected = "abc"
        assertEquals(expected, collapse(input))
    }

    @Test
    fun testMultipleRepeatedCharacters() {
        val input = "aabbccddeeffgghh"
        val expected = "abcdefgh"
        assertEquals(expected, collapse(input))
    }

    @Test
    fun testMixedCharacters() {
        val input = "The better the 4-wheel drive, the further you'll be from help when ya get stuck!"
        val expected = "The beter the 4-whel drive, the further you'l be from help when ya get stuck!"
        assertEquals(expected, collapse(input))
    }

    @Test
    fun testLongStringWithRepeats() {
        val input = "..1111111111111111111111111111111111111111111111111111111111111117777888"
        val expected = ".1.78"
        assertEquals(expected, collapse(input))
    }

    @Test
    fun testStringWithSpaces() {
        val input = "I never give 'em hell, I just tell the truth, and they think it's hell. "
        val expected = "I never give 'em hel, I just tell the truth, and they think it's hel. "
        assertEquals(expected, collapse(input))
    }

    @Test
    fun testStringWithManySpaces() {
        val input = "                                                   ---  Harry S Truman  "
        val expected = " --- Harry S Truman "
        assertEquals(expected, collapse(input))
    }

    @Test
    fun testStringWithSpecialCharacters() {
        val input = "headmistressship"
        val expected = "headmistreship"
        assertEquals(expected, collapse(input))
    }

    @Test
    fun testStringWithSingleCharacter() {
        val input = "aardvark"
        val expected = "ardvark"
        assertEquals(expected, collapse(input))
    }
}
