import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TwoDimArrayTest {

    @Test
    public void testArrayCreationAndAccess() {
        // Simulate user input
        String input = "3\n4\n"; // Example input for dimensions 3x4
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the main method
        twoDimArray.main(new String[]{});

        // Check the output
        String expectedOutput = "The number at place [0 0] is 42.0\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testNegativeDimensions() {
        // Simulate user input with negative dimensions
        String input = "-1\n4\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Run the main method, expecting an exception
        twoDimArray.main(new String[]{});
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testNegativeDimensionsBoth() {
        // Simulate user input with both dimensions negative
        String input = "-1\n-1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Run the main method, expecting an exception
        twoDimArray.main(new String[]{});
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testNegativeDimensionsSecond() {
        // Simulate user input with the second dimension negative
        String input = "3\n-1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Run the main method, expecting an exception
        twoDimArray.main(new String[]{});
    }
}
