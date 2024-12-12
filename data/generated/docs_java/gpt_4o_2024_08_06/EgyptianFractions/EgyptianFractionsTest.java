import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EgyptianFractionsTest {

    @Test
    void testProperFractionConversion() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(43, 48);
        List<EgyptianFractions.Frac> egyptian = frac.toEgyptian();
        assertEquals("[1/2, 1/3, 1/16]", egyptian.toString());
    }

    @Test
    void testImproperFractionConversion() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(2014, 59);
        List<EgyptianFractions.Frac> egyptian = frac.toEgyptian();
        assertEquals("[34, 1/2, 1/3, 1/11, 1/231, 1/644874]", egyptian.toString());
    }

    @Test
    void testFractionWithLargeDenominator() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(5, 121);
        List<EgyptianFractions.Frac> egyptian = frac.toEgyptian();
        assertEquals("[1/25, 1/757, 1/763309]", egyptian.toString());
    }

    @Test
    void testFractionWithLargestNumberOfTerms() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(8, 15);
        List<EgyptianFractions.Frac> egyptian = frac.toEgyptian();
        assertEquals("[1/2, 1/4, 1/60]", egyptian.toString());
    }

    @Test
    void testFractionEquality() {
        EgyptianFractions.Frac frac1 = new EgyptianFractions.Frac(1, 2);
        EgyptianFractions.Frac frac2 = new EgyptianFractions.Frac(2, 4);
        assertEquals(frac1, frac2);
    }

    @Test
    void testFractionAddition() {
        EgyptianFractions.Frac frac1 = new EgyptianFractions.Frac(1, 3);
        EgyptianFractions.Frac frac2 = new EgyptianFractions.Frac(1, 6);
        EgyptianFractions.Frac result = frac1.plus(frac2);
        assertEquals(new EgyptianFractions.Frac(1, 2), result);
    }

    @Test
    void testFractionSubtraction() {
        EgyptianFractions.Frac frac1 = new EgyptianFractions.Frac(1, 2);
        EgyptianFractions.Frac frac2 = new EgyptianFractions.Frac(1, 3);
        EgyptianFractions.Frac result = frac1.minus(frac2);
        assertEquals(new EgyptianFractions.Frac(1, 6), result);
    }

    @Test
    void testFractionToString() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(3, 4);
        assertEquals("3/4", frac.toString());
    }

    @Test
    void testFractionToBigDecimal() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(1, 3);
        assertEquals(new BigDecimal("0.3333333333333333"), frac.toBigDecimal());
    }

    @Test
    void testZeroNumerator() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(0, 5);
        List<EgyptianFractions.Frac> egyptian = frac.toEgyptian();
        assertEquals("[0]", egyptian.toString());
    }

    @Test
    void testNegativeDenominator() {
        assertThrows(IllegalArgumentException.class, () -> {
            new EgyptianFractions.Frac(1, 0);
        });
    }
}
