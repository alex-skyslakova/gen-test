import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CubanPrimesTest {

    @Before
    public void setUp() {
        CubanPrimes.preCompute();
    }

    @Test
    public void testIsPrime() {
        assertTrue(CubanPrimes.isPrime(7));
        assertTrue(CubanPrimes.isPrime(13));
        assertFalse(CubanPrimes.isPrime(15));
        assertFalse(CubanPrimes.isPrime(25));
    }

    @Test
    public void testCubanPrimeFirst200() {
        long result = CubanPrimes.cubanPrime(200, false);
        assertEquals(167701, result);
    }

    @Test
    public void testCubanPrime100000th() {
        long result = CubanPrimes.cubanPrime(100000, false);
        assertEquals(1792617147, result);
    }

    @Test
    public void testCubanPrime10th() {
        long result = CubanPrimes.cubanPrime(10, false);
        assertEquals(127, result);
    }

    @Test
    public void testCubanPrime100th() {
        long result = CubanPrimes.cubanPrime(100, false);
        assertEquals(1573, result);
    }

    @Test
    public void testCubanPrime1000th() {
        long result = CubanPrimes.cubanPrime(1000, false);
        assertEquals(15373, result);
    }

    @Test
    public void testCubanPrime10000th() {
        long result = CubanPrimes.cubanPrime(10000, false);
        assertEquals(153049, result);
    }
}
