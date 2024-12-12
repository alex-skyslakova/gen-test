import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LzwTest {

    @Test
    fun testCompressEmptyString() {
        val input = ""
        val expectedOutput = mutableListOf<Int>()
        assertEquals(expectedOutput, Lzw.compress(input))
    }

    @Test
    fun testCompressSingleCharacter() {
        val input = "A"
        val expectedOutput = mutableListOf(65)
        assertEquals(expectedOutput, Lzw.compress(input))
    }

    @Test
    fun testCompressRepeatedCharacter() {
        val input = "AAAAA"
        val expectedOutput = mutableListOf(65, 256, 257, 258)
        assertEquals(expectedOutput, Lzw.compress(input))
    }

    @Test
    fun testCompressSimpleString() {
        val input = "TOBEORNOTTOBEORTOBEORNOT"
        val expectedOutput = mutableListOf(84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263)
        assertEquals(expectedOutput, Lzw.compress(input))
    }

    @Test
    fun testDecompressEmptyList() {
        val input = mutableListOf<Int>()
        val expectedOutput = ""
        assertEquals(expectedOutput, Lzw.decompress(input))
    }

    @Test
    fun testDecompressSingleCharacter() {
        val input = mutableListOf(65)
        val expectedOutput = "A"
        assertEquals(expectedOutput, Lzw.decompress(input))
    }

    @Test
    fun testDecompressRepeatedCharacter() {
        val input = mutableListOf(65, 256, 257, 258)
        val expectedOutput = "AAAAA"
        assertEquals(expectedOutput, Lzw.decompress(input))
    }

    @Test
    fun testDecompressSimpleString() {
        val input = mutableListOf(84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263)
        val expectedOutput = "TOBEORNOTTOBEORTOBEORNOT"
        assertEquals(expectedOutput, Lzw.decompress(input))
    }

    @Test
    fun testDecompressInvalidInput() {
        val input = mutableListOf(300)
        assertThrows<IllegalArgumentException> {
            Lzw.decompress(input)
        }
    }

    @Test
    fun testCompressDecompressRoundTrip() {
        val input = "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG"
        val compressed = Lzw.compress(input)
        val decompressed = Lzw.decompress(compressed)
        assertEquals(input, decompressed)
    }
}
