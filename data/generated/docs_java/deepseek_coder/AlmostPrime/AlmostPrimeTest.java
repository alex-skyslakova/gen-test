import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlmostPrimeTest {

    @Test
    public void testKPrime_k1() {
        // 1-almost-primes are prime numbers
        assertTrue(AlmostPrime.kprime(2, 1));
        assertTrue(AlmostPrime.kprime(3, 1));
        assertTrue(AlmostPrime.kprime(5, 1));
        assertTrue(AlmostPrime.kprime(7, 1));
        assertTrue(AlmostPrime.kprime(11, 1));
        assertFalse(AlmostPrime.kprime(4, 1));
        assertFalse(AlmostPrime.kprime(6, 1));
        assertFalse(AlmostPrime.kprime(8, 1));
        assertFalse(AlmostPrime.kprime(9, 1));
    }

    @Test
    public void testKPrime_k2() {
        // 2-almost-primes are semiprimes
        assertTrue(AlmostPrime.kprime(4, 2)); // 2*2
        assertTrue(AlmostPrime.kprime(6, 2)); // 2*3
        assertTrue(AlmostPrime.kprime(9, 2)); // 3*3
        assertTrue(AlmostPrime.kprime(10, 2)); // 2*5
        assertTrue(AlmostPrime.kprime(14, 2)); // 2*7
        assertFalse(AlmostPrime.kprime(8, 2)); // 2*2*2
        assertFalse(AlmostPrime.kprime(15, 2)); // 3*5 but not semiprime
    }

    @Test
    public void testKPrime_k3() {
        // 3-almost-primes are numbers with exactly 3 prime factors
        assertTrue(AlmostPrime.kprime(8, 3)); // 2*2*2
        assertTrue(AlmostPrime.kprime(12, 3)); // 2*2*3
        assertTrue(AlmostPrime.kprime(18, 3)); // 2*3*3
        assertTrue(AlmostPrime.kprime(20, 3)); // 2*2*5
        assertTrue(AlmostPrime.kprime(27, 3)); // 3*3*3
        assertFalse(AlmostPrime.kprime(25, 3)); // 5*5
        assertFalse(AlmostPrime.kprime(30, 3)); // 2*3*5 but not 3-almost-prime
    }

    @Test
    public void testKPrime_k4() {
        // 4-almost-primes are numbers with exactly 4 prime factors
        assertTrue(AlmostPrime.kprime(16, 4)); // 2*2*2*2
        assertTrue(AlmostPrime.kprime(24, 4)); // 2*2*2*3
        assertTrue(AlmostPrime.kprime(36, 4)); // 2*2*3*3
        assertTrue(AlmostPrime.kprime(40, 4)); // 2*2*2*5
        assertTrue(AlmostPrime.kprime(54, 4)); // 2*3*3*3
        assertFalse(AlmostPrime.kprime(32, 4)); // 2*2*2*2*2
        assertFalse(AlmostPrime.kprime(48, 4)); // 2*2*2*2*3
    }

    @Test
    public void testKPrime_k5() {
        // 5-almost-primes are numbers with exactly 5 prime factors
        assertTrue(AlmostPrime.kprime(32, 5)); // 2*2*2*2*2
        assertTrue(AlmostPrime.kprime(48, 5)); // 2*2*2*2*3
        assertTrue(AlmostPrime.kprime(64, 5)); // 2*2*2*2*2
        assertTrue(AlmostPrime.kprime(72, 5)); // 2*2*2*3*3
        assertTrue(AlmostPrime.kprime(80, 5)); // 2*2*2*2*5
        assertFalse(AlmostPrime.kprime(60, 5)); // 2*2*3*5
        assertFalse(AlmostPrime.kprime(96, 5)); // 2*2*2*2*2*3
    }

    @Test
    public void testKPrime_invalidK() {
        // k should be a positive integer
        assertFalse(AlmostPrime.kprime(2, 0));
        assertFalse(AlmostPrime.kprime(2, -1));
        assertFalse(AlmostPrime.kprime(2, 6)); // No 6-almost-primes in this range
    }
}
