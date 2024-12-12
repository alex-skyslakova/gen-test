import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class CreateFileTestTest {

    @BeforeAll
    public static void setup() {
        // Delete pre-existing files/directories for a clean test environment
        deleteFile("output.txt");
        deleteFile(File.separator + "output.txt");
        deleteDirectory("docs");
        deleteDirectory(File.separator + "docs"); 
    }

    @AfterAll
    public static void cleanup() {
        // Cleanup created files/directories after tests
        deleteFile("output.txt");
        deleteFile(File.separator + "output.txt");
        deleteDirectory("docs");
        deleteDirectory(File.separator + "docs");
    }


    @Test
    public void testFileAndDirectoryCreation() throws IOException {
        CreateFileTest.main(new String[]{});

        assertTrue(new File("output.txt").exists());
        assertEquals(0, new File("output.txt").length());
        assertTrue(new File("docs").exists());
        assertTrue(new File("docs").isDirectory());


        assertTrue(new File(File.separator + "output.txt").exists());
        assertEquals(0, new File(File.separator + "output.txt").length());
        assertTrue(new File(File.separator + "docs").exists());
        assertTrue(new File(File.separator + "docs").isDirectory());
    }



    private static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    private static void deleteDirectory(String path) {
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            // Delete files within directory (if any)
            File[] files = directory.listFiles();
            if(files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file.getAbsolutePath());
                    } else {
                        file.delete();
                    }
                }
            }

            directory.delete();
        }
    }
}
