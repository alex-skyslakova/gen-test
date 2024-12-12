import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DerangedAnagramsTest {

    @Test
    void testIsDeranged() {
        assertTrue(DerangedAnagrams.isDeranged("abc", "bca"));
        assertTrue(DerangedAnagrams.isDeranged("listen", "silent"));
        assertFalse(DerangedAnagrams.isDeranged("abc", "abc"));
        assertFalse(DerangedAnagrams.isDeranged("abc", "cab"));
        assertFalse(DerangedAnagrams.isDeranged("a", "a"));
    }

    @Test
    void testPrintLongestDerangedAnagram() {
        List<String> words = Arrays.asList("listen", "silent", "enlist", "inlets", "google", "gogole");
        // Capture the output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        DerangedAnagrams.printLongestDerangedAnagram(words);

        // Check if the output contains the expected deranged anagram pair
        String output = outContent.toString().trim();
        assertTrue(output.equals("listen silent") || output.equals("silent listen") ||
                   output.equals("enlist inlets") || output.equals("inlets enlist"));
    }

    @Test
    void testNoDerangedAnagram() {
        List<String> words = Arrays.asList("abc", "def", "ghi");
        // Capture the output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        DerangedAnagrams.printLongestDerangedAnagram(words);

        // Check if the output is "no result"
        String output = outContent.toString().trim();
        assertEquals("no result", output);
    }
}
