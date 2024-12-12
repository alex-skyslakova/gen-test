import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HofstadterConwaySequenceTest {

    @Test
    public void testSequenceGeneration() {
        // Test the first few elements of the sequence
        assertEquals(1, HofstadterConwaySequence.HCS[1]);
        assertEquals(1, HofstadterConwaySequence.HCS[2]);
        assertEquals(2, HofstadterConwaySequence.HCS[3]);
        assertEquals(2, HofstadterConwaySequence.HCS[4]);
        assertEquals(3, HofstadterConwaySequence.HCS[5]);
        assertEquals(4, HofstadterConwaySequence.HCS[6]);
        assertEquals(4, HofstadterConwaySequence.HCS[7]);
        assertEquals(4, HofstadterConwaySequence.HCS[8]);
        assertEquals(5, HofstadterConwaySequence.HCS[9]);
    }

    @Test
    public void testMaxRatioBetweenPowersOfTwo() {
        // Test the maximum ratio between 2^1 and 2^2
        double maxRatio = 0.0;
        for (int n = 2; n <= 4; n++) {
            double ratio = (double) HofstadterConwaySequence.HCS[n] / n;
            maxRatio = Math.max(ratio, maxRatio);
        }
        assertEquals(0.5, maxRatio, 0.0001);

        // Test the maximum ratio between 2^2 and 2^3
        maxRatio = 0.0;
        for (int n = 4; n <= 8; n++) {
            double ratio = (double) HofstadterConwaySequence.HCS[n] / n;
            maxRatio = Math.max(ratio, maxRatio);
        }
        assertEquals(0.5, maxRatio, 0.0001);

        // Test the maximum ratio between 2^3 and 2^4
        maxRatio = 0.0;
        for (int n = 8; n <= 16; n++) {
            double ratio = (double) HofstadterConwaySequence.HCS[n] / n;
            maxRatio = Math.max(ratio, maxRatio);
        }
        assertEquals(0.5, maxRatio, 0.0001);
    }

    @Test
    public void testMallowsNumber() {
        // Test the Mallow's number calculation
        int mNum = 0;
        for (int m = 1; m < 20; m++) {
            int min = (int) Math.pow(2, m);
            int max = min * 2;
            for (int n = min; n <= max; n++) {
                double ratio = (double) HofstadterConwaySequence.HCS[n] / n;
                if (ratio >= 0.55) {
                    mNum = n;
                }
            }
        }
        // The exact value of Mallow's number can be verified against known results
        assertEquals(1489, mNum);
    }
}
