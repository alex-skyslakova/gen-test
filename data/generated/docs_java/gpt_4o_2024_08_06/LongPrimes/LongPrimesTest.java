import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.List;

public class LongPrimesTest {

    @Test
    public void testSieve() {
        List<Integer> primes = new LinkedList<>();
        LongPrimes.sieve(10, primes);
        assertEquals(List.of(3, 5, 7), primes);

        primes.clear();
        LongPrimes.sieve(20, primes);
        assertEquals(List.of(3, 5, 7, 11, 13, 17, 19), primes);
    }

    @Test
    public void testFindPeriod() {
        assertEquals(6, LongPrimes.findPeriod(7));  // 1/7 = 0.142857...
        assertEquals(16, LongPrimes.findPeriod(17)); // 1/17 = 0.0588235294117647...
        assertEquals(18, LongPrimes.findPeriod(19)); // 1/19 = 0.052631578947368421...
    }

    @Test
    public void testLongPrimesUpTo500() {
        List<Integer> primes = new LinkedList<>();
        LongPrimes.sieve(500, primes);
        List<Integer> longPrimes = new LinkedList<>();
        for (int prime : primes) {
            if (LongPrimes.findPeriod(prime) == prime - 1) {
                longPrimes.add(prime);
            }
        }
        assertEquals(List.of(7, 17, 19, 23, 29, 47, 59, 61, 97, 109, 113, 131, 149, 167, 179, 181, 193, 223, 229, 233, 239, 241, 263, 269, 283, 293, 307, 311, 313, 337, 367, 373, 383, 389, 409, 419, 421, 431, 433, 439, 461, 463, 487), longPrimes);
    }

    @Test
    public void testNumberOfLongPrimes() {
        int[] numbers = new int[]{500, 1000, 2000, 4000, 8000, 16000, 32000, 64000};
        int[] expectedTotals = new int[]{42, 60, 109, 183, 313, 564, 1028, 1900};

        List<Integer> primes = new LinkedList<>();
        LongPrimes.sieve(64000, primes);
        List<Integer> longPrimes = new LinkedList<>();
        for (int prime : primes) {
            if (LongPrimes.findPeriod(prime) == prime - 1) {
                longPrimes.add(prime);
            }
        }

        int count = 0, index = 0;
        int[] totals = new int[numbers.length];
        for (int longPrime : longPrimes) {
            if (longPrime > numbers[index]) {
                totals[index++] = count;
            }
            ++count;
        }
        totals[numbers.length - 1] = count;

        assertArrayEquals(expectedTotals, totals);
    }
}
