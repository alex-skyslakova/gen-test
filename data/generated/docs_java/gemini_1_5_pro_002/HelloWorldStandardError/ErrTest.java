import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class ErrTest {

    @Test
    public void testStandardErrorOutput() {
        // Redirect System.err
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        // Execute main method
        Err.main(new String[]{});

        // Reset System.err
        System.setErr(originalErr);

        // Assert expected output
        assertEquals("Goodbye, World!\n", errContent.toString()); 
    }
}

