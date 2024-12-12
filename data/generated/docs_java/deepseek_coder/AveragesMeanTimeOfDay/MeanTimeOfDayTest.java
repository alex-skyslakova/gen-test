import org.junit.Test;
import static org.junit.Assert.*;

public class MeanTimeOfDayTest {

    @Test
    public void testMeanAngle() {
        double[] angles = {23.0, 23.67222222222222, 3.1875, 4.325};
        double expectedMeanAngle = 23.50694444444444; // Expected mean angle in degrees
        double actualMeanAngle = MeanTimeOfDay.meanAngle(angles);
        assertEquals(expectedMeanAngle, actualMeanAngle, 0.0001); // Allowing a small delta for floating point precision
    }

    @Test
    public void testTimeToSecs() {
        String time = "23:40:20";
        int expectedSeconds = 85220; // 23 hours * 3600 + 40 minutes * 60 + 20 seconds
        int actualSeconds = MeanTimeOfDay.timeToSecs(time);
        assertEquals(expectedSeconds, actualSeconds);
    }

    @Test
    public void testTimeToDegrees() {
        String time = "00:12:45";
        double expectedDegrees = 3.1875; // 765 seconds / 240
        double actualDegrees = MeanTimeOfDay.timeToDegrees(time);
        assertEquals(expectedDegrees, actualDegrees, 0.0001); // Allowing a small delta for floating point precision
    }

    @Test
    public void testDegreesToTime() {
        double degrees = 23.50694444444444;
        String expectedTime = "23:30:24"; // Expected time in "hh:mm:ss" format
        String actualTime = MeanTimeOfDay.degreesToTime(degrees);
        assertEquals(expectedTime, actualTime);
    }

    @Test
    public void testMain() {
        // This test is more of an integration test to ensure the main method runs without exceptions
        MeanTimeOfDay.main(new String[]{});
    }
}
