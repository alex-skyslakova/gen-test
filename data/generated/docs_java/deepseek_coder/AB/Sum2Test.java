import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class Sum2Test {

    @Test
    public void testSumPositiveNumbers() {
        String input = "2 2";
        String expectedOutput = "4\n";
        assertEquals(expectedOutput, getProgramOutput(input));
    }

    @Test
    public void testSumNegativeNumbers() {
        String input = "-3 -2";
        String expectedOutput = "-5\n";
        assertEquals(expectedOutput, getProgramOutput(input));
    }

    @Test
    public void testSumPositiveAndNegativeNumbers() {
        String input = "3 -2";
        String expectedOutput = "1\n";
        assertEquals(expectedOutput, getProgramOutput(input));
    }

    @Test
    public void testSumZeroAndPositiveNumber() {
        String input = "0 5";
        String expectedOutput = "5\n";
        assertEquals(expectedOutput, getProgramOutput(input));
    }

    @Test
    public void testSumZeroAndNegativeNumber() {
        String input = "0 -5";
        String expectedOutput = "-5\n";
        assertEquals(expectedOutput, getProgramOutput(input));
    }

    @Test
    public void testSumTwoZeros() {
        String input = "0 0";
        String expectedOutput = "0\n";
        assertEquals(expectedOutput, getProgramOutput(input));
    }

    @Test
    public void testSumMaxValues() {
        String input = "1000 1000";
        String expectedOutput = "2000\n";
        assertEquals(expectedOutput, getProgramOutput(input));
    }

    @Test
    public void testSumMinValues() {
        String input = "-1000 -1000";
        String expectedOutput = "-2000\n";
        assertEquals(expectedOutput, getProgramOutput(input));
    }

    private String getProgramOutput(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            // Set the input
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            // Capture the output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Run the program
            Sum2.main(new String[0]);

            // Return the captured output
            return outputStream.toString();
        } finally {
            // Restore original streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
