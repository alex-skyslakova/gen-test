import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DerangedAnagramsTest {

    @Test
    void isDeranged_sameWord_returnsFalse() {
        assertFalse(DerangedAnagrams.isDeranged("word", "word"));
    }

    @Test
    void isDeranged_differentWords_returnsTrue() {
        assertTrue(DerangedAnagrams.isDeranged("abcd", "badc"));
    }

    @Test
    void isDeranged_oneCommonCharacter_returnsFalse() {
        assertFalse(DerangedAnagrams.isDeranged("abcd", "abdc"));

    }
    @Test
    void isDeranged_differentLengths_returnsFalse(){
        assertFalse(DerangedAnagrams.isDeranged("abc","abcd"));
    }


    @Test
    void printLongestDerangedAnagram_noAnagrams_printsNoResult() {
        List<String> words = Arrays.asList("word", "other");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);


        DerangedAnagrams.printLongestDerangedAnagram(words);

        assertEquals("no result" + System.lineSeparator(), outputStream.toString());

        System.setOut(System.out); // Restore standard output
    }
    @Test
    void printLongestDerangedAnagram_findsLongestDerangedAnagram(){
         List<String> words = Arrays.asList("abc", "bac","abcd","bdca", "word","other");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);


        DerangedAnagrams.printLongestDerangedAnagram(words);

        assertEquals("abcd bdca" + System.lineSeparator(), outputStream.toString());

        System.setOut(System.out); // Restore standard output
    }



    @Test
    void printLongestDerangedAnagram_anagramsPresent_printsAnagrams() {

        List<String> words = Arrays.asList("listen", "silent", "enlist", "tinsel","abc","bca");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);


        DerangedAnagrams.printLongestDerangedAnagram(words);

        assertEquals("listen silent" + System.lineSeparator(), outputStream.toString());


        System.setOut(System.out); // Restore standard output
    }

    @Test
    void printLongestDerangedAnagram_emptyList_printsNoResult() {
        List<String> words = new ArrayList<>();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);


        DerangedAnagrams.printLongestDerangedAnagram(words);

        assertEquals("no result" + System.lineSeparator(), outputStream.toString());

        System.setOut(System.out);
    }

}
