import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.*;

public class DecodeTest {

    @Test
    public void testValidBase64Decoding() {
        String data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g=";
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(data);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        String expected = "To err is human, but to really foul things up you need a computer.\n    -- Paul R. Ehrlich";
        assertEquals(expected, decodedStr);
    }

    @Test
    public void testIncorrectPadding() {
        String data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g"; // Missing one padding character
        Base64.Decoder decoder = Base64.getDecoder();
        assertThrows(IllegalArgumentException.class, () -> {
            decoder.decode(data);
        });
    }

    @Test
    public void testEmptyString() {
        String data = "";
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(data);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        assertEquals("", decodedStr);
    }

    @Test
    public void testNonBase64Characters() {
        String data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g!"; // Contains a non-Base64 character '!'
        Base64.Decoder decoder = Base64.getDecoder();
        assertThrows(IllegalArgumentException.class, () -> {
            decoder.decode(data);
        });
    }

    @Test
    public void testWhitespaceInBase64String() {
        String data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g=";
        data = data.replace(" ", ""); // Remove whitespace
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(data);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        String expected = "To err is human, but to really foul things up you need a computer.\n    -- Paul R. Ehrlich";
        assertEquals(expected, decodedStr);
    }
}
