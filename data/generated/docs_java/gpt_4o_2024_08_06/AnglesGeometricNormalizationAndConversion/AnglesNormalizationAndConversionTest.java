import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnglesNormalizationAndConversionTest {

    private static final double DELTA = 1e-6;

    @Test
    public void testD2D() {
        assertEquals(-2, AnglesNormalizationAndConversion.d2d(-2), DELTA);
        assertEquals(-1, AnglesNormalizationAndConversion.d2d(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.d2d(0), DELTA);
        assertEquals(1, AnglesNormalizationAndConversion.d2d(1), DELTA);
        assertEquals(2, AnglesNormalizationAndConversion.d2d(2), DELTA);
        assertEquals(6.2831853 % 360, AnglesNormalizationAndConversion.d2d(6.2831853), DELTA);
        assertEquals(16, AnglesNormalizationAndConversion.d2d(16), DELTA);
        assertEquals(57.2957795, AnglesNormalizationAndConversion.d2d(57.2957795), DELTA);
        assertEquals(359, AnglesNormalizationAndConversion.d2d(359), DELTA);
        assertEquals(399 % 360, AnglesNormalizationAndConversion.d2d(399), DELTA);
        assertEquals(6399 % 360, AnglesNormalizationAndConversion.d2d(6399), DELTA);
        assertEquals(1000000 % 360, AnglesNormalizationAndConversion.d2d(1000000), DELTA);
    }

    @Test
    public void testD2G() {
        assertEquals(-2 * (400.0 / 360), AnglesNormalizationAndConversion.d2g(-2), DELTA);
        assertEquals(-1 * (400.0 / 360), AnglesNormalizationAndConversion.d2g(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.d2g(0), DELTA);
        assertEquals(1 * (400.0 / 360), AnglesNormalizationAndConversion.d2g(1), DELTA);
        assertEquals(2 * (400.0 / 360), AnglesNormalizationAndConversion.d2g(2), DELTA);
    }

    @Test
    public void testD2M() {
        assertEquals(-2 * (6400.0 / 360), AnglesNormalizationAndConversion.d2m(-2), DELTA);
        assertEquals(-1 * (6400.0 / 360), AnglesNormalizationAndConversion.d2m(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.d2m(0), DELTA);
        assertEquals(1 * (6400.0 / 360), AnglesNormalizationAndConversion.d2m(1), DELTA);
        assertEquals(2 * (6400.0 / 360), AnglesNormalizationAndConversion.d2m(2), DELTA);
    }

    @Test
    public void testD2R() {
        assertEquals(-2 * (2 * Math.PI / 360), AnglesNormalizationAndConversion.d2r(-2), DELTA);
        assertEquals(-1 * (2 * Math.PI / 360), AnglesNormalizationAndConversion.d2r(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.d2r(0), DELTA);
        assertEquals(1 * (2 * Math.PI / 360), AnglesNormalizationAndConversion.d2r(1), DELTA);
        assertEquals(2 * (2 * Math.PI / 360), AnglesNormalizationAndConversion.d2r(2), DELTA);
    }

    @Test
    public void testG2D() {
        assertEquals(-2 * (360.0 / 400), AnglesNormalizationAndConversion.g2d(-2), DELTA);
        assertEquals(-1 * (360.0 / 400), AnglesNormalizationAndConversion.g2d(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.g2d(0), DELTA);
        assertEquals(1 * (360.0 / 400), AnglesNormalizationAndConversion.g2d(1), DELTA);
        assertEquals(2 * (360.0 / 400), AnglesNormalizationAndConversion.g2d(2), DELTA);
    }

    @Test
    public void testG2G() {
        assertEquals(-2 % 400, AnglesNormalizationAndConversion.g2g(-2), DELTA);
        assertEquals(-1 % 400, AnglesNormalizationAndConversion.g2g(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.g2g(0), DELTA);
        assertEquals(1 % 400, AnglesNormalizationAndConversion.g2g(1), DELTA);
        assertEquals(2 % 400, AnglesNormalizationAndConversion.g2g(2), DELTA);
    }

    @Test
    public void testG2M() {
        assertEquals(-2 * (6400.0 / 400), AnglesNormalizationAndConversion.g2m(-2), DELTA);
        assertEquals(-1 * (6400.0 / 400), AnglesNormalizationAndConversion.g2m(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.g2m(0), DELTA);
        assertEquals(1 * (6400.0 / 400), AnglesNormalizationAndConversion.g2m(1), DELTA);
        assertEquals(2 * (6400.0 / 400), AnglesNormalizationAndConversion.g2m(2), DELTA);
    }

    @Test
    public void testG2R() {
        assertEquals(-2 * (2 * Math.PI / 400), AnglesNormalizationAndConversion.g2r(-2), DELTA);
        assertEquals(-1 * (2 * Math.PI / 400), AnglesNormalizationAndConversion.g2r(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.g2r(0), DELTA);
        assertEquals(1 * (2 * Math.PI / 400), AnglesNormalizationAndConversion.g2r(1), DELTA);
        assertEquals(2 * (2 * Math.PI / 400), AnglesNormalizationAndConversion.g2r(2), DELTA);
    }

    @Test
    public void testM2D() {
        assertEquals(-2 * (360.0 / 6400), AnglesNormalizationAndConversion.m2d(-2), DELTA);
        assertEquals(-1 * (360.0 / 6400), AnglesNormalizationAndConversion.m2d(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.m2d(0), DELTA);
        assertEquals(1 * (360.0 / 6400), AnglesNormalizationAndConversion.m2d(1), DELTA);
        assertEquals(2 * (360.0 / 6400), AnglesNormalizationAndConversion.m2d(2), DELTA);
    }

    @Test
    public void testM2G() {
        assertEquals(-2 * (400.0 / 6400), AnglesNormalizationAndConversion.m2g(-2), DELTA);
        assertEquals(-1 * (400.0 / 6400), AnglesNormalizationAndConversion.m2g(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.m2g(0), DELTA);
        assertEquals(1 * (400.0 / 6400), AnglesNormalizationAndConversion.m2g(1), DELTA);
        assertEquals(2 * (400.0 / 6400), AnglesNormalizationAndConversion.m2g(2), DELTA);
    }

    @Test
    public void testM2M() {
        assertEquals(-2 % 6400, AnglesNormalizationAndConversion.m2m(-2), DELTA);
        assertEquals(-1 % 6400, AnglesNormalizationAndConversion.m2m(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.m2m(0), DELTA);
        assertEquals(1 % 6400, AnglesNormalizationAndConversion.m2m(1), DELTA);
        assertEquals(2 % 6400, AnglesNormalizationAndConversion.m2m(2), DELTA);
    }

    @Test
    public void testM2R() {
        assertEquals(-2 * (2 * Math.PI / 6400), AnglesNormalizationAndConversion.m2r(-2), DELTA);
        assertEquals(-1 * (2 * Math.PI / 6400), AnglesNormalizationAndConversion.m2r(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.m2r(0), DELTA);
        assertEquals(1 * (2 * Math.PI / 6400), AnglesNormalizationAndConversion.m2r(1), DELTA);
        assertEquals(2 * (2 * Math.PI / 6400), AnglesNormalizationAndConversion.m2r(2), DELTA);
    }

    @Test
    public void testR2D() {
        assertEquals(-2 * (360.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2d(-2), DELTA);
        assertEquals(-1 * (360.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2d(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.r2d(0), DELTA);
        assertEquals(1 * (360.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2d(1), DELTA);
        assertEquals(2 * (360.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2d(2), DELTA);
    }

    @Test
    public void testR2G() {
        assertEquals(-2 * (400.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2g(-2), DELTA);
        assertEquals(-1 * (400.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2g(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.r2g(0), DELTA);
        assertEquals(1 * (400.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2g(1), DELTA);
        assertEquals(2 * (400.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2g(2), DELTA);
    }

    @Test
    public void testR2M() {
        assertEquals(-2 * (6400.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2m(-2), DELTA);
        assertEquals(-1 * (6400.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2m(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.r2m(0), DELTA);
        assertEquals(1 * (6400.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2m(1), DELTA);
        assertEquals(2 * (6400.0 / (2 * Math.PI)), AnglesNormalizationAndConversion.r2m(2), DELTA);
    }

    @Test
    public void testR2R() {
        assertEquals(-2 % (2 * Math.PI), AnglesNormalizationAndConversion.r2r(-2), DELTA);
        assertEquals(-1 % (2 * Math.PI), AnglesNormalizationAndConversion.r2r(-1), DELTA);
        assertEquals(0, AnglesNormalizationAndConversion.r2r(0), DELTA);
        assertEquals(1 % (2 * Math.PI), AnglesNormalizationAndConversion.r2r(1), DELTA);
        assertEquals(2 % (2 * Math.PI), AnglesNormalizationAndConversion.r2r(2), DELTA);
    }
}
