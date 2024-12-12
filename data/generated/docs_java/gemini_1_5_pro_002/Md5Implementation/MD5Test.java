import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class MD5Test {

    @Test
    public void testEmptyString() {
        byte[] expected = hexStringToByteArray("D41D8CD98F00B204E9800998ECF8427E");
        byte[] actual = MD5.computeMD5("".getBytes());
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSingleCharacter() {
        byte[] expected = hexStringToByteArray("0CC175B9C0F1B6A831C399E269772661");
        byte[] actual = MD5.computeMD5("a".getBytes());
        assertArrayEquals(expected, actual);

    }

    @Test
    public void testAbc() {
        byte[] expected = hexStringToByteArray("900150983CD24FB0D6963F7D28E17F72");
        byte[] actual = MD5.computeMD5("abc".getBytes());
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMessageDigest() {
        byte[] expected = hexStringToByteArray("F96B697D7CB7938D525A2F31AAF161D0");
        byte[] actual = MD5.computeMD5("message digest".getBytes());
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAlphabet() {
        byte[] expected = hexStringToByteArray("C3FCD3D76192E4007DFB496CCA67E13B");
        byte[] actual = MD5.computeMD5("abcdefghijklmnopqrstuvwxyz".getBytes());
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAlphaNumeric() {
        byte[] expected = hexStringToByteArray("D174AB98D277D9F5A5611C2C9F419D9F");
        byte[] actual = MD5.computeMD5("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".getBytes());
        assertArrayEquals(expected, actual);

    }
    @Test
    public void testNumericLong(){
        byte[] expected = hexStringToByteArray("57EDF4A22BE3C955AC49DA2E2107B67A");
        byte[] actual = MD5.computeMD5("12345678901234567890123456789012345678901234567890123456789012345678901234567890".getBytes());
         assertArrayEquals(expected, actual);

    }


    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
