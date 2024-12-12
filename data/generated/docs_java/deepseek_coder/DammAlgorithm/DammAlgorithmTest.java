import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DammAlgorithmTest {

    @Test
    public void testValidNumbers() {
        assertTrue(DammAlgorithm.damm("5724"));
        assertTrue(DammAlgorithm.damm("112946"));
    }

    @Test
    public void testInvalidNumbers() {
        assertFalse(DammAlgorithm.damm("5727"));
        assertFalse(DammAlgorithm.damm("112949"));
    }

    @Test
    public void testSingleDigitNumber() {
        assertTrue(DammAlgorithm.damm("0"));
        assertTrue(DammAlgorithm.damm("1"));
        assertTrue(DammAlgorithm.damm("2"));
        assertTrue(DammAlgorithm.damm("3"));
        assertTrue(DammAlgorithm.damm("4"));
        assertTrue(DammAlgorithm.damm("5"));
        assertTrue(DammAlgorithm.damm("6"));
        assertTrue(DammAlgorithm.damm("7"));
        assertTrue(DammAlgorithm.damm("8"));
        assertTrue(DammAlgorithm.damm("9"));
    }

    @Test
    public void testEmptyString() {
        assertTrue(DammAlgorithm.damm(""));
    }

    @Test
    public void testNonDigitCharacters() {
        assertThrows(NumberFormatException.class, () -> DammAlgorithm.damm("572A"));
        assertThrows(NumberFormatException.class, () -> DammAlgorithm.damm("11294B"));
    }

    @Test
    public void testMixedDigitsAndNonDigits() {
        assertThrows(NumberFormatException.class, () -> DammAlgorithm.damm("5724A"));
        assertThrows(NumberFormatException.class, () -> DammAlgorithm.damm("112946B"));
    }

    @Test
    public void testLeadingZeros() {
        assertTrue(DammAlgorithm.damm("0005724"));
        assertFalse(DammAlgorithm.damm("0005727"));
    }

    @Test
    public void testLargeNumber() {
        assertTrue(DammAlgorithm.damm("1234567890123456789012345678901234567890"));
        assertFalse(DammAlgorithm.damm("1234567890123456789012345678901234567891"));
    }
}
