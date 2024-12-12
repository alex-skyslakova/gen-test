import org.junit.Test;
import static org.junit.Assert.*;

public class AlmostPrimeTest {

    @Test
    public void testKprimeWithK1() {
        // Testing for k = 1 (prime numbers)
        assertTrue(AlmostPrime.kprime(2, 1));
        assertTrue(AlmostPrime.kprime(3, 1));
        assertTrue(AlmostPrime.kprime(5, 1));
        assertTrue(AlmostPrime.kprime(7, 1));
        assertFalse(AlmostPrime.kprime(4, 1)); // 4 is not a prime number
    }

    @Test
    public void testKprimeWithK2() {
        // Testing for k = 2 (semiprimes)
        assertTrue(AlmostPrime.kprime(4, 2)); // 2 * 2
        assertTrue(AlmostPrime.kprime(6, 2)); // 2 * 3
        assertTrue(AlmostPrime.kprime(9, 2)); // 3 * 3
        assertFalse(AlmostPrime.kprime(8, 2)); // 8 is 2 * 2 * 2, not a semiprime
    }

    @Test
    public void testKprimeWithK3() {
        // Testing for k = 3
        assertTrue(AlmostPrime.kprime(8, 3)); // 2 * 2 * 2
        assertTrue(AlmostPrime.kprime(12, 3)); // 2 * 2 * 3
        assertTrue(AlmostPrime.kprime(18, 3)); // 2 * 3 * 3
        assertFalse(AlmostPrime.kprime(16, 3)); // 16 is 2 * 2 * 2 * 2, not k=3
    }

    @Test
    public void testKprimeWithK4() {
        // Testing for k = 4
        assertTrue(AlmostPrime.kprime(16, 4)); // 2 * 2 * 2 * 2
        assertTrue(AlmostPrime.kprime(24, 4)); // 2 * 2 * 2 * 3
        assertTrue(AlmostPrime.kprime(36, 4)); // 2 * 2 * 3 * 3
        assertFalse(AlmostPrime.kprime(32, 4)); // 32 is 2 * 2 * 2 * 2 * 2, not k=4
    }

    @Test
    public void testKprimeWithK5() {
        // Testing for k = 5
        assertTrue(AlmostPrime.kprime(32, 5)); // 2 * 2 * 2 * 2 * 2
        assertTrue(AlmostPrime.kprime(48, 5)); // 2 * 2 * 2 * 2 * 3
        assertTrue(AlmostPrime.kprime(72, 5)); // 2 * 2 * 2 * 3 * 3
        assertFalse(AlmostPrime.kprime(64, 5)); // 64 is 2 * 2 * 2 * 2 * 2 * 2, not k=5
    }
}
