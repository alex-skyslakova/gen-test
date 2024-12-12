import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestStringChallengeTest {

    // Helper method to create a temporary file with given content
    private File createTempFile(String content) throws IOException {
        File tempFile = File.createTempFile("test", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }
        return tempFile;
    }

    // Helper method to capture the output of the program
    private String captureOutput(File inputFile) throws Exception {
        String originalFilePath = "lines.txt";
        Files.copy(inputFile.toPath(), Paths.get(originalFilePath));
        LongestStringChallenge.main(new String[]{});
        try (Scanner scanner = new Scanner(new File(originalFilePath))) {
            StringBuilder output = new StringBuilder();
            while (scanner.hasNextLine()) {
                output.append(scanner.nextLine()).append("\n");
            }
            return output.toString().trim();
        }
    }

    @Test
    public void testLongestLine() throws Exception {
        String input = "a\nbb\nccc\nddd\nee\nf\nggg";
        File inputFile = createTempFile(input);
        String expectedOutput = "ccc\nddd\nggg";
        assertEquals(expectedOutput, captureOutput(inputFile));
    }

    @Test
    public void testSingleLine() throws Exception {
        String input = "singleline";
        File inputFile = createTempFile(input);
        String expectedOutput = "singleline";
        assertEquals(expectedOutput, captureOutput(inputFile));
    }

    @Test
    public void testEmptyInput() throws Exception {
        String input = "";
        File inputFile = createTempFile(input);
        String expectedOutput = "";
        assertEquals(expectedOutput, captureOutput(inputFile));
    }

    @Test
    public void testAllLinesSameLength() throws Exception {
        String input = "abc\ndef\nghi";
        File inputFile = createTempFile(input);
        String expectedOutput = "abc\ndef\nghi";
        assertEquals(expectedOutput, captureOutput(inputFile));
    }

    @Test
    public void testMultipleLongestLines() throws Exception {
        String input = "short\nlonger\nlongest\nlongest\nshorter";
        File inputFile = createTempFile(input);
        String expectedOutput = "longest\nlongest";
        assertEquals(expectedOutput, captureOutput(inputFile));
    }
}
