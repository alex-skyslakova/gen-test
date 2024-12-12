import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DerangedAnagramsTest {

    @Test
    public void testIsDeranged_True() {
        assertTrue(DerangedAnagrams.isDeranged("abcd", "dcba"));
        assertTrue(DerangedAnagrams.isDeranged("hello", "olelh"));
    }

    @Test
    public void testIsDeranged_False() {
        assertFalse(DerangedAnagrams.isDeranged("abcd", "abdc"));
        assertFalse(DerangedAnagrams.isDeranged("hello", "olleh"));
    }

    @Test
    public void testPrintLongestDerangedAnagram_NoResult() {
        List<String> words = Arrays.asList("abc", "def", "ghi");
        DerangedAnagrams.printLongestDerangedAnagram(words);
        // Since there are no deranged anagrams, the output should be "no result"
        // This test is more about verifying the behavior when no deranged anagrams are found.
    }

    @Test
    public void testPrintLongestDerangedAnagram_WithResult() {
        List<String> words = Arrays.asList("abc", "bca", "cab", "def", "ghi");
        DerangedAnagrams.printLongestDerangedAnagram(words);
        // Since "abc" and "bca" are deranged anagrams, the output should be "abc bca"
        // This test is more about verifying the behavior when deranged anagrams are found.
    }

    @Test
    public void testPrintLongestDerangedAnagram_LongestFirst() {
        List<String> words = Arrays.asList("abc", "bca", "cab", "defg", "gfed", "ghi");
        DerangedAnagrams.printLongestDerangedAnagram(words);
        // Since "defg" and "gfed" are deranged anagrams and longer, the output should be "defg gfed"
        // This test is more about verifying the behavior when longer deranged anagrams are found.
    }

    @Test
    public void testPrintLongestDerangedAnagram_MultipleAnagrams() {
        List<String> words = Arrays.asList("abc", "bca", "cab", "def", "fed", "ghi");
        DerangedAnagrams.printLongestDerangedAnagram(words);
        // Since there are multiple deranged anagrams, the output should be the first found
        // This test is more about verifying the behavior when multiple deranged anagrams are found.
    }

    @Test
    public void testPrintLongestDerangedAnagram_CaseInsensitive() {
        List<String> words = Arrays.asList("abc", "BCA", "cab", "def", "fed", "ghi");
        DerangedAnagrams.printLongestDerangedAnagram(words);
        // Since "abc" and "BCA" are deranged anagrams, the output should be "abc BCA"
        // This test is more about verifying the behavior when case-insensitive deranged anagrams are found.
    }
}
