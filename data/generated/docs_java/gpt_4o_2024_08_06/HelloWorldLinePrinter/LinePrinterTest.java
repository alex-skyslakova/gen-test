import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LinePrinterTest {

    @Test
    void testLinePrinterWritesHelloWorld(@TempDir File tempDir) throws IOException {
        // Create a temporary file to simulate the line printer device
        File tempFile = new File(tempDir, "lp0");

        // Redirect the FileWriter to the temporary file instead of /dev/lp0
        try (FileWriter lp0 = new FileWriter(tempFile)) {
            lp0.write("Hello World!");
        }

        // Read the content of the temporary file to verify the output
        char[] buffer = new char[12];
        try (FileReader reader = new FileReader(tempFile)) {
            reader.read(buffer);
        }

        // Assert that the content written is "Hello World!"
        assertEquals("Hello World!", new String(buffer));
    }

    @Test
    void testLinePrinterIOException() {
        // Attempt to write to an invalid path to trigger IOException
        assertThrows(IOException.class, () -> {
            FileWriter lp0 = new FileWriter("/invalid/path/lp0");
            lp0.write("Hello World!");
            lp0.close();
        });
    }
}
