import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LZWTest {

    @Test
    void compress_emptyString() {
        assertEquals(new ArrayList<>(), LZW.compress(""));
    }

    @Test
    void compress_singleCharacter() {
        List<Integer> expected = new ArrayList<>(Arrays.asList((int) 'A'));
        assertEquals(expected, LZW.compress("A"));
    }

    @Test
    void compress_repeatedCharacters() {
        List<Integer> expected = new ArrayList<>(Arrays.asList((int) 'A', 256));
        assertEquals(expected, LZW.compress("AA"));
    }

    @Test
    void compress_exampleFromWikipedia() {
        List<Integer> expected = new ArrayList<>(Arrays.asList(84, 79, 256, 258, 69, 82, 79, 84, 259, 260));
        assertEquals(expected, LZW.compress("TOBEORNOTTOBEORTOBEORNOT"));

    }


    @Test
    void decompress_emptyString() {
        assertEquals("", LZW.decompress(new ArrayList<>()));
    }

    @Test
    void decompress_singleCharacter() {
        List<Integer> compressed = new ArrayList<>(Arrays.asList((int) 'A'));
        assertEquals("A", LZW.decompress(new ArrayList<>(compressed))); // Create a copy to avoid modification
    }


    @Test
    void decompress_repeatedCharacters() {
        List<Integer> compressed = new ArrayList<>(Arrays.asList((int) 'A', 256));
        assertEquals("AA", LZW.decompress(new ArrayList<>(compressed)));
    }

    @Test
    void decompress_exampleFromWikipedia() {
        List<Integer> compressed = new ArrayList<>(Arrays.asList(84, 79, 256, 258, 69, 82, 79, 84, 259, 260));
        assertEquals("TOBEORNOTTOBEORTOBEORNOT", LZW.decompress(new ArrayList<>(compressed)));
    }

    @Test
    void decompress_invalidInput() {
        List<Integer> compressed = new ArrayList<>(Arrays.asList(84, 79, 256, 999)); // Invalid code
        assertThrows(IllegalArgumentException.class, () -> LZW.decompress(compressed));
    }

     @Test
    void roundTrip_complexString() {
        String input = "ABABCABABABCABABABC";
        List<Integer> compressed = LZW.compress(input);
        String decompressed = LZW.decompress(new ArrayList<>(compressed)); // Create a copy to avoid modification.
        assertEquals(input, decompressed);
    }


}
