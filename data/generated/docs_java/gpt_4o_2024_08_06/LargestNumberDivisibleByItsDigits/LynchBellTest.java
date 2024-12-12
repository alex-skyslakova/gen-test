import org.junit.Test;
import static org.junit.Assert.*;

public class LynchBellTest {

    @Test
    public void testUniqueDigits() {
        assertTrue(LynchBell.uniqueDigits(123456789));
        assertFalse(LynchBell.uniqueDigits(112345678));
        assertFalse(LynchBell.uniqueDigits(123456780));
        assertFalse(LynchBell.uniqueDigits(123456785));
    }

    @Test
    public void testTestNumber() {
        assertTrue(LynchBell.testNumber(135));
        assertFalse(LynchBell.testNumber(136));
        assertTrue(LynchBell.testNumber(123));
        assertFalse(LynchBell.testNumber(124));
    }

    @Test
    public void testMain() {
        // Redirecting System.out to capture the output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        LynchBell.main(new String[]{});

        // Resetting System.out
        System.setOut(System.out);

        // Check if the correct number is printed
        String expectedOutput = "Number found: 9867312\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
