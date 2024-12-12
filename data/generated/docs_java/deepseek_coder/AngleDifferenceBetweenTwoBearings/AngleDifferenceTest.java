import static org.junit.Assert.*;
import org.junit.Test;

public class AngleDifferenceTest {

    @Test
    public void testBasicAngles() {
        assertEquals(25.0, AngleDifference.getDifference(20.0, 45.0), 0.0001);
        assertEquals(90.0, AngleDifference.getDifference(-45.0, 45.0), 0.0001);
        assertEquals(175.0, AngleDifference.getDifference(-85.0, 90.0), 0.0001);
        assertEquals(-175.0, AngleDifference.getDifference(-95.0, 90.0), 0.0001);
        assertEquals(170.0, AngleDifference.getDifference(-45.0, 125.0), 0.0001);
        assertEquals(190.0, AngleDifference.getDifference(-45.0, 145.0), 0.0001);
        assertEquals(118.1184, AngleDifference.getDifference(29.4803, -88.6381), 0.0001);
        assertEquals(-80.7109, AngleDifference.getDifference(-78.3251, -159.036), 0.0001);
    }

    @Test
    public void testWiderRangeAngles() {
        assertEquals(-139.58328312338563, AngleDifference.getDifference(-70099.74233810938, 29840.67437876723), 0.0001);
        assertEquals(-72.34391851868713, AngleDifference.getDifference(-165313.6666297357, 33693.9894517456), 0.0001);
        assertEquals(-161.5029523074151, AngleDifference.getDifference(1174.8380510598456, -154146.66490124757), 0.0001);
        assertEquals(-157.1844648142687, AngleDifference.getDifference(60175.77306795546, 42213.07192354373), 0.0001);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0.0, AngleDifference.getDifference(0.0, 0.0), 0.0001);
        assertEquals(180.0, AngleDifference.getDifference(-180.0, 180.0), 0.0001);
        assertEquals(-180.0, AngleDifference.getDifference(180.0, -180.0), 0.0001);
        assertEquals(180.0, AngleDifference.getDifference(90.0, -90.0), 0.0001);
        assertEquals(-180.0, AngleDifference.getDifference(-90.0, 90.0), 0.0001);
    }

    @Test
    public void testLargeNegativeAngles() {
        assertEquals(170.0, AngleDifference.getDifference(-36000.0, -35830.0), 0.0001);
        assertEquals(-170.0, AngleDifference.getDifference(-35830.0, -36000.0), 0.0001);
    }

    @Test
    public void testLargePositiveAngles() {
        assertEquals(170.0, AngleDifference.getDifference(36000.0, 36170.0), 0.0001);
        assertEquals(-170.0, AngleDifference.getDifference(36170.0, 36000.0), 0.0001);
    }

    @Test
    public void testMixedLargeAngles() {
        assertEquals(170.0, AngleDifference.getDifference(-36000.0, 36170.0), 0.0001);
        assertEquals(-170.0, AngleDifference.getDifference(36170.0, -36000.0), 0.0001);
    }
}
