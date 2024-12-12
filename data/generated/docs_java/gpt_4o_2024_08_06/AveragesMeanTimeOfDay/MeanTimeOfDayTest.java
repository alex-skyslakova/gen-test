import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MeanTimeOfDayTest {

    @Test
    public void testTimeToSecs() {
        assertEquals(82817, MeanTimeOfDay.timeToSecs("23:00:17"));
        assertEquals(85220, MeanTimeOfDay.timeToSecs("23:40:20"));
        assertEquals(765, MeanTimeOfDay.timeToSecs("00:12:45"));
        assertEquals(1039, MeanTimeOfDay.timeToSecs("00:17:19"));
    }

    @Test
    public void testTimeToDegrees() {
        assertEquals(345.0708333333333, MeanTimeOfDay.timeToDegrees("23:00:17"), 0.0001);
        assertEquals(355.0833333333333, MeanTimeOfDay.timeToDegrees("23:40:20"), 0.0001);
        assertEquals(3.1875, MeanTimeOfDay.timeToDegrees("00:12:45"), 0.0001);
        assertEquals(4.329166666666667, MeanTimeOfDay.timeToDegrees("00:17:19"), 0.0001);
    }

    @Test
    public void testDegreesToTime() {
        assertEquals("23:00:17", MeanTimeOfDay.degreesToTime(345.0708333333333));
        assertEquals("23:40:20", MeanTimeOfDay.degreesToTime(355.0833333333333));
        assertEquals(" 0:12:45", MeanTimeOfDay.degreesToTime(3.1875));
        assertEquals(" 0:17:19", MeanTimeOfDay.degreesToTime(4.329166666666667));
    }

    @Test
    public void testMeanAngle() {
        double[] angles = {345.0708333333333, 355.0833333333333, 3.1875, 4.329166666666667};
        assertEquals(356.4625, MeanTimeOfDay.meanAngle(angles), 0.0001);
    }

    @Test
    public void testAverageTimeCalculation() {
        String[] tm = {"23:00:17", "23:40:20", "00:12:45", "00:17:19"};
        double[] angles = new double[4];
        for (int i = 0; i < 4; i++) angles[i] = MeanTimeOfDay.timeToDegrees(tm[i]);
        double mean = MeanTimeOfDay.meanAngle(angles);
        assertEquals("23:45:51", MeanTimeOfDay.degreesToTime(mean));
    }
}
