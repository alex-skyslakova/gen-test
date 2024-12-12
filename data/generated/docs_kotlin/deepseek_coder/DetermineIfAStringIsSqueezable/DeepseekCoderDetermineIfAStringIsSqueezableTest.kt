import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SqueezeTest {

    @Test
    fun testEmptyString() {
        val input = ""
        val include = ' '
        val expected = ""
        assertEquals(expected, squeeze(input, include))
    }

    @Test
    fun testNoRepeatedCharacter() {
        val input = "abcdefg"
        val include = 'a'
        val expected = "abcdefg"
        assertEquals(expected, squeeze(input, include))
    }

    @Test
    fun testSingleRepeatedCharacter() {
        val input = "aaabbbccc"
        val include = 'a'
        val expected = "abbbccc"
        assertEquals(expected, squeeze(input, include))
    }

    @Test
    fun testMultipleRepeatedCharacters() {
        val input = "aaabbbccc"
        val include = 'b'
        val expected = "aaabccc"
        assertEquals(expected, squeeze(input, include))
    }

    @Test
    fun testMixedCharacters() {
        val input = "aabbccddeeff"
        val include = 'd'
        val expected = "aabbccdeeff"
        assertEquals(expected, squeeze(input, include))
    }

    @Test
    fun testAllRepeatedCharacters() {
        val input = "aaaa"
        val include = 'a'
        val expected = "a"
        assertEquals(expected, squeeze(input, include))
    }

    @Test
    fun testSpecifiedCharacterNotInString() {
        val input = "abcdefg"
        val include = 'z'
        val expected = "abcdefg"
        assertEquals(expected, squeeze(input, include))
    }

    @Test
    fun testMultipleSpecifiedCharacters() {
        val input = "aabbccddeeff"
        val include = 'a'
        val expected = "abccddeeff"
        assertEquals(expected, squeeze(input, include))
    }

    @Test
    fun testComplexString() {
        val input = "The better the 4-wheel drive, the further you'll be from help when ya get stuck!"
        val include = 'e'
        val expected = "The better the 4-whel drive, the further you'll be from help when ya get stuck!"
        assertEquals(expected, squeeze(input, include))
    }

    @Test
    fun testTrumanSignatureLineWithBlank() {
        val input = "                                                    --- Harry S Truman  "
        val include = ' '
        val expected = " --- Harry S Truman "
        assertEquals(expected, squeeze(input, include))
    }

    @Test
    fun testTrumanSignatureLineWithMinus() {
        val input = "                                                    --- Harry S Truman  "
        val include = '-'
        val expected = "                                                    - Harry S Truman  "
        assertEquals(expected, squeeze(input, include))
    }

    @Test
    fun testTrumanSignatureLineWithR() {
        val input = "                                                    --- Harry S Truman  "
        val include = 'r'
        val expected = "                                                    --- Harry S Truman  "
        assertEquals(expected, squeeze(input, include))
    }
}
