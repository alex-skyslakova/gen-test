import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class CreateFileTestTest {

    @Test
    public void testCreateFilesAndDirectories() {
        // Run the main method to create files and directories
        CreateFileTest.main(new String[]{});

        // Test if the files and directories were created in the current working directory
        File currentOutputFile = new File("output.txt");
        File currentDocsDir = new File("docs");

        assertTrue(currentOutputFile.exists(), "output.txt should exist in the current directory");
        assertEquals(0, currentOutputFile.length(), "output.txt should be of size 0 bytes");

        assertTrue(currentDocsDir.exists(), "docs directory should exist in the current directory");
        assertTrue(currentDocsDir.isDirectory(), "docs should be a directory");

        // Test if the files and directories were created in the filesystem root
        File rootOutputFile = new File(File.separator + "output.txt");
        File rootDocsDir = new File(File.separator + "docs");

        assertTrue(rootOutputFile.exists(), "output.txt should exist in the root directory");
        assertEquals(0, rootOutputFile.length(), "output.txt should be of size 0 bytes");

        assertTrue(rootDocsDir.exists(), "docs directory should exist in the root directory");
        assertTrue(rootDocsDir.isDirectory(), "docs should be a directory");

        // Clean up the created files and directories
        assertTrue(currentOutputFile.delete(), "Failed to delete output.txt in the current directory");
        assertTrue(currentDocsDir.delete(), "Failed to delete docs directory in the current directory");
        assertTrue(rootOutputFile.delete(), "Failed to delete output.txt in the root directory");
        assertTrue(rootDocsDir.delete(), "Failed to delete docs directory in the root directory");
    }

    @Test
    public void testIOExceptionHandling() {
        // Mock an IOException by trying to create a file in a non-existent directory
        assertThrows(IOException.class, () -> {
            new File("nonExistentDir" + File.separator + "output.txt").createNewFile();
        }, "IOException should be thrown when trying to create a file in a non-existent directory");
    }
}
