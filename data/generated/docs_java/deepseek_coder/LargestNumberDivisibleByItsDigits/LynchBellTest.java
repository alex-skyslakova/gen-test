import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LynchBellTest {

    @Test
    public void testUniqueDigits_UniqueDigits_ReturnsTrue() {
        assertTrue(LynchBell.uniqueDigits(1234));
    }

    @Test
    public void testUniqueDigits_NonUniqueDigits_ReturnsFalse() {
        assertFalse(LynchBell.uniqueDigits(1224));
    }

    @Test
    public void testUniqueDigits_ContainsZero_ReturnsFalse() {
        assertFalse(LynchBell.uniqueDigits(1204));
    }

    @Test
    public void testUniqueDigits_ContainsFive_ReturnsFalse() {
        assertFalse(LynchBell.uniqueDigits(1254));
    }

    @Test
    public void testUniqueDigits_SingleDigit_ReturnsTrue() {
        assertTrue(LynchBell.uniqueDigits(7));
    }

    @Test
    public void testUniqueDigits_AllUniqueDigits_ReturnsTrue() {
        assertTrue(LynchBell.uniqueDigits(98764321));
    }

    @Test
    public void testTestNumber_DivisibleByAllDigits_ReturnsTrue() {
        assertTrue(LynchBell.testNumber(135));
    }

    @Test
    public void testTestNumber_NotDivisibleByAllDigits_ReturnsFalse() {
        assertFalse(LynchBell.testNumber(175));
    }

    @Test
    public void testTestNumber_SingleDigit_ReturnsTrue() {
        assertTrue(LynchBell.testNumber(7));
    }

    @Test
    public void testTestNumber_LargeNumberDivisibleByAllDigits_ReturnsTrue() {
        assertTrue(LynchBell.testNumber(9867312));
    }

    @Test
    public void testTestNumber_LargeNumberNotDivisibleByAllDigits_ReturnsFalse() {
        assertFalse(LynchBell.testNumber(9867313));
    }

    @Test
    public void testMain_FindsLynchBellNumber() {
        // This test will run the main method and check if it finds a Lynch-Bell number
        // Since the main method prints the result, we can capture the output and verify it
        // Note: This test assumes that the main method will find a Lynch-Bell number within a reasonable time
        // and that the output will be as expected.

        // Redirect System.out to capture the output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Run the main method
        LynchBell.main(new String[]{});

        // Capture the output
        String output = outContent.toString();

        // Verify the output contains the expected message
        assertTrue(output.contains("Number found: "));
    }
}
