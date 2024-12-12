import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.Base64

class Base64DecodeDataTest {

    @Test
    fun `test valid base64 encoded string`() {
        val data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g="
        val decoder = Base64.getDecoder()
        val decoded = decoder.decode(data)
        val decodedStr = String(decoded, Charsets.UTF_8)
        assertEquals("To err is human, but to really foul things up you need a computer.\n    -- Paul R. Ehrlich", decodedStr)
    }

    @Test
    fun `test empty base64 encoded string`() {
        val data = ""
        val decoder = Base64.getDecoder()
        val decoded = decoder.decode(data)
        val decodedStr = String(decoded, Charsets.UTF_8)
        assertEquals("", decodedStr)
    }

    @Test
    fun `test invalid base64 encoded string`() {
        val data = "InvalidBase64String!"
        val decoder = Base64.getDecoder()
        assertThrows(IllegalArgumentException::class.java) {
            decoder.decode(data)
        }
    }

    @Test
    fun `test base64 encoded string with incorrect padding`() {
        val data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g"
        val decoder = Base64.getDecoder()
        val decoded = decoder.decode(data)
        val decodedStr = String(decoded, Charsets.UTF_8)
        assertEquals("To err is human, but to really foul things up you need a computer.\n    -- Paul R. Ehrlich", decodedStr)
    }

    @Test
    fun `test base64 encoded string with whitespace`() {
        val data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g=\n"
        val decoder = Base64.getDecoder()
        val decoded = decoder.decode(data.trim())
        val decodedStr = String(decoded, Charsets.UTF_8)
        assertEquals("To err is human, but to really foul things up you need a computer.\n    -- Paul R. Ehrlich", decodedStr)
    }
}
