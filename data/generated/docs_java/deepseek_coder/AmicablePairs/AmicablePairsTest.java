import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmicablePairsTest {

    @Test
    public void testProperDivsSum() {
        // Test cases for properDivsSum method
        assertEquals(1, AmicablePairs.properDivsSum(2)); // Proper divisors of 2: 1
        assertEquals(1, AmicablePairs.properDivsSum(3)); // Proper divisors of 3: 1
        assertEquals(3, AmicablePairs.properDivsSum(4)); // Proper divisors of 4: 1, 2
        assertEquals(16, AmicablePairs.properDivsSum(12)); // Proper divisors of 12: 1, 2, 3, 4, 6
        assertEquals(284, AmicablePairs.properDivsSum(220)); // Proper divisors of 220: 1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110
        assertEquals(220, AmicablePairs.properDivsSum(284)); // Proper divisors of 284: 1, 2, 4, 71, 142
    }

    @Test
    public void testAmicablePairs() {
        // Capture the output of the main method
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the main method with a limit of 20000
        AmicablePairs.main(new String[]{});

        // Expected output for amicable pairs below 20000
        String expectedOutput = "220 284 \n" +
                                "1184 1210 \n" +
                                "2620 2924 \n" +
                                "5020 5564 \n" +
                                "6232 6368 \n" +
                                "10744 10856 \n" +
                                "12285 14595 \n" +
                                "17296 18416 \n";

        // Compare the actual output with the expected output
        assertEquals(expectedOutput, outContent.toString());
    }
}
