import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LongPrimesTest {

    @Test
    void testSieve() {
        List<Integer> primes = new LinkedList<>();
        LongPrimes.sieve(10, primes);
        assertEquals(List.of(3, 5, 7), primes);

        primes.clear();
        LongPrimes.sieve(20, primes);
        assertEquals(List.of(3, 5, 7, 11, 13, 17, 19), primes);
    }

    @Test
    void testFindPeriod() {
        assertEquals(6, LongPrimes.findPeriod(7));
        assertEquals(16, LongPrimes.findPeriod(17));
        assertEquals(2, LongPrimes.findPeriod(3));
        assertEquals(1, LongPrimes.findPeriod(2));
        assertEquals(4, LongPrimes.findPeriod(5));

    }


    @Test
    void testIntegration() {
       int[] numbers = new int[]{500, 1000, 2000, 4000, 8000, 16000, 32000, 64000};
        int[] expectedTotals = new int[]{16, 26, 41, 65, 95, 128, 172, 216}; // Pre-calculated expected values

        List<Integer> primes = new LinkedList<>();
        List<Integer> longPrimes = new LinkedList<>();
        LongPrimes.sieve(64000, primes);
        for (int prime : primes)
            if (LongPrimes.findPeriod(prime) == prime - 1)
                longPrimes.add(prime);

        int[] actualTotals = new int[numbers.length];
        int count = 0, index = 0;
        for (int longPrime : longPrimes) {
            if (longPrime > numbers[index])
                actualTotals[index++] = count;
            ++count;
        }
        actualTotals[numbers.length - 1] = count;

       assertArrayEquals(expectedTotals, actualTotals);

        List<Integer> expectedLongPrimesBelow500 = List.of(7, 17, 19, 23, 29, 47, 59, 61, 97, 109, 113, 131, 149, 167, 179, 181, 193, 223, 229, 233, 257, 263, 269, 313, 337, 367, 379, 383, 389, 419, 433, 461, 487, 491, 499);
        List<Integer> actualLongPrimesBelow500 = longPrimes.subList(0, actualTotals[0]);
        assertEquals(expectedLongPrimesBelow500.size(), actualLongPrimesBelow500.size());
        assertTrue(expectedLongPrimesBelow500.containsAll(actualLongPrimesBelow500)); // Order doesn't matter for this test

    }
}
