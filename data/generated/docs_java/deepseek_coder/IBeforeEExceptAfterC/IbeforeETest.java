import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IbeforeETest {

    @Test
    public void testIsPlausibleWord() {
        IbeforeE ibeforeE = new IbeforeE();

        // Test cases for "I before E when not preceded by C"
        assertTrue(ibeforeE.isPlausibleWord("believe")); // "ie" not preceded by "c"
        assertTrue(ibeforeE.isPlausibleWord("fierce"));  // "ie" not preceded by "c"
        assertFalse(ibeforeE.isPlausibleWord("friend")); // "ie" preceded by "c"

        // Test cases for "E before I when preceded by C"
        assertTrue(ibeforeE.isPlausibleWord("receive")); // "cei"
        assertTrue(ibeforeE.isPlausibleWord("ceiling")); // "cei"
        assertFalse(ibeforeE.isPlausibleWord("science")); // "cie"
    }

    @Test
    public void testIsOppPlausibleWord() {
        IbeforeE ibeforeE = new IbeforeE();

        // Test cases for "I before E when not preceded by C"
        assertFalse(ibeforeE.isOppPlausibleWord("believe")); // "ie" not preceded by "c"
        assertFalse(ibeforeE.isOppPlausibleWord("fierce"));  // "ie" not preceded by "c"
        assertTrue(ibeforeE.isOppPlausibleWord("friend"));  // "ie" preceded by "c"

        // Test cases for "E before I when preceded by C"
        assertFalse(ibeforeE.isOppPlausibleWord("receive")); // "cei"
        assertFalse(ibeforeE.isOppPlausibleWord("ceiling")); // "cei"
        assertTrue(ibeforeE.isOppPlausibleWord("science"));  // "cie"
    }

    @Test
    public void testIsPlausibleRule() {
        IbeforeE ibeforeE = new IbeforeE();

        // Mock the word list for testing
        String mockWordlist = "mock_wordlist.txt";
        try {
            // Create a mock word list file
            java.io.FileWriter fileWriter = new java.io.FileWriter(mockWordlist);
            fileWriter.write("believe\nfierce\nfriend\nreceive\nceiling\nscience\n");
            fileWriter.close();

            // Test the rule plausibility
            assertTrue(ibeforeE.isPlausibleRule(mockWordlist));

            // Clean up the mock file
            java.io.File file = new java.io.File(mockWordlist);
            file.delete();
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
