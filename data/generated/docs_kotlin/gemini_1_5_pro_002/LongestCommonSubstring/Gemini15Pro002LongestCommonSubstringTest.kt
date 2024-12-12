import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LCSTest {

    @Test
    fun `empty strings`() {
        assertEquals("", lcs("", ""))
    }

    @Test
    fun `one empty string`() {
        assertEquals("", lcs("abc", ""))
        assertEquals("", lcs("", "abc"))
    }

    @Test
    fun `no common substring`() {
        assertEquals("", lcs("abc", "def"))
    }

    @Test
    fun `example case`() {
        assertEquals("test", lcs("thisisatest", "testing123testing"))
    }

    @Test
    fun `substring at beginning`() {
        assertEquals("abc", lcs("abcdef", "abcxyz"))
    }
    
    @Test
    fun `substring at end`(){
        assertEquals("def", lcs("xyzdef", "abcdef"))
    }

    @Test
    fun `substring in the middle`() {
        assertEquals("cde", lcs("abcde123", "012cde456"))
    }

    @Test
    fun `identical strings`() {
        assertEquals("abcdef", lcs("abcdef", "abcdef"))
    }

    @Test
    fun `different length strings`() {
        assertEquals("abcd", lcs("abcd", "abcdef"))
        assertEquals("abcd", lcs("abcdef", "abcd"))
    }
    
    @Test
    fun `overlapping substrings`() {
        assertEquals("abab", lcs("ababab", "bababa"))
    }

    @Test
    fun `multiple common substrings of same length`() {
        assertEquals("abc", lcs("123abc456", "789abc000"))  // Picks the first one encountered. Could potentially be an issue depending on the exact requirements.
    }



}
