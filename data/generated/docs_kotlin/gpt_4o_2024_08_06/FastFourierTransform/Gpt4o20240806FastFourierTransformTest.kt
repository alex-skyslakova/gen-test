import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.math.sqrt

class FastFourierTransformTest {

    @Test
    fun testComplexAddition() {
        val c1 = Complex(1.0, 2.0)
        val c2 = Complex(3.0, 4.0)
        val result = c1 plus c2
        assertEquals(Complex(4.0, 6.0), result)
    }

    @Test
    fun testComplexSubtraction() {
        val c1 = Complex(5.0, 6.0)
        val c2 = Complex(3.0, 4.0)
        val result = c1 minus c2
        assertEquals(Complex(2.0, 2.0), result)
    }

    @Test
    fun testComplexMultiplicationWithScalar() {
        val c = Complex(2.0, 3.0)
        val scalar = 2.0
        val result = c times scalar
        assertEquals(Complex(4.0, 6.0), result)
    }

    @Test
    fun testComplexMultiplicationWithComplex() {
        val c1 = Complex(1.0, 2.0)
        val c2 = Complex(3.0, 4.0)
        val result = c1 times c2
        assertEquals(Complex(-5.0, 10.0), result)
    }

    @Test
    fun testComplexDivisionWithScalar() {
        val c = Complex(4.0, 6.0)
        val scalar = 2.0
        val result = c div scalar
        assertEquals(Complex(2.0, 3.0), result)
    }

    @Test
    fun testComplexExponential() {
        val c = Complex(0.0, Math.PI)
        val result = c.exp
        assertEquals(Complex(-1.0, 0.0), result)
    }

    @Test
    fun testComplexToString() {
        val c1 = Complex(1.0, 0.0)
        assertEquals("1.000", c1.toString())

        val c2 = Complex(0.0, 1.0)
        assertEquals("1.000i", c2.toString())

        val c3 = Complex(1.0, 1.0)
        assertEquals("1.000 + 1.000i", c3.toString())

        val c4 = Complex(1.0, -1.0)
        assertEquals("1.000 - 1.000i", c4.toString())
    }

    @Test
    fun testComplexMagnitude() {
        val c = Complex(3.0, 4.0)
        val magnitude = sqrt(c.re * c.re + c.im * c.im)
        assertEquals(5.0, magnitude)
    }
}
