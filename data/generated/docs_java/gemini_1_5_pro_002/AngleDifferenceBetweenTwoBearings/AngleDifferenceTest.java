import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AngleDifferenceTest {

    @Test
    void getDifference_withinRange() {
        assertEquals(25.0, AngleDifference.getDifference(20.0, 45.0));
        assertEquals(90.0, AngleDifference.getDifference(-45.0, 45.0));
        assertEquals(175.0, AngleDifference.getDifference(-85.0, 90.0));
        assertEquals(-175.0, AngleDifference.getDifference(-95.0, 90.0));
        assertEquals(170.0, AngleDifference.getDifference(-45.0, 125.0));
        assertEquals(-170.0, AngleDifference.getDifference(-45.0, 145.0));
        assertEquals(-118.1184, AngleDifference.getDifference(29.4803, -88.6381));
        assertEquals(80.7109, AngleDifference.getDifference(-78.3251, -159.036));
    }

    @Test
    void getDifference_widerRange() {
        assertEquals(-149.06796133285714, AngleDifference.getDifference(-70099.74233810938, 29840.67437876723));
        assertEquals(144.69232778778485, AngleDifference.getDifference(-165313.6666297357, 33693.9894517456));
        assertEquals(22.801335599282584, AngleDifference.getDifference(1174.8380510598456, -154146.66490124757));
        assertEquals(-179.62700114441173, AngleDifference.getDifference(60175.77306795546, 42213.07192354373));
    }

    @Test
    void getDifference_edgeCases(){
        assertEquals(0.0, AngleDifference.getDifference(180.0, -180.0));
        assertEquals(0.0, AngleDifference.getDifference(180.0, 540.0));
        assertEquals(-180.0, AngleDifference.getDifference(0.0, 180.0));

    }
}
