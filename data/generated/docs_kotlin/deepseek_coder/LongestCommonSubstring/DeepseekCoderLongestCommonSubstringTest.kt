import org.junit.Test
import kotlin.test.assertEquals

class LongestCommonSubstringTest {

    @Test
    fun testEmptyStrings() {
        assertEquals("", lcs("", ""))
    }

    @Test
    fun testOneEmptyString() {
        assertEquals("", lcs("abc", ""))
        assertEquals("", lcs("", "abc"))
    }

    @Test
    fun testNoCommonSubstring() {
        assertEquals("", lcs("abc", "def"))
    }

    @Test
    fun testSingleCharacterCommonSubstring() {
        assertEquals("a", lcs("abc", "a"))
        assertEquals("a", lcs("a", "abc"))
    }

    @Test
    fun testMultipleCharacterCommonSubstring() {
        assertEquals("test", lcs("testing123testing", "thisisatest"))
    }

    @Test
    fun testLongerCommonSubstring() {
        assertEquals("substring", lcs("longestcommonsubstring", "substring"))
    }

    @Test
    fun testRepeatedCommonSubstring() {
        assertEquals("abc", lcs("abcabcabc", "defabcxyz"))
    }

    @Test
    fun testCaseInsensitivity() {
        assertEquals("test", lcs("TeStInG123tEsTiNg", "tHiSiSaTeSt"))
    }

    @Test
    fun testSpecialCharacters() {
        assertEquals("!@#", lcs("abc!@#def", "!@#xyz"))
    }

    @Test
    fun testWhitespace() {
        assertEquals("  ", lcs("  abc  ", "def  ghi"))
    }

    @Test
    fun testLongStrings() {
        val longString1 = "a".repeat(1000) + "b".repeat(1000)
        val longString2 = "c".repeat(1000) + "b".repeat(1000)
        assertEquals("b".repeat(1000), lcs(longString1, longString2))
    }

    @Test
    fun testIdenticalStrings() {
        val identicalString = "identical"
        assertEquals(identicalString, lcs(identicalString, identicalString))
    }

    @Test
    fun testSubstringAtStart() {
        assertEquals("start", lcs("startmiddleend", "start"))
    }

    @Test
    fun testSubstringAtEnd() {
        assertEquals("end", lcs("startmiddleend", "end"))
    }

    @Test
    fun testSubstringInMiddle() {
        assertEquals("middle", lcs("startmiddleend", "middle"))
    }

    @Test
    fun testSubstringAtMultiplePositions() {
        assertEquals("common", lcs("commonstartcommonend", "anothercommon"))
    }

    @Test
    fun testSubstringWithDifferentLengths() {
        assertEquals("short", lcs("shortlong", "anothershort"))
    }

    @Test
    fun testSubstringWithDifferentOrder() {
        assertEquals("order", lcs("orderfirst", "secondorder"))
    }

    @Test
    fun testSubstringWithOverlappingMatches() {
        assertEquals("abcab", lcs("abcabcabc", "xyzabcabpqr"))
    }
}
