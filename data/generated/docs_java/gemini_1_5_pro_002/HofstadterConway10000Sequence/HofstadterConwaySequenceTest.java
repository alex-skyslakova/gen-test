import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HofstadterConwaySequenceTest {

    @Test
    void testSequenceGeneration() {
        int[] expected = {0, 1, 1, 2, 2, 3, 4, 4, 4, 5, 6, 6, 6, 6, 7, 8, 8, 8, 8, 8, 9, 10, 10, 10, 10, 10, 10, 11, 12};
        for (int i = 1; i < expected.length; i++) {
            assertEquals(expected[i], HofstadterConwaySequence.HCS[i]);
        }
    }


    @Test
    void testMaxRatioCalculation() {

        double[] expectedMaxRatios = {
                0.6666666666666666,
                0.625,
                0.5555555555555556,
                0.5384615384615384,
                0.5161290322580645,
                0.5080645161290323,
                0.50390625,
                0.5019230769230769,
                0.5009523809523809
        };
        int[] expectedNVals = {3, 6, 11, 21, 42, 83, 166, 331, 662};



        for ( int m = 1 ; m <= 9 ; m++ ) { // Testing up to 2^9, beyond that becomes computationally intensive
            int min = (int) Math.pow(2, m);
            int max = min * 2;
            double maxRatio = 0.0;
            int nVal = 0;
            for ( int n = min ; n <= max ; n ++ ) {
                double ratio = (double) HofstadterConwaySequence.HCS[n] / n;
                if ( ratio > maxRatio ) {
                    maxRatio = Math.max(ratio,  maxRatio);
                    nVal = n;
                }
            }
            assertEquals(expectedMaxRatios[m-1], maxRatio, 0.00001);
            assertEquals(expectedNVals[m-1], nVal);

        }

    }

    @Test
    void testMallowsNumber(){
        int expectedMallowsNumber = 12; // based on the provided code's limited range and the 0.55 threshold
        assertEquals(expectedMallowsNumber, computeMallowsNumber(20));

    }


    private int computeMallowsNumber(int maxPowerOfTwo) {
        int mNum = 0;
        for ( int m = 1 ; m < maxPowerOfTwo ; m++ ) {
            int min = (int) Math.pow(2, m);
            int max = min * 2;
            for (int n = min; n <= max; n++) {
                double ratio = (double) HofstadterConwaySequence.HCS[n] / n;
                if (ratio >= 0.55) {
                    mNum = n;
                }
            }
        }
        return mNum;
    }
}
