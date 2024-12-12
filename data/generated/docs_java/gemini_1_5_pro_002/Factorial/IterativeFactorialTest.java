import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class IterativeFactorialTest {

    @Test
    void factorialZero() {
        assertEquals(BigInteger.ONE, new IterativeFactorial().factorial(BigInteger.ZERO));
    }

    @Test
    void factorialOne() {
        assertEquals(BigInteger.ONE, new IterativeFactorial().factorial(BigInteger.ONE));
    }

    @Test
    void factorialFive() {
        assertEquals(BigInteger.valueOf(120), new IterativeFactorial().factorial(BigInteger.valueOf(5)));
    }

    @Test
    void factorialTen() {
        assertEquals(BigInteger.valueOf(3628800), new IterativeFactorial().factorial(BigInteger.valueOf(10)));
    }

    @Test
    void factorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> new IterativeFactorial().factorial(BigInteger.valueOf(-1)));
    }

    @Test
    void factorialNull() {
        assertThrows(IllegalArgumentException.class, () -> new IterativeFactorial().factorial(null));
    }
}
