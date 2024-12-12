package programas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;

public class IterativeFactorialTest {

    @Test
    public void testFactorialOfZero() {
        IterativeFactorial factorial = new IterativeFactorial();
        assertEquals(BigInteger.ONE, factorial.factorial(BigInteger.ZERO), "Factorial of 0 should be 1");
    }

    @Test
    public void testFactorialOfOne() {
        IterativeFactorial factorial = new IterativeFactorial();
        assertEquals(BigInteger.ONE, factorial.factorial(BigInteger.ONE), "Factorial of 1 should be 1");
    }

    @Test
    public void testFactorialOfPositiveNumber() {
        IterativeFactorial factorial = new IterativeFactorial();
        assertEquals(new BigInteger("120"), factorial.factorial(new BigInteger("5")), "Factorial of 5 should be 120");
    }

    @Test
    public void testFactorialOfLargeNumber() {
        IterativeFactorial factorial = new IterativeFactorial();
        assertEquals(new BigInteger("2432902008176640000"), factorial.factorial(new BigInteger("20")), "Factorial of 20 should be 2432902008176640000");
    }

    @Test
    public void testFactorialOfNegativeNumber() {
        IterativeFactorial factorial = new IterativeFactorial();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factorial.factorial(new BigInteger("-5"));
        });
        assertEquals("Argument must be a non-negative integer", exception.getMessage());
    }

    @Test
    public void testFactorialOfNull() {
        IterativeFactorial factorial = new IterativeFactorial();
        assertThrows(IllegalArgumentException.class, () -> {
            factorial.factorial(null);
        });
    }
}
