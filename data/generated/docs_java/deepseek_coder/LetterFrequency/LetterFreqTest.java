import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LetterFreqTest {

    @Test
    public void testCountLetters_EmptyFile() throws IOException {
        // Create an empty file
        String filename = "emptyFile.txt";
        Files.write(Paths.get(filename), new byte[0]);

        int[] expected = new int[26];
        int[] result = LetterFreq.countLetters(filename);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testCountLetters_SingleLetter() throws IOException {
        // Create a file with a single letter 'A'
        String filename = "singleLetter.txt";
        Files.write(Paths.get(filename), "A".getBytes());

        int[] expected = new int[26];
        expected[0] = 1; // 'A' is at index 0
        int[] result = LetterFreq.countLetters(filename);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testCountLetters_MultipleLetters() throws IOException {
        // Create a file with multiple letters 'A', 'B', 'C'
        String filename = "multipleLetters.txt";
        Files.write(Paths.get(filename), "ABC".getBytes());

        int[] expected = new int[26];
        expected[0] = 1; // 'A'
        expected[1] = 1; // 'B'
        expected[2] = 1; // 'C'
        int[] result = LetterFreq.countLetters(filename);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testCountLetters_MixedCase() throws IOException {
        // Create a file with mixed case letters 'a', 'B', 'c'
        String filename = "mixedCase.txt";
        Files.write(Paths.get(filename), "aBc".getBytes());

        int[] expected = new int[26];
        expected[0] = 1; // 'A'
        expected[1] = 1; // 'B'
        expected[2] = 1; // 'C'
        int[] result = LetterFreq.countLetters(filename);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testCountLetters_NonLetterCharacters() throws IOException {
        // Create a file with non-letter characters '1', '!', ' '
        String filename = "nonLetterCharacters.txt";
        Files.write(Paths.get(filename), "1! ".getBytes());

        int[] expected = new int[26];
        int[] result = LetterFreq.countLetters(filename);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testCountLetters_AllLetters() throws IOException {
        // Create a file with all letters from 'A' to 'Z'
        String filename = "allLetters.txt";
        Files.write(Paths.get(filename), "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes());

        int[] expected = new int[26];
        for (int i = 0; i < 26; i++) {
            expected[i] = 1;
        }
        int[] result = LetterFreq.countLetters(filename);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testCountLetters_RepeatedLetters() throws IOException {
        // Create a file with repeated letters 'A', 'A', 'B', 'B', 'B'
        String filename = "repeatedLetters.txt";
        Files.write(Paths.get(filename), "AABB".getBytes());

        int[] expected = new int[26];
        expected[0] = 2; // 'A'
        expected[1] = 2; // 'B'
        int[] result = LetterFreq.countLetters(filename);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testCountLetters_MixedContent() throws IOException {
        // Create a file with mixed content "Hello, World!"
        String filename = "mixedContent.txt";
        Files.write(Paths.get(filename), "Hello, World!".getBytes());

        int[] expected = new int[26];
        expected[7] = 1; // 'H'
        expected[4] = 1; // 'E'
        expected[11] = 3; // 'L'
        expected[14] = 2; // 'O'
        expected[22] = 1; // 'W'
        expected[17] = 1; // 'R'
        expected[3] = 1; // 'D'
        int[] result = LetterFreq.countLetters(filename);

        assertArrayEquals(expected, result);
    }
}
