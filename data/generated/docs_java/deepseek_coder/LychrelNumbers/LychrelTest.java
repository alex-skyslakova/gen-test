import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LychrelTest {

    @Test
    public void testRev() {
        assertEquals(new BigInteger("123"), Lychrel.rev(new BigInteger("321")));
        assertEquals(new BigInteger("123456789"), Lychrel.rev(new BigInteger("987654321")));
        assertEquals(new BigInteger("1"), Lychrel.rev(new BigInteger("1")));
        assertEquals(new BigInteger("0"), Lychrel.rev(new BigInteger("0")));
    }

    @Test
    public void testLychrel() {
        // Test a number that becomes a palindrome quickly
        Lychrel.Tuple result = Lychrel.lychrel(new BigInteger("12"));
        assertFalse(result.flag);
        assertEquals(BigInteger.ZERO, result.bi);

        // Test a number that is already a palindrome
        result = Lychrel.lychrel(new BigInteger("55"));
        assertFalse(result.flag);
        assertEquals(BigInteger.ZERO, result.bi);

        // Test a Lychrel number candidate
        result = Lychrel.lychrel(new BigInteger("196"));
        assertTrue(result.flag);
        assertEquals(new BigInteger("196"), result.bi);

        // Test a number that converges to a known Lychrel number
        result = Lychrel.lychrel(new BigInteger("689"));
        assertTrue(result.flag);
        assertEquals(new BigInteger("196"), result.bi);
    }

    @Test
    public void testMain() {
        // Capture the output of the main method
        List<String> output = new ArrayList<>();
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.add(String.valueOf((char) b));
            }
        }));

        Lychrel.main(new String[]{});

        // Restore the original output stream
        System.setOut(System.out);

        // Convert the captured output to a single string
        String capturedOutput = String.join("", output);

        // Assert the expected output
        assertTrue(capturedOutput.contains("Lychrel seeds:"));
        assertTrue(capturedOutput.contains("Lychrel related"));
        assertTrue(capturedOutput.contains("Lychrel palindromes:"));
    }
}
