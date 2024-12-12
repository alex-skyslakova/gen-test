import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EgyptianDivisionTest {

    @Test
    public void testDivide_580By34() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Perform the division
        EgyptianDivision.divide(580, 34);

        // Expected output
        String expectedOutput = "17, remainder 2\n";

        // Verify the output
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testDivide_ZeroDividend() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Perform the division
        EgyptianDivision.divide(0, 34);

        // Expected output
        String expectedOutput = "0, remainder 0\n";

        // Verify the output
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testDivide_DividendLessThanDivisor() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Perform the division
        EgyptianDivision.divide(33, 34);

        // Expected output
        String expectedOutput = "0, remainder 33\n";

        // Verify the output
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testDivide_DividendEqualsDivisor() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Perform the division
        EgyptianDivision.divide(34, 34);

        // Expected output
        String expectedOutput = "1, remainder 0\n";

        // Verify the output
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testDivide_LargeNumbers() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Perform the division
        EgyptianDivision.divide(1000000, 3456);

        // Expected output
        String expectedOutput = "289, remainder 1696\n";

        // Verify the output
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testDivide_NegativeDividend() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Perform the division
        EgyptianDivision.divide(-580, 34);

        // Expected output
        String expectedOutput = "-17, remainder -2\n";

        // Verify the output
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testDivide_NegativeDivisor() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Perform the division
        EgyptianDivision.divide(580, -34);

        // Expected output
        String expectedOutput = "-17, remainder 2\n";

        // Verify the output
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testDivide_BothNegative() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Perform the division
        EgyptianDivision.divide(-580, -34);

        // Expected output
        String expectedOutput = "17, remainder -2\n";

        // Verify the output
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }
}
