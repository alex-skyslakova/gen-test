import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class BenfordsLawTest {

    private BigInteger[] generateFibonacci(int n) {
        BigInteger[] fib = new BigInteger[n];
        fib[0] = BigInteger.ONE;
        fib[1] = BigInteger.ONE;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 2].add(fib[i - 1]);
        }
        return fib;
    }


    @Test
    void testFibonacciDistribution() {
        BigInteger[] numbers = generateFibonacci(1000);
        int[] firstDigits = new int[10];
        for (BigInteger number : numbers) {
            firstDigits[Integer.valueOf(number.toString().substring(0, 1))]++;
        }

        for (int i = 1; i < firstDigits.length; i++) {
            double actualDistribution = (double) firstDigits[i] / numbers.length;
            double expectedDistribution = Math.log10(1.0 + 1.0 / i);
            // Using an assertEquals with a delta for comparing doubles due to potential precision issues.
            assertEquals(expectedDistribution, actualDistribution, 0.01, String.format(Locale.ROOT,"Digit: %d", i)); 
        }
    }


    @Test
    void testEmptyInput() {
        BigInteger[] numbers = generateFibonacci(0);
        int[] firstDigits = new int[10];
        for (BigInteger number : numbers) {
            firstDigits[Integer.valueOf(number.toString().substring(0, 1))]++;
        }
        for (int i = 1; i < firstDigits.length; i++) {
            double actualDistribution = (double) firstDigits[i] / (numbers.length==0? 1 :numbers.length) ; // Avoid division by zero
            double expectedDistribution = Math.log10(1.0 + 1.0 / i);
            assertEquals(0, actualDistribution, 0.001); // Expecting zero distribution for an empty input.
        }
    }

    @Test 
    void testSingleNumberInput(){
        BigInteger[] numbers = generateFibonacci(1);
        int[] firstDigits = new int[10];
        for (BigInteger number : numbers) {
            firstDigits[Integer.valueOf(number.toString().substring(0, 1))]++;
        }
        assertEquals(1, firstDigits[1]); // Expecting the first digit to be 1 in the first Fibonacci number.

        for(int i = 2; i < 10; ++i){
            assertEquals(0, firstDigits[i]); //The rest of the digits should have a count of 0.
        }

    }

}
