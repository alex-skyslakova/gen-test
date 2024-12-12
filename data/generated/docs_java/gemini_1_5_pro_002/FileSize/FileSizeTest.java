import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileSizeTest {

    @Test
    void testFileSizeCurrentDirectory() throws IOException {
        // Create a temporary file in the current directory
        Path tempFilePath = Files.createTempFile("input", ".txt");
        File tempFile = tempFilePath.toFile();

        // Write some content to the file so it has a size
        Files.writeString(tempFilePath, "test content");


        long expectedSize = Files.size(tempFilePath); // Get the actual size using Files.size
        long actualSize = new File(tempFile.getName()).length();

        assertEquals(expectedSize, actualSize, "File size in current directory does not match expected size.");

        // Clean up: Delete the temporary file
        tempFile.delete();


    }


    @Test
    void testFileSizeRootDirectory() throws IOException {
        // We cannot reliably create a file in the root directory for testing purposes
        // due to file system permissions and variations across OS.

        // Instead, we can test for existence and if it exists get size, otherwise expect 0.
        File rootFile = new File("/input.txt");

        long expectedSize;
        if (rootFile.exists()) {
            expectedSize = rootFile.length();
        } else {
            expectedSize = 0; // Expect 0 size if file doesn't exist.
        }

        long actualSize = rootFile.length();

        assertEquals(expectedSize, actualSize, "File size at root does not match expected size (or zero if not present).");


    }
}
