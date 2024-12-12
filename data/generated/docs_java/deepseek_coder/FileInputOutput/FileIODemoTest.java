import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIODemoTest {

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";
    private static final String TEST_CONTENT = "This is a test content.";

    @Before
    public void setUp() throws IOException {
        // Create input.txt with test content
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INPUT_FILE))) {
            writer.write(TEST_CONTENT);
        }
    }

    @After
    public void tearDown() throws IOException {
        // Delete input.txt and output.txt after each test
        Files.deleteIfExists(Paths.get(INPUT_FILE));
        Files.deleteIfExists(Paths.get(OUTPUT_FILE));
    }

    @Test
    public void testFileCopy() throws IOException {
        // Run the main method of FileIODemo
        FileIODemo.main(new String[]{});

        // Read the content of output.txt
        String outputContent = new String(Files.readAllBytes(Paths.get(OUTPUT_FILE)));

        // Assert that the content of output.txt matches the content of input.txt
        assertEquals(TEST_CONTENT, outputContent);
    }

    @Test(expected = FileNotFoundException.class)
    public void testFileNotFound() throws IOException {
        // Delete input.txt to simulate FileNotFoundException
        Files.deleteIfExists(Paths.get(INPUT_FILE));

        // Run the main method of FileIODemo, expecting a FileNotFoundException
        FileIODemo.main(new String[]{});
    }

    @Test
    public void testEmptyFile() throws IOException {
        // Create an empty input.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INPUT_FILE))) {
            writer.write("");
        }

        // Run the main method of FileIODemo
        FileIODemo.main(new String[]{});

        // Read the content of output.txt
        String outputContent = new String(Files.readAllBytes(Paths.get(OUTPUT_FILE)));

        // Assert that the content of output.txt is empty
        assertEquals("", outputContent);
    }
}
