import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

class HofstadterTest {

    @Test
    void testFirstTenValuesOfR() {
        int[] expectedR = {1, 3, 7, 12, 18, 26, 35, 45, 56, 69};
        for (int n = 1; n <= 10; n++) {
            assertEquals(expectedR[n - 1], Hofstadter.ffr(n), "R(" + n + ") should be " + expectedR[n - 1]);
        }
    }

    @Test
    void testFirstTenValuesOfS() {
        int[] expectedS = {2, 4, 5, 6, 8, 9, 10, 11, 13, 14}; // Example values for S(1) to S(10)
        for (int n = 1; n <= 10; n++) {
            assertEquals(expectedS[n - 1], Hofstadter.ffs(n), "S(" + n + ") should be " + expectedS[n - 1]);
        }
    }

    @Test
    void testFirst40RPlusFirst960S() {
        Set<Integer> first40R = new HashSet<>();
        for (int n = 1; n <= 40; n++) {
            first40R.add(Hofstadter.ffr(n));
        }

        Set<Integer> first960S = new HashSet<>();
        for (int n = 1; n <= 960; n++) {
            first960S.add(Hofstadter.ffs(n));
        }

        Set<Integer> combinedSet = new HashSet<>(first40R);
        combinedSet.addAll(first960S);

        for (int i = 1; i <= 1000; i++) {
            assertTrue(combinedSet.contains(i), "Integer " + i + " should be in the combined set of R(1..40) and S(1..960)");
        }
    }

    @Test
    void testNoDuplicatesInFirst40RPlusFirst960S() {
        Set<Integer> first40R = new HashSet<>();
        for (int n = 1; n <= 40; n++) {
            first40R.add(Hofstadter.ffr(n));
        }

        Set<Integer> first960S = new HashSet<>();
        for (int n = 1; n <= 960; n++) {
            first960S.add(Hofstadter.ffs(n));
        }

        Set<Integer> combinedSet = new HashSet<>(first40R);
        combinedSet.addAll(first960S);

        assertEquals(1000, combinedSet.size(), "The combined set of R(1..40) and S(1..960) should contain exactly 1000 unique integers");
    }
}
