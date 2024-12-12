import static org.junit.Assert.*;
import org.junit.Test;

public class AdditivePrimesTest {

    @Test
    public void testIsPrime() {
        // Test known primes
        assertTrue(additivePrimes.isPrime(2));
        assertTrue(additivePrimes.isPrime(3));
        assertTrue(additivePrimes.isPrime(5));
        assertTrue(additivePrimes.isPrime(7));
        assertTrue(additivePrimes.isPrime(11));
        assertTrue(additivePrimes.isPrime(13));
        assertTrue(additivePrimes.isPrime(17));
        assertTrue(additivePrimes.isPrime(19));
        
        // Test known non-primes
        assertFalse(additivePrimes.isPrime(1));
        assertFalse(additivePrimes.isPrime(4));
        assertFalse(additivePrimes.isPrime(6));
        assertFalse(additivePrimes.isPrime(8));
        assertFalse(additivePrimes.isPrime(9));
        assertFalse(additivePrimes.isPrime(10));
        assertFalse(additivePrimes.isPrime(12));
    }

    @Test
    public void testDigitSum() {
        // Test single digit
        assertEquals(5, additivePrimes.digitSum(5));
        
        // Test multiple digits
        assertEquals(6, additivePrimes.digitSum(123)); // 1 + 2 + 3 = 6
        assertEquals(10, additivePrimes.digitSum(1234)); // 1 + 2 + 3 + 4 = 10
        assertEquals(15, additivePrimes.digitSum(555)); // 5 + 5 + 5 = 15
        assertEquals(9, additivePrimes.digitSum(333)); // 3 + 3 + 3 = 9
    }

    @Test
    public void testAdditivePrimes() {
        // Known additive primes less than 500
        int[] expectedAdditivePrimes = {2, 3, 5, 7, 11, 23, 29, 41, 43, 47, 61, 67, 83, 89, 101, 113, 131, 151, 181, 191, 211, 223, 227, 229, 233, 239, 241, 263, 269, 281, 283, 293, 311, 313, 317, 337, 347, 353, 359, 367, 373, 383, 389, 401, 409, 419, 421, 431, 439, 443, 449, 461, 463, 467, 479, 487, 491, 499};
        
        int index = 0;
        for (int i = 2; i < 500; i++) {
            if (additivePrimes.isPrime(i) && additivePrimes.isPrime(additivePrimes.digitSum(i))) {
                assertEquals(expectedAdditivePrimes[index], i);
                index++;
            }
        }
        
        // Check the number of additive primes found
        assertEquals(expectedAdditivePrimes.length, index);
    }
}
