import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnglesNormalizationAndConversionTest {

    private static final double DELTA = 1e-6;

    @Test
    void testD2d() {
        assertEquals(-2.0, AnglesNormalizationAndConversion.d2d(-2), DELTA);
        assertEquals(-1.0, AnglesNormalizationAndConversion.d2d(-1), DELTA);
        assertEquals(0.0, AnglesNormalizationAndConversion.d2d(0), DELTA);
        assertEquals(1.0, AnglesNormalizationAndConversion.d2d(1), DELTA);
        assertEquals(2.0, AnglesNormalizationAndConversion.d2d(2), DELTA);
        assertEquals(6.2831853 % 360, AnglesNormalizationAndConversion.d2d(6.2831853), DELTA);
        assertEquals(16.0, AnglesNormalizationAndConversion.d2d(16), DELTA);
        assertEquals(57.2957795 % 360, AnglesNormalizationAndConversion.d2d(57.2957795), DELTA);

        assertEquals(-1.0, AnglesNormalizationAndConversion.d2d(359), DELTA); // -360 + 359 = -1
        assertEquals(-1.0, AnglesNormalizationAndConversion.d2d(399), DELTA);
        assertEquals(-1.0, AnglesNormalizationAndConversion.d2d(6399), DELTA);
        assertEquals(280, AnglesNormalizationAndConversion.d2d(1000000), DELTA);

    }

    @Test
    void testG2g() {
         //Similar tests as d2d, but with 400 instead of 360
        assertEquals(-2.0, AnglesNormalizationAndConversion.g2g(-2), DELTA);
        assertEquals(-1.0, AnglesNormalizationAndConversion.g2g(-1), DELTA);
        assertEquals(0.0, AnglesNormalizationAndConversion.g2g(0), DELTA);
        assertEquals(1.0, AnglesNormalizationAndConversion.g2g(1), DELTA);
        assertEquals(2.0, AnglesNormalizationAndConversion.g2g(2), DELTA);
        assertEquals(6.2831853 % 400, AnglesNormalizationAndConversion.g2g(6.2831853), DELTA);
        assertEquals(16.0, AnglesNormalizationAndConversion.g2g(16), DELTA);
        assertEquals(-1.0, AnglesNormalizationAndConversion.g2g(399), DELTA);
        assertEquals(-1.0, AnglesNormalizationAndConversion.g2g(6399), DELTA); // Remainder of -1 is correct
        assertEquals(0.0, AnglesNormalizationAndConversion.g2g(1000000), DELTA); // 2500 * 400
    }


    @Test
    void testM2m() {
        //Similar tests as d2d, but with 6400 instead of 360
        assertEquals(-2.0, AnglesNormalizationAndConversion.m2m(-2), DELTA);
        assertEquals(-1.0, AnglesNormalizationAndConversion.m2m(-1), DELTA);
        assertEquals(0.0, AnglesNormalizationAndConversion.m2m(0), DELTA);
        assertEquals(1.0, AnglesNormalizationAndConversion.m2m(1), DELTA);
        assertEquals(2.0, AnglesNormalizationAndConversion.m2m(2), DELTA);
        assertEquals(6.2831853 % 6400, AnglesNormalizationAndConversion.m2m(6.2831853), DELTA);
        assertEquals(16.0, AnglesNormalizationAndConversion.m2m(16), DELTA);
        assertEquals(-1.0, AnglesNormalizationAndConversion.m2m(6399), DELTA);
        assertEquals(0.0, AnglesNormalizationAndConversion.m2m(1000000), DELTA);
    }

    @Test
    void testR2r() {
        //Similar tests as d2d, but with 2*PI instead of 360
        double twoPi = 2 * Math.PI;
        assertEquals(-2.0, AnglesNormalizationAndConversion.r2r(-2), DELTA);
        assertEquals(-1.0, AnglesNormalizationAndConversion.r2r(-1), DELTA);
        assertEquals(0.0, AnglesNormalizationAndConversion.r2r(0), DELTA);
        assertEquals(1.0, AnglesNormalizationAndConversion.r2r(1), DELTA);
        assertEquals(2.0, AnglesNormalizationAndConversion.r2r(2), DELTA);
        assertEquals(6.2831853 % twoPi, AnglesNormalizationAndConversion.r2r(6.2831853), DELTA);
        assertEquals(16 % twoPi, AnglesNormalizationAndConversion.r2r(16), DELTA);

    }


    // Tests for conversion functions (d2g, d2m, d2r, g2d, etc.) are omitted for brevity.
    // They would follow a similar pattern: call the conversion function with a 
    // specific input and assert that the output is the expected converted value 
    // (calculated manually or using a reliable calculator), using DELTA for 
    // comparison of floating-point numbers.
    // For example:
    // @Test
    // void testD2g() {
    //     assertEquals(4.444444, AnglesNormalizationAndConversion.d2g(4), DELTA);
    // }



}
