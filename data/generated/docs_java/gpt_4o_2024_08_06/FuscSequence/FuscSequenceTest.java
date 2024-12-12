import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuscSequenceTest {

    @Test
    public void testFuscSequenceInitialization() {
        // Test the first few values of the fusc sequence
        assertEquals(0, FuscSequence.fusc[0], "fusc(0) should be 0");
        assertEquals(1, FuscSequence.fusc[1], "fusc(1) should be 1");
        assertEquals(1, FuscSequence.fusc[2], "fusc(2) should be 1");
        assertEquals(2, FuscSequence.fusc[3], "fusc(3) should be 2");
        assertEquals(1, FuscSequence.fusc[4], "fusc(4) should be 1");
        assertEquals(3, FuscSequence.fusc[5], "fusc(5) should be 3");
        assertEquals(2, FuscSequence.fusc[6], "fusc(6) should be 2");
        assertEquals(3, FuscSequence.fusc[7], "fusc(7) should be 3");
        assertEquals(1, FuscSequence.fusc[8], "fusc(8) should be 1");
        assertEquals(4, FuscSequence.fusc[9], "fusc(9) should be 4");
    }

    @Test
    public void testFuscNumberLengthIncrease() {
        // Test the fusc number and its index whose length is greater than any previous fusc number length
        int[] expectedIndices = {10, 18, 26, 54, 94, 174};
        int[] expectedValues = {5, 10, 20, 100, 1000, 10000};

        int start = 0;
        for (int i = 0; i <= 5; i++) {
            int val = i != 0 ? (int) Math.pow(10, i) : -1;
            for (int j = start; j < FuscSequence.FUSC_MAX; j++) {
                if (FuscSequence.fusc[j] > val) {
                    assertEquals(expectedIndices[i], j, "Index mismatch for length increase at i=" + i);
                    assertEquals(expectedValues[i], FuscSequence.fusc[j], "Value mismatch for length increase at i=" + i);
                    start = j;
                    break;
                }
            }
        }
    }

    @Test
    public void testFuscSymmetry() {
        // Test the symmetry property: fusc(A) = fusc(B) where B is the binary reverse of A
        for (int n = 0; n < 1000; n++) {
            int reversed = Integer.parseInt(new StringBuilder(Integer.toBinaryString(n)).reverse().toString(), 2);
            assertEquals(FuscSequence.fusc[n], FuscSequence.fusc[reversed], "Symmetry property failed for n=" + n);
        }
    }
}
