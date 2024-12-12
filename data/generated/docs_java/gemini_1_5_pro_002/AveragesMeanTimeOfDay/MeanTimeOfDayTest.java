import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MeanTimeOfDayTest {

    @Test
    void testMeanAngle() {
        double[] angles = {0.0, 90.0, 180.0, 270.0};
        double expectedMean = -45.0; // Or 315.0
        double actualMean = MeanTimeOfDay.meanAngle(angles);
        // Due to the nature of atan2, we need to check if the difference is close to 360
        assertTrue(Math.abs(actualMean - expectedMean) < 1e-6 || Math.abs(actualMean - expectedMean - 360) < 1e-6 || Math.abs(actualMean - expectedMean + 360) < 1e-6);


        double[] angles2 = {0,0,0};
         expectedMean = 0.0;
         actualMean = MeanTimeOfDay.meanAngle(angles2);
        assertEquals(expectedMean,actualMean, 1e-6 );

          double[] angles3 = {90,90,90};
         expectedMean = 90.0;
         actualMean = MeanTimeOfDay.meanAngle(angles3);
        assertEquals(expectedMean,actualMean, 1e-6 );

          double[] angles4 = {180,180,180};
         expectedMean = 180;
         actualMean = MeanTimeOfDay.meanAngle(angles4);
        assertEquals(expectedMean,actualMean, 1e-6 );

          double[] angles5 = {0,45,90};
         expectedMean = 45.0;
         actualMean = MeanTimeOfDay.meanAngle(angles5);
        assertEquals(expectedMean,actualMean, 1e-6 );



    }

    @Test
    void testTimeToSecs() {
        assertEquals(82817, MeanTimeOfDay.timeToSecs("23:00:17"));
        assertEquals(0, MeanTimeOfDay.timeToSecs("00:00:00"));
        assertEquals(3600, MeanTimeOfDay.timeToSecs("01:00:00"));
        assertEquals(60, MeanTimeOfDay.timeToSecs("00:01:00"));
        assertEquals(1, MeanTimeOfDay.timeToSecs("00:00:01"));
    }

    @Test
    void testTimeToDegrees() {
        assertEquals(345.0708333333333, MeanTimeOfDay.timeToDegrees("23:00:17"));
        assertEquals(0.0, MeanTimeOfDay.timeToDegrees("00:00:00"));
        assertEquals(15.0, MeanTimeOfDay.timeToDegrees("01:00:00"));

    }

    @Test
    void testDegreesToTime() {
        assertEquals("23:00:17", MeanTimeOfDay.degreesToTime(345.0708333333333));
        assertEquals("00:00:00", MeanTimeOfDay.degreesToTime(0.0));
        assertEquals("01:00:00", MeanTimeOfDay.degreesToTime(15.0));
        assertEquals("00:00:00", MeanTimeOfDay.degreesToTime(360.0));
         assertEquals("00:00:00", MeanTimeOfDay.degreesToTime(-360.0));
        assertEquals("12:00:00", MeanTimeOfDay.degreesToTime(180.0));
        assertEquals("12:00:00", MeanTimeOfDay.degreesToTime(-180.0));

    }

    @Test
        void testExample(){
               String[] tm = {"23:00:17", "23:40:20", "00:12:45", "00:17:19"};
        double[] angles = new double[4];
        for (int i = 0; i < 4; i++) angles[i] = MeanTimeOfDay.timeToDegrees(tm[i]);        
        double mean = MeanTimeOfDay.meanAngle(angles);
        String expectedTime = "23:52:35";
         String actualTime = MeanTimeOfDay.degreesToTime(mean);
          assertEquals(expectedTime, actualTime);
        }
}
