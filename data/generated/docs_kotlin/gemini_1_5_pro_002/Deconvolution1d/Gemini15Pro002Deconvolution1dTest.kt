import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.math.abs

class Deconvolution1dTest {

    private fun nearlyEqual(a: Double, b: Double, epsilon: Double = 1e-9): Boolean {
        return abs(a - b) < epsilon
    }

    private fun nearlyEqualArrays(a: DoubleArray, b: DoubleArray, epsilon: Double = 1e-9): Boolean {
        if (a.size != b.size) return false
        for (i in a.indices) {
            if (!nearlyEqual(a[i], b[i], epsilon)) return false
        }
        return true
    }


    @Test
    fun testDeconv1() {
        val h = doubleArrayOf(-8.0, -9.0, -3.0, -1.0, -6.0, 7.0)
        val f = doubleArrayOf(-3.0, -6.0, -1.0,  8.0, -6.0,  3.0, -1.0, -9.0, 
                          -9.0,  3.0, -2.0,  5.0,  2.0, -2.0, -7.0, -1.0)
        val g = doubleArrayOf(24.0,  75.0, 71.0, -34.0,  3.0,  22.0, -45.0, 
                          23.0, 245.0, 25.0,  52.0, 25.0, -67.0, -96.0,
                          96.0,  31.0, 55.0,  36.0, 29.0, -43.0,  -7.0)

        val h_recovered = deconv(g, f)
        assertTrue(nearlyEqualArrays(h, h_recovered))
    }


    @Test
    fun testDeconv2() {
        val h = doubleArrayOf(-8.0, -9.0, -3.0, -1.0, -6.0, 7.0)
        val f = doubleArrayOf(-3.0, -6.0, -1.0,  8.0, -6.0,  3.0, -1.0, -9.0, 
                          -9.0,  3.0, -2.0,  5.0,  2.0, -2.0, -7.0, -1.0)
        val g = doubleArrayOf(24.0,  75.0, 71.0, -34.0,  3.0,  22.0, -45.0, 
                          23.0, 245.0, 25.0,  52.0, 25.0, -67.0, -96.0,
                          96.0,  31.0, 55.0,  36.0, 29.0, -43.0,  -7.0)

        val f_recovered = deconv(g, h)
        assertTrue(nearlyEqualArrays(f, f_recovered))

    }

    @Test
    fun testDeconv_f0_is_zero() {
        val h = doubleArrayOf(1.0, 2.0)
        val f = doubleArrayOf(0.0, 1.0)
        val g = doubleArrayOf(0.0, 1.0, 2.0)

        assertThrows(ArithmeticException::class.java) { deconv(g,f) }
    }



}
