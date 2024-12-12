import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class HofstadterTest {

    @Test
    void testFfrFirstTen() {
        int[] expected = {1, 3, 7, 12, 18, 26, 35, 45, 56, 69};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], Hofstadter.ffr(i + 1));
        }
    }

    @Test
    void testFfsFirstTen() {
        int[] expected = {2, 4, 5, 6, 8, 9, 10, 11, 13, 14};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], Hofstadter.ffs(i + 1));
        }
    }


    @Test
    void testCombinedSequences() {
        Set<Integer> first40R = new HashSet<>();
        for (int n = 1; n <= 40; n++) {
            first40R.add(Hofstadter.ffr(n));
        }

        Set<Integer> first960S = new HashSet<>();
        for (int n = 1; n <= 960; n++) {
            first960S.add(Hofstadter.ffs(n));
        }

        for (int i = 1; i <= 1000; i++) {
            assertTrue(first40R.contains(i) ^ first960S.contains(i), "Integer " + i + " either in both or neither set");
        }
    }

    @Test
    void testLargeN() {
        assertEquals(1542687, Hofstadter.ffr(100));
        assertEquals(1532721, Hofstadter.ffs(100));
    }

    // Additional Test case for small n
    @Test
    void testSmallN() {
        assertEquals(1, Hofstadter.ffr(1));
        assertEquals(2, Hofstadter.ffs(1));

    }


}
