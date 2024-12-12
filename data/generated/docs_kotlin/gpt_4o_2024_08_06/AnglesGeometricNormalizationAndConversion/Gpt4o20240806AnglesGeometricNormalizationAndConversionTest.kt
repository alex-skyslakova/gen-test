import kotlin.test.Test
import kotlin.test.assertEquals

class AnglesGeometricNormalizationAndConversionTest {

    private val delta = 1e-6

    @Test
    fun testD2D() {
        assertEquals(-2.0, d2d(-2.0), delta)
        assertEquals(-1.0, d2d(-1.0), delta)
        assertEquals(0.0, d2d(0.0), delta)
        assertEquals(1.0, d2d(1.0), delta)
        assertEquals(2.0, d2d(2.0), delta)
        assertEquals(6.2831853 % 360, d2d(6.2831853), delta)
        assertEquals(16.0, d2d(16.0), delta)
        assertEquals(57.2957795, d2d(57.2957795), delta)
        assertEquals(359.0, d2d(359.0), delta)
        assertEquals(399.0 % 360, d2d(399.0), delta)
        assertEquals(6399.0 % 360, d2d(6399.0), delta)
        assertEquals(1000000.0 % 360, d2d(1000000.0), delta)
    }

    @Test
    fun testD2G() {
        assertEquals(-2.222222, d2g(-2.0), delta)
        assertEquals(-1.111111, d2g(-1.0), delta)
        assertEquals(0.0, d2g(0.0), delta)
        assertEquals(1.111111, d2g(1.0), delta)
        assertEquals(2.222222, d2g(2.0), delta)
        assertEquals(6.981317, d2g(6.2831853 % 360), delta)
        assertEquals(17.777778, d2g(16.0), delta)
        assertEquals(63.661977, d2g(57.2957795), delta)
        assertEquals(398.888889, d2g(359.0), delta)
        assertEquals(442.222222 % 400, d2g(399.0 % 360), delta)
        assertEquals(7111.111111 % 400, d2g(6399.0 % 360), delta)
        assertEquals(1111111.111111 % 400, d2g(1000000.0 % 360), delta)
    }

    @Test
    fun testD2M() {
        assertEquals(-35.555556, d2m(-2.0), delta)
        assertEquals(-17.777778, d2m(-1.0), delta)
        assertEquals(0.0, d2m(0.0), delta)
        assertEquals(17.777778, d2m(1.0), delta)
        assertEquals(35.555556, d2m(2.0), delta)
        assertEquals(111.701587, d2m(6.2831853 % 360), delta)
        assertEquals(284.444444, d2m(16.0), delta)
        assertEquals(1018.666667, d2m(57.2957795), delta)
        assertEquals(6382.222222, d2m(359.0), delta)
        assertEquals(7106.666667 % 6400, d2m(399.0 % 360), delta)
        assertEquals(113333.333333 % 6400, d2m(6399.0 % 360), delta)
        assertEquals(17777777.777778 % 6400, d2m(1000000.0 % 360), delta)
    }

    @Test
    fun testD2R() {
        assertEquals(-0.034907, d2r(-2.0), delta)
        assertEquals(-0.017453, d2r(-1.0), delta)
        assertEquals(0.0, d2r(0.0), delta)
        assertEquals(0.017453, d2r(1.0), delta)
        assertEquals(0.034907, d2r(2.0), delta)
        assertEquals(0.109662, d2r(6.2831853 % 360), delta)
        assertEquals(0.279253, d2r(16.0), delta)
        assertEquals(1.000000, d2r(57.2957795), delta)
        assertEquals(6.265732, d2r(359.0), delta)
        assertEquals(6.981317 % (2 * Math.PI), d2r(399.0 % 360), delta)
        assertEquals(111.701587 % (2 * Math.PI), d2r(6399.0 % 360), delta)
        assertEquals(1745.329252 % (2 * Math.PI), d2r(1000000.0 % 360), delta)
    }

    @Test
    fun testG2D() {
        assertEquals(-1.8, g2d(-2.0), delta)
        assertEquals(-0.9, g2d(-1.0), delta)
        assertEquals(0.0, g2d(0.0), delta)
        assertEquals(0.9, g2d(1.0), delta)
        assertEquals(1.8, g2d(2.0), delta)
        assertEquals(5.654867, g2d(6.2831853 % 400), delta)
        assertEquals(14.4, g2d(16.0), delta)
        assertEquals(51.566201, g2d(57.2957795 % 400), delta)
        assertEquals(323.1, g2d(359.0 % 400), delta)
        assertEquals(359.1, g2d(399.0 % 400), delta)
        assertEquals(5759.1 % 360, g2d(6399.0 % 400), delta)
        assertEquals(900000.0 % 360, g2d(1000000.0 % 400), delta)
    }

    @Test
    fun testG2G() {
        assertEquals(-2.0, g2g(-2.0), delta)
        assertEquals(-1.0, g2g(-1.0), delta)
        assertEquals(0.0, g2g(0.0), delta)
        assertEquals(1.0, g2g(1.0), delta)
        assertEquals(2.0, g2g(2.0), delta)
        assertEquals(6.2831853 % 400, g2g(6.2831853), delta)
        assertEquals(16.0, g2g(16.0), delta)
        assertEquals(57.2957795 % 400, g2g(57.2957795), delta)
        assertEquals(359.0 % 400, g2g(359.0), delta)
        assertEquals(399.0 % 400, g2g(399.0), delta)
        assertEquals(6399.0 % 400, g2g(6399.0), delta)
        assertEquals(1000000.0 % 400, g2g(1000000.0), delta)
    }

    @Test
    fun testG2M() {
        assertEquals(-32.0, g2m(-2.0), delta)
        assertEquals(-16.0, g2m(-1.0), delta)
        assertEquals(0.0, g2m(0.0), delta)
        assertEquals(16.0, g2m(1.0), delta)
        assertEquals(32.0, g2m(2.0), delta)
        assertEquals(100.530965, g2m(6.2831853 % 400), delta)
        assertEquals(256.0, g2m(16.0), delta)
        assertEquals(916.732472, g2m(57.2957795 % 400), delta)
        assertEquals(5744.0, g2m(359.0 % 400), delta)
        assertEquals(6399.0 % 6400, g2m(399.0 % 400), delta)
        assertEquals(102384.0 % 6400, g2m(6399.0 % 400), delta)
        assertEquals(1600000.0 % 6400, g2m(1000000.0 % 400), delta)
    }

    @Test
    fun testG2R() {
        assertEquals(-0.031416, g2r(-2.0), delta)
        assertEquals(-0.015708, g2r(-1.0), delta)
        assertEquals(0.0, g2r(0.0), delta)
        assertEquals(0.015708, g2r(1.0), delta)
        assertEquals(0.031416, g2r(2.0), delta)
        assertEquals(0.098696, g2r(6.2831853 % 400), delta)
        assertEquals(0.251327, g2r(16.0), delta)
        assertEquals(0.897597, g2r(57.2957795 % 400), delta)
        assertEquals(5.654867, g2r(359.0 % 400), delta)
        assertEquals(6.265732 % (2 * Math.PI), g2r(399.0 % 400), delta)
        assertEquals(100.530965 % (2 * Math.PI), g2r(6399.0 % 400), delta)
        assertEquals(1570.796327 % (2 * Math.PI), g2r(1000000.0 % 400), delta)
    }

    @Test
    fun testM2D() {
        assertEquals(-0.1125, m2d(-2.0), delta)
        assertEquals(-0.05625, m2d(-1.0), delta)
        assertEquals(0.0, m2d(0.0), delta)
        assertEquals(0.05625, m2d(1.0), delta)
        assertEquals(0.1125, m2d(2.0), delta)
        assertEquals(0.353324, m2d(6.2831853 % 6400), delta)
        assertEquals(0.9, m2d(16.0), delta)
        assertEquals(3.225, m2d(57.2957795 % 6400), delta)
        assertEquals(33.609375, m2d(359.0 % 6400), delta)
        assertEquals(39.84375 % 360, m2d(399.0 % 6400), delta)
        assertEquals(999.84375 % 360, m2d(6399.0 % 6400), delta)
        assertEquals(156250.0 % 360, m2d(1000000.0 % 6400), delta)
    }

    @Test
    fun testM2G() {
        assertEquals(-0.125, m2g(-2.0), delta)
        assertEquals(-0.0625, m2g(-1.0), delta)
        assertEquals(0.0, m2g(0.0), delta)
        assertEquals(0.0625, m2g(1.0), delta)
        assertEquals(0.125, m2g(2.0), delta)
        assertEquals(0.392699, m2g(6.2831853 % 6400), delta)
        assertEquals(1.0, m2g(16.0), delta)
        assertEquals(3.666667, m2g(57.2957795 % 6400), delta)
        assertEquals(38.75, m2g(359.0 % 6400), delta)
        assertEquals(45.0 % 400, m2g(399.0 % 6400), delta)
        assertEquals(1125.0 % 400, m2g(6399.0 % 6400), delta)
        assertEquals(175000.0 % 400, m2g(1000000.0 % 6400), delta)
    }

    @Test
    fun testM2M() {
        assertEquals(-2.0, m2m(-2.0), delta)
        assertEquals(-1.0, m2m(-1.0), delta)
        assertEquals(0.0, m2m(0.0), delta)
        assertEquals(1.0, m2m(1.0), delta)
        assertEquals(2.0, m2m(2.0), delta)
        assertEquals(6.2831853 % 6400, m2m(6.2831853), delta)
        assertEquals(16.0, m2m(16.0), delta)
        assertEquals(57.2957795 % 6400, m2m(57.2957795), delta)
        assertEquals(359.0 % 6400, m2m(359.0), delta)
        assertEquals(399.0 % 6400, m2m(399.0), delta)
        assertEquals(6399.0 % 6400, m2m(6399.0), delta)
        assertEquals(1000000.0 % 6400, m2m(1000000.0), delta)
    }

    @Test
    fun testM2R() {
        assertEquals(-0.000981, m2r(-2.0), delta)
        assertEquals(-0.000491, m2r(-1.0), delta)
        assertEquals(0.0, m2r(0.0), delta)
        assertEquals(0.000491, m2r(1.0), delta)
        assertEquals(0.000981, m2r(2.0), delta)
        assertEquals(0.003141, m2r(6.2831853 % 6400), delta)
        assertEquals(0.007854, m2r(16.0), delta)
        assertEquals(0.028319, m2r(57.2957795 % 6400), delta)
        assertEquals(0.352556, m2r(359.0 % 6400), delta)
        assertEquals(0.391699 % (2 * Math.PI), m2r(399.0 % 6400), delta)
        assertEquals(6.281186 % (2 * Math.PI), m2r(6399.0 % 6400), delta)
        assertEquals(981.747704 % (2 * Math.PI), m2r(1000000.0 % 6400), delta)
    }

    @Test
    fun testR2D() {
        assertEquals(-114.59156, r2d(-2.0), delta)
        assertEquals(-57.29578, r2d(-1.0), delta)
        assertEquals(0.0, r2d(0.0), delta)
        assertEquals(57.29578, r2d(1.0), delta)
        assertEquals(114.59156, r2d(2.0), delta)
        assertEquals(360.0, r2d(6.2831853 % (2 * Math.PI)), delta)
        assertEquals(916.733, r2d(16.0), delta)
        assertEquals(3283.185, r2d(57.2957795 % (2 * Math.PI)), delta)
        assertEquals(20577.5, r2d(359.0 % (2 * Math.PI)), delta)
        assertEquals(22800.0 % 360, r2d(399.0 % (2 * Math.PI)), delta)
        assertEquals(365760.0 % 360, r2d(6399.0 % (2 * Math.PI)), delta)
        assertEquals(57142857.142857 % 360, r2d(1000000.0 % (2 * Math.PI)), delta)
    }

    @Test
    fun testR2G() {
        assertEquals(-127.32395, r2g(-2.0), delta)
        assertEquals(-63.66198, r2g(-1.0), delta)
        assertEquals(0.0, r2g(0.0), delta)
        assertEquals(63.66198, r2g(1.0), delta)
        assertEquals(127.32395, r2g(2.0), delta)
        assertEquals(400.0, r2g(6.2831853 % (2 * Math.PI)), delta)
        assertEquals(1018.591, r2g(16.0), delta)
        assertEquals(3649.787, r2g(57.2957795 % (2 * Math.PI)), delta)
        assertEquals(22800.0, r2g(359.0 % (2 * Math.PI)), delta)
        assertEquals(25200.0 % 400, r2g(399.0 % (2 * Math.PI)), delta)
        assertEquals(404800.0 % 400, r2g(6399.0 % (2 * Math.PI)), delta)
        assertEquals(63142857.142857 % 400, r2g(1000000.0 % (2 * Math.PI)), delta)
    }

    @Test
    fun testR2M() {
        assertEquals(-2034.907, r2m(-2.0), delta)
        assertEquals(-1017.453, r2m(-1.0), delta)
        assertEquals(0.0, r2m(0.0), delta)
        assertEquals(1017.453, r2m(1.0), delta)
        assertEquals(2034.907, r2m(2.0), delta)
        assertEquals(6400.0, r2m(6.2831853 % (2 * Math.PI)), delta)
        assertEquals(16297.455, r2m(16.0), delta)
        assertEquals(58436.364, r2m(57.2957795 % (2 * Math.PI)), delta)
        assertEquals(365760.0, r2m(359.0 % (2 * Math.PI)), delta)
        assertEquals(404800.0 % 6400, r2m(399.0 % (2 * Math.PI)), delta)
        assertEquals(649600.0 % 6400, r2m(6399.0 % (2 * Math.PI)), delta)
        assertEquals(101714285.714286 % 6400, r2m(1000000.0 % (2 * Math.PI)), delta)
    }

    @Test
    fun testR2R() {
        assertEquals(-2.0, r2r(-2.0), delta)
        assertEquals(-1.0, r2r(-1.0), delta)
        assertEquals(0.0, r2r(0.0), delta)
        assertEquals(1.0, r2r(1.0), delta)
        assertEquals(2.0, r2r(2.0), delta)
        assertEquals(6.2831853 % (2 * Math.PI), r2r(6.2831853), delta)
        assertEquals(16.0 % (2 * Math.PI), r2r(16.0), delta)
        assertEquals(57.2957795 % (2 * Math.PI), r2r(57.2957795), delta)
        assertEquals(359.0 % (2 * Math.PI), r2r(359.0), delta)
        assertEquals(399.0 % (2 * Math.PI), r2r(399.0), delta)
        assertEquals(6399.0 % (2 * Math.PI), r2r(6399.0), delta)
        assertEquals(1000000.0 % (2 * Math.PI), r2r(1000000.0), delta)
    }
}
