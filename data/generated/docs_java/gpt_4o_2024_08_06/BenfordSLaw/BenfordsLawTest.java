import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BenfordsLawTest {

    private BigInteger[] fibonacciNumbers;

    @BeforeEach
    public void setUp() {
        fibonacciNumbers = BenfordsLaw.generateFibonacci(1000);
    }

    @Test
    public void testFibonacciGeneration() {
        // Test the first few Fibonacci numbers
        assertEquals(BigInteger.ONE, fibonacciNumbers[0]);
        assertEquals(BigInteger.ONE, fibonacciNumbers[1]);
        assertEquals(new BigInteger("2"), fibonacciNumbers[2]);
        assertEquals(new BigInteger("3"), fibonacciNumbers[3]);
        assertEquals(new BigInteger("5"), fibonacciNumbers[4]);
        assertEquals(new BigInteger("8"), fibonacciNumbers[5]);
    }

    @Test
    public void testFibonacciLength() {
        // Test that 1000 Fibonacci numbers are generated
        assertEquals(1000, fibonacciNumbers.length);
    }

    @Test
    public void testFirstDigitDistribution() {
        int[] firstDigits = new int[10];
        for (BigInteger number : fibonacciNumbers) {
            firstDigits[Integer.valueOf(number.toString().substring(0, 1))]++;
        }

        // Check that the distribution of first digits follows Benford's law
        for (int i = 1; i < firstDigits.length; i++) {
            double actualDistribution = (double) firstDigits[i] / fibonacciNumbers.length;
            double expectedDistribution = Math.log10(1.0 + 1.0 / i);

            // Allow a small margin of error due to the finite sample size
            assertTrue(Math.abs(actualDistribution - expectedDistribution) < 0.05,
                    String.format(Locale.ROOT, "Digit %d: actual %.6f, expected %.6f", i, actualDistribution, expectedDistribution));
        }
    }
}
