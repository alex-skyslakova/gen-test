import org.junit.Test;
import static org.junit.Assert.*;

public class DammAlgorithmTest {

    @Test
    public void testValidChecksum() {
        assertTrue(DammAlgorithm.damm("5724")); // Valid checksum
        assertTrue(DammAlgorithm.damm("112946")); // Valid checksum
    }

    @Test
    public void testInvalidChecksum() {
        assertFalse(DammAlgorithm.damm("5727")); // Invalid checksum
        assertFalse(DammAlgorithm.damm("112949")); // Invalid checksum
    }

    @Test
    public void testSingleDigitError() {
        assertFalse(DammAlgorithm.damm("5725")); // Single digit error from valid "5724"
    }

    @Test
    public void testAdjacentTranspositionError() {
        assertFalse(DammAlgorithm.damm("5274")); // Adjacent transposition error from valid "5724"
    }

    @Test
    public void testEmptyString() {
        assertTrue(DammAlgorithm.damm("")); // Edge case: empty string should be valid
    }

    @Test
    public void testSingleDigit() {
        assertFalse(DammAlgorithm.damm("1")); // Single digit, should be invalid
    }

    @Test
    public void testNonDigitCharacters() {
        try {
            DammAlgorithm.damm("57a24");
            fail("Expected NumberFormatException for non-digit characters");
        } catch (NumberFormatException e) {
            // Expected exception
        }
    }
}
