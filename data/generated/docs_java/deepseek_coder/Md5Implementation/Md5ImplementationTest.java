import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MD5Test {

    @Test
    void testEmptyString() {
        String input = "";
        String expected = "D41D8CD98F00B204E9800998ECF8427E";
        assertEquals(expected, MD5.toHexString(MD5.computeMD5(input.getBytes())).toUpperCase());
    }

    @Test
    void testSingleCharacter() {
        String input = "a";
        String expected = "0CC175B9C0F1B6A831C399E269772661";
        assertEquals(expected, MD5.toHexString(MD5.computeMD5(input.getBytes())).toUpperCase());
    }

    @Test
    void testThreeCharacters() {
        String input = "abc";
        String expected = "900150983CD24FB0D6963F7D28E17F72";
        assertEquals(expected, MD5.toHexString(MD5.computeMD5(input.getBytes())).toUpperCase());
    }

    @Test
    void testMessageDigest() {
        String input = "message digest";
        String expected = "F96B697D7CB7938D525A2F31AAF161D0";
        assertEquals(expected, MD5.toHexString(MD5.computeMD5(input.getBytes())).toUpperCase());
    }

    @Test
    void testAlphabet() {
        String input = "abcdefghijklmnopqrstuvwxyz";
        String expected = "C3FCD3D76192E4007DFB496CCA67E13B";
        assertEquals(expected, MD5.toHexString(MD5.computeMD5(input.getBytes())).toUpperCase());
    }

    @Test
    void testAlphabetAndNumbers() {
        String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String expected = "D174AB98D277D9F5A5611C2C9F419D9F";
        assertEquals(expected, MD5.toHexString(MD5.computeMD5(input.getBytes())).toUpperCase());
    }

    @Test
    void testLongString() {
        String input = "12345678901234567890123456789012345678901234567890123456789012345678901234567890";
        String expected = "57EDF4A22BE3C955AC49DA2E2107B67A";
        assertEquals(expected, MD5.toHexString(MD5.computeMD5(input.getBytes())).toUpperCase());
    }
}
