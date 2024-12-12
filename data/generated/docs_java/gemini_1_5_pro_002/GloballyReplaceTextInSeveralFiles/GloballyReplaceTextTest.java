import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class GloballyReplaceTextTest {

    @Test
    void testReplacementOccurs() throws IOException {
        // Setup: Create test files with initial content
        Files.writeString(Paths.get("test1.txt"), "Goodbye London! This is a test.");
        Files.writeString(Paths.get("test2.txt"), "Another file says Goodbye London! too.");

        // Execute the main method
        GloballyReplaceText.main(new String[]{});

        // Verify the content after replacement
        String content1 = Files.readString(Paths.get("test1.txt"));
        String content2 = Files.readString(Paths.get("test2.txt"));

        assertEquals("Hello New York! This is a test.", content1);
        assertEquals("Another file says Hello New York! too.", content2);

         //Clean up test files
        Files.delete(Paths.get("test1.txt"));
        Files.delete(Paths.get("test2.txt"));

    }



    @Test
    void testNoReplacementNeeded() throws IOException {
         // Setup: Create test files without the target string
        Files.writeString(Paths.get("test1.txt"), "This file doesn't contain the target string.");
        Files.writeString(Paths.get("test2.txt"), "Neither does this one.");

        // Execute the main method
        GloballyReplaceText.main(new String[]{});


        // Verify the content remains unchanged
        String content1 = Files.readString(Paths.get("test1.txt"));
        String content2 = Files.readString(Paths.get("test2.txt"));

        assertEquals("This file doesn't contain the target string.", content1);
        assertEquals("Neither does this one.", content2);

        //Clean up test files
        Files.delete(Paths.get("test1.txt"));
        Files.delete(Paths.get("test2.txt"));
    }


    @Test
    void testMultipleOccurrences() throws IOException {
        // Setup: Create a test file with multiple occurrences of the target string
        Files.writeString(Paths.get("test1.txt"), "Goodbye London! Goodbye London! Goodbye London!");

        // Execute the main method
        GloballyReplaceText.main(new String[]{});

        // Verify all occurrences are replaced
        String content1 = Files.readString(Paths.get("test1.txt"));
        assertEquals("Hello New York! Hello New York! Hello New York!", content1);

         //Clean up test files
        Files.delete(Paths.get("test1.txt"));

    }




}
