import org.junit.Assert.assertEquals
import org.junit.Test

class LzwTest {

    @Test
    fun testCompressAndDecompress() {
        val input = "TOBEORNOTTOBEORTOBEORNOT"
        val compressed = Lzw.compress(input)
        val decompressed = Lzw.decompress(compressed)
        assertEquals(input, decompressed)
    }

    @Test
    fun testCompressEmptyString() {
        val input = ""
        val compressed = Lzw.compress(input)
        val decompressed = Lzw.decompress(compressed)
        assertEquals(input, decompressed)
    }

    @Test
    fun testCompressSingleCharacter() {
        val input = "A"
        val compressed = Lzw.compress(input)
        val decompressed = Lzw.decompress(compressed)
        assertEquals(input, decompressed)
    }

    @Test
    fun testCompressRepeatedCharacter() {
        val input = "AAAAAA"
        val compressed = Lzw.compress(input)
        val decompressed = Lzw.decompress(compressed)
        assertEquals(input, decompressed)
    }

    @Test
    fun testCompressMixedCharacters() {
        val input = "ABABABA"
        val compressed = Lzw.compress(input)
        val decompressed = Lzw.decompress(compressed)
        assertEquals(input, decompressed)
    }

    @Test
    fun testCompressWithSpecialCharacters() {
        val input = "!@#$%^&*()"
        val compressed = Lzw.compress(input)
        val decompressed = Lzw.decompress(compressed)
        assertEquals(input, decompressed)
    }

    @Test
    fun testCompressWithNumbers() {
        val input = "1234567890"
        val compressed = Lzw.compress(input)
        val decompressed = Lzw.decompress(compressed)
        assertEquals(input, decompressed)
    }

    @Test
    fun testCompressWithMixedAlphanumeric() {
        val input = "A1B2C3D4E5F6G7H8I9J0"
        val compressed = Lzw.compress(input)
        val decompressed = Lzw.decompress(compressed)
        assertEquals(input, decompressed)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDecompressWithBadCompressedData() {
        val compressed = mutableListOf(256) // This should throw an exception
        Lzw.decompress(compressed)
    }

    @Test
    fun testDecompressWithSingleEntry() {
        val compressed = mutableListOf(84) // 'T'
        val decompressed = Lzw.decompress(compressed)
        assertEquals("T", decompressed)
    }

    @Test
    fun testDecompressWithMultipleEntries() {
        val compressed = mutableListOf(84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263)
        val decompressed = Lzw.decompress(compressed)
        assertEquals("TOBEORNOTTOBEORTOBEORNOT", decompressed)
    }
}
