import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class BenfordsLawTest {

    @Test
    public void testGenerateFibonacci() {
        BigInteger[] fibonacci = BenfordsLaw.generateFibonacci(10);
        assertArrayEquals(new BigInteger[]{
                BigInteger.ONE, BigInteger.ONE, new BigInteger("2"), new BigInteger("3"), new BigInteger("5"),
                new BigInteger("8"), new BigInteger("13"), new BigInteger("21"), new BigInteger("34"), new BigInteger("55")
        }, fibonacci);
    }

    @Test
    public void testFirstDigitDistribution() {
        BigInteger[] numbers = BenfordsLaw.generateFibonacci(1000);
        int[] firstDigits = new int[10];
        for (BigInteger number : numbers) {
            firstDigits[Integer.valueOf(number.toString().substring(0, 1))]++;
        }

        // Expected distribution based on Benford's Law
        double[] expectedDistribution = new double[10];
        for (int i = 1; i < 10; i++) {
            expectedDistribution[i] = Math.log10(1.0 + 1.0 / i) * 1000;
        }

        // Assert that the actual distribution is close to the expected distribution
        for (int i = 1; i < 10; i++) {
            double actualPercentage = (double) firstDigits[i] / numbers.length * 1000;
            assertEquals(expectedDistribution[i], actualPercentage, 20, "Digit " + i + " distribution is not as expected");
        }
    }

    @Test
    public void testFirstDigitExtraction() {
        BigInteger number = new BigInteger("123456");
        int firstDigit = Integer.valueOf(number.toString().substring(0, 1));
        assertEquals(1, firstDigit);
    }

    @Test
    public void testFirstDigitExtractionWithSingleDigit() {
        BigInteger number = new BigInteger("7");
        int firstDigit = Integer.valueOf(number.toString().substring(0, 1));
        assertEquals(7, firstDigit);
    }

    @Test
    public void testFirstDigitExtractionWithZero() {
        BigInteger number = BigInteger.ZERO;
        assertThrows(NumberFormatException.class, () -> {
            Integer.valueOf(number.toString().substring(0, 1));
        });
    }

    @Test
    public void testFirstDigitExtractionWithNegativeNumber() {
        BigInteger number = new BigInteger("-123456");
        int firstDigit = Integer.valueOf(number.toString().substring(1, 2));
        assertEquals(1, firstDigit);
    }
}
