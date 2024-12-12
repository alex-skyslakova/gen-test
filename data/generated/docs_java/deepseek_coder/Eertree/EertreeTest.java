import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EertreeTest {

    @Test
    public void testEertreeConstruction() {
        List<Eertree.Node> tree = Eertree.eertree("eertree");
        assertNotNull(tree);
        assertEquals(10, tree.size()); // Expected size of the tree
    }

    @Test
    public void testSubPalindromes() {
        List<Eertree.Node> tree = Eertree.eertree("eertree");
        List<String> result = Eertree.subPalindromes(tree);

        List<String> expected = Arrays.asList(
            "e", "ee", "eertree", "ertre", "etete", "r", "rttr", "t", "tt"
        );

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testEmptyString() {
        List<Eertree.Node> tree = Eertree.eertree("");
        List<String> result = Eertree.subPalindromes(tree);

        List<String> expected = Arrays.asList();

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testSingleCharacterString() {
        List<Eertree.Node> tree = Eertree.eertree("a");
        List<String> result = Eertree.subPalindromes(tree);

        List<String> expected = Arrays.asList("a");

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testPalindromeString() {
        List<Eertree.Node> tree = Eertree.eertree("racecar");
        List<String> result = Eertree.subPalindromes(tree);

        List<String> expected = Arrays.asList(
            "r", "raccar", "aceca", "c", "cec", "e", "rcr"
        );

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testNonPalindromeString() {
        List<Eertree.Node> tree = Eertree.eertree("hello");
        List<String> result = Eertree.subPalindromes(tree);

        List<String> expected = Arrays.asList(
            "h", "e", "l", "ll", "o"
        );

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }
}
