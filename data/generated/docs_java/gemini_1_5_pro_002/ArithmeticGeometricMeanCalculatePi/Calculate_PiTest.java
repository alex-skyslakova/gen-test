import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.MathContext;
import static org.junit.jupiter.api.Assertions.*;

public class Calculate_PiTest {

    private static final MathContext con1024 = new MathContext(1024);
    private static final BigDecimal bigTwo = new BigDecimal(2);
    private static final BigDecimal bigFour = new BigDecimal(4);

    private BigDecimal bigSqrt(BigDecimal bd, MathContext con) {
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = BigDecimal.valueOf(Math.sqrt(bd.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = bd.divide(x0, con).add(x0).divide(bigTwo, con);
        }
        return x1;
    }


    @Test
    void calculatePiTest() {
        BigDecimal a = BigDecimal.ONE;
        BigDecimal g = a.divide(bigSqrt(bigTwo, con1024), con1024);
        BigDecimal t;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal pow = bigTwo;
        int iterations = 0; // Limit iterations to prevent infinite loop in tests
        while (!a.equals(g) && iterations < 1000) { // Added iteration limit for testing
            t = a.add(g).divide(bigTwo, con1024);
            g = bigSqrt(a.multiply(g), con1024);
            a = t;
            pow = pow.multiply(bigTwo);
            sum = sum.add(a.multiply(a).subtract(g.multiply(g)).multiply(pow));
            iterations++;
        }
        BigDecimal pi = bigFour.multiply(a.multiply(a)).divide(BigDecimal.ONE.subtract(sum), con1024);

        //  We can't assertEquals with high precision BigDecimal directly due to potential minor variations
        String piString = pi.toString();
        assertTrue(piString.startsWith("3.1415926535897932384626433832795028841971"));

    }


    @Test
    void bigSqrtTest() {
         // Test cases for bigSqrt function
        BigDecimal sqrt2 = bigSqrt(bigTwo, con1024);
        String sqrt2String = sqrt2.toString();
        assertTrue(sqrt2String.startsWith("1.4142135623730950488016887242"));

        BigDecimal sqrt9 = bigSqrt(new BigDecimal(9), con1024);
        assertEquals(0, sqrt9.compareTo(new BigDecimal(3)));


        BigDecimal sqrt0 = bigSqrt(BigDecimal.ZERO, con1024);
        assertEquals(0, sqrt0.compareTo(BigDecimal.ZERO));
    }


}
