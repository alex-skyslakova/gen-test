import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class HofstadterTest {

    @Test
    void testFfr() {
        // Test the first ten values of R
        int[] expectedR = {1, 3, 7, 12, 18, 26, 35, 45, 56, 69};
        for (int n = 1; n <= 10; n++) {
            assertEquals(expectedR[n - 1], Hofstadter.ffr(n), "R(" + n + ") should be " + expectedR[n - 1]);
        }
    }

    @Test
    void testFfs() {
        // Test the first few values of S
        int[] expectedS = {2, 4, 5, 6, 8, 9, 10, 11, 13, 14};
        for (int n = 1; n <= 10; n++) {
            assertEquals(expectedS[n - 1], Hofstadter.ffs(n), "S(" + n + ") should be " + expectedS[n - 1]);
        }
    }

    @Test
    void testUniqueIntegersFrom1To1000() {
        // Test that the first 40 values of R and the first 960 values of S include all integers from 1 to 1000 exactly once
        Set<Integer> first40R = new HashSet<>();
        for (int n = 1; n <= 40; n++) {
            first40R.add(Hofstadter.ffr(n));
        }

        Set<Integer> first960S = new HashSet<>();
        for (int n = 1; n <= 960; n++) {
            first960S.add(Hofstadter.ffs(n));
        }

        for (int i = 1; i <= 1000; i++) {
            Integer n = Integer.valueOf(i);
            assertTrue(first40R.contains(n) ^ first960S.contains(n), "Integer " + i + " should be in exactly one set");
        }
    }
}
