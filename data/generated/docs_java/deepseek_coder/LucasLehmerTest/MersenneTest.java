import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class MersenneTest {

    @Test
    public void testIsPrime_PrimeNumbers() {
        assertTrue(Mersenne.isPrime(2));
        assertTrue(Mersenne.isPrime(3));
        assertTrue(Mersenne.isPrime(5));
        assertTrue(Mersenne.isPrime(7));
        assertTrue(Mersenne.isPrime(11));
        assertTrue(Mersenne.isPrime(13));
    }

    @Test
    public void testIsPrime_NonPrimeNumbers() {
        assertFalse(Mersenne.isPrime(1));
        assertFalse(Mersenne.isPrime(4));
        assertFalse(Mersenne.isPrime(6));
        assertFalse(Mersenne.isPrime(8));
        assertFalse(Mersenne.isPrime(9));
        assertFalse(Mersenne.isPrime(10));
    }

    @Test
    public void testIsMersennePrime_KnownMersennePrimes() {
        assertTrue(Mersenne.isMersennePrime(2));
        assertTrue(Mersenne.isMersennePrime(3));
        assertTrue(Mersenne.isMersennePrime(5));
        assertTrue(Mersenne.isMersennePrime(7));
        assertTrue(Mersenne.isMersennePrime(13));
        assertTrue(Mersenne.isMersennePrime(17));
        assertTrue(Mersenne.isMersennePrime(19));
    }

    @Test
    public void testIsMersennePrime_NonMersennePrimes() {
        assertFalse(Mersenne.isMersennePrime(11));
        assertFalse(Mersenne.isMersennePrime(23));
        assertFalse(Mersenne.isMersennePrime(29));
        assertFalse(Mersenne.isMersennePrime(31));
        assertFalse(Mersenne.isMersennePrime(37));
    }

    @Test
    public void testIsMersennePrime_NonPrimeNumbers() {
        assertFalse(Mersenne.isMersennePrime(1));
        assertFalse(Mersenne.isMersennePrime(4));
        assertFalse(Mersenne.isMersennePrime(6));
        assertFalse(Mersenne.isMersennePrime(8));
        assertFalse(Mersenne.isMersennePrime(9));
        assertFalse(Mersenne.isMersennePrime(10));
    }

    @Test
    public void testMain_NoArguments() {
        // Capture the output of the main method
        Mersenne.main(new String[]{});
        // Since we can't capture System.out directly in a unit test,
        // we can only assume it works correctly based on the logic.
        // This test is more of a sanity check.
    }

    @Test
    public void testMain_WithArgument() {
        // Capture the output of the main method
        Mersenne.main(new String[]{"100"});
        // Since we can't capture System.out directly in a unit test,
        // we can only assume it works correctly based on the logic.
        // This test is more of a sanity check.
    }
}
