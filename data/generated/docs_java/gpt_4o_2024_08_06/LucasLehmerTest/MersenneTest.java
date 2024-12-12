import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;

public class MersenneTest {

    @Test
    public void testIsPrime() {
        assertTrue(Mersenne.isPrime(2), "2 should be prime");
        assertTrue(Mersenne.isPrime(3), "3 should be prime");
        assertTrue(Mersenne.isPrime(5), "5 should be prime");
        assertTrue(Mersenne.isPrime(7), "7 should be prime");
        assertFalse(Mersenne.isPrime(1), "1 should not be prime");
        assertFalse(Mersenne.isPrime(0), "0 should not be prime");
        assertFalse(Mersenne.isPrime(-1), "-1 should not be prime");
        assertFalse(Mersenne.isPrime(4), "4 should not be prime");
        assertFalse(Mersenne.isPrime(9), "9 should not be prime");
    }

    @Test
    public void testIsMersennePrime() {
        assertTrue(Mersenne.isMersennePrime(2), "Mersenne prime for p=2 should be true");
        assertTrue(Mersenne.isMersennePrime(3), "Mersenne prime for p=3 should be true");
        assertTrue(Mersenne.isMersennePrime(5), "Mersenne prime for p=5 should be true");
        assertTrue(Mersenne.isMersennePrime(7), "Mersenne prime for p=7 should be true");
        assertFalse(Mersenne.isMersennePrime(11), "Mersenne prime for p=11 should be false");
        assertFalse(Mersenne.isMersennePrime(13), "Mersenne prime for p=13 should be false");
        assertFalse(Mersenne.isMersennePrime(4), "Mersenne prime for p=4 should be false");
    }

    @Test
    public void testMersennePrimeCalculation() {
        // Test known Mersenne primes
        assertTrue(Mersenne.isMersennePrime(17), "Mersenne prime for p=17 should be true");
        assertTrue(Mersenne.isMersennePrime(19), "Mersenne prime for p=19 should be true");
        assertTrue(Mersenne.isMersennePrime(31), "Mersenne prime for p=31 should be true");
        assertTrue(Mersenne.isMersennePrime(61), "Mersenne prime for p=61 should be true");
    }

    @Test
    public void testMainMethod() {
        // This test is more of a sanity check for the main method
        // It doesn't assert anything but ensures no exceptions are thrown
        Mersenne.main(new String[]{"20"});
        Mersenne.main(new String[]{"50"});
    }
}
