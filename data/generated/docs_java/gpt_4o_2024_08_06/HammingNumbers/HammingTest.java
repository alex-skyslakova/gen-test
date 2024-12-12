import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;

public class HammingTest {

    @Test(expected = IllegalArgumentException.class)
    public void testHammingWithZero() {
        Hamming.hamming(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHammingWithNegative() {
        Hamming.hamming(-1);
    }

    @Test
    public void testFirstTwentyHammingNumbers() {
        BigInteger[] expected = {
            BigInteger.valueOf(1), BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(4),
            BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(8), BigInteger.valueOf(9),
            BigInteger.valueOf(10), BigInteger.valueOf(12), BigInteger.valueOf(15), BigInteger.valueOf(16),
            BigInteger.valueOf(18), BigInteger.valueOf(20), BigInteger.valueOf(24), BigInteger.valueOf(25),
            BigInteger.valueOf(27), BigInteger.valueOf(30), BigInteger.valueOf(32), BigInteger.valueOf(36)
        };
        for (int i = 1; i <= 20; i++) {
            assertEquals(expected[i - 1], Hamming.hamming(i));
        }
    }

    @Test
    public void test1691stHammingNumber() {
        BigInteger expected = BigInteger.valueOf(2125764000);
        assertEquals(expected, Hamming.hamming(1691));
    }

    @Test
    public void testOneMillionthHammingNumber() {
        // This test might take a while due to the large computation
        BigInteger expected = new BigInteger("519312780448000");
        assertEquals(expected, Hamming.hamming(1000000));
    }
}
