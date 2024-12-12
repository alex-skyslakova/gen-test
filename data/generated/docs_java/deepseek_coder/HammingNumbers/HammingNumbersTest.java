import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

class HammingTest {

    @Test
    void testHammingFirstTwenty() {
        BigInteger[] expected = {
                BigInteger.valueOf(1), BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(4), BigInteger.valueOf(5),
                BigInteger.valueOf(6), BigInteger.valueOf(8), BigInteger.valueOf(9), BigInteger.valueOf(10), BigInteger.valueOf(12),
                BigInteger.valueOf(15), BigInteger.valueOf(16), BigInteger.valueOf(18), BigInteger.valueOf(20), BigInteger.valueOf(24),
                BigInteger.valueOf(25), BigInteger.valueOf(27), BigInteger.valueOf(30), BigInteger.valueOf(32), BigInteger.valueOf(36)
        };

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], Hamming.hamming(i + 1));
        }
    }

    @Test
    void testHamming1691() {
        BigInteger expected = new BigInteger("2125764000");
        assertEquals(expected, Hamming.hamming(1691));
    }

    @Test
    void testHamming1000000() {
        BigInteger expected = new BigInteger("519312780448388736089589843750000000000000000000000000000000000000000000000000000000000000000000");
        assertEquals(expected, Hamming.hamming(1000000));
    }

    @Test
    void testHammingInvalidParameter() {
        assertThrows(IllegalArgumentException.class, () -> Hamming.hamming(0));
        assertThrows(IllegalArgumentException.class, () -> Hamming.hamming(-1));
    }
}
