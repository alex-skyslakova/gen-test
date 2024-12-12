import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LevenshteinAlignmentTest {

    @Test
    public void testAlignment_SameStrings() {
        String[] result = LevenshteinAlignment.alignment("hello", "hello");
        assertArrayEquals(new String[]{"hello", "hello"}, result);
    }

    @Test
    public void testAlignment_OneInsertion() {
        String[] result = LevenshteinAlignment.alignment("hello", "helloo");
        assertArrayEquals(new String[]{"hello-", "helloo"}, result);
    }

    @Test
    public void testAlignment_OneDeletion() {
        String[] result = LevenshteinAlignment.alignment("hello", "helo");
        assertArrayEquals(new String[]{"hello", "helo-"}, result);
    }

    @Test
    public void testAlignment_OneSubstitution() {
        String[] result = LevenshteinAlignment.alignment("hello", "hallo");
        assertArrayEquals(new String[]{"hello", "hallo"}, result);
    }

    @Test
    public void testAlignment_MultipleOperations() {
        String[] result = LevenshteinAlignment.alignment("rosettacode", "raisethysword");
        assertArrayEquals(new String[]{"r-oset-tacode", "raisethysword"}, result);
    }

    @Test
    public void testAlignment_EmptyStrings() {
        String[] result = LevenshteinAlignment.alignment("", "");
        assertArrayEquals(new String[]{"", ""}, result);
    }

    @Test
    public void testAlignment_OneEmptyString() {
        String[] result = LevenshteinAlignment.alignment("hello", "");
        assertArrayEquals(new String[]{"hello", "-----"}, result);
    }

    @Test
    public void testAlignment_CaseInsensitivity() {
        String[] result = LevenshteinAlignment.alignment("Hello", "hello");
        assertArrayEquals(new String[]{"hello", "hello"}, result);
    }

    @Test
    public void testAlignment_ComplexCase() {
        String[] result = LevenshteinAlignment.alignment("kitten", "sitting");
        assertArrayEquals(new String[]{"kitten-", "sitting"}, result);
    }

    @Test
    public void testAlignment_DifferentLengths() {
        String[] result = LevenshteinAlignment.alignment("abcdef", "azced");
        assertArrayEquals(new String[]{"abc-def", "azc-ed-"}, result);
    }
}
