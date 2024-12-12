import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import static org.junit.jupiter.api.Assertions.*;

public class HickersonTest {

    final static String LN2 = "0.693147180559945309417232121458";

    @Test
    public void testAlmostIntegerForN1() {
        assertTrue(almostInteger(1));
    }

    @Test
    public void testAlmostIntegerForN2() {
        assertTrue(almostInteger(2));
    }

    @Test
    public void testAlmostIntegerForN3() {
        assertTrue(almostInteger(3));
    }

    @Test
    public void testAlmostIntegerForN4() {
        assertTrue(almostInteger(4));
    }

    @Test
    public void testAlmostIntegerForN5() {
        assertTrue(almostInteger(5));
    }

    @Test
    public void testAlmostIntegerForN6() {
        assertTrue(almostInteger(6));
    }

    @Test
    public void testAlmostIntegerForN7() {
        assertTrue(almostInteger(7));
    }

    @Test
    public void testAlmostIntegerForN8() {
        assertTrue(almostInteger(8));
    }

    @Test
    public void testAlmostIntegerForN9() {
        assertTrue(almostInteger(9));
    }

    @Test
    public void testAlmostIntegerForN10() {
        assertTrue(almostInteger(10));
    }

    @Test
    public void testAlmostIntegerForN11() {
        assertTrue(almostInteger(11));
    }

    @Test
    public void testAlmostIntegerForN12() {
        assertTrue(almostInteger(12));
    }

    @Test
    public void testAlmostIntegerForN13() {
        assertTrue(almostInteger(13));
    }

    @Test
    public void testAlmostIntegerForN14() {
        assertTrue(almostInteger(14));
    }

    @Test
    public void testAlmostIntegerForN15() {
        assertTrue(almostInteger(15));
    }

    @Test
    public void testAlmostIntegerForN16() {
        assertTrue(almostInteger(16));
    }

    @Test
    public void testAlmostIntegerForN17() {
        assertTrue(almostInteger(17));
    }

    @Test
    public void testAlmostIntegerForN18() {
        assertFalse(almostInteger(18));
    }

    @Test
    public void testAlmostIntegerForN0() {
        assertFalse(almostInteger(0));
    }

    @Test
    public void testAlmostIntegerForNegativeN() {
        assertFalse(almostInteger(-1));
    }

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
}
