import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EgyptianFractionsTest {

    @Test
    public void testGCD() {
        assertEquals(BigInteger.valueOf(5), EgyptianFractions.gcd(BigInteger.valueOf(10), BigInteger.valueOf(15)));
        assertEquals(BigInteger.valueOf(1), EgyptianFractions.gcd(BigInteger.valueOf(7), BigInteger.valueOf(13)));
        assertEquals(BigInteger.valueOf(12), EgyptianFractions.gcd(BigInteger.valueOf(144), BigInteger.valueOf(60)));
    }

    @Test
    public void testFracConstructor() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(43, 48);
        assertEquals(BigInteger.valueOf(43), frac.num);
        assertEquals(BigInteger.valueOf(48), frac.denom);

        frac = new EgyptianFractions.Frac(-43, 48);
        assertEquals(BigInteger.valueOf(-43), frac.num);
        assertEquals(BigInteger.valueOf(48), frac.denom);

        frac = new EgyptianFractions.Frac(43, -48);
        assertEquals(BigInteger.valueOf(-43), frac.num);
        assertEquals(BigInteger.valueOf(48), frac.denom);

        frac = new EgyptianFractions.Frac(-43, -48);
        assertEquals(BigInteger.valueOf(43), frac.num);
        assertEquals(BigInteger.valueOf(48), frac.denom);

        assertThrows(IllegalArgumentException.class, () -> new EgyptianFractions.Frac(43, 0));
    }

    @Test
    public void testFracOperations() {
        EgyptianFractions.Frac frac1 = new EgyptianFractions.Frac(1, 2);
        EgyptianFractions.Frac frac2 = new EgyptianFractions.Frac(1, 3);

        EgyptianFractions.Frac sum = frac1.plus(frac2);
        assertEquals(BigInteger.valueOf(5), sum.num);
        assertEquals(BigInteger.valueOf(6), sum.denom);

        EgyptianFractions.Frac diff = frac1.minus(frac2);
        assertEquals(BigInteger.valueOf(1), diff.num);
        assertEquals(BigInteger.valueOf(6), diff.denom);

        EgyptianFractions.Frac neg = frac1.unaryMinus();
        assertEquals(BigInteger.valueOf(-1), neg.num);
        assertEquals(BigInteger.valueOf(2), neg.denom);
    }

    @Test
    public void testFracComparison() {
        EgyptianFractions.Frac frac1 = new EgyptianFractions.Frac(1, 2);
        EgyptianFractions.Frac frac2 = new EgyptianFractions.Frac(1, 3);
        EgyptianFractions.Frac frac3 = new EgyptianFractions.Frac(1, 2);

        assertTrue(frac1.compareTo(frac2) > 0);
        assertTrue(frac2.compareTo(frac1) < 0);
        assertTrue(frac1.compareTo(frac3) == 0);
        assertTrue(frac1.equals(frac3));
        assertFalse(frac1.equals(frac2));
    }

    @Test
    public void testToEgyptian() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(43, 48);
        List<EgyptianFractions.Frac> egyptian = frac.toEgyptian();
        assertEquals(3, egyptian.size());
        assertEquals(new EgyptianFractions.Frac(1, 2), egyptian.get(0));
        assertEquals(new EgyptianFractions.Frac(1, 3), egyptian.get(1));
        assertEquals(new EgyptianFractions.Frac(1, 16), egyptian.get(2));

        frac = new EgyptianFractions.Frac(2014, 59);
        egyptian = frac.toEgyptian();
        assertEquals(4, egyptian.size());
        assertEquals(new EgyptianFractions.Frac(34, 1), egyptian.get(0));
        assertEquals(new EgyptianFractions.Frac(1, 8), egyptian.get(1));
        assertEquals(new EgyptianFractions.Frac(1, 95), egyptian.get(2));
        assertEquals(new EgyptianFractions.Frac(1, 14033), egyptian.get(3));
    }

    @Test
    public void testMain() {
        // This test is more of an integration test to ensure the main method runs without exceptions
        assertDoesNotThrow(() -> EgyptianFractions.main(new String[]{}));
    }
}
