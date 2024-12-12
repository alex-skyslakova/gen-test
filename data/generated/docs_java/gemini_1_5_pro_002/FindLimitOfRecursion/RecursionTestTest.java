import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RecursionTestTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testRecursionLimit() {
        RecursionTest.main(new String[]{});
        String output = outContent.toString();
        // We cannot predict the exact recursion depth, so we just check the output format.
        Assertions.assertTrue(output.matches("Recursion depth on this system is \\d+\\."));
    }
}
