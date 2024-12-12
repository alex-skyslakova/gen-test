import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class FileExistsTestTest {

    @Test
    public void testFileExistsInCurrentDirectory() {
        assertTrue(FileExistsTest.isFileExists("input.txt"), "File 'input.txt' in current directory should exist");
    }

    @Test
    public void testFileExistsInRootDirectory() {
        assertTrue(FileExistsTest.isFileExists(File.separator + "input.txt"), "File 'input.txt' in root directory should exist");
    }

    @Test
    public void testDirectoryExistsInCurrentDirectory() {
        assertTrue(FileExistsTest.isFileExists("docs"), "Directory 'docs' in current directory should exist");
    }

    @Test
    public void testDirectoryExistsInRootDirectory() {
        assertTrue(FileExistsTest.isFileExists(File.separator + "docs" + File.separator), "Directory 'docs' in root directory should exist");
    }

    @Test
    public void testZeroLengthFileExists() {
        assertTrue(FileExistsTest.isFileExists("zeroLengthFile.txt"), "Zero-length file 'zeroLengthFile.txt' should exist");
    }

    @Test
    public void testUnusualFilenameExists() {
        assertTrue(FileExistsTest.isFileExists("Abdu'l-Bahá.txt"), "File with unusual name 'Abdu'l-Bahá.txt' should exist");
    }

    @Test
    public void testFileDoesNotExist() {
        assertFalse(FileExistsTest.isFileExists("nonExistentFile.txt"), "File 'nonExistentFile.txt' should not exist");
    }

    @Test
    public void testDirectoryDoesNotExist() {
        assertFalse(FileExistsTest.isFileExists("nonExistentDir"), "Directory 'nonExistentDir' should not exist");
    }
}
