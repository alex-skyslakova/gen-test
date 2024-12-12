import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EertreeTest {

    @Test
    fun testEertreeConstruction() {
        val str = "eertree"
        val eertree = Eertree(str)
        val expectedSubPalindromes = listOf("e", "ee", "r", "t", "e", "ee", "eertree")
        val actualSubPalindromes = eertree.getSubPalindromes().sorted()
        assertEquals(expectedSubPalindromes.sorted(), actualSubPalindromes)
    }

    @Test
    fun testEmptyString() {
        val str = ""
        val eertree = Eertree(str)
        val expectedSubPalindromes = emptyList<String>()
        val actualSubPalindromes = eertree.getSubPalindromes()
        assertEquals(expectedSubPalindromes, actualSubPalindromes)
    }

    @Test
    fun testSingleCharacterString() {
        val str = "a"
        val eertree = Eertree(str)
        val expectedSubPalindromes = listOf("a")
        val actualSubPalindromes = eertree.getSubPalindromes()
        assertEquals(expectedSubPalindromes, actualSubPalindromes)
    }

    @Test
    fun testAllUniqueCharacters() {
        val str = "abc"
        val eertree = Eertree(str)
        val expectedSubPalindromes = listOf("a", "b", "c")
        val actualSubPalindromes = eertree.getSubPalindromes().sorted()
        assertEquals(expectedSubPalindromes.sorted(), actualSubPalindromes)
    }

    @Test
    fun testAllSameCharacters() {
        val str = "aaa"
        val eertree = Eertree(str)
        val expectedSubPalindromes = listOf("a", "aa", "aaa")
        val actualSubPalindromes = eertree.getSubPalindromes().sorted()
        assertEquals(expectedSubPalindromes.sorted(), actualSubPalindromes)
    }

    @Test
    fun testComplexString() {
        val str = "abacaba"
        val eertree = Eertree(str)
        val expectedSubPalindromes = listOf("a", "b", "a", "c", "aba", "aca", "bacab", "abacaba")
        val actualSubPalindromes = eertree.getSubPalindromes().sorted()
        assertEquals(expectedSubPalindromes.sorted(), actualSubPalindromes)
    }
}
