import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class EertreeTest {

    @Test
    fun testEertreeEmpty() {
        val eertree = Eertree("")
        assertEquals(0, eertree.nodes.size)
        assertEquals(listOf<String>(), eertree.getSubPalindromes())
    }

    @Test
    fun testEertreeSingleChar() {
        val eertree = Eertree("a")
        assertEquals(1, eertree.nodes.size)
        assertEquals(listOf("a"), eertree.getSubPalindromes())
    }

    @Test
    fun testEertreePalindrome() {
        val eertree = Eertree("abaaba")
        assertEquals(5, eertree.nodes.size)
        assertEquals(listOf("a", "b", "aba", "a", "abaaba", "b"), eertree.getSubPalindromes())
    }


    @Test
    fun testEertreeExample() {
        val str = "eertree"
        val eertree = Eertree(str)
        assertEquals(7, eertree.nodes.size)
        assertEquals(listOf("e", "r", "t", "ee", "e", "eerree", "r", "t", "reeeert"), eertree.getSubPalindromes())
    }

    @Test
    fun testEertreeRepeatedChars() {
        val eertree = Eertree("aaaa")
        assertEquals(3, eertree.nodes.size)
        assertEquals(listOf("a", "aa", "aaa", "aaaa"), eertree.getSubPalindromes())
    }

    @Test
    fun testEertreeNoPalindromes() {
        val eertree = Eertree("abcdefg")
        assertEquals(7, eertree.nodes.size)
        assertEquals(listOf("a", "b", "c", "d", "e", "f", "g"), eertree.getSubPalindromes())
    }


}
