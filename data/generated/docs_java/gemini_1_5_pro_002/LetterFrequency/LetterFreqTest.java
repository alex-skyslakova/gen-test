import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class LetterFreqTest {

    @Test
    void testEmptyFile() throws IOException {
        File tempFile = createTempFile("");
        int[] freqs = LetterFreq.countLetters(tempFile.getPath());
        assertArrayEquals(new int[26], freqs);
        tempFile.delete();
    }

    @Test
    void testOnlyLetters() throws IOException {
        File tempFile = createTempFile("TheQuickBrownFoxJumpsOverTheLazyDog");
        int[] freqs = LetterFreq.countLetters(tempFile.getPath());
        assertTrue(freqs['T' - 'A'] > 0);
        assertTrue(freqs['H' - 'A'] > 0);
        assertTrue(freqs['E' - 'A'] > 0);
        // ... (check other letters)
        tempFile.delete();
    }


    @Test
    void testMixedCaseAndPunctuation() throws IOException {
        File tempFile = createTempFile("This is a test, with punctuation! and UPPER and lower case.");
        int[] freqs = LetterFreq.countLetters(tempFile.getPath());
        assertTrue(freqs['T' - 'A'] > 0);
        assertTrue(freqs['H' - 'A'] > 0);
        assertTrue(freqs['I' - 'A'] > 0);
        assertTrue(freqs['S' - 'A'] > 0);
        assertTrue(freqs['A' - 'A'] > 0);
        // ... (check other letters, ensuring punctuation is ignored)
        tempFile.delete();

    }


    @Test
    void testNumbersAndSpecialChars() throws IOException {
        File tempFile = createTempFile("1234567890!@#$%^&*()_+=-`~[]\';,.//{}|:\"<>?");
        int[] freqs = LetterFreq.countLetters(tempFile.getPath());
        assertArrayEquals(new int[26], freqs); // Should be all zeros
        tempFile.delete();
    }



    private File createTempFile(String content) throws IOException {
        File tempFile = File.createTempFile("temp", ".txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write(content);
        writer.close();
        return tempFile;
    }
}
