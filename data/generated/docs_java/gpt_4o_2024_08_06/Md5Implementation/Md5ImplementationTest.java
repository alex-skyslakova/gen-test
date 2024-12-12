import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MD5Test {

    @Test
    void testEmptyString() {
        String input = "";
        String expectedOutput = "D41D8CD98F00B204E9800998ECF8427E";
        assertEquals(expectedOutput, MD5.toHexString(MD5.computeMD5(input.getBytes())));
    }

    @Test
    void testSingleCharacter() {
        String input = "a";
        String expectedOutput = "0CC175B9C0F1B6A831C399E269772661";
        assertEquals(expectedOutput, MD5.toHexString(MD5.computeMD5(input.getBytes())));
    }

    @Test
    void testSimpleString() {
        String input = "abc";
        String expectedOutput = "900150983CD24FB0D6963F7D28E17F72";
        assertEquals(expectedOutput, MD5.toHexString(MD5.computeMD5(input.getBytes())));
    }

    @Test
    void testMessageDigest() {
        String input = "message digest";
        String expectedOutput = "F96B697D7CB7938D525A2F31AAF161D0";
        assertEquals(expectedOutput, MD5.toHexString(MD5.computeMD5(input.getBytes())));
    }

    @Test
    void testAlphabet() {
        String input = "abcdefghijklmnopqrstuvwxyz";
        String expectedOutput = "C3FCD3D76192E4007DFB496CCA67E13B";
        assertEquals(expectedOutput, MD5.toHexString(MD5.computeMD5(input.getBytes())));
    }

    @Test
    void testAlphanumeric() {
        String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String expectedOutput = "D174AB98D277D9F5A5611C2C9F419D9F";
        assertEquals(expectedOutput, MD5.toHexString(MD5.computeMD5(input.getBytes())));
    }

    @Test
    void testNumericSequence() {
        String input = "12345678901234567890123456789012345678901234567890123456789012345678901234567890";
        String expectedOutput = "57EDF4A22BE3C955AC49DA2E2107B67A";
        assertEquals(expectedOutput, MD5.toHexString(MD5.computeMD5(input.getBytes())));
    }
}
