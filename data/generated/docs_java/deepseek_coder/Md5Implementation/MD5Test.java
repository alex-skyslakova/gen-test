import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MD5Test {

    @Test
    public void testEmptyString() {
        String input = "";
        String expected = "D41D8CD98F00B204E9800998ECF8427E";
        String result = MD5.toHexString(MD5.computeMD5(input.getBytes()));
        assertEquals(expected, result);
    }

    @Test
    public void testSingleCharacter() {
        String input = "a";
        String expected = "0CC175B9C0F1B6A831C399E269772661";
        String result = MD5.toHexString(MD5.computeMD5(input.getBytes()));
        assertEquals(expected, result);
    }

    @Test
    public void testThreeCharacters() {
        String input = "abc";
        String expected = "900150983CD24FB0D6963F7D28E17F72";
        String result = MD5.toHexString(MD5.computeMD5(input.getBytes()));
        assertEquals(expected, result);
    }

    @Test
    public void testMessageDigest() {
        String input = "message digest";
        String expected = "F96B697D7CB7938D525A2F31AAF161D0";
        String result = MD5.toHexString(MD5.computeMD5(input.getBytes()));
        assertEquals(expected, result);
    }

    @Test
    public void testAlphabet() {
        String input = "abcdefghijklmnopqrstuvwxyz";
        String expected = "C3FCD3D76192E4007DFB496CCA67E13B";
        String result = MD5.toHexString(MD5.computeMD5(input.getBytes()));
        assertEquals(expected, result);
    }

    @Test
    public void testAlphabetAndNumbers() {
        String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String expected = "D174AB98D277D9F5A5611C2C9F419D9F";
        String result = MD5.toHexString(MD5.computeMD5(input.getBytes()));
        assertEquals(expected, result);
    }

    @Test
    public void testLongString() {
        String input = "12345678901234567890123456789012345678901234567890123456789012345678901234567890";
        String expected = "57EDF4A22BE3C955AC49DA2E2107B67A";
        String result = MD5.toHexString(MD5.computeMD5(input.getBytes()));
        assertEquals(expected, result);
    }
}
