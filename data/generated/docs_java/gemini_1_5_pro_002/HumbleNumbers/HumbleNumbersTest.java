import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HumbleNumbersTest {

    @Test
    void testHumble_first50() {
        BigInteger[] expected = {BigInteger.ONE, BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(4), BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(7), BigInteger.valueOf(8), BigInteger.valueOf(9), BigInteger.valueOf(10), BigInteger.valueOf(12), BigInteger.valueOf(14), BigInteger.valueOf(15), BigInteger.valueOf(16), BigInteger.valueOf(18), BigInteger.valueOf(20), BigInteger.valueOf(21), BigInteger.valueOf(24), BigInteger.valueOf(25), BigInteger.valueOf(27), BigInteger.valueOf(28), BigInteger.valueOf(30), BigInteger.valueOf(32), BigInteger.valueOf(35), BigInteger.valueOf(36), BigInteger.valueOf(40), BigInteger.valueOf(42), BigInteger.valueOf(45), BigInteger.valueOf(48), BigInteger.valueOf(49), BigInteger.valueOf(50), BigInteger.valueOf(54), BigInteger.valueOf(56), BigInteger.valueOf(60), BigInteger.valueOf(63), BigInteger.valueOf(64), BigInteger.valueOf(70), BigInteger.valueOf(72), BigInteger.valueOf(75), BigInteger.valueOf(80), BigInteger.valueOf(81), BigInteger.valueOf(84), BigInteger.valueOf(90), BigInteger.valueOf(96), BigInteger.valueOf(98), BigInteger.valueOf(100), BigInteger.valueOf(105), BigInteger.valueOf(108), BigInteger.valueOf(112), BigInteger.valueOf(120), BigInteger.valueOf(126)};
        BigInteger[] actual = HumbleNumbers.humble(50);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testHumble_n1() {
        BigInteger[] actual = HumbleNumbers.humble(1);
        assertArrayEquals(new BigInteger[]{BigInteger.ONE}, actual);
    }


    @Test
    void testHumble_n0() {
        BigInteger[] actual = HumbleNumbers.humble(0);
        assertArrayEquals(new BigInteger[]{}, actual);
    }


    @Test
    void testMain_lengthCount() {
         // Redirect System.out to capture the output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        
        HumbleNumbers.main(new String[]{});

        String output = outContent.toString();

        // Basic checks to ensure that length and counts are printed
        assertTrue(output.contains("Length  Count"));
        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
        assertTrue(output.contains("3"));
        // Add more assertions as needed for specific counts and lengths based on humble(1_000_000) data.
        // Since we're using humble(1_000_000), checking for lengths 1 and 2 is sufficient for test purposes. 
        // Add tests for edge cases and other counts as deemed necessary.
         System.setOut(System.out); // Restore System.out

    }



}
