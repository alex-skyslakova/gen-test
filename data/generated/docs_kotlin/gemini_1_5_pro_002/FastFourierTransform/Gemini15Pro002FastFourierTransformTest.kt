import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.math.*

class ComplexTest {

    @Test
    fun testPlus() {
        assertEquals(Complex(3.0, 4.0), Complex(1.0, 2.0) + Complex(2.0, 2.0))
    }

    @Test
    fun testMinus() {
        assertEquals(Complex(-1.0, 0.0), Complex(1.0, 2.0) - Complex(2.0, 2.0))
    }

    @Test
    fun testTimesDouble() {
        assertEquals(Complex(2.0, 4.0), Complex(1.0, 2.0) * 2.0)
    }

    @Test
    fun testTimesComplex() {
        assertEquals(Complex(-3.0, 4.0), Complex(1.0, 2.0) * Complex(1.0, -1.0))
    }

    @Test
    fun testDiv() {
        assertEquals(Complex(0.5, 1.0), Complex(1.0, 2.0) / 2.0)
    }
    
    @Test
    fun testExp() {
        val complex = Complex(1.0, PI / 2)
        val expected = Complex(0.0, E)
        val actual = complex.exp
        assertEquals(expected.re, actual.re, 0.001)
        assertEquals(expected.im, actual.im, 0.001)


        val complex2 = Complex(0.0, 0.0)
        val expected2 = Complex(1.0, 0.0)
        val actual2 = complex2.exp
        assertEquals(expected2.re, actual2.re, 0.001)
        assertEquals(expected2.im, actual2.im, 0.001)


        val complex3 = Complex(0.0, PI)
        val expected3 = Complex(-1.0, 0.0)
        val actual3 = complex3.exp
        assertEquals(expected3.re, actual3.re, 0.001)
        assertEquals(expected3.im, actual3.im, 0.001)
    }

    @Test
    fun testToString() {
        assertEquals("1.000 + 2.000i", Complex(1.0, 2.0).toString())
        assertEquals("1.000 - 2.000i", Complex(1.0, -2.0).toString())
        assertEquals("1.000", Complex(1.0, 0.0).toString())
        assertEquals("2.000i", Complex(0.0, 2.0).toString())
        assertEquals("-2.000i", Complex(0.0, -2.0).toString())

    }
}



// Note:  FFT tests are omitted because the FFT implementation
// itself is not provided in the problem description.  The 
// prompt only asks for tests *based on the provided code*, which
// is the Complex class. Once the FFT implementation is available, 
// FFT tests can be written based on various input sequences
// (e.g. impulse, constant signal, sine wave etc.)  and their 
// corresponding expected FFT outputs.


