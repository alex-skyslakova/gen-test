import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSizeTest {

    @Test
    public void testFileSizeInCurrentDirectory() {
        // Create a file object for input.txt in the current directory
        File fileInCurrentDir = new File("input.txt");
        
        // Assuming the file exists and has a known size, e.g., 100 bytes
        long expectedSize = 100; // Replace with the actual expected size
        assertEquals(expectedSize, fileInCurrentDir.length(), "File size in current directory does not match expected size.");
    }

    @Test
    public void testFileSizeInRootDirectory() {
        // Create a file object for /input.txt in the root directory
        File fileInRootDir = new File("/input.txt");
        
        // Assuming the file exists and has a known size, e.g., 200 bytes
        long expectedSize = 200; // Replace with the actual expected size
        assertEquals(expectedSize, fileInRootDir.length(), "File size in root directory does not match expected size.");
    }
}
