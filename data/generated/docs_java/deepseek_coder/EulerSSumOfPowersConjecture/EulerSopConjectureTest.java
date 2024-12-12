import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class EulerSopConjectureTest {

    @Test
    public void testEulerSopConjecture() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the main method
        eulerSopConjecture.main(new String[]{});

        // Capture the output
        String output = outputStream.toString();

        // Expected output format
        String expectedPattern = "\\d+\\^5 \\+ \\d+\\^5 \\+ \\d+\\^5 \\+ \\d+\\^5 = \\d+\\^5";

        // Check if the output matches the expected pattern
        assertTrue(output.matches(expectedPattern), "Output does not match the expected pattern");

        // Additional assertions can be added to verify specific values if known
        // For example, if we know a specific solution:
        // assertTrue(output.contains("27^5 + 84^5 + 110^5 + 133^5 = 144^5"), "Known solution not found");
    }

    @Test
    public void testFifthPowerArray() {
        long[] fifth = new long[eulerSopConjecture.MAX_NUMBER];
        for (int i = 1; i <= eulerSopConjecture.MAX_NUMBER; i++) {
            long i2 = i * i;
            fifth[i - 1] = i2 * i2 * i;
        }

        // Verify the first few elements to ensure the calculation is correct
        assertEquals(1, fifth[0]);
        assertEquals(32, fifth[1]);
        assertEquals(243, fifth[2]);
        assertEquals(1024, fifth[3]);
        assertEquals(3125, fifth[4]);
    }

    @Test
    public void testBinarySearch() {
        long[] fifth = new long[eulerSopConjecture.MAX_NUMBER];
        for (int i = 1; i <= eulerSopConjecture.MAX_NUMBER; i++) {
            long i2 = i * i;
            fifth[i - 1] = i2 * i2 * i;
        }

        // Test binary search for known values
        int index = java.util.Arrays.binarySearch(fifth, 1);
        assertEquals(0, index);

        index = java.util.Arrays.binarySearch(fifth, 32);
        assertEquals(1, index);

        index = java.util.Arrays.binarySearch(fifth, 243);
        assertEquals(2, index);

        // Test binary search for non-existent value
        index = java.util.Arrays.binarySearch(fifth, 2);
        assertTrue(index < 0);
    }
}
