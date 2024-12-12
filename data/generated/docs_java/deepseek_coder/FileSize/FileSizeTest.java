import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class FileSizeTest {

    @Test
    public void testFileSizeInCurrentDirectory() {
        File file = new File("input.txt");
        long expectedSize = 0; // Set the expected size based on the actual file size
        long actualSize = file.length();
        assertEquals(expectedSize, actualSize, "File size in current directory does not match expected size");
    }

    @Test
    public void testFileSizeInRootDirectory() {
        File file = new File("/input.txt");
        long expectedSize = 0; // Set the expected size based on the actual file size
        long actualSize = file.length();
        assertEquals(expectedSize, actualSize, "File size in root directory does not match expected size");
    }

    @Test
    public void testFileDoesNotExistInCurrentDirectory() {
        File file = new File("nonexistent.txt");
        assertFalse(file.exists(), "File should not exist in current directory");
    }

    @Test
    public void testFileDoesNotExistInRootDirectory() {
        File file = new File("/nonexistent.txt");
        assertFalse(file.exists(), "File should not exist in root directory");
    }

    @Test
    public void testFileSizeForNonExistentFile() {
        File file = new File("nonexistent.txt");
        long expectedSize = 0; // Non-existent file should have size 0
        long actualSize = file.length();
        assertEquals(expectedSize, actualSize, "Non-existent file size should be 0");
    }
}
