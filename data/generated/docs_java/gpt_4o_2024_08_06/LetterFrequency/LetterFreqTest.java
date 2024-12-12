import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LetterFreqTest {

    @Test
    public void testEmptyFile() throws IOException {
        String filename = "empty.txt";
        createFile(filename, "");
        int[] expected = new int[26];
        assertArrayEquals(expected, LetterFreq.countLetters(filename));
        deleteFile(filename);
    }

    @Test
    public void testFileWithNoLetters() throws IOException {
        String filename = "no_letters.txt";
        createFile(filename, "1234567890!@#$%^&*()");
        int[] expected = new int[26];
        assertArrayEquals(expected, LetterFreq.countLetters(filename));
        deleteFile(filename);
    }

    @Test
    public void testFileWithAllLettersOnce() throws IOException {
        String filename = "all_letters_once.txt";
        createFile(filename, "abcdefghijklmnopqrstuvwxyz");
        int[] expected = new int[26];
        for (int i = 0; i < 26; i++) {
            expected[i] = 1;
        }
        assertArrayEquals(expected, LetterFreq.countLetters(filename));
        deleteFile(filename);
    }

    @Test
    public void testFileWithMixedCaseLetters() throws IOException {
        String filename = "mixed_case.txt";
        createFile(filename, "aAbBcC");
        int[] expected = new int[26];
        expected[0] = 2; // A/a
        expected[1] = 2; // B/b
        expected[2] = 2; // C/c
        assertArrayEquals(expected, LetterFreq.countLetters(filename));
        deleteFile(filename);
    }

    @Test
    public void testFileWithRepeatedLetters() throws IOException {
        String filename = "repeated_letters.txt";
        createFile(filename, "aaaBBBccc");
        int[] expected = new int[26];
        expected[0] = 3; // A/a
        expected[1] = 3; // B/b
        expected[2] = 3; // C/c
        assertArrayEquals(expected, LetterFreq.countLetters(filename));
        deleteFile(filename);
    }

    private void createFile(String filename, String content) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }

    private void deleteFile(String filename) throws IOException {
        Files.delete(Paths.get(filename));
    }
}
