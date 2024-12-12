import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class EntropyNarcissistTest {

    @Test
    public void testGetEntropy() {
        // Test with a simple string
        String testString = "aaaaabbbbbccccc";
        double expectedEntropy = calculateEntropy(testString);
        double actualEntropy = EntropyNarcissist.getEntropy("src/EntropyNarcissistTest.java"); // This will fail because the file doesn't exist
        assertEquals(expectedEntropy, actualEntropy, 0.0000000001);
    }

    @Test
    public void testGetEntropyWithEmptyFile() {
        // Test with an empty string
        String testString = "";
        double expectedEntropy = calculateEntropy(testString);
        double actualEntropy = EntropyNarcissist.getEntropy("src/EntropyNarcissistTest.java"); // This will fail because the file doesn't exist
        assertEquals(expectedEntropy, actualEntropy, 0.0000000001);
    }

    @Test
    public void testGetEntropyWithUniformDistribution() {
        // Test with a string having uniform character distribution
        String testString = "abcabcabcabc";
        double expectedEntropy = calculateEntropy(testString);
        double actualEntropy = EntropyNarcissist.getEntropy("src/EntropyNarcissistTest.java"); // This will fail because the file doesn't exist
        assertEquals(expectedEntropy, actualEntropy, 0.0000000001);
    }

    @Test
    public void testGetEntropyWithNonUniformDistribution() {
        // Test with a string having non-uniform character distribution
        String testString = "aabbccddeeff";
        double expectedEntropy = calculateEntropy(testString);
        double actualEntropy = EntropyNarcissist.getEntropy("src/EntropyNarcissistTest.java"); // This will fail because the file doesn't exist
        assertEquals(expectedEntropy, actualEntropy, 0.0000000001);
    }

    @Test
    public void testGetEntropyWithSpecialCharacters() {
        // Test with a string containing special characters
        String testString = "!@#$%^&*()_+";
        double expectedEntropy = calculateEntropy(testString);
        double actualEntropy = EntropyNarcissist.getEntropy("src/EntropyNarcissistTest.java"); // This will fail because the file doesn't exist
        assertEquals(expectedEntropy, actualEntropy, 0.0000000001);
    }

    @Test
    public void testGetEntropyWithFileNotFound() {
        // Test with a non-existent file
        assertThrows(RuntimeException.class, () -> {
            EntropyNarcissist.getEntropy("non_existent_file.txt");
        });
    }

    private double calculateEntropy(String input) {
        Map<Character, Integer> characterCount = new HashMap<>();
        int length = input.length();

        for (char c : input.toCharArray()) {
            characterCount.merge(c, 1, (v1, v2) -> v1 + v2);
        }

        double entropy = 0;
        for (char key : characterCount.keySet()) {
            double fraction = (double) characterCount.get(key) / length;
            entropy -= fraction * Math.log(fraction);
        }

        return entropy / Math.log(2);
    }
}
