import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GloballyReplaceTextTest {

    private static final String TEST_FILE_1 = "test1.txt";
    private static final String TEST_FILE_2 = "test2.txt";
    private static final String ORIGINAL_TEXT = "Goodbye London!";
    private static final String REPLACEMENT_TEXT = "Hello New York!";

    @BeforeEach
    public void setUp() throws IOException {
        // Create test files with the original text
        createTestFile(TEST_FILE_1, ORIGINAL_TEXT);
        createTestFile(TEST_FILE_2, ORIGINAL_TEXT);
    }

    @AfterEach
    public void tearDown() {
        // Delete test files after each test
        deleteTestFile(TEST_FILE_1);
        deleteTestFile(TEST_FILE_2);
    }

    @Test
    public void testTextReplacementInFiles() throws IOException {
        // Run the main method to replace text in files
        GloballyReplaceText.main(new String[]{});

        // Verify the text has been replaced in both files
        assertFileContent(TEST_FILE_1, REPLACEMENT_TEXT);
        assertFileContent(TEST_FILE_2, REPLACEMENT_TEXT);
    }

    private void createTestFile(String fileName, String content) throws IOException {
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(content);
        }
    }

    private void deleteTestFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private void assertFileContent(String fileName, String expectedContent) throws IOException {
        String actualContent = new String(Files.readAllBytes(Paths.get(fileName)));
        assertEquals(expectedContent, actualContent);
    }
}
