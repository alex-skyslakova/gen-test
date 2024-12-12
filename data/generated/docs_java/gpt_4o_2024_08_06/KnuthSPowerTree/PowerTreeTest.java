import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class PowerTreeTest {

    @Test
    public void testPowerOfTwo() {
        for (int n = 0; n <= 17; ++n) {
            BigDecimal expected = BigDecimal.valueOf(Math.pow(2, n));
            BigDecimal result = PowerTree.treePow(2.0, n);
            assertEquals(expected, result, "2^" + n + " should be " + expected);
        }
    }

    @Test
    public void testPowerOfThree() {
        int n = 191;
        BigDecimal expected = BigDecimal.valueOf(Math.pow(3, n));
        BigDecimal result = PowerTree.treePow(3.0, n);
        assertEquals(expected, result, "3^" + n + " should be " + expected);
    }

    @Test
    public void testPowerOfOnePointOne() {
        int n = 81;
        BigDecimal expected = BigDecimal.valueOf(Math.pow(1.1, n));
        BigDecimal result = PowerTree.treePow(1.1, n);
        assertEquals(0, expected.compareTo(result), "1.1^" + n + " should be approximately " + expected);
    }

    @Test
    public void testZeroPower() {
        double base = 5.0;
        int n = 0;
        BigDecimal expected = BigDecimal.ONE;
        BigDecimal result = PowerTree.treePow(base, n);
        assertEquals(expected, result, base + "^0 should be 1");
    }

    @Test
    public void testNegativePower() {
        double base = 2.0;
        int n = -3;
        BigDecimal expected = BigDecimal.valueOf(Math.pow(base, n));
        BigDecimal result = PowerTree.treePow(base, n);
        assertEquals(0, expected.compareTo(result), base + "^" + n + " should be approximately " + expected);
    }
}
