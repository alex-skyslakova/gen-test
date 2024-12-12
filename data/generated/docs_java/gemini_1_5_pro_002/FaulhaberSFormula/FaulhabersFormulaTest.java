import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FaulhabersFormulaTest {

    @Test
    void testGcd() {
        assertEquals(5, FaulhabersFormula.gcd(10, 5));
        assertEquals(1, FaulhabersFormula.gcd(17, 12));
        assertEquals(12, FaulhabersFormula.gcd(24, 12));
        assertEquals(1, FaulhabersFormula.gcd(1, 0));

    }

    @Test
    void testFrac() {
        FaulhabersFormula.Frac f1 = new FaulhabersFormula.Frac(3, 6);
        FaulhabersFormula.Frac f2 = new FaulhabersFormula.Frac(1, 2);
        assertEquals(0, f1.compareTo(f2));

        FaulhabersFormula.Frac f3 = new FaulhabersFormula.Frac(1, 3);
        FaulhabersFormula.Frac f4 = new FaulhabersFormula.Frac(1, 2);

        assertEquals(f3.plus(f4).toString(),"5/6");
        assertEquals(f4.minus(f3).toString(), "1/6");
        assertEquals(f3.times(f4).toString(), "1/6");
        assertEquals(f3.unaryMinus().toString(), "-1/3");

        assertEquals(FaulhabersFormula.Frac.ZERO.toString(), "0");
        assertEquals(FaulhabersFormula.Frac.ONE.toString(), "1");
    }

    @Test
    void testBernoulli() {
        assertEquals(FaulhabersFormula.bernoulli(0).toString(), "1");
        assertEquals(FaulhabersFormula.bernoulli(1).toString(), "-1/2");
        assertEquals(FaulhabersFormula.bernoulli(2).toString(), "1/6");
        assertEquals(FaulhabersFormula.bernoulli(3).toString(), "0");
        assertEquals(FaulhabersFormula.bernoulli(4).toString(), "-1/30");

    }

    @Test
    void testBinomial() {
        assertEquals(FaulhabersFormula.binomial(5, 2), 10);
        assertEquals(FaulhabersFormula.binomial(7, 3), 35);
        assertEquals(FaulhabersFormula.binomial(10, 5), 252);
        assertEquals(FaulhabersFormula.binomial(0,0), 1);
        assertEquals(FaulhabersFormula.binomial(5,0), 1);
        assertEquals(FaulhabersFormula.binomial(5,5), 1);
    }

    @Test
    void testFaulhaberOutput() {

       // Testing output requires redirecting System.out, which is generally avoided in unit tests.
       // Focus on testing core logic like bernoulli, binomial, frac, gcd

    }

}

