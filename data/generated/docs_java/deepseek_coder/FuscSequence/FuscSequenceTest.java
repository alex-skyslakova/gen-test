import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuscSequenceTest {

    @Test
    public void testFuscSequenceFirst61Numbers() {
        int[] expectedFuscSequence = {
            0, 1, 1, 2, 1, 3, 2, 3, 1, 4, 3, 5, 2, 5, 3, 4, 1, 5, 4, 7, 3, 8, 5, 7, 2, 7, 5, 8, 3, 7, 4, 5, 1,
            6, 5, 9, 4, 11, 7, 10, 3, 11, 8, 13, 5, 12, 7, 9, 2, 9, 7, 12, 5, 13, 8, 11, 3, 10, 7, 11, 4, 9, 5, 6
        };

        for (int i = 0; i < 61; i++) {
            assertEquals(expectedFuscSequence[i], FuscSequence.fusc[i], "Fusc number at index " + i + " is incorrect");
        }
    }

    @Test
    public void testFuscSequenceLengths() {
        int[] expectedIndices = {0, 37, 1173, 35499, 699051};
        int[] expectedValues = {0, 11, 108, 1076, 10946};

        int start = 0;
        for (int i = 0; i <= 5; i++) {
            int val = i != 0 ? (int) Math.pow(10, i) : -1;
            for (int j = start; j < FuscSequence.FUSC_MAX; j++) {
                if (FuscSequence.fusc[j] > val) {
                    assertEquals(expectedIndices[i], j, "Index for length " + i + " is incorrect");
                    assertEquals(expectedValues[i], FuscSequence.fusc[j], "Fusc value for length " + i + " is incorrect");
                    start = j;
                    break;
                }
            }
        }
    }

    @Test
    public void testFuscSequenceInitialization() {
        assertEquals(0, FuscSequence.fusc[0], "Fusc number at index 0 is incorrect");
        assertEquals(1, FuscSequence.fusc[1], "Fusc number at index 1 is incorrect");

        for (int n = 2; n < FuscSequence.FUSC_MAX; n++) {
            int expectedFusc = (n % 2 == 0) ? FuscSequence.fusc[n / 2] : FuscSequence.fusc[(n - 1) / 2] + FuscSequence.fusc[(n + 1) / 2];
            assertEquals(expectedFusc, FuscSequence.fusc[n], "Fusc number at index " + n + " is incorrect");
        }
    }
}
