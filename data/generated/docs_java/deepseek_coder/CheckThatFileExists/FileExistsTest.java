import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class FileExistsTest {

    @Test
    public void testFileExistsInCurrentDirectory() {
        assertTrue(isFileExists("input.txt"), "input.txt should exist in the current directory");
    }

    @Test
    public void testFileExistsInRootDirectory() {
        assertTrue(isFileExists(File.separator + "input.txt"), "input.txt should exist in the root directory");
    }

    @Test
    public void testDirectoryExistsInCurrentDirectory() {
        assertTrue(isFileExists("docs"), "docs directory should exist in the current directory");
    }

    @Test
    public void testDirectoryExistsInRootDirectory() {
        assertTrue(isFileExists(File.separator + "docs" + File.separator), "docs directory should exist in the root directory");
    }

    @Test
    public void testZeroLengthFileExists() {
        assertTrue(isFileExists("zeroLengthFile.txt"), "zeroLengthFile.txt should exist in the current directory");
    }

    @Test
    public void testUnusualFilenameExists() {
        assertTrue(isFileExists("Abdu'l-Bahá.txt"), "Abdu'l-Bahá.txt should exist in the current directory");
    }

    public static boolean isFileExists(String filename) {
        boolean exists = new File(filename).exists();
        return exists;
    }
}
