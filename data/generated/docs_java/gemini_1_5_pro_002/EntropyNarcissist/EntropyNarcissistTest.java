import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EntropyNarcissistTest {

    @Test
    void testEmptyFile() throws IOException {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
        assertEquals(0.0, EntropyNarcissist.getEntropy(tempFile.getAbsolutePath()), 0.0001);
    }

    @Test
    void testSingleCharacterFile() throws IOException {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write('a');
        }
        assertEquals(0.0, EntropyNarcissist.getEntropy(tempFile.getAbsolutePath()), 0.0001);
    }

    @Test
    void testUniformDistribution() throws IOException {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("aabb");
        }
        assertEquals(1.0, EntropyNarcissist.getEntropy(tempFile.getAbsolutePath()), 0.0001);
    }

    @Test
    void testSkewedDistribution() throws IOException {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("aaabbc");
        }
        assertEquals(1.4591479170272448, EntropyNarcissist.getEntropy(tempFile.getAbsolutePath()), 0.0001);

    }


    @Test
    void testNonExistentFile() {
        assertThrows(RuntimeException.class, () -> EntropyNarcissist.getEntropy("non_existent_file.txt"));
    }


    @Test
    void testLargeFileWithVariousCharacters() throws IOException {
         File tempFile = File.createTempFile("test", ".txt");
         tempFile.deleteOnExit();
         try (FileWriter writer = new FileWriter(tempFile)) {
             String content = "This is a test string with various characters, including spaces and newlines.\n" +
                     "It should have a higher entropy than a string with fewer unique characters.\n" +
                     "Let's add some numbers: 1234567890.\n" +
                     "And some special characters: !@#$%^&*()_+=-`~[]\\;',./{}|:\"<>?";
                 writer.write(content);
             }
         double entropy = EntropyNarcissist.getEntropy(tempFile.getAbsolutePath());
         // Instead of a fixed value, check if entropy is within a reasonable range.
         // Large files with diverse characters should have higher entropy, but not necessarily maximum entropy since it depends on character distribution.
         // Adjust 3.5 and 5 based on experience with typical text files.
         // This is to address entropy variance of text files being tested. 
         assert (entropy >= 3.5 && entropy <= 5) : "Entropy is outside the expected range.";

    }


}
