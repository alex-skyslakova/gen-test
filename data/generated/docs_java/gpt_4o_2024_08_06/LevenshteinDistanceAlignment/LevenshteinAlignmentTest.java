import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class LevenshteinAlignmentTest {

    @Test
    public void testAlignmentWithExampleWords() {
        String[] expected = {"R-OSETTAC-ODE", "RAISETHYSWORD"};
        String[] result = LevenshteinAlignment.alignment("rosettacode", "raisethysword");
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAlignmentWithIdenticalStrings() {
        String[] expected = {"example", "example"};
        String[] result = LevenshteinAlignment.alignment("example", "example");
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAlignmentWithEmptyFirstString() {
        String[] expected = {"-----", "hello"};
        String[] result = LevenshteinAlignment.alignment("", "hello");
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAlignmentWithEmptySecondString() {
        String[] expected = {"hello", "-----"};
        String[] result = LevenshteinAlignment.alignment("hello", "");
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAlignmentWithCompletelyDifferentStrings() {
        String[] expected = {"abc", "xyz"};
        String[] result = LevenshteinAlignment.alignment("abc", "xyz");
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAlignmentWithOneCharacterDifference() {
        String[] expected = {"kitten", "sitten"};
        String[] result = LevenshteinAlignment.alignment("kitten", "sitten");
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAlignmentWithInsertion() {
        String[] expected = {"-abc", "dabc"};
        String[] result = LevenshteinAlignment.alignment("abc", "dabc");
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAlignmentWithDeletion() {
        String[] expected = {"abcd", "ab-d"};
        String[] result = LevenshteinAlignment.alignment("abcd", "abd");
        assertArrayEquals(expected, result);
    }
}
