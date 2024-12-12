import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HoughTransformTest {

    @Test
    public void testHoughTransform() {
        // Create a simple input data with a single line
        int[] inputArray = new int[100];
        for (int i = 0; i < 10; i++) {
            inputArray[i * 10 + 5] = 255; // A vertical line at x=5
        }
        HoughTransform.ArrayData inputData = new HoughTransform.ArrayData(inputArray, 10, 10);

        // Perform the Hough transform
        HoughTransform.ArrayData outputData = HoughTransform.houghTransform(inputData, 180, 200, 128);

        // Verify the output data
        // For a vertical line at x=5, we expect a peak at theta=0 and rho=5
        int thetaAxisSize = 180;
        int rAxisSize = 200;
        int halfRAxisSize = rAxisSize / 2;
        int expectedRho = 5;
        int expectedTheta = 0;
        int expectedRhoScaled = expectedRho + halfRAxisSize;

        // Check the value at the expected peak
        int peakValue = outputData.get(expectedTheta, expectedRhoScaled);
        assertTrue(peakValue > 0, "Expected a peak at theta=" + expectedTheta + ", rho=" + expectedRhoScaled);

        // Check that the peak is the maximum value in the output data
        int maxValue = outputData.getMax();
        assertEquals(peakValue, maxValue, "The peak value should be the maximum value in the output data");
    }

    @Test
    public void testArrayDataContrast() {
        // Create a simple input data with a contrast point
        int[] inputArray = new int[100];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                inputArray[i * 10 + j] = 100;
            }
        }
        inputArray[55] = 200; // A point with high contrast

        HoughTransform.ArrayData inputData = new HoughTransform.ArrayData(inputArray, 10, 10);

        // Test contrast method
        assertTrue(inputData.contrast(5, 5, 50), "Expected contrast at (5, 5)");
        assertFalse(inputData.contrast(0, 0, 50), "Expected no contrast at (0, 0)");
    }

    @Test
    public void testArrayDataAccumulate() {
        HoughTransform.ArrayData arrayData = new HoughTransform.ArrayData(10, 10);

        // Accumulate values
        arrayData.accumulate(2, 3, 5);
        arrayData.accumulate(2, 3, 3);

        // Check the accumulated value
        assertEquals(8, arrayData.get(2, 3), "Expected accumulated value at (2, 3) to be 8");
    }

    @Test
    public void testArrayDataGetMax() {
        int[] inputArray = new int[100];
        for (int i = 0; i < 100; i++) {
            inputArray[i] = i;
        }
        HoughTransform.ArrayData arrayData = new HoughTransform.ArrayData(inputArray, 10, 10);

        // Check the maximum value
        assertEquals(99, arrayData.getMax(), "Expected maximum value to be 99");
    }
}
