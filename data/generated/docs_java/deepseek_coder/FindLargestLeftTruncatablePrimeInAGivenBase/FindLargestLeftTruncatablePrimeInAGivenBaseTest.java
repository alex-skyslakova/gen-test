import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

class LeftTruncatablePrimeTest {

    @Test
    void testGetLargestLeftTruncatablePrimeBase3() {
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(3, 10);
        assertEquals(new BigInteger("23"), result);
    }

    @Test
    void testGetLargestLeftTruncatablePrimeBase10() {
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(10, 10);
        assertEquals(new BigInteger("357686312646216567629137"), result);
    }

    @Test
    void testGetLargestLeftTruncatablePrimeNoPrime() {
        // Assuming base 4 has no left-truncatable prime (this is a hypothetical case)
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(4, 10);
        assertNull(result);
    }

    @Test
    void testGetLargestLeftTruncatablePrimeLargeBase() {
        // Test with a larger base (e.g., base 16)
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(16, 10);
        assertNotNull(result);
    }

    @Test
    void testGetLargestLeftTruncatablePrimeMinCertainty() {
        // Test with the minimum Miller-Rabin certainty (1)
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(3, 1);
        assertEquals(new BigInteger("23"), result);
    }

    @Test
    void testGetLargestLeftTruncatablePrimeInvalidBase() {
        // Test with an invalid base (less than 3)
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(2, 10);
        assertNull(result);
    }

    @Test
    void testGetNextLeftTruncatablePrimesBase3() {
        List<BigInteger> result = LeftTruncatablePrime.getNextLeftTruncatablePrimes(BigInteger.ZERO, 3, 10);
        List<BigInteger> expected = Arrays.asList(new BigInteger("2"), new BigInteger("12"));
        assertEquals(expected, result);
    }

    @Test
    void testGetNextLeftTruncatablePrimesBase10() {
        List<BigInteger> result = LeftTruncatablePrime.getNextLeftTruncatablePrimes(new BigInteger("3"), 10, 10);
        List<BigInteger> expected = Arrays.asList(new BigInteger("13"), new BigInteger("23"), new BigInteger("43"), new BigInteger("53"), new BigInteger("73"), new BigInteger("83"));
        assertEquals(expected, result);
    }
}
