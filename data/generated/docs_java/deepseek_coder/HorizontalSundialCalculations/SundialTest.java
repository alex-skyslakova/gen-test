import static org.junit.Assert.*;
import org.junit.Test;

public class SundialTest {

    @Test
    public void testSineOfLatitude() {
        double lat = 4.95;
        double expectedSlat = Math.sin(Math.toRadians(lat));
        assertEquals(expectedSlat, Math.sin(Math.toRadians(lat)), 0.001);
    }

    @Test
    public void testDiffLongitude() {
        double lng = 150.5;
        double ref = 150.0;
        double expectedDiff = lng - ref;
        assertEquals(expectedDiff, lng - ref, 0.001);
    }

    @Test
    public void testHourCalculation() {
        double lat = 4.95;
        double lng = 150.5;
        double ref = 150.0;
        double slat = Math.sin(Math.toRadians(lat));

        for (int h = -6; h <= 6; h++) {
            double hra = 15.0 * h;
            hra = hra - lng + ref;
            double hraRad = Math.toRadians(hra);
            double hla = Math.toDegrees(Math.atan2(Math.sin(hraRad) * Math.sin(Math.toRadians(lat)), Math.cos(hraRad)));

            // Calculate expected values
            double expectedHra = 15.0 * h - lng + ref;
            double expectedHla = Math.toDegrees(Math.atan2(Math.sin(Math.toRadians(expectedHra)) * slat, Math.cos(Math.toRadians(expectedHra))));

            assertEquals(expectedHra, hra, 0.001);
            assertEquals(expectedHla, hla, 0.001);
        }
    }

    @Test
    public void testBoundaryConditions() {
        double lat = 90.0; // North Pole
        double lng = 0.0;
        double ref = 0.0;
        double slat = Math.sin(Math.toRadians(lat));

        for (int h = -6; h <= 6; h++) {
            double hra = 15.0 * h;
            hra = hra - lng + ref;
            double hraRad = Math.toRadians(hra);
            double hla = Math.toDegrees(Math.atan2(Math.sin(hraRad) * Math.sin(Math.toRadians(lat)), Math.cos(hraRad)));

            // Calculate expected values
            double expectedHra = 15.0 * h - lng + ref;
            double expectedHla = Math.toDegrees(Math.atan2(Math.sin(Math.toRadians(expectedHra)) * slat, Math.cos(Math.toRadians(expectedHra))));

            assertEquals(expectedHra, hra, 0.001);
            assertEquals(expectedHla, hla, 0.001);
        }
    }

    @Test
    public void testNegativeLatitude() {
        double lat = -4.95; // South of the Equator
        double lng = 150.5;
        double ref = 150.0;
        double slat = Math.sin(Math.toRadians(lat));

        for (int h = -6; h <= 6; h++) {
            double hra = 15.0 * h;
            hra = hra - lng + ref;
            double hraRad = Math.toRadians(hra);
            double hla = Math.toDegrees(Math.atan2(Math.sin(hraRad) * Math.sin(Math.toRadians(lat)), Math.cos(hraRad)));

            // Calculate expected values
            double expectedHra = 15.0 * h - lng + ref;
            double expectedHla = Math.toDegrees(Math.atan2(Math.sin(Math.toRadians(expectedHra)) * slat, Math.cos(Math.toRadians(expectedHra))));

            assertEquals(expectedHra, hra, 0.001);
            assertEquals(expectedHla, hla, 0.001);
        }
    }
}
