import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FaulhabersTriangleTest {

    @Test
    void testGcd() {
        assertEquals(5, FaulhabersTriangle.gcd(10, 5));
        assertEquals(1, FaulhabersTriangle.gcd(7, 13));
        assertEquals(12, FaulhabersTriangle.gcd(144, 60));
    }


    @Test
    void testFrac() {
        FaulhabersTriangle.Frac f1 = new FaulhabersTriangle.Frac(3, 6);
        FaulhabersTriangle.Frac f2 = new FaulhabersTriangle.Frac(1, 2);
        assertEquals(0, f1.compareTo(f2));
        assertEquals(f1,f2);

        FaulhabersTriangle.Frac f3 = new FaulhabersTriangle.Frac(1, 3);
        FaulhabersTriangle.Frac sum = f1.plus(f3);
        assertEquals(new FaulhabersTriangle.Frac(5,6), sum);

        FaulhabersTriangle.Frac diff = f1.minus(f3);
        assertEquals(new FaulhabersTriangle.Frac(1,6), diff);
        
        FaulhabersTriangle.Frac prod = f1.times(f3);
        assertEquals(new FaulhabersTriangle.Frac(1,6), prod);

        FaulhabersTriangle.Frac neg = f1.unaryMinus();
        assertEquals(new FaulhabersTriangle.Frac(-1,2), neg);

        assertEquals("1/2", f1.toString());
        assertEquals(0.5, f1.toDouble(), 1e-9);


    }
    @Test
    void testBernoulli() {
        assertEquals(new FaulhabersTriangle.Frac(1, 1), FaulhabersTriangle.bernoulli(0));
        assertEquals(new FaulhabersTriangle.Frac(-1, 2), FaulhabersTriangle.bernoulli(1));
        assertEquals(new FaulhabersTriangle.Frac(1, 6), FaulhabersTriangle.bernoulli(2));
        assertEquals(new FaulhabersTriangle.Frac(0, 1), FaulhabersTriangle.bernoulli(3));
    }


    @Test
    void testBinomial() {
        assertEquals(1, FaulhabersTriangle.binomial(0, 0));
        assertEquals(1, FaulhabersTriangle.binomial(5, 0));
        assertEquals(5, FaulhabersTriangle.binomial(5, 1));
        assertEquals(10, FaulhabersTriangle.binomial(5, 2));
        assertEquals(10, FaulhabersTriangle.binomial(5, 3));
        assertEquals(5, FaulhabersTriangle.binomial(5, 4));
        assertEquals(1, FaulhabersTriangle.binomial(5, 5));

    }

    @Test
    void testFaulhaberTriangle() {
        FaulhabersTriangle.Frac[] row2 = FaulhabersTriangle.faulhaberTriangle(2);
        assertArrayEquals(new FaulhabersTriangle.Frac[]{new FaulhabersTriangle.Frac(1, 6), new FaulhabersTriangle.Frac(1, 2), new FaulhabersTriangle.Frac(1, 3)}, row2);

        FaulhabersTriangle.Frac[] row3 = FaulhabersTriangle.faulhaberTriangle(3);
        assertArrayEquals(new FaulhabersTriangle.Frac[]{new FaulhabersTriangle.Frac(0,1), new FaulhabersTriangle.Frac(1, 4), new FaulhabersTriangle.Frac(1, 2), new FaulhabersTriangle.Frac(1, 4)}, row3);
    }


    @Test
    void testSum17() {
                // Expected Value for sum of k^17 from 1 to 1000
        BigInteger expectedSum = new BigInteger("283198181168246451250776");
        // get coeffs for (k + 1)th row
        int k = 17;
        FaulhabersTriangle.Frac[] cc = FaulhabersTriangle.faulhaberTriangle(k);
        int n = 1000;
        BigDecimal nn = BigDecimal.valueOf(n);
        BigDecimal np = BigDecimal.ONE;
        BigDecimal sum = BigDecimal.ZERO;
        for (FaulhabersTriangle.Frac c : cc) {
            np = np.multiply(nn);
            sum = sum.add(np.multiply(c.toBigDecimal()));
        }
        assertEquals(expectedSum, sum.toBigInteger());

    }
}

