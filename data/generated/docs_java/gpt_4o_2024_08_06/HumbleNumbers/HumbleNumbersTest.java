import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;

public class HumbleNumbersTest {

    @Test
    public void testFirst50HumbleNumbers() {
        BigInteger[] expected = {
            BigInteger.valueOf(1), BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(4),
            BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(7), BigInteger.valueOf(8),
            BigInteger.valueOf(9), BigInteger.valueOf(10), BigInteger.valueOf(12), BigInteger.valueOf(14),
            BigInteger.valueOf(15), BigInteger.valueOf(16), BigInteger.valueOf(18), BigInteger.valueOf(20),
            BigInteger.valueOf(21), BigInteger.valueOf(24), BigInteger.valueOf(25), BigInteger.valueOf(27),
            BigInteger.valueOf(28), BigInteger.valueOf(30), BigInteger.valueOf(32), BigInteger.valueOf(35),
            BigInteger.valueOf(36), BigInteger.valueOf(40), BigInteger.valueOf(42), BigInteger.valueOf(45),
            BigInteger.valueOf(48), BigInteger.valueOf(49), BigInteger.valueOf(50), BigInteger.valueOf(54),
            BigInteger.valueOf(56), BigInteger.valueOf(60), BigInteger.valueOf(63), BigInteger.valueOf(64),
            BigInteger.valueOf(70), BigInteger.valueOf(72), BigInteger.valueOf(75), BigInteger.valueOf(80),
            BigInteger.valueOf(81), BigInteger.valueOf(84), BigInteger.valueOf(90), BigInteger.valueOf(96),
            BigInteger.valueOf(98), BigInteger.valueOf(100), BigInteger.valueOf(105), BigInteger.valueOf(108),
            BigInteger.valueOf(112), BigInteger.valueOf(120)
        };
        assertArrayEquals(expected, HumbleNumbers.humble(50));
    }

    @Test
    public void testHumbleNumbersLengthCount() {
        BigInteger[] humbleNumbers = HumbleNumbers.humble(1000000);
        Map<Integer, Integer> lengthCountMap = new HashMap<>();
        for (BigInteger humbleNumber : humbleNumbers) {
            int len = humbleNumber.toString().length();
            lengthCountMap.merge(len, 1, Integer::sum);
        }

        // Check some known values
        assertEquals(1, lengthCountMap.get(1)); // Only '1' has 1 digit
        assertEquals(4, lengthCountMap.get(2)); // 2, 3, 4, 5 have 2 digits
        assertTrue(lengthCountMap.containsKey(3)); // There are numbers with 3 digits
        assertTrue(lengthCountMap.containsKey(10)); // There are numbers with 10 digits
    }
}
