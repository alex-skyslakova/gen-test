import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LzwTest {

    @Test
    fun testCompressEmpty() {
        assertEquals(mutableListOf<Int>(), Lzw.compress(""))
    }

    @Test
    fun testCompressSingleChar() {
        assertEquals(mutableListOf<Int>(84), Lzw.compress("T"))
    }

    @Test
    fun testCompressRepeatingChar() {
        assertEquals(mutableListOf<Int>(84, 256), Lzw.compress("TT"))
    }


    @Test
    fun testCompressExample() {
        val compressed = Lzw.compress("TOBEORNOTTOBEORTOBEORNOT")
        assertEquals(mutableListOf<Int>(84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 259, 262), compressed)
    }

    @Test
    fun testDecompressEmpty() {
        assertEquals("", Lzw.decompress(mutableListOf()))
    }

    @Test
    fun testDecompressSingleChar() {
        assertEquals("T", Lzw.decompress(mutableListOf(84)))
    }

    @Test
    fun testDecompressRepeatingChar() {
        assertEquals("TT", Lzw.decompress(mutableListOf(84, 256)))
    }

    @Test
    fun testDecompressExample() {
        val compressed = mutableListOf<Int>(84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 259, 262)
        val decompressed = Lzw.decompress(compressed)
        assertEquals("TOBEORNOTTOBEORTOBEORNOT", decompressed)

    }

    @Test
    fun testCompressDecompressCombined() {
        val originalString =  "ABABABA"
        val compressed = Lzw.compress(originalString)
        val decompressed = Lzw.decompress(compressed)

        assertEquals(originalString, decompressed)

    }

        @Test
    fun testCompressDecompressCombinedLong() {
        val originalString =  "This is a longer string with some repeating patterns like like like."
        val compressed = Lzw.compress(originalString)
        val decompressed = Lzw.decompress(compressed)

        assertEquals(originalString, decompressed)

    }


    @Test(expected = IllegalArgumentException::class)
    fun testDecompressInvalidInput() {
        Lzw.decompress(mutableListOf(84, 9999)) // Invalid code
    }




}
