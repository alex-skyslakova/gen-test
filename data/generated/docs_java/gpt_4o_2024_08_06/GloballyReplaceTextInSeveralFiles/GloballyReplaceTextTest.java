import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

public class GloballyReplaceTextTest {

    @BeforeEach
    public void setUp() throws IOException {
        // Set up test files with initial content
        Files.write(Paths.get("test1.txt"), "Goodbye London! Welcome to the city.".getBytes());
        Files.write(Paths.get("test2.txt"), "Goodbye London! Have a great day.".getBytes());
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up test files
        Files.deleteIfExists(Paths.get("test1.txt"));
        Files.deleteIfExists(Paths.get("test2.txt"));
    }

    @Test
    public void testReplacementInTest1() throws IOException {
        // Run the main method to perform the replacement
        GloballyReplaceText.main(null);

        // Read the content of the file after replacement
        String content = new String(Files.readAllBytes(Paths.get("test1.txt")));

        // Assert that the replacement was successful
        Assertions.assertEquals("Hello New York! Welcome to the city.", content);
    }

    @Test
    public void testReplacementInTest2() throws IOException {
        // Run the main method to perform the replacement
        GloballyReplaceText.main(null);

        // Read the content of the file after replacement
        String content = new String(Files.readAllBytes(Paths.get("test2.txt")));

        // Assert that the replacement was successful
        Assertions.assertEquals("Hello New York! Have a great day.", content);
    }

    @Test
    public void testNoReplacementNeeded() throws IOException {
        // Set up a file with no matching text
        Files.write(Paths.get("test1.txt"), "Welcome to the city.".getBytes());

        // Run the main method to perform the replacement
        GloballyReplaceText.main(null);

        // Read the content of the file after replacement
        String content = new String(Files.readAllBytes(Paths.get("test1.txt")));

        // Assert that the content remains unchanged
        Assertions.assertEquals("Welcome to the city.", content);
    }
}
