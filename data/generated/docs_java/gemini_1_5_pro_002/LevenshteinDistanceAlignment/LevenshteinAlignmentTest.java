import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LevenshteinAlignmentTest {

    @Test
    void testIdenticalStrings() {
        String[] result = LevenshteinAlignment.alignment("hello", "hello");
        assertArrayEquals(new String[]{"hello", "hello"}, result);
    }

    @Test
    void testEmptyString() {
        String[] result = LevenshteinAlignment.alignment("", "");
        assertArrayEquals(new String[]{"", ""}, result);
    }

    @Test
    void testOneEmptyString() {
        String[] result = LevenshteinAlignment.alignment("hello", "");
        assertArrayEquals(new String[]{"hello", "-----"}, result);

        result = LevenshteinAlignment.alignment("", "hello");
        assertArrayEquals(new String[]{"-----", "hello"}, result);
    }

    @Test
    void testRosettaCodeExample() {
        String[] result = LevenshteinAlignment.alignment("rosettacode", "raisethysword");
        assertArrayEquals(new String[]{"rosettacode", "r-aiset-hysword"}, result);
    }

    @Test
    void testInsertion() {
        String[] result = LevenshteinAlignment.alignment("place", "palace");
        assertArrayEquals(new String[]{"p-lace", "palace"}, result);
    }

    @Test
    void testDeletion() {
        String[] result = LevenshteinAlignment.alignment("palace", "place");
        assertArrayEquals(new String[]{"palace", "p-lace"}, result);
    }
    
    @Test
    void testSubstitution() {
        String[] result = LevenshteinAlignment.alignment("sit", "sat");
        assertArrayEquals(new String[]{"sit", "sat"}, result);
    }

    @Test
    void testMixedOperations() {
        String[] result = LevenshteinAlignment.alignment("kitten", "sitting");
        assertArrayEquals(new String[]{"kitten-", "s-itting"}, result);
    }

    @Test
    void testCaseInsensitivity() {
        String[] result = LevenshteinAlignment.alignment("Hello", "hELLo");
        assertArrayEquals(new String[]{"hello", "hello"}, result);
    }


}
