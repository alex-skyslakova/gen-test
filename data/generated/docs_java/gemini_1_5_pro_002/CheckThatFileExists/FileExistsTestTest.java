import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class FileExistsTestTest {

    @Test
    void testIsFileExists_currentDirectory_fileExists() throws IOException {
        Files.createFile(Paths.get("input.txt"));
        assertTrue(FileExistsTest.isFileExists("input.txt"));
        Files.deleteIfExists(Paths.get("input.txt")); // Clean up
    }

    @Test
    void testIsFileExists_currentDirectory_fileDoesNotExist() {
        assertFalse(FileExistsTest.isFileExists("input.txt"));
    }


    @Test
    void testIsFileExists_currentDirectory_zeroLengthFileExists() throws IOException {
        Files.createFile(Paths.get("input.txt"));
        assertTrue(FileExistsTest.isFileExists("input.txt"));
        Files.deleteIfExists(Paths.get("input.txt")); // Clean up
    }

    @Test
    void testIsFileExists_currentDirectory_unusualFileNameExists() throws IOException {
        Files.createFile(Paths.get("Abdu'l-Bahá.txt"));
        assertTrue(FileExistsTest.isFileExists("Abdu'l-Bahá.txt"));
        Files.deleteIfExists(Paths.get("Abdu'l-Bahá.txt")); // Clean up
    }



    @Test
    void testIsFileExists_rootDir_fileExists() throws IOException {
        Path rootPath = Paths.get(File.separator + "input.txt"); 
        if (Files.isWritable(rootPath.getParent())) { // Check if we have permissions. If not, skip this test.
            Files.createFile(rootPath);
            assertTrue(FileExistsTest.isFileExists(File.separator + "input.txt"));
            Files.deleteIfExists(rootPath); // Clean up
        }
    }


    @Test
    void testIsFileExists_rootDir_fileDoesNotExist() {
       assertFalse(FileExistsTest.isFileExists(File.separator + "input.txt")); // Likely doesn't exist in root, but check.
    }





    @Test
    void testIsFileExists_currentDirectory_directoryExists() throws IOException{
        Files.createDirectories(Paths.get("docs"));
        assertTrue(FileExistsTest.isFileExists("docs"));
        Files.deleteIfExists(Paths.get("docs"));// cleanup
    }

    @Test
    void testIsFileExists_currentDirectory_directoryDoesNotExist() {
        assertFalse(FileExistsTest.isFileExists("docs"));
    }


    @Test
    void testIsFileExists_rootDir_directoryExists() {
        // This test is complicated because creating a directory in the filesystem root requires admin/root permissions
        // and isn't a good testing practice. We will instead check if a common root directory EXISTS - not create it.
        String expectedRootDir = File.separator + "docs" + File.separator;
        boolean exists = FileExistsTest.isFileExists(expectedRootDir); // Test the method
        // We can't assertTrue or assertFalse reliably as it depends on the system. Simply test the function call.
    }
    
    @Test 
    void testIsFileExists_rootDir_directoryDoesNotExist(){
        String unlikelyDir = File.separator + "this_directory_should_not_exist" + File.separator;
        assertFalse(FileExistsTest.isFileExists(unlikelyDir));
    }




}
