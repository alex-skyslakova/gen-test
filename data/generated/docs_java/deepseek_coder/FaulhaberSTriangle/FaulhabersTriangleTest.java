import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class FaulhabersTriangleTest {

    @Test
    public void testGcd() {
        assertEquals(6, FaulhabersTriangle.gcd(54, 24));
        assertEquals(1, FaulhabersTriangle.gcd(101, 103));
        assertEquals(12, FaulhabersTriangle.gcd(48, 180));
    }

    @Test
    public void testFracOperations() {
        FaulhabersTriangle.Frac frac1 = new FaulhabersTriangle.Frac(1, 2);
        FaulhabersTriangle.Frac frac2 = new FaulhabersTriangle.Frac(1, 3);

        FaulhabersTriangle.Frac sum = frac1.plus(frac2);
        assertEquals(new FaulhabersTriangle.Frac(5, 6), sum);

        FaulhabersTriangle.Frac diff = frac1.minus(frac2);
        assertEquals(new FaulhabersTriangle.Frac(1, 6), diff);

        FaulhabersTriangle.Frac product = frac1.times(frac2);
        assertEquals(new FaulhabersTriangle.Frac(1, 6), product);

        assertEquals(0, frac1.compareTo(frac1));
        assertTrue(frac1.compareTo(frac2) > 0);
        assertTrue(frac2.compareTo(frac1) < 0);
    }

    @Test
    public void testBernoulli() {
        assertEquals(new FaulhabersTriangle.Frac(1, 1), FaulhabersTriangle.bernoulli(0));
        assertEquals(new FaulhabersTriangle.Frac(-1, 2), FaulhabersTriangle.bernoulli(1));
        assertEquals(new FaulhabersTriangle.Frac(1, 6), FaulhabersTriangle.bernoulli(2));
        assertEquals(new FaulhabersTriangle.Frac(0, 1), FaulhabersTriangle.bernoulli(3));
        assertEquals(new FaulhabersTriangle.Frac(-1, 30), FaulhabersTriangle.bernoulli(4));
    }

    @Test
    public void testBinomial() {
        assertEquals(1, FaulhabersTriangle.binomial(0, 0));
        assertEquals(1, FaulhabersTriangle.binomial(1, 0));
        assertEquals(1, FaulhabersTriangle.binomial(1, 1));
        assertEquals(10, FaulhabersTriangle.binomial(5, 2));
        assertEquals(252, FaulhabersTriangle.binomial(10, 5));
    }

    @Test
    public void testFaulhaberTriangle() {
        FaulhabersTriangle.Frac[] row0 = FaulhabersTriangle.faulhaberTriangle(0);
        assertArrayEquals(new FaulhabersTriangle.Frac[]{new FaulhabersTriangle.Frac(1, 1)}, row0);

        FaulhabersTriangle.Frac[] row1 = FaulhabersTriangle.faulhaberTriangle(1);
        assertArrayEquals(new FaulhabersTriangle.Frac[]{new FaulhabersTriangle.Frac(1, 2), new FaulhabersTriangle.Frac(1, 2)}, row1);

        FaulhabersTriangle.Frac[] row2 = FaulhabersTriangle.faulhaberTriangle(2);
        assertArrayEquals(new FaulhabersTriangle.Frac[]{new FaulhabersTriangle.Frac(1, 6), new FaulhabersTriangle.Frac(1, 2), new FaulhabersTriangle.Frac(1, 3)}, row2);

        FaulhabersTriangle.Frac[] row3 = FaulhabersTriangle.faulhaberTriangle(3);
        assertArrayEquals(new FaulhabersTriangle.Frac[]{new FaulhabersTriangle.Frac(0, 1), new FaulhabersTriangle.Frac(1, 4), new FaulhabersTriangle.Frac(1, 2), new FaulhabersTriangle.Frac(1, 4)}, row3);
    }

    @Test
    public void testMainMethod() {
        // Capture the output of the main method
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        FaulhabersTriangle.main(new String[]{});

        String expectedOutput = "    1  \n" +
                                "  1/2    1/2  \n" +
                                "  1/6    1/2    1/3  \n" +
                                "    0    1/4    1/2    1/4  \n" +
                                " -1/30      0    1/3    1/2    1/5  \n" +
                                "    0  -1/12    1/4    1/2    1/6  \n" +
                                " 1/42      0   -1/6    1/4    1/2    1/7  \n" +
                                "    0   1/12   -1/8    1/4    1/2    1/8  \n" +
                                " -1/30      0   1/10   -1/4    1/4    1/2    1/9  \n" +
                                "    0  -3/20      0   1/12   -1/4    1/4    1/2    1/10  \n" +
                                "\n" +
                                "56056972216555580122144510223848058447";

        assertEquals(expectedOutput, outContent.toString().trim());
    }
}
