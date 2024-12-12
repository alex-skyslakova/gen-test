import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LongestCommonSubstringTest {

    @Test
    fun testLongestCommonSubstring() {
        assertEquals("test", lcs("thisisatest", "testing123testing"))
    }

    @Test
    fun testNoCommonSubstring() {
        assertEquals("", lcs("abc", "def"))
    }

    @Test
    fun testIdenticalStrings() {
        assertEquals("abc", lcs("abc", "abc"))
    }

    @Test
    fun testOneEmptyString() {
        assertEquals("", lcs("", "abc"))
        assertEquals("", lcs("abc", ""))
    }

    @Test
    fun testBothEmptyStrings() {
        assertEquals("", lcs("", ""))
    }

    @Test
    fun testSubstringAtStart() {
        assertEquals("abc", lcs("abcxyz", "abcdef"))
    }

    @Test
    fun testSubstringAtEnd() {
        assertEquals("xyz", lcs("abcxyz", "wxyz"))
    }

    @Test
    fun testSubstringInMiddle() {
        assertEquals("def", lcs("abcdefghi", "xyzdefuvw"))
    }

    @Test
    fun testMultipleCommonSubstrings() {
        assertEquals("abc", lcs("abcxyzabc", "abcuvwabc"))
    }

    @Test
    fun testCaseSensitivity() {
        assertEquals("abc", lcs("abc", "ABCabc"))
        assertEquals("", lcs("abc", "ABC"))
    }
}
