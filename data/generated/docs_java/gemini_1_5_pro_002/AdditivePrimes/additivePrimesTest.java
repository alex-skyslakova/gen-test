import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class additivePrimesTest {

    @Test
    void isPrimeTest() {
        assertTrue(additivePrimes.isPrime(2));
        assertTrue(additivePrimes.isPrime(3));
        assertTrue(additivePrimes.isPrime(5));
        assertTrue(additivePrimes.isPrime(7));
        assertTrue(additivePrimes.isPrime(11));
        assertTrue(additivePrimes.isPrime(23));
        assertFalse(additivePrimes.isPrime(1));
        assertFalse(additivePrimes.isPrime(4));
        assertFalse(additivePrimes.isPrime(6));
        assertFalse(additivePrimes.isPrime(8));
        assertFalse(additivePrimes.isPrime(9));
        assertFalse(additivePrimes.isPrime(10));
        assertFalse(additivePrimes.isPrime(15));
    }

    @Test
    void digitSumTest() {
        assertEquals(0, additivePrimes.digitSum(0));
        assertEquals(1, additivePrimes.digitSum(1));
        assertEquals(5, additivePrimes.digitSum(5));
        assertEquals(6, additivePrimes.digitSum(15));
        assertEquals(6, additivePrimes.digitSum(6));
        assertEquals(1, additivePrimes.digitSum(10));
        assertEquals(3, additivePrimes.digitSum(12));
        assertEquals(10, additivePrimes.digitSum(127));
        assertEquals(10, additivePrimes.digitSum(1000));

    }


    @Test
    void additivePrimesLessThan500Test() {
        //Since the main method prints to the console, it's difficult to directly test it.
        //Instead, we test individual additive primes and non-additive primes below 500.

        // Testing some known additive primes
        assertTrue(additivePrimes.isPrime(2) && additivePrimes.isPrime(additivePrimes.digitSum(2)));
        assertTrue(additivePrimes.isPrime(3) && additivePrimes.isPrime(additivePrimes.digitSum(3)));
        assertTrue(additivePrimes.isPrime(5) && additivePrimes.isPrime(additivePrimes.digitSum(5)));
        assertTrue(additivePrimes.isPrime(7) && additivePrimes.isPrime(additivePrimes.digitSum(7)));
        assertTrue(additivePrimes.isPrime(23) && additivePrimes.isPrime(additivePrimes.digitSum(23)));
        assertTrue(additivePrimes.isPrime(29) && additivePrimes.isPrime(additivePrimes.digitSum(29)));

        // Testing some non-additive primes 
        assertFalse(additivePrimes.isPrime(4) || additivePrimes.isPrime(additivePrimes.digitSum(4)));
        assertFalse(additivePrimes.isPrime(9) || additivePrimes.isPrime(additivePrimes.digitSum(9)));
        assertFalse(additivePrimes.isPrime(15) || additivePrimes.isPrime(additivePrimes.digitSum(15)));
        assertFalse(additivePrimes.isPrime(85) && additivePrimes.isPrime(additivePrimes.digitSum(85))); //85 is not prime, but sum is
        assertFalse(additivePrimes.isPrime(169) && additivePrimes.isPrime(additivePrimes.digitSum(169))); //169 is not prime but sum is


    }
}
