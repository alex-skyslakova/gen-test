import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AbundantOddNumbersTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testFirst25AbundantOddNumbers() {
        AbundantOddNumbers.abundantOdd(1, 100000, 25, false);
        String output = outContent.toString();
        String[] lines = output.split("\n");

        // Ensure we have exactly 25 lines of output
        assertEquals(25, lines.length, "Expected 25 abundant odd numbers");

        // Ensure each line contains a valid number and its sum of divisors
        for (String line : lines) {
            assertTrue(line.matches("\\s*\\d+ <= \\d+\\s*"), "Invalid output format: " + line);
        }
    }

    @Test
    public void testThousandthAbundantOddNumber() {
        AbundantOddNumbers.abundantOdd(1, 2500000, 1000, true);
        String output = outContent.toString();
        String[] lines = output.split("\n");

        // Ensure we have exactly 1 line of output
        assertEquals(1, lines.length, "Expected only one abundant odd number");

        // Ensure the line contains a valid number and its sum of divisors
        assertTrue(lines[0].matches("\\s*\\d+ <= \\d+\\s*"), "Invalid output format: " + lines[0]);
    }

    @Test
    public void testFirstAbundantOddNumberOverOneBillion() {
        AbundantOddNumbers.abundantOdd(1000000001, 2147483647, 1, false);
        String output = outContent.toString();
        String[] lines = output.split("\n");

        // Ensure we have exactly 1 line of output
        assertEquals(1, lines.length, "Expected only one abundant odd number");

        // Ensure the line contains a valid number and its sum of divisors
        assertTrue(lines[0].matches("\\s*\\d+ <= \\d+\\s*"), "Invalid output format: " + lines[0]);
    }

    @BeforeEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
