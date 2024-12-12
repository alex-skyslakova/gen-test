import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FaulhabersFormulaTest {

    @Test
    void testGcd() {
        assertEquals(1, FaulhabersFormula.gcd(1, 0));
        assertEquals(5, FaulhabersFormula.gcd(10, 5));
        assertEquals(6, FaulhabersFormula.gcd(54, 24));
        assertEquals(1, FaulhabersFormula.gcd(17, 13));
    }

    @Test
    void testFracCreation() {
        FaulhabersFormula.Frac frac = new FaulhabersFormula.Frac(3, 4);
        assertEquals("3/4", frac.toString());

        FaulhabersFormula.Frac fracNegative = new FaulhabersFormula.Frac(-3, 4);
        assertEquals("-3/4", fracNegative.toString());

        FaulhabersFormula.Frac fracZero = new FaulhabersFormula.Frac(0, 4);
        assertEquals("0", fracZero.toString());

        FaulhabersFormula.Frac fracReduced = new FaulhabersFormula.Frac(6, 8);
        assertEquals("3/4", fracReduced.toString());
    }

    @Test
    void testFracOperations() {
        FaulhabersFormula.Frac frac1 = new FaulhabersFormula.Frac(1, 2);
        FaulhabersFormula.Frac frac2 = new FaulhabersFormula.Frac(1, 3);

        assertEquals("5/6", frac1.plus(frac2).toString());
        assertEquals("1/6", frac1.minus(frac2).toString());
        assertEquals("1/6", frac1.times(frac2).toString());
    }

    @Test
    void testFracComparison() {
        FaulhabersFormula.Frac frac1 = new FaulhabersFormula.Frac(1, 2);
        FaulhabersFormula.Frac frac2 = new FaulhabersFormula.Frac(2, 4);

        assertEquals(0, frac1.compareTo(frac2));
        assertTrue(frac1.equals(frac2));

        FaulhabersFormula.Frac frac3 = new FaulhabersFormula.Frac(3, 4);
        assertTrue(frac1.compareTo(frac3) < 0);
        assertTrue(frac3.compareTo(frac1) > 0);
    }

    @Test
    void testBernoulliNumbers() {
        assertEquals("1", FaulhabersFormula.bernoulli(0).toString());
        assertEquals("-1/2", FaulhabersFormula.bernoulli(1).toString());
        assertEquals("1/6", FaulhabersFormula.bernoulli(2).toString());
        assertEquals("0", FaulhabersFormula.bernoulli(3).toString());
        assertEquals("-1/30", FaulhabersFormula.bernoulli(4).toString());
    }

    @Test
    void testBinomialCoefficients() {
        assertEquals(1, FaulhabersFormula.binomial(5, 0));
        assertEquals(5, FaulhabersFormula.binomial(5, 1));
        assertEquals(10, FaulhabersFormula.binomial(5, 2));
        assertEquals(10, FaulhabersFormula.binomial(5, 3));
        assertEquals(5, FaulhabersFormula.binomial(5, 4));
        assertEquals(1, FaulhabersFormula.binomial(5, 5));
    }

    @Test
    void testFaulhaber() {
        // This test will check the output of the faulhaber method for p = 0 to 9
        // Since the method prints to the console, we will redirect the output to a stream
        // and compare it with the expected output.
        String expectedOutput =
                "0 : n\n" +
                "1 : 1/2n^2 + 1/2n\n" +
                "2 : 1/3n^3 + 1/2n^2 + 1/6n\n" +
                "3 : 1/4n^4 + 1/2n^3 + 1/4n^2\n" +
                "4 : 1/5n^5 + 1/2n^4 + 1/3n^3 - 1/30n\n" +
                "5 : 1/6n^6 + 1/2n^5 + 5/12n^4 - 1/12n^2\n" +
                "6 : 1/7n^7 + 1/2n^6 + 1/2n^5 - 1/6n^3 + 1/42n\n" +
                "7 : 1/8n^8 + 1/2n^7 + 7/12n^6 - 7/24n^4 + 1/12n^2\n" +
                "8 : 1/9n^9 + 1/2n^8 + 2/3n^7 - 7/15n^5 + 2/9n^3 - 1/30n\n" +
                "9 : 1/10n^10 + 1/2n^9 + 3/4n^8 - 7/12n^6 + 3/4n^4 - 3/20n^2\n";

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        for (int i = 0; i <= 9; ++i) {
            FaulhabersFormula.faulhaber(i);
        }

        assertEquals(expectedOutput, outContent.toString());
    }
}
