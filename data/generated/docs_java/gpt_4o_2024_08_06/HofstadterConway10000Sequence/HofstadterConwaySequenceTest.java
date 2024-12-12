import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HofstadterConwaySequenceTest {

    @Test
    public void testInitialValues() {
        assertEquals(1, HofstadterConwaySequence.HCS[1]);
        assertEquals(1, HofstadterConwaySequence.HCS[2]);
    }

    @Test
    public void testSequenceGeneration() {
        // Test a few known values from the sequence
        assertEquals(2, HofstadterConwaySequence.HCS[3]);
        assertEquals(2, HofstadterConwaySequence.HCS[4]);
        assertEquals(3, HofstadterConwaySequence.HCS[5]);
        assertEquals(4, HofstadterConwaySequence.HCS[6]);
        assertEquals(4, HofstadterConwaySequence.HCS[7]);
        assertEquals(4, HofstadterConwaySequence.HCS[8]);
        assertEquals(5, HofstadterConwaySequence.HCS[9]);
    }

    @Test
    public void testMaxRatioCalculation() {
        int mNum = 0;
        for (int m = 1; m < 20; m++) {
            int min = (int) Math.pow(2, m);
            int max = min * 2;
            double maxRatio = 0.0;
            int nVal = 0;
            for (int n = min; n <= max; n++) {
                double ratio = (double) HofstadterConwaySequence.HCS[n] / n;
                if (ratio > maxRatio) {
                    maxRatio = Math.max(ratio, maxRatio);
                    nVal = n;
                }
                if (ratio >= 0.55) {
                    mNum = n;
                }
            }
            assertTrue(maxRatio < 0.55, "Max ratio should be less than 0.55");
        }
    }

    @Test
    public void testMallowsNumber() {
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
        assertEquals(3_173_375_556, mNum, "Mallow's number should be 3,173,375,556");
    }
}
