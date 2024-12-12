import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class EertreeTest {

    @Test
    public void testEertreeConstruction() {
        List<Eertree.Node> tree = Eertree.eertree("eertree");
        assertNotNull(tree, "The eertree should not be null.");
        assertTrue(tree.size() > 0, "The eertree should contain nodes.");
    }

    @Test
    public void testSubPalindromes() {
        List<Eertree.Node> tree = Eertree.eertree("eertree");
        List<String> subPalindromes = Eertree.subPalindromes(tree);
        
        assertNotNull(subPalindromes, "The list of sub-palindromes should not be null.");
        assertTrue(subPalindromes.size() > 0, "The list of sub-palindromes should not be empty.");
        
        // Expected palindromes in "eertree"
        String[] expectedPalindromes = {"e", "ee", "r", "e", "ee", "eertree"};
        
        for (String palindrome : expectedPalindromes) {
            assertTrue(subPalindromes.contains(palindrome), "The palindrome list should contain: " + palindrome);
        }
    }

    @Test
    public void testEmptyString() {
        List<Eertree.Node> tree = Eertree.eertree("");
        List<String> subPalindromes = Eertree.subPalindromes(tree);
        
        assertNotNull(subPalindromes, "The list of sub-palindromes should not be null for an empty string.");
        assertEquals(0, subPalindromes.size(), "The list of sub-palindromes should be empty for an empty string.");
    }

    @Test
    public void testSingleCharacterString() {
        List<Eertree.Node> tree = Eertree.eertree("a");
        List<String> subPalindromes = Eertree.subPalindromes(tree);
        
        assertNotNull(subPalindromes, "The list of sub-palindromes should not be null for a single character string.");
        assertEquals(1, subPalindromes.size(), "The list of sub-palindromes should contain one element for a single character string.");
        assertEquals("a", subPalindromes.get(0), "The single palindrome should be the character itself.");
    }

    @Test
    public void testNoPalindromes() {
        List<Eertree.Node> tree = Eertree.eertree("abc");
        List<String> subPalindromes = Eertree.subPalindromes(tree);
        
        assertNotNull(subPalindromes, "The list of sub-palindromes should not be null.");
        assertEquals(3, subPalindromes.size(), "The list of sub-palindromes should contain only single characters.");
        assertTrue(subPalindromes.contains("a"), "The palindrome list should contain: a");
        assertTrue(subPalindromes.contains("b"), "The palindrome list should contain: b");
        assertTrue(subPalindromes.contains("c"), "The palindrome list should contain: c");
    }
}
