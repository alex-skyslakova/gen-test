import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LongPrimesTest {

    @Test
    public void testSieve() {
        List<Integer> primes = new LinkedList<>();
        LongPrimes.sieve(50, primes);
        List<Integer> expectedPrimes = List.of(3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47);
        assertEquals(expectedPrimes, primes);
    }

    @Test
    public void testFindPeriod() {
        assertEquals(6, LongPrimes.findPeriod(7)); // 1/7 has a period of 6
        assertEquals(1, LongPrimes.findPeriod(3)); // 1/3 has a period of 1
        assertEquals(16, LongPrimes.findPeriod(17)); // 1/17 has a period of 16
        assertEquals(18, LongPrimes.findPeriod(19)); // 1/19 has a period of 18
    }

    @Test
    public void testLongPrimesCount() {
        List<Integer> primes = new LinkedList<>();
        LongPrimes.sieve(500, primes);
        List<Integer> longPrimes = new LinkedList<>();
        for (int prime : primes) {
            if (LongPrimes.findPeriod(prime) == prime - 1) {
                longPrimes.add(prime);
            }
        }
        assertEquals(5, longPrimes.size()); // There are 5 long primes up to 500
    }

    @Test
    public void testLongPrimesList() {
        List<Integer> primes = new LinkedList<>();
        LongPrimes.sieve(500, primes);
        List<Integer> longPrimes = new LinkedList<>();
        for (int prime : primes) {
            if (LongPrimes.findPeriod(prime) == prime - 1) {
                longPrimes.add(prime);
            }
        }
        List<Integer> expectedLongPrimes = List.of(7, 17, 19, 23, 29);
        assertEquals(expectedLongPrimes, longPrimes);
    }
}
