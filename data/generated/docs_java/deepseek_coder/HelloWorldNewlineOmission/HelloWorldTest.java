import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class HelloWorldTest {

    @Test
    public void testMainOutput() {
        // Redirect standard output to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the main method of HelloWorld
        HelloWorld.main(new String[0]);

        // Get the output as a string
        String output = outputStream.toString();

        // Assert that the output is exactly "Goodbye, World!" without a trailing newline
        assertEquals("Goodbye, World!", output);
    }
}
