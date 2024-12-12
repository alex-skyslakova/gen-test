import org.junit.Test;
import static org.junit.Assert.*;

public class RecursionTestTest {

    @Test
    public void testRecursionLimit() {
        // This test will check if the recursion limit is reached and the correct message is printed.
        // Since the original code prints directly to the console, we need to capture the output.
        
        // Redirect System.out to capture the output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Call the main method which starts the recursion
        RecursionTest.main(new String[]{});

        // Check if the output contains the expected message
        String output = outContent.toString().trim();
        assertTrue(output.startsWith("Recursion depth on this system is "));
        
        // Reset System.out to its original state
        System.setOut(System.out);
    }
}
