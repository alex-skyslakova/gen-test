import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class twoDimArrayTest {

    @Test
    public void testMainWithValidInput() {
        String input = "3\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        twoDimArray.main(new String[]{});

        String expectedOutput = "The number at place [0 0] is 42.0\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMainWithZeroDimensions() {
        String input = "0\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        twoDimArray.main(new String[]{});

        String expectedOutput = "The number at place [0 0] is 42.0\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMainWithNegativeDimensions() {
        String input = "-3\n-4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertThrows(NegativeArraySizeException.class, () -> {
            twoDimArray.main(new String[]{});
        });
    }

    @Test
    public void testMainWithOneDimensionZero() {
        String input = "3\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        twoDimArray.main(new String[]{});

        String expectedOutput = "The number at place [0 0] is 42.0\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMainWithOneDimensionNegative() {
        String input = "3\n-4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertThrows(NegativeArraySizeException.class, () -> {
            twoDimArray.main(new String[]{});
        });
    }
}
