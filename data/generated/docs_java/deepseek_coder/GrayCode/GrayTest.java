import static org.junit.Assert.*;
import org.junit.Test;

public class GrayTest {

    @Test
    public void testGrayEncode() {
        // Test cases for encoding
        assertEquals(0b0, Gray.grayEncode(0b0));
        assertEquals(0b1, Gray.grayEncode(0b1));
        assertEquals(0b11, Gray.grayEncode(0b10));
        assertEquals(0b10, Gray.grayEncode(0b11));
        assertEquals(0b110, Gray.grayEncode(0b100));
        assertEquals(0b111, Gray.grayEncode(0b101));
        assertEquals(0b101, Gray.grayEncode(0b110));
        assertEquals(0b100, Gray.grayEncode(0b111));
        assertEquals(0b1100, Gray.grayEncode(0b1000));
        assertEquals(0b1101, Gray.grayEncode(0b1001));
        assertEquals(0b1111, Gray.grayEncode(0b1010));
        assertEquals(0b1110, Gray.grayEncode(0b1011));
        assertEquals(0b1010, Gray.grayEncode(0b1100));
        assertEquals(0b1011, Gray.grayEncode(0b1101));
        assertEquals(0b1001, Gray.grayEncode(0b1110));
        assertEquals(0b1000, Gray.grayEncode(0b1111));
    }

    @Test
    public void testGrayDecode() {
        // Test cases for decoding
        assertEquals(0b0, Gray.grayDecode(0b0));
        assertEquals(0b1, Gray.grayDecode(0b1));
        assertEquals(0b10, Gray.grayDecode(0b11));
        assertEquals(0b11, Gray.grayDecode(0b10));
        assertEquals(0b100, Gray.grayDecode(0b110));
        assertEquals(0b101, Gray.grayDecode(0b111));
        assertEquals(0b110, Gray.grayDecode(0b101));
        assertEquals(0b111, Gray.grayDecode(0b100));
        assertEquals(0b1000, Gray.grayDecode(0b1100));
        assertEquals(0b1001, Gray.grayDecode(0b1101));
        assertEquals(0b1010, Gray.grayDecode(0b1111));
        assertEquals(0b1011, Gray.grayDecode(0b1110));
        assertEquals(0b1100, Gray.grayDecode(0b1010));
        assertEquals(0b1101, Gray.grayDecode(0b1011));
        assertEquals(0b1110, Gray.grayDecode(0b1001));
        assertEquals(0b1111, Gray.grayDecode(0b1000));
    }

    @Test
    public void testGrayEncodeDecode() {
        // Test cases for encoding and then decoding
        for (int i = 0; i < 32; i++) {
            long gray = Gray.grayEncode(i);
            long decoded = Gray.grayDecode(gray);
            assertEquals(i, decoded);
        }
    }

    @Test
    public void testGrayEncodeDecodeEdgeCases() {
        // Test edge cases
        assertEquals(0, Gray.grayDecode(Gray.grayEncode(0)));
        assertEquals(31, Gray.grayDecode(Gray.grayEncode(31)));
        assertEquals(15, Gray.grayDecode(Gray.grayEncode(15)));
        assertEquals(7, Gray.grayDecode(Gray.grayEncode(7)));
        assertEquals(25, Gray.grayDecode(Gray.grayEncode(25)));
    }
}
