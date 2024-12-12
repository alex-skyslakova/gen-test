import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CollapseStringTest {

    @Test
    fun testEmptyString() {
        val original = ""
        val expected = ""
        assertEquals(expected, collapse(original))
    }

    @Test
    fun testStringWithNoRepetitions() {
        val original = "aardvark"
        val expected = "aardvark"
        assertEquals(expected, collapse(original))
    }

    @Test
    fun testStringWithRepetitions() {
        val original = "headmistressship"
        val expected = "headmistreship"
        assertEquals(expected, collapse(original))
    }

    @Test
    fun testStringWithSpaces() {
        val original = "                                                   ---  Harry S Truman  "
        val expected = " --- Harry S Truman "
        assertEquals(expected, collapse(original))
    }

    @Test
    fun testStringWithPunctuation() {
        val original = "\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln "
        val expected = "\"If I were two-faced, would I be wearing this one?\" - Abraham Lincoln "
        assertEquals(expected, collapse(original))
    }

    @Test
    fun testStringWithNumbers() {
        val original = "..1111111111111111111111111111111111111111111111111111111111111117777888"
        val expected = ".1178"
        assertEquals(expected, collapse(original))
    }

    @Test
    fun testStringWithRepeatedCharacters() {
        val original = "The better the 4-wheel drive, the further you'll be from help when ya get stuck!"
        val expected = "The beter the 4-whel drive, the further you'll be from help when ya get stuck!"
        assertEquals(expected, collapse(original))
    }

    @Test
    fun testStringWithRepeatedCharactersAndSpaces() {
        val original = "I never give 'em hell, I just tell the truth, and they think it's hell. "
        val expected = "I never give 'em hel, I just tel the truth, and they think it's hel. "
        assertEquals(expected, collapse(original))
    }
}
