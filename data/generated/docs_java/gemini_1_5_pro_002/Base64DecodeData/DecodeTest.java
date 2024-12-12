import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecodeTest {

    @Test
    void testDecodeValidBase64() {
        String encodedData = "VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g=";
        String expectedDecodedString = "To err is human, but to really foul things up you need a computer.\n      -- Paul R. Ehrlich";

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(encodedData);
        String actualDecodedString = new String(decodedBytes, StandardCharsets.UTF_8);

        assertEquals(expectedDecodedString, actualDecodedString);
    }


    @Test
    void testDecodeWithPadding1() {
        String encodedData = "c29tZSBkYXRhIHdpdGggcGFkZGluZw=="; //"some data with padding"
        String expectedDecodedString = "some data with padding";

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(encodedData);
        String actualDecodedString = new String(decodedBytes, StandardCharsets.UTF_8);

        assertEquals(expectedDecodedString, actualDecodedString);
    }

    @Test
    void testDecodeWithPadding2() {
        String encodedData = "c29tZSBkYXRhIHdpdGggcGFkZGluZw="; //"some data with padding"
        String expectedDecodedString = "some data with padding";

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(encodedData);
        String actualDecodedString = new String(decodedBytes, StandardCharsets.UTF_8);

        assertEquals(expectedDecodedString, actualDecodedString);
    }


    @Test
    void testDecodeEmptyInput() {
      String encodedData = "";
      String expectedDecodedString = "";

      Base64.Decoder decoder = Base64.getDecoder();
      byte[] decodedBytes = decoder.decode(encodedData);
      String actualDecodedString = new String(decodedBytes, StandardCharsets.UTF_8);

      assertEquals(expectedDecodedString, actualDecodedString);
  }



    @Test
    void testDecodeInvalidBase64() { // Testing IllegalArgumentException directly is generally discouraged, but given the specific context and how Java's Base64.Decoder handles invalid input, it's reasonable here.
        String encodedData = "ThisIsNotValidBase64";
        Base64.Decoder decoder = Base64.getDecoder();

        try {
            decoder.decode(encodedData);  // Expecting an IllegalArgumentException
           assert false; // Should not reach here if the exception is thrown

        } catch (IllegalArgumentException e) {
            // Test passes if the exception is thrown

         // Optionally, you can check the exception message for more specific error details.
            // assertEquals("Input byte array must not contain illegal characters: ...", e.getMessage()); 
        }

}
}


