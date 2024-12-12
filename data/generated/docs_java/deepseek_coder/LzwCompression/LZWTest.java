import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LZWTest {

    @Test
    public void testCompressEmptyString() {
        List<Integer> result = LZW.compress("");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCompressSingleCharacter() {
        List<Integer> result = LZW.compress("a");
        assertEquals(Arrays.asList(97), result);
    }

    @Test
    public void testCompressRepeatedCharacter() {
        List<Integer> result = LZW.compress("aaa");
        assertEquals(Arrays.asList(97, 97, 97), result);
    }

    @Test
    public void testCompressComplexString() {
        List<Integer> result = LZW.compress("TOBEORNOTTOBEORTOBEORNOT");
        List<Integer> expected = Arrays.asList(84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263, 267, 269);
        assertEquals(expected, result);
    }

    @Test
    public void testDecompressEmptyList() {
        List<Integer> compressed = Arrays.asList();
        String result = LZW.decompress(compressed);
        assertEquals("", result);
    }

    @Test
    public void testDecompressSingleCharacter() {
        List<Integer> compressed = Arrays.asList(97);
        String result = LZW.decompress(compressed);
        assertEquals("a", result);
    }

    @Test
    public void testDecompressRepeatedCharacter() {
        List<Integer> compressed = Arrays.asList(97, 97, 97);
        String result = LZW.decompress(compressed);
        assertEquals("aaa", result);
    }

    @Test
    public void testDecompressComplexList() {
        List<Integer> compressed = Arrays.asList(84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263, 267, 269);
        String result = LZW.decompress(compressed);
        assertEquals("TOBEORNOTTOBEORTOBEORNOT", result);
    }

    @Test
    public void testDecompressInvalidCode() {
        List<Integer> compressed = Arrays.asList(84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263, 267, 269, 1000);
        assertThrows(IllegalArgumentException.class, () -> LZW.decompress(compressed));
    }

    @Test
    public void testCompressDecompressRoundTrip() {
        String original = "TOBEORNOTTOBEORTOBEORNOT";
        List<Integer> compressed = LZW.compress(original);
        String decompressed = LZW.decompress(compressed);
        assertEquals(original, decompressed);
    }
}
