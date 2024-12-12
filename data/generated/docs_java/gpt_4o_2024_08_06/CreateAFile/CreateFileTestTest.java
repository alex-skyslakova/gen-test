import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class CreateFileTestTest {

    @BeforeEach
    public void setUp() {
        // Clean up before each test
        new File("output.txt").delete();
        new File("docs").delete();
        new File(File.separator + "output.txt").delete();
        new File(File.separator + "docs").delete();
    }

    @Test
    public void testCreateFilesAndDirectories() {
        try {
            // Create files and directories
            new File("output.txt").createNewFile();
            new File(File.separator + "output.txt").createNewFile();
            new File("docs").mkdir();
            new File(File.separator + "docs").mkdir();

            // Assert files and directories are created
            assertTrue(new File("output.txt").exists(), "output.txt should exist in the current directory");
            assertTrue(new File(File.separator + "output.txt").exists(), "output.txt should exist in the root directory");
            assertTrue(new File("docs").exists() && new File("docs").isDirectory(), "docs should exist and be a directory in the current directory");
            assertTrue(new File(File.separator + "docs").exists() && new File(File.separator + "docs").isDirectory(), "docs should exist and be a directory in the root directory");

        } catch (IOException e) {
            fail("IOException was thrown: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        // Clean up after each test
        new File("output.txt").delete();
        new File("docs").delete();
        new File(File.separator + "output.txt").delete();
        new File(File.separator + "docs").delete();
    }
}
