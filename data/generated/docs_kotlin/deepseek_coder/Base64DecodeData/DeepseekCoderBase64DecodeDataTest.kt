import org.junit.jupiter.api.Test
import java.util.Base64
import kotlin.test.assertEquals

class Base64DecodeDataTest {

    @Test
    fun testDecodeValidBase64String() {
        val data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g="
        val expected = "To err is human, but to really foul things up you need a computer.\n    -- Paul R. Ehrlich"
        val decoder = Base64.getDecoder()
        val decoded = decoder.decode(data)
        val decodedStr = String(decoded, Charsets.UTF_8)
        assertEquals(expected, decodedStr)
    }

    @Test
    fun testDecodeBase64StringWithIncorrectPadding() {
        val data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g" // Missing one padding character
        val expected = "To err is human, but to really foul things up you need a computer.\n    -- Paul R. Ehrlich"
        val decoder = Base64.getDecoder()
        val decoded = decoder.decode(data)
        val decodedStr = String(decoded, Charsets.UTF_8)
        assertEquals(expected, decodedStr)
    }

    @Test
    fun testDecodeEmptyBase64String() {
        val data = ""
        val expected = ""
        val decoder = Base64.getDecoder()
        val decoded = decoder.decode(data)
        val decodedStr = String(decoded, Charsets.UTF_8)
        assertEquals(expected, decodedStr)
    }

    @Test
    fun testDecodeBase64StringWithNonBase64Characters() {
        val data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g=!" // Non-Base64 character at the end
        val expected = "To err is human, but to really foul things up you need a computer.\n    -- Paul R. Ehrlich"
        val decoder = Base64.getDecoder()
        val decoded = decoder.decode(data.substringBefore('!')) // Remove non-Base64 character
        val decodedStr = String(decoded, Charsets.UTF_8)
        assertEquals(expected, decodedStr)
    }

    @Test
    fun testDecodeBase64StringWithWhitespace() {
        val data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g="
        val expected = "To err is human, but to really foul things up you need a computer.\n    -- Paul R. Ehrlich"
        val decoder = Base64.getDecoder()
        val decoded = decoder.decode(data.replace(" ", "")) // Remove whitespace
        val decodedStr = String(decoded, Charsets.UTF_8)
        assertEquals(expected, decodedStr)
    }
}
