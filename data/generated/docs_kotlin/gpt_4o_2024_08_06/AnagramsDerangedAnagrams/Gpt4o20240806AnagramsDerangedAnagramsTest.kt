import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DerangedAnagramsTest {

    @Test
    fun testIsDeranged() {
        assertTrue(isDeranged("abc", "bca"))
        assertTrue(isDeranged("listen", "silent"))
        assertFalse(isDeranged("abc", "abc"))
        assertFalse(isDeranged("abcd", "abdc"))
        assertTrue(isDeranged("abcd", "dcba"))
    }

    @Test
    fun testIsDerangedDifferentLengths() {
        assertFalse(isDeranged("abc", "abcd"))
        assertFalse(isDeranged("abcd", "abc"))
    }

    @Test
    fun testIsDerangedEmptyStrings() {
        assertTrue(isDeranged("", ""))
    }

    @Test
    fun testIsDerangedSingleCharacter() {
        assertFalse(isDeranged("a", "a"))
        assertTrue(isDeranged("a", "b"))
    }

    @Test
    fun testIsDerangedSameCharactersDifferentOrder() {
        assertTrue(isDeranged("aabbcc", "bbaacc"))
        assertFalse(isDeranged("aabbcc", "aabbcc"))
    }
}
