import org.junit.Assert.assertArrayEquals
import org.junit.Test

class Deconvolution1dTest {

    @Test
    fun testDeconvWithGivenData() {
        val h = doubleArrayOf(-8.0, -9.0, -3.0, -1.0, -6.0, 7.0)
        val f = doubleArrayOf(-3.0, -6.0, -1.0, 8.0, -6.0, 3.0, -1.0, -9.0, -9.0, 3.0, -2.0, 5.0, 2.0, -2.0, -7.0, -1.0)
        val g = doubleArrayOf(24.0, 75.0, 71.0, -34.0, 3.0, 22.0, -45.0, 23.0, 245.0, 25.0, 52.0, 25.0, -67.0, -96.0, 96.0, 31.0, 55.0, 36.0, 29.0, -43.0, -7.0)

        // Test deconv(g, f) = h
        val resultH = deconv(g, f)
        assertArrayEquals(h, resultH, 0.0001)

        // Test deconv(g, h) = f
        val resultF = deconv(g, h)
        assertArrayEquals(f, resultF, 0.0001)
    }

    @Test
    fun testDeconvWithZeroLengthF() {
        val g = doubleArrayOf(1.0, 2.0, 3.0)
        val f = doubleArrayOf()
        val expected = doubleArrayOf(1.0, 2.0, 3.0)

        val result = deconv(g, f)
        assertArrayEquals(expected, result, 0.0001)
    }

    @Test
    fun testDeconvWithZeroLengthG() {
        val g = doubleArrayOf()
        val f = doubleArrayOf(1.0, 2.0, 3.0)
        val expected = doubleArrayOf()

        val result = deconv(g, f)
        assertArrayEquals(expected, result, 0.0001)
    }

    @Test
    fun testDeconvWithEqualLengthFAndG() {
        val g = doubleArrayOf(1.0, 2.0, 3.0)
        val f = doubleArrayOf(1.0, 2.0, 3.0)
        val expected = doubleArrayOf(1.0)

        val result = deconv(g, f)
        assertArrayEquals(expected, result, 0.0001)
    }

    @Test
    fun testDeconvWithFLongerThanG() {
        val g = doubleArrayOf(1.0, 2.0, 3.0)
        val f = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val expected = doubleArrayOf()

        val result = deconv(g, f)
        assertArrayEquals(expected, result, 0.0001)
    }

    @Test
    fun testDeconvWithComplexData() {
        val g = doubleArrayOf(1.0, 3.0, 6.0, 10.0, 15.0, 21.0)
        val f = doubleArrayOf(1.0, 2.0, 3.0)
        val expected = doubleArrayOf(1.0, 1.0, 1.0, 1.0, 1.0)

        val result = deconv(g, f)
        assertArrayEquals(expected, result, 0.0001)
    }
}
