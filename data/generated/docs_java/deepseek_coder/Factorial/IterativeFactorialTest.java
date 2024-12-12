import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class IterativeFactorialTest {

    @Test
    public void testFactorialOfZero() {
        IterativeFactorial iterativeFactorial = new IterativeFactorial();
        BigInteger result = iterativeFactorial.factorial(BigInteger.ZERO);
        assertEquals(BigInteger.ONE, result);
    }

    @Test
    public void testFactorialOfPositiveNumber() {
        IterativeFactorial iterativeFactorial = new IterativeFactorial();
        BigInteger result = iterativeFactorial.factorial(BigInteger.valueOf(5));
        assertEquals(BigInteger.valueOf(120), result);
    }

    @Test
    public void testFactorialOfLargeNumber() {
        IterativeFactorial iterativeFactorial = new IterativeFactorial();
        BigInteger result = iterativeFactorial.factorial(BigInteger.valueOf(20));
        assertEquals(new BigInteger("2432902008176640000"), result);
    }

    @Test
    public void testFactorialOfNull() {
        IterativeFactorial iterativeFactorial = new IterativeFactorial();
        assertThrows(IllegalArgumentException.class, () -> {
            iterativeFactorial.factorial(null);
        });
    }

    @Test
    public void testFactorialOfNegativeNumber() {
        IterativeFactorial iterativeFactorial = new IterativeFactorial();
        assertThrows(IllegalArgumentException.class, () -> {
            iterativeFactorial.factorial(BigInteger.valueOf(-5));
        });
    }
}
