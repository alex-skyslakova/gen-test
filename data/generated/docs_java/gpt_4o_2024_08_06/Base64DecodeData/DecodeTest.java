import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.*;

public class DecodeTest {

    @Test
    public void testValidBase64String() {
        String data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g=";
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(data);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        assertEquals("To err is human, but to really foul things up you need a computer.\n    -- Paul R. Ehrlich", decodedStr);
    }

    @Test
    public void testEmptyBase64String() {
        String data = "";
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(data);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        assertEquals("", decodedStr);
    }

    @Test
    public void testInvalidBase64String() {
        String data = "InvalidBase64String!";
        Base64.Decoder decoder = Base64.getDecoder();
        assertThrows(IllegalArgumentException.class, () -> {
            decoder.decode(data);
        });
    }

    @Test
    public void testBase64StringWithIncorrectPadding() {
        String data = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g"; // Removed padding
        Base64.Decoder decoder = Base64.getDecoder();
        assertThrows(IllegalArgumentException.class, () -> {
            decoder.decode(data);
        });
    }
}
