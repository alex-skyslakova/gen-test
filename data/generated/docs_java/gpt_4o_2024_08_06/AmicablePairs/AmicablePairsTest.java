import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AmicablePairsTest {

    @Test
    void testProperDivsSum() {
        // Test known proper divisor sums
        assertEquals(1L, AmicablePairs.properDivsSum(1));
        assertEquals(1L, AmicablePairs.properDivsSum(2));
        assertEquals(3L, AmicablePairs.properDivsSum(4));
        assertEquals(6L, AmicablePairs.properDivsSum(6)); // 6 is a perfect number
        assertEquals(284L, AmicablePairs.properDivsSum(220)); // 220 and 284 are amicable
        assertEquals(220L, AmicablePairs.properDivsSum(284)); // 220 and 284 are amicable
    }

    @Test
    void testAmicablePairsBelow20000() {
        // Known amicable pairs below 20,000
        long[][] expectedPairs = {
            {220, 284},
            {1184, 1210},
            {2620, 2924},
            {5020, 5564},
            {6232, 6368},
            {10744, 10856},
            {12285, 14595},
            {17296, 18416}
        };

        int limit = 20_000;
        Map<Long, Long> map = LongStream.rangeClosed(1, limit)
                .parallel()
                .boxed()
                .collect(Collectors.toMap(Function.identity(), AmicablePairs::properDivsSum));

        List<long[]> foundPairs = new ArrayList<>();

        LongStream.rangeClosed(1, limit)
                .forEach(n -> {
                    long m = map.get(n);
                    if (m > n && m <= limit && map.get(m) == n) {
                        foundPairs.add(new long[]{n, m});
                    }
                });

        assertEquals(expectedPairs.length, foundPairs.size(), "Number of amicable pairs does not match");

        for (int i = 0; i < expectedPairs.length; i++) {
            assertArrayEquals(expectedPairs[i], foundPairs.get(i), "Amicable pair does not match");
        }
    }
}
