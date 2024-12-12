import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class LZWTest {

    @Test
    public void testCompressAndDecompress() {
        String original = "TOBEORNOTTOBEORTOBEORNOT";
        List<Integer> compressed = LZW.compress(original);
        String decompressed = LZW.decompress(compressed);
        assertEquals(original, decompressed);
    }

    @Test
    public void testCompressEmptyString() {
        String original = "";
        List<Integer> compressed = LZW.compress(original);
        assertTrue(compressed.isEmpty());
    }

    @Test
    public void testDecompressEmptyList() {
        List<Integer> compressed = new ArrayList<>();
        String decompressed = LZW.decompress(compressed);
        assertEquals("", decompressed);
    }

    @Test
    public void testCompressSingleCharacter() {
        String original = "A";
        List<Integer> compressed = LZW.compress(original);
        assertEquals(1, compressed.size());
        assertEquals((Integer)(int)'A', compressed.get(0));
    }

    @Test
    public void testDecompressSingleCharacter() {
        List<Integer> compressed = Arrays.asList((int)'A');
        String decompressed = LZW.decompress(compressed);
        assertEquals("A", decompressed);
    }

    @Test
    public void testCompressRepeatedCharacters() {
        String original = "AAAAAA";
        List<Integer> compressed = LZW.compress(original);
        assertTrue(compressed.size() < original.length());
    }

    @Test
    public void testDecompressRepeatedCharacters() {
        List<Integer> compressed = LZW.compress("AAAAAA");
        String decompressed = LZW.decompress(compressed);
        assertEquals("AAAAAA", decompressed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecompressInvalidCompressedData() {
        List<Integer> compressed = Arrays.asList(256);
        LZW.decompress(compressed);
    }
}
