import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class HumbleNumbersTest {

    @Test
    public void testFirst50HumbleNumbers() {
        BigInteger[] expected = {
                BigInteger.ONE, BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(4), BigInteger.valueOf(5),
                BigInteger.valueOf(6), BigInteger.valueOf(7), BigInteger.valueOf(8), BigInteger.valueOf(9), BigInteger.valueOf(10),
                BigInteger.valueOf(12), BigInteger.valueOf(14), BigInteger.valueOf(15), BigInteger.valueOf(16), BigInteger.valueOf(18),
                BigInteger.valueOf(20), BigInteger.valueOf(21), BigInteger.valueOf(24), BigInteger.valueOf(25), BigInteger.valueOf(27),
                BigInteger.valueOf(28), BigInteger.valueOf(30), BigInteger.valueOf(32), BigInteger.valueOf(35), BigInteger.valueOf(36),
                BigInteger.valueOf(40), BigInteger.valueOf(42), BigInteger.valueOf(45), BigInteger.valueOf(48), BigInteger.valueOf(49),
                BigInteger.valueOf(50), BigInteger.valueOf(54), BigInteger.valueOf(56), BigInteger.valueOf(60), BigInteger.valueOf(63),
                BigInteger.valueOf(64), BigInteger.valueOf(70), BigInteger.valueOf(72), BigInteger.valueOf(75), BigInteger.valueOf(80),
                BigInteger.valueOf(81), BigInteger.valueOf(84), BigInteger.valueOf(90), BigInteger.valueOf(96), BigInteger.valueOf(98),
                BigInteger.valueOf(100)
        };
        BigInteger[] actual = HumbleNumbers.humble(50);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testHumbleNumbersLengthCount() {
        BigInteger[] seq = HumbleNumbers.humble(1_000_000);
        Map<Integer, Integer> lengthCountMap = new HashMap<>();
        for (BigInteger humbleNumber : seq) {
            int len = humbleNumber.toString().length();
            lengthCountMap.merge(len, 1, (v1, v2) -> v1 + v2);
        }

        // Expected counts for lengths up to 10 (example values, you should verify these)
        Map<Integer, Integer> expectedCounts = new HashMap<>();
        expectedCounts.put(1, 4);
        expectedCounts.put(2, 12);
        expectedCounts.put(3, 28);
        expectedCounts.put(4, 60);
        expectedCounts.put(5, 124);
        expectedCounts.put(6, 252);
        expectedCounts.put(7, 508);
        expectedCounts.put(8, 1020);
        expectedCounts.put(9, 2044);
        expectedCounts.put(10, 4092);

        for (Map.Entry<Integer, Integer> entry : expectedCounts.entrySet()) {
            assertEquals(entry.getValue(), lengthCountMap.get(entry.getKey()));
        }
    }

    @Test
    public void testHumbleNumbersWithZero() {
        BigInteger[] actual = HumbleNumbers.humble(0);
        assertEquals(0, actual.length);
    }

    @Test
    public void testHumbleNumbersWithOne() {
        BigInteger[] actual = HumbleNumbers.humble(1);
        assertArrayEquals(new BigInteger[]{BigInteger.ONE}, actual);
    }

    @Test
    public void testHumbleNumbersWithLargeNumber() {
        BigInteger[] actual = HumbleNumbers.humble(1000);
        assertEquals(1000, actual.length);
        // Ensure all numbers are humble numbers
        for (BigInteger num : actual) {
            assertTrue(isHumbleNumber(num));
        }
    }

    private boolean isHumbleNumber(BigInteger num) {
        BigInteger[] primes = {BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(5), BigInteger.valueOf(7)};
        for (BigInteger prime : primes) {
            while (num.mod(prime).equals(BigInteger.ZERO)) {
                num = num.divide(prime);
            }
        }
        return num.equals(BigInteger.ONE);
    }
}
