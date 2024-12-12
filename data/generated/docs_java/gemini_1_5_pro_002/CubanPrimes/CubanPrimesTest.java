import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CubanPrimesTest {

    @Test
    void testIsPrime_smallPrimes() {
        CubanPrimes.preCompute();
        assertTrue(CubanPrimes.isPrime(2));
        assertTrue(CubanPrimes.isPrime(3));
        assertTrue(CubanPrimes.isPrime(5));
        assertTrue(CubanPrimes.isPrime(7));
        assertFalse(CubanPrimes.isPrime(4));
        assertFalse(CubanPrimes.isPrime(6));
        assertFalse(CubanPrimes.isPrime(8));
        assertFalse(CubanPrimes.isPrime(9));
    }

    @Test
    void testIsPrime_largePrime() {
        CubanPrimes.preCompute();
        assertTrue(CubanPrimes.isPrime(1_000_003)); // Prime greater than MAX
    }
    
    @Test
    void testIsPrime_largeComposite() {
        CubanPrimes.preCompute();
        assertFalse(CubanPrimes.isPrime(1_000_005)); // Composite greater than MAX
    }



    @Test
    void testCubanPrime_firstFew() {
        CubanPrimes.preCompute();
        assertEquals(7, CubanPrimes.cubanPrime(1, false));
        assertEquals(19, CubanPrimes.cubanPrime(2, false));
        assertEquals(37, CubanPrimes.cubanPrime(3, false));
    }

    @Test
    void testCubanPrime_largeN() {
        CubanPrimes.preCompute();
        assertEquals(12_996_707L, CubanPrimes.cubanPrime(10_000, false)); // Verified using OEIS
    }
}
