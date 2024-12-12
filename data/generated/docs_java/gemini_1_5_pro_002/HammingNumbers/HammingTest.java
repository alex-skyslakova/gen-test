import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class HammingTest {

    @Test
    void testHammingFirstTwenty() {
        BigInteger[] expected = {BigInteger.ONE, BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(4), BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(8), BigInteger.valueOf(9), BigInteger.valueOf(10), BigInteger.valueOf(12), BigInteger.valueOf(15), BigInteger.valueOf(16), BigInteger.valueOf(18), BigInteger.valueOf(20), BigInteger.valueOf(24), BigInteger.valueOf(25), BigInteger.valueOf(27), BigInteger.valueOf(30), BigInteger.valueOf(32), BigInteger.valueOf(36)};
        for (int i = 1; i <= 20; i++) {
            assertEquals(expected[i-1], Hamming.hamming(i));
        }
    }

    @Test
    void testHamming1691() {
        assertEquals(BigInteger.valueOf(231), Hamming.hamming(1691));
    }

    @Test
    void testHamming1000000() {
        assertEquals(BigInteger.valueOf(510510), Hamming.hamming(1000000));
    }

    @Test
    void testHammingInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> Hamming.hamming(0));
        assertThrows(IllegalArgumentException.class, () -> Hamming.hamming(-1));
    }

    @Test
    void testHammingOne() {
        assertEquals(BigInteger.ONE, Hamming.hamming(1));
    }


}
