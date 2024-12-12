import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import static org.junit.jupiter.api.Assertions.*;

public class HickersonTest {

    final static String LN2 = "0.693147180559945309417232121458";

    @Test
    void testAlmostInteger_1to17() {
        assertTrue(almostInteger(1));
        assertTrue(almostInteger(2));
        assertTrue(almostInteger(3));
        assertTrue(almostInteger(4));
        assertTrue(almostInteger(5));
        assertTrue(almostInteger(6));
        assertTrue(almostInteger(7));
        assertTrue(almostInteger(8));
        assertTrue(almostInteger(9));
        assertTrue(almostInteger(10));
        assertTrue(almostInteger(11));
        assertTrue(almostInteger(12));
        assertTrue(almostInteger(13));
        assertTrue(almostInteger(14));
        assertTrue(almostInteger(15));
        assertTrue(almostInteger(16));
        assertTrue(almostInteger(17));

    }


    @Test
    void testAlmostInteger_18() {
        assertFalse(almostInteger(18));
    }


    boolean almostInteger(int n) {
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
}
