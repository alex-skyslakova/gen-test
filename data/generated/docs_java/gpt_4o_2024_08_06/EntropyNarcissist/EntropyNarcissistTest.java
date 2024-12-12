import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EntropyNarcissistTest {

    @Test
    public void testGetEntropyWithKnownFile() throws IOException {
        // Create a temporary file with known content
        Path tempFile = Files.createTempFile("testEntropy", ".txt");
        Files.write(tempFile, "aaabbc".getBytes());

        // Calculate entropy
        double entropy = EntropyNarcissist.getEntropy(tempFile.toString());

        // Expected entropy calculation
        // Probability of 'a' = 3/6, 'b' = 2/6, 'c' = 1/6
        // Entropy = - (3/6 * log2(3/6) + 2/6 * log2(2/6) + 1/6 * log2(1/6))
        double expectedEntropy = -(0.5 * Math.log(0.5) / Math.log(2) 
                                 + 1.0/3 * Math.log(1.0/3) / Math.log(2) 
                                 + 1.0/6 * Math.log(1.0/6) / Math.log(2));

        assertEquals(expectedEntropy, entropy, 1e-12);

        // Clean up
        Files.delete(tempFile);
    }

    @Test
    public void testGetEntropyWithEmptyFile() throws IOException {
        // Create an empty temporary file
        Path tempFile = Files.createTempFile("testEntropyEmpty", ".txt");

        // Calculate entropy
        double entropy = EntropyNarcissist.getEntropy(tempFile.toString());

        // Expected entropy for an empty file should be 0
        assertEquals(0.0, entropy, 1e-12);

        // Clean up
        Files.delete(tempFile);
    }

    @Test
    public void testGetEntropyWithSingleCharacterFile() throws IOException {
        // Create a temporary file with a single character
        Path tempFile = Files.createTempFile("testEntropySingleChar", ".txt");
        Files.write(tempFile, "aaaaa".getBytes());

        // Calculate entropy
        double entropy = EntropyNarcissist.getEntropy(tempFile.toString());

        // Expected entropy for a file with a single repeated character should be 0
        assertEquals(0.0, entropy, 1e-12);

        // Clean up
        Files.delete(tempFile);
    }

    @Test
    public void testGetEntropyWithIOException() {
        // Test with a non-existent file
        Exception exception = assertThrows(RuntimeException.class, () -> {
            EntropyNarcissist.getEntropy("non_existent_file.txt");
        });

        String expectedMessage = "non_existent_file.txt (No such file or directory)";
        String actualMessage = exception.getCause().getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
