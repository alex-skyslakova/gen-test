import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimeGeneratorTest {

    @Test
    public void testFirstTwentyPrimes() {
        PrimeGenerator pgen = new PrimeGenerator(20, 200000);
        int[] expectedPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71};
        for (int expectedPrime : expectedPrimes) {
            assertEquals(expectedPrime, pgen.nextPrime());
        }
    }

    @Test
    public void testPrimesBetween100And150() {
        PrimeGenerator pgen = new PrimeGenerator(20, 200000);
        int[] expectedPrimes = {101, 103, 107, 109, 113, 127, 131, 137, 139, 149};
        int index = 0;
        while (true) {
            int prime = pgen.nextPrime();
            if (prime > 150) break;
            if (prime >= 100) {
                assertEquals(expectedPrimes[index++], prime);
            }
        }
        assertEquals(expectedPrimes.length, index);
    }

    @Test
    public void testNumberOfPrimesBetween7700And8000() {
        PrimeGenerator pgen = new PrimeGenerator(20, 200000);
        int count = 0;
        while (true) {
            int prime = pgen.nextPrime();
            if (prime > 8000) break;
            if (prime >= 7700) {
                count++;
            }
        }
        assertEquals(24, count); // Known number of primes between 7700 and 8000
    }

    @Test
    public void test10000thPrime() {
        PrimeGenerator pgen = new PrimeGenerator(20, 200000);
        int prime = 0;
        while (pgen.count() < 10000) {
            prime = pgen.nextPrime();
        }
        assertEquals(104729, prime); // 10000th prime is 104729
    }
}
