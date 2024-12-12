import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import static org.junit.jupiter.api.Assertions.*;

class HickersonTest {

    final static String LN2 = "0.693147180559945309417232121458";

    static boolean almostInteger(int n) {
        BigDecimal a = new BigDecimal(LN2);
        a = a.pow(n + 1).multiply(BigDecimal.valueOf(2));

        long f = n;
        while (--n > 1)
            f *= n;

        BigDecimal b = new BigDecimal(f);
        b = b.divide(a, MathContext.DECIMAL128);

        BigInteger c = b.movePointRight(1).toBigInteger().mod(BigInteger.TEN);

        return c.toString().matches("0|9");
    }

    @Test
    void testAlmostInteger() {
        // Test for n from 1 to 17
        for (int n = 1; n <= 17; n++) {
            boolean result = almostInteger(n);
            assertTrue(result, "h(" + n + ") should be an almost integer.");
        }
    }

    @Test
    void testNonAlmostInteger() {
        // Test for n greater than 17 to ensure they are not almost integers
        for (int n = 18; n <= 20; n++) {
            boolean result = almostInteger(n);
            assertFalse(result, "h(" + n + ") should not be an almost integer.");
        }
    }
}
