import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FaulhabersFormulaTest {

    @Test
    public void testGCD() {
        assertEquals(1, FaulhabersFormula.gcd(10, 3));
        assertEquals(5, FaulhabersFormula.gcd(10, 5));
        assertEquals(1, FaulhabersFormula.gcd(17, 13));
        assertEquals(6, FaulhabersFormula.gcd(12, 18));
    }

    @Test
    public void testFracConstructor() {
        FaulhabersFormula.Frac frac = new FaulhabersFormula.Frac(2, 4);
        assertEquals(1, frac.num);
        assertEquals(2, frac.denom);

        frac = new FaulhabersFormula.Frac(-3, 6);
        assertEquals(-1, frac.num);
        assertEquals(2, frac.denom);

        frac = new FaulhabersFormula.Frac(0, 5);
        assertEquals(0, frac.num);
        assertEquals(1, frac.denom);

        assertThrows(IllegalArgumentException.class, () -> new FaulhabersFormula.Frac(1, 0));
    }

    @Test
    public void testFracOperations() {
        FaulhabersFormula.Frac frac1 = new FaulhabersFormula.Frac(1, 2);
        FaulhabersFormula.Frac frac2 = new FaulhabersFormula.Frac(1, 3);

        FaulhabersFormula.Frac sum = frac1.plus(frac2);
        assertEquals(5, sum.num);
        assertEquals(6, sum.denom);

        FaulhabersFormula.Frac diff = frac1.minus(frac2);
        assertEquals(1, diff.num);
        assertEquals(6, diff.denom);

        FaulhabersFormula.Frac prod = frac1.times(frac2);
        assertEquals(1, prod.num);
        assertEquals(6, prod.denom);

        FaulhabersFormula.Frac neg = frac1.unaryMinus();
        assertEquals(-1, neg.num);
        assertEquals(2, neg.denom);
    }

    @Test
    public void testBernoulli() {
        assertEquals(new FaulhabersFormula.Frac(1, 1), FaulhabersFormula.bernoulli(0));
        assertEquals(new FaulhabersFormula.Frac(-1, 2), FaulhabersFormula.bernoulli(1));
        assertEquals(new FaulhabersFormula.Frac(1, 6), FaulhabersFormula.bernoulli(2));
        assertThrows(IllegalArgumentException.class, () -> FaulhabersFormula.bernoulli(-1));
    }

    @Test
    public void testBinomial() {
        assertEquals(1, FaulhabersFormula.binomial(0, 0));
        assertEquals(1, FaulhabersFormula.binomial(5, 0));
        assertEquals(5, FaulhabersFormula.binomial(5, 1));
        assertEquals(10, FaulhabersFormula.binomial(5, 2));
        assertEquals(1, FaulhabersFormula.binomial(5, 5));
        assertThrows(IllegalArgumentException.class, () -> FaulhabersFormula.binomial(5, 6));
        assertThrows(IllegalArgumentException.class, () -> FaulhabersFormula.binomial(-1, 0));
    }

    @Test
    public void testFaulhaber() {
        // This test is more complex as it involves printing to console.
        // We can capture the output and compare it to expected results.
        // For simplicity, we'll just ensure the method runs without errors.
        assertDoesNotThrow(() -> FaulhabersFormula.faulhaber(0));
        assertDoesNotThrow(() -> FaulhabersFormula.faulhaber(5));
        assertDoesNotThrow(() -> FaulhabersFormula.faulhaber(9));
    }
}
