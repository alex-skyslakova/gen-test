import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PrimeGeneratorTest {

    @Test
    public void testFirstTwentyPrimes() {
        PrimeGenerator pgen = new PrimeGenerator(20, 200000);
        List<Integer> firstTwentyPrimes = new ArrayList<>();
        for (int i = 0; i < 20; ++i) {
            firstTwentyPrimes.add(pgen.nextPrime());
        }
        List<Integer> expectedPrimes = List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71);
        assertEquals(expectedPrimes, firstTwentyPrimes);
    }

    @Test
    public void testPrimesBetween100And150() {
        PrimeGenerator pgen = new PrimeGenerator(20, 200000);
        List<Integer> primesBetween100And150 = new ArrayList<>();
        for (int i = 0; ; ) {
            int prime = pgen.nextPrime();
            if (prime > 150)
                break;
            if (prime >= 100) {
                primesBetween100And150.add(prime);
            }
        }
        List<Integer> expectedPrimes = List.of(101, 103, 107, 109, 113, 127, 131, 137, 139, 149);
        assertEquals(expectedPrimes, primesBetween100And150);
    }

    @Test
    public void testNumberOfPrimesBetween7700And8000() {
        PrimeGenerator pgen = new PrimeGenerator(20, 200000);
        int count = 0;
        for (;;) {
            int prime = pgen.nextPrime();
            if (prime > 8000)
                break;
            if (prime >= 7700)
                ++count;
        }
        assertEquals(30, count);
    }

    @Test
    public void test10000thPrime() {
        PrimeGenerator pgen = new PrimeGenerator(20, 200000);
        int n = 10000;
        int prime = 0;
        for (;;) {
            prime = pgen.nextPrime();
            if (prime == 0) {
                fail("Can't generate any more primes.");
                break;
            }
            if (pgen.count() == n) {
                break;
            }
        }
        assertEquals(104729, prime);
    }

    @Test
    public void testNextPrimeAfterMaxInteger() {
        PrimeGenerator pgen = new PrimeGenerator(Integer.MAX_VALUE - 10, 20);
        int prime = pgen.nextPrime();
        assertEquals(0, prime);
    }
}
