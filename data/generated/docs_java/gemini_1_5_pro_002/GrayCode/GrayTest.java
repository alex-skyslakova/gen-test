import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrayTest {

    @Test
    void grayEncodeTest() {
        assertEquals(0, Gray.grayEncode(0));
        assertEquals(1, Gray.grayEncode(1));
        assertEquals(3, Gray.grayEncode(2));
        assertEquals(2, Gray.grayEncode(3));
        assertEquals(7, Gray.grayEncode(4));
        assertEquals(6, Gray.grayEncode(5));
        assertEquals(4, Gray.grayEncode(6));
        assertEquals(5, Gray.grayEncode(7));
        assertEquals(15, Gray.grayEncode(8));
        assertEquals(12, Gray.grayEncode(10));
        assertEquals(10, Gray.grayEncode(12));
        assertEquals(31, Gray.grayEncode(16));

        // Test with larger numbers
        assertEquals(987654321L ^ (987654321L >>> 1), Gray.grayEncode(987654321L));

        // Test with negative numbers (behavior is defined by Java's unsigned right shift)
        assertEquals(-1L ^ (-1L >>> 1), Gray.grayEncode(-1L));  
        assertEquals(-2L ^ (-2L >>> 1), Gray.grayEncode(-2L));
    }

    @Test
    void grayDecodeTest() {
        for (int i = 0; i < 32; i++) {
            assertEquals(i, Gray.grayDecode(Gray.grayEncode(i)));
        }
        // Test with larger numbers
        assertEquals(987654321L, Gray.grayDecode(Gray.grayEncode(987654321L)));
       
        // Test with values derived from encoded negative numbers (already in Gray code).
        assertEquals(Gray.grayDecode(Gray.grayEncode(-1L)), Gray.grayDecode(Gray.grayEncode(-1L)));
         assertEquals(Gray.grayDecode(Gray.grayEncode(-2L)), Gray.grayDecode(Gray.grayEncode(-2L)));


    }

     @Test
    void grayDecodeEdgeCasesTest() {
        assertEquals(0, Gray.grayDecode(0));
        assertEquals(1, Gray.grayDecode(1));
        assertEquals(Long.MAX_VALUE, Gray.grayDecode(Long.MAX_VALUE)); // Check Max Value
         // Check with Gray coded representation of Long.MIN_VALUE as input to decode.
          assertEquals(Gray.grayDecode(-9223372036854775808L), Gray.grayDecode(-9223372036854775808L));

    }





}
