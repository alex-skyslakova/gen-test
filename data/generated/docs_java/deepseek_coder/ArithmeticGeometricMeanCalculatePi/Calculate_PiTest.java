import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.MathContext;
import static org.junit.jupiter.api.Assertions.*;

public class Calculate_PiTest {

    private static final MathContext con1024 = new MathContext(1024);
    private static final BigDecimal bigTwo = new BigDecimal(2);
    private static final BigDecimal bigFour = new BigDecimal(4);

    @Test
    public void testBigSqrt() {
        BigDecimal input = new BigDecimal(2);
        BigDecimal expected = new BigDecimal(Math.sqrt(2));
        BigDecimal result = Calculate_Pi.bigSqrt(input, con1024);
        assertTrue(result.subtract(expected).abs().compareTo(new BigDecimal("0.000000000000001")) < 0);
    }

    @Test
    public void testMainCalculation() {
        BigDecimal a = BigDecimal.ONE;
        BigDecimal g = a.divide(Calculate_Pi.bigSqrt(bigTwo, con1024), con1024);
        BigDecimal t;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal pow = bigTwo;
        while (!a.equals(g)) {
            t = a.add(g).divide(bigTwo, con1024);
            g = Calculate_Pi.bigSqrt(a.multiply(g), con1024);
            a = t;
            pow = pow.multiply(bigTwo);
            sum = sum.add(a.multiply(a).subtract(g.multiply(g)).multiply(pow));
        }
        BigDecimal pi = bigFour.multiply(a.multiply(a)).divide(BigDecimal.ONE.subtract(sum), con1024);
        BigDecimal expectedPi = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
        assertTrue(pi.subtract(expectedPi).abs().compareTo(new BigDecimal("0.000000000000001")) < 0);
    }

    @Test
    public void testBigSqrtWithZero() {
        BigDecimal input = BigDecimal.ZERO;
        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal result = Calculate_Pi.bigSqrt(input, con1024);
        assertEquals(expected, result);
    }

    @Test
    public void testBigSqrtWithOne() {
        BigDecimal input = BigDecimal.ONE;
        BigDecimal expected = BigDecimal.ONE;
        BigDecimal result = Calculate_Pi.bigSqrt(input, con1024);
        assertEquals(expected, result);
    }

    @Test
    public void testBigSqrtWithLargeNumber() {
        BigDecimal input = new BigDecimal("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        BigDecimal expected = new BigDecimal("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000").sqrt(con1024);
        BigDecimal result = Calculate_Pi.bigSqrt(input, con1024);
        assertTrue(result.subtract(expected).abs().compareTo(new BigDecimal("0.000000000000001")) < 0);
    }
}
