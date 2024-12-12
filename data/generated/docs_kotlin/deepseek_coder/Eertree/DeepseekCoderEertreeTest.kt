import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class EertreeTest {

    @Test
    fun testEertreeConstruction() {
        val str = "eertree"
        val eertree = Eertree(str)

        // Verify the number of nodes created
        assertEquals(10, eertree.nodes.size)
    }

    @Test
    fun testGetSubPalindromes() {
        val str = "eertree"
        val eertree = Eertree(str)
        val result = eertree.getSubPalindromes()

        // Verify the sub-palindromes
        val expectedSubPalindromes = listOf(
            "e", "ee", "eertree", "ertre", "etrte", "r", "rttr", "t", "tt"
        )
        assertEquals(expectedSubPalindromes.sorted(), result.sorted())
    }

    @Test
    fun testAddNewNode() {
        val str = "eertree"
        val eertree = Eertree(str)

        // Verify that adding a new character creates a new node
        val newChar = 'a'
        val newNodeCreated = eertree.add(newChar)
        assertTrue(newNodeCreated)
        assertEquals(11, eertree.nodes.size)
    }

    @Test
    fun testAddExistingNode() {
        val str = "eertree"
        val eertree = Eertree(str)

        // Verify that adding an existing character does not create a new node
        val existingChar = 'e'
        val newNodeCreated = eertree.add(existingChar)
        assertFalse(newNodeCreated)
        assertEquals(10, eertree.nodes.size)
    }

    @Test
    fun testMaxSuffixPalOdd() {
        val str = "eertree"
        val eertree = Eertree(str)

        // Verify the maximum suffix palindrome for an odd length root
        val maxSuffixPal = eertree.getMaxSuffixPal(eertree.rto, 'e')
        assertEquals(-1, maxSuffixPal.len)
    }

    @Test
    fun testMaxSuffixPalEven() {
        val str = "eertree"
        val eertree = Eertree(str)

        // Verify the maximum suffix palindrome for an even length root
        val maxSuffixPal = eertree.getMaxSuffixPal(eertree.rte, 'e')
        assertEquals(0, maxSuffixPal.len)
    }

    @Test
    fun testInfiniteLoopDetection() {
        val str = "eertree"
        val eertree = Eertree(str)

        // Verify that an infinite loop is detected if a node links to itself
        assertThrows(RuntimeException::class.java) {
            eertree.getMaxSuffixPal(eertree.rto, 'x')
        }
    }
}
