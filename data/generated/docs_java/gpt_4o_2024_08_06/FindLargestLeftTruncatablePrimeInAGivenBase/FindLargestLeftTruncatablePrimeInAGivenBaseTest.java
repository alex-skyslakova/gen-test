import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigInteger;

class LeftTruncatablePrimeTest {

    @Test
    void testGetLargestLeftTruncatablePrimeBase3() {
        BigInteger expected = new BigInteger("2"); // 2 in base 3 is 2 in base 10
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(3, 10);
        assertEquals(expected, result);
    }

    @Test
    void testGetLargestLeftTruncatablePrimeBase10() {
        BigInteger expected = new BigInteger("739397"); // Largest left truncatable prime in base 10
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(10, 10);
        assertEquals(expected, result);
    }

    @Test
    void testGetLargestLeftTruncatablePrimeBase5() {
        BigInteger expected = new BigInteger("13"); // 13 in base 5 is 8 in base 10
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(5, 10);
        assertEquals(expected, result);
    }

    @Test
    void testGetLargestLeftTruncatablePrimeInvalidBase() {
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(2, 10);
        assertNull(result, "Base 2 should not have any left truncatable primes.");
    }

    @Test
    void testGetLargestLeftTruncatablePrimeHighCertainty() {
        BigInteger expected = new BigInteger("739397"); // Largest left truncatable prime in base 10
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(10, 100);
        assertEquals(expected, result);
    }

    @Test
    void testGetLargestLeftTruncatablePrimeLowCertainty() {
        BigInteger expected = new BigInteger("739397"); // Largest left truncatable prime in base 10
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(10, 1);
        assertEquals(expected, result);
    }

    @Test
    void testGetLargestLeftTruncatablePrimeBase7() {
        BigInteger expected = new BigInteger("53"); // 53 in base 7 is 38 in base 10
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(7, 10);
        assertEquals(expected, result);
    }
}
