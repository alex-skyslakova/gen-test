import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HelloWorldTest {

    @Test
    public void testGoodbyeWorldOutput() {
        // Capture the standard output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Run the HelloWorld main method
        HelloWorld.main(new String[]{});

        // Restore the original standard output
        System.setOut(originalOut);

        // Verify the output
        assertEquals("Goodbye, World!", outputStream.toString());
    }
}
