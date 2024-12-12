import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LynchBellTest {

    @Test
    void testUniqueDigits_uniqueDigits() {
        assertTrue(LynchBell.uniqueDigits(1234));
    }

    @Test
    void testUniqueDigits_duplicateDigits() {
        assertFalse(LynchBell.uniqueDigits(1224));
    }

    @Test
    void testUniqueDigits_containsZero() {
        assertFalse(LynchBell.uniqueDigits(1024));
    }

    @Test
    void testUniqueDigits_containsFive() {
        assertFalse(LynchBell.uniqueDigits(1524));
    }


    @Test
    void testTestNumber_divisibleByAllDigits() {
        assertTrue(LynchBell.testNumber(135)); // Example from problem description
    }

    @Test
    void testTestNumber_notDivisibleByAllDigits() {
        assertFalse(LynchBell.testNumber(137));
    }


    @Test
    void testMain_findsCorrectNumber() {
        // Capture System.out
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        LynchBell.main(new String[]{}); // Run main

        String expectedOutput = "Number found: 9876321\r\n"; // adjust for your OS line ending if needed
        assertEquals(expectedOutput, outContent.toString());

         // Restore System.out
        System.setOut(System.out); 
    }

    // Additional test for a smaller range to ensure faster testing during development
        @Test
    void testMain_smallerRange() {
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Modify LynchBell.main to accept a range argument for testing
        LynchBell.main(new String[]{"1000"});  // Start from 1000 instead of 98764321

        // The largest Lynch-Bell number less than 1000 is 984
        String expectedOutput = "Number found: 984\r\n"; // Or no output if no number is found in the range
        assertEquals(expectedOutput, outContent.toString());

        System.setOut(System.out);
    }

}

public static void main(String args[]) {
        int i = 98764321;

        if (args.length > 0) { // Allows setting an upper bound for testing
            try {
                i = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        // ...rest of main
 }

