import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EgyptianFractionsTest {

    @Test
    void test43_48() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(43, 48);
        List<EgyptianFractions.Frac> egyptianFracs = frac.toEgyptian();
        assertEquals(3, egyptianFracs.size());
        assertEquals(new EgyptianFractions.Frac(1, 2), egyptianFracs.get(0));
        assertEquals(new EgyptianFractions.Frac(1, 3), egyptianFracs.get(1));
        assertEquals(new EgyptianFractions.Frac(1, 16), egyptianFracs.get(2));

    }

    @Test
    void test5_121() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(5, 121);
        List<EgyptianFractions.Frac> egyptianFracs = frac.toEgyptian();
        assertEquals(3, egyptianFracs.size());
        assertEquals(new EgyptianFractions.Frac(1, 25), egyptianFracs.get(0));
        assertEquals(new EgyptianFractions.Frac(1, 757), egyptianFracs.get(1));
        assertEquals(new EgyptianFractions.Frac(1, 18925), egyptianFracs.get(2));
    }

    @Test
    void test2014_59() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(2014, 59);
        List<EgyptianFractions.Frac> egyptianFracs = frac.toEgyptian();
        assertEquals(4, egyptianFracs.size());
        assertEquals(new EgyptianFractions.Frac(34, 1), egyptianFractions.get(0));
        assertEquals(new EgyptianFractions.Frac(1, 2), egyptianFracs.get(1));
        assertEquals(new EgyptianFractions.Frac(1, 9), egyptianFracs.get(2));
        assertEquals(new EgyptianFractions.Frac(1, 177), egyptianFracs.get(3));

    }


    @Test
    void testZeroNumerator() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(0, 5);
        List<EgyptianFractions.Frac> egyptianFracs = frac.toEgyptian();
        assertEquals(1, egyptianFracs.size());
        assertEquals(new EgyptianFractions.Frac(0, 1), egyptianFracs.get(0));
    }

    @Test
    void testNegativeFraction() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(-3, 4);
        List<EgyptianFractions.Frac> egyptianFracs = frac.toEgyptian();
        assertEquals(1, egyptianFracs.size());
        assertEquals(new EgyptianFractions.Frac(-3,4), egyptianFracs.get(0));

        frac = new EgyptianFractions.Frac(3, -4);
        egyptianFracs = frac.toEgyptian();
        assertEquals(1, egyptianFracs.size());
        assertEquals(new EgyptianFractions.Frac(-3,4), egyptianFracs.get(0));
    }

    @Test
    void testImproperFraction2_1() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(2, 1);
        List<EgyptianFractions.Frac> egyptianFracs = frac.toEgyptian();
        assertEquals(1, egyptianFracs.size());
        assertEquals(new EgyptianFractions.Frac(2, 1), egyptianFracs.get(0));
    }

    @Test
    void testLargeNumbers() {
        EgyptianFractions.Frac frac = new EgyptianFractions.Frac(BigInteger.valueOf(36), BigInteger.valueOf(17));
        List<EgyptianFractions.Frac> egyptianFracs = frac.toEgyptian();
        assertEquals(3, egyptianFracs.size());
        assertEquals("2", egyptianFracs.get(0).toString());
        assertEquals("1/9", egyptianFracs.get(1).toString());
        assertEquals("1/153", egyptianFracs.get(2).toString());

    }
}
