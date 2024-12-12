import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.MathContext;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatePiTest {

    private static final MathContext con1024 = new MathContext(1024);
    private static final BigDecimal bigTwo = new BigDecimal(2);
    private static final BigDecimal bigFour = new BigDecimal(4);

    @Test
    public void testBigSqrt() {
        BigDecimal input = new BigDecimal(2);
        BigDecimal expected = new BigDecimal(Math.sqrt(2));
        BigDecimal result = Calculate_Pi.bigSqrt(input, con1024);
        assertEquals(expected.setScale(10, BigDecimal.ROUND_HALF_UP), result.setScale(10, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void testMain() {
        // Capture the output of the main method
        BigDecimal pi = Calculate_Pi.main(null);

        // Compare the result with a known value of Pi
        BigDecimal knownPi = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
        assertEquals(knownPi.setScale(10, BigDecimal.ROUND_HALF_UP), pi.setScale(10, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void testAgmConvergence() {
        BigDecimal a = BigDecimal.ONE;
        BigDecimal g = a.divide(Calculate_Pi.bigSqrt(bigTwo, con1024), con1024);
        BigDecimal t;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal pow = bigTwo;
        int iterations = 0;
        while (!Objects.equals(a, g) && iterations < 1000) {
            t = a.add(g).divide(bigTwo, con1024);
            g = Calculate_Pi.bigSqrt(a.multiply(g), con1024);
            a = t;
            pow = pow.multiply(bigTwo);
            sum = sum.add(a.multiply(a).subtract(g.multiply(g)).multiply(pow));
            iterations++;
        }
        // Ensure that the AGM converges within a reasonable number of iterations
        assertTrue(iterations < 1000);
    }

    @Test
    public void testPiCalculation() {
        BigDecimal a = BigDecimal.ONE;
        BigDecimal g = a.divide(Calculate_Pi.bigSqrt(bigTwo, con1024), con1024);
        BigDecimal t;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal pow = bigTwo;
        while (!Objects.equals(a, g)) {
            t = a.add(g).divide(bigTwo, con1024);
            g = Calculate_Pi.bigSqrt(a.multiply(g), con1024);
            a = t;
            pow = pow.multiply(bigTwo);
            sum = sum.add(a.multiply(a).subtract(g.multiply(g)).multiply(pow));
        }
        BigDecimal pi = bigFour.multiply(a.multiply(a)).divide(BigDecimal.ONE.subtract(sum), con1024);

        // Compare the result with a known value of Pi
        BigDecimal knownPi = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
        assertEquals(knownPi.setScale(10, BigDecimal.ROUND_HALF_UP), pi.setScale(10, BigDecimal.ROUND_HALF_UP));
    }
}
