import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EertreeTest {

    @Test
    void testEertreeEmpty() {
        List<Eertree.Node> tree = Eertree.eertree("");
        assertEquals(2, tree.size());
        assertEquals(0, tree.get(0).length);
        assertEquals(1, tree.get(0).suffix);
        assertEquals(-1, tree.get(1).length);
        assertEquals(1, tree.get(1).suffix);
    }

    @Test
    void testEertreeSingleChar() {
        List<Eertree.Node> tree = Eertree.eertree("a");
        assertEquals(3, tree.size());
        assertEquals(1, tree.get(2).length);
        assertEquals(0, tree.get(2).suffix);
        assertTrue(tree.get(1).edges.containsKey('a'));
        assertEquals(2, (int)tree.get(1).edges.get('a'));

    }

    @Test
    void testEertreePalindrome() {
        List<Eertree.Node> tree = Eertree.eertree("abba");
        assertEquals(6, tree.size());
    }


    @Test
    void testEertreeExample() {
        List<Eertree.Node> tree = Eertree.eertree("eertree");
        assertEquals(11, tree.size());
    }



    @Test
    void testSubPalindromesEmpty() {
        List<Eertree.Node> tree = Eertree.eertree("");
        List<String> palindromes = Eertree.subPalindromes(tree);
        assertTrue(palindromes.isEmpty());

    }

    @Test
    void testSubPalindromesSingleChar() {
         List<Eertree.Node> tree = Eertree.eertree("a");
        List<String> palindromes = Eertree.subPalindromes(tree);
        assertEquals(1, palindromes.size());
        assertEquals("a", palindromes.get(0));
    }



    @Test
    void testSubPalindromesExample() {
        List<Eertree.Node> tree = Eertree.eertree("eertree");
        List<String> palindromes = Eertree.subPalindromes(tree);

        List<String> expected = List.of("e", "ee", "r", "t", "r", "ee", "e");
        assertEquals(expected.size(), palindromes.size());
        assertTrue(palindromes.containsAll(expected));
    }


    @Test
    void testSubPalindromesPalindrome(){
        List<Eertree.Node> tree = Eertree.eertree("abba");
        List<String> palindromes = Eertree.subPalindromes(tree);
        List<String> expected = List.of("a", "b", "bb", "a", "abba");
        assertEquals(expected.size(), palindromes.size());
        assertTrue(palindromes.containsAll(expected));

    }
}
