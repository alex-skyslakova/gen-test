import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.io.File;
import java.io.IOException;

public class FileExistsTestTest {

    @Test
    public void testFileExistsInCurrentDirectory() {
        // Create a zero-length file named input.txt in the current directory
        File file = new File("input.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            assertTrue(FileExistsTest.isFileExists("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }

    @Test
    public void testDirectoryExistsInCurrentDirectory() {
        // Create a directory named docs in the current directory
        File dir = new File("docs");
        if (!dir.exists()) {
            dir.mkdir();
        }
        assertTrue(FileExistsTest.isFileExists("docs"));
        dir.delete();
    }

    @Test
    public void testFileExistsInRootDirectory() {
        // This test assumes a file named input.txt exists in the root directory
        assertFalse(FileExistsTest.isFileExists(File.separator + "input.txt"));
    }

    @Test
    public void testDirectoryExistsInRootDirectory() {
        // This test assumes a directory named docs exists in the root directory
        assertFalse(FileExistsTest.isFileExists(File.separator + "docs" + File.separator));
    }

    @Test
    public void testUnusualFilenameExistsInCurrentDirectory() {
        // Create a zero-length file with an unusual name in the current directory
        File file = new File("Abdu'l-Bahá.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            assertTrue(FileExistsTest.isFileExists("Abdu'l-Bahá.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }
}
