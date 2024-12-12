import static org.junit.Assert.*;
import org.junit.Test;

public class AdditivePrimesTest {

    @Test
    public void testIsPrime() {
        // Test prime numbers
        assertTrue(additivePrimes.isPrime(2));
        assertTrue(additivePrimes.isPrime(3));
        assertTrue(additivePrimes.isPrime(5));
        assertTrue(additivePrimes.isPrime(7));
        assertTrue(additivePrimes.isPrime(11));
        assertTrue(additivePrimes.isPrime(13));
        assertTrue(additivePrimes.isPrime(17));
        assertTrue(additivePrimes.isPrime(19));
        assertTrue(additivePrimes.isPrime(23));
        assertTrue(additivePrimes.isPrime(29));

        // Test non-prime numbers
        assertFalse(additivePrimes.isPrime(1));
        assertFalse(additivePrimes.isPrime(4));
        assertFalse(additivePrimes.isPrime(6));
        assertFalse(additivePrimes.isPrime(8));
        assertFalse(additivePrimes.isPrime(9));
        assertFalse(additivePrimes.isPrime(10));
        assertFalse(additivePrimes.isPrime(12));
        assertFalse(additivePrimes.isPrime(14));
        assertFalse(additivePrimes.isPrime(15));
        assertFalse(additivePrimes.isPrime(16));
    }

    @Test
    public void testDigitSum() {
        assertEquals(1, additivePrimes.digitSum(1));
        assertEquals(2, additivePrimes.digitSum(2));
        assertEquals(3, additivePrimes.digitSum(12));
        assertEquals(4, additivePrimes.digitSum(13));
        assertEquals(5, additivePrimes.digitSum(14));
        assertEquals(6, additivePrimes.digitSum(15));
        assertEquals(7, additivePrimes.digitSum(16));
        assertEquals(8, additivePrimes.digitSum(17));
        assertEquals(9, additivePrimes.digitSum(18));
        assertEquals(10, additivePrimes.digitSum(19));
        assertEquals(11, additivePrimes.digitSum(29));
        assertEquals(12, additivePrimes.digitSum(39));
        assertEquals(13, additivePrimes.digitSum(49));
        assertEquals(14, additivePrimes.digitSum(59));
        assertEquals(15, additivePrimes.digitSum(69));
        assertEquals(16, additivePrimes.digitSum(79));
        assertEquals(17, additivePrimes.digitSum(89));
        assertEquals(18, additivePrimes.digitSum(99));
    }

    @Test
    public void testAdditivePrimesCount() {
        // The number of additive primes less than 500 is 54.
        // This test assumes that the main method correctly counts and prints the additive primes.
        additivePrimes.main(null);
        // The output should contain "Found 54 additive primes less than 500"
        // This test is more of an integration test, as it relies on the main method's output.
        // In a real-world scenario, you might want to refactor the code to make it more testable.
    }
}
