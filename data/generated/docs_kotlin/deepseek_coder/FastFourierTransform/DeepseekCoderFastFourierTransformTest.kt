import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FastFourierTransformTest {

    @Test
    fun `test FFT with real input`() {
        val input = listOf(Complex(1.0, 0.0), Complex(2.0, 0.0), Complex(3.0, 0.0), Complex(4.0, 0.0))
        val expected = listOf(
            Complex(10.0, 0.0),
            Complex(-2.0, 2.0),
            Complex(-2.0, 0.0),
            Complex(-2.0, -2.0)
        )

        val result = fft(input)

        assertComplexListEquals(expected, result)
    }

    @Test
    fun `test FFT with complex input`() {
        val input = listOf(
            Complex(1.0, 1.0),
            Complex(2.0, 2.0),
            Complex(3.0, 3.0),
            Complex(4.0, 4.0)
        )
        val expected = listOf(
            Complex(10.0, 10.0),
            Complex(-2.0, 2.0),
            Complex(-2.0, -2.0),
            Complex(2.0, -2.0)
        )

        val result = fft(input)

        assertComplexListEquals(expected, result)
    }

    @Test
    fun `test FFT with single element input`() {
        val input = listOf(Complex(1.0, 0.0))
        val expected = listOf(Complex(1.0, 0.0))

        val result = fft(input)

        assertComplexListEquals(expected, result)
    }

    @Test
    fun `test FFT with empty input`() {
        val input = emptyList<Complex>()
        val expected = emptyList<Complex>()

        val result = fft(input)

        assertComplexListEquals(expected, result)
    }

    @Test
    fun `test FFT with odd length input`() {
        val input = listOf(Complex(1.0, 0.0), Complex(2.0, 0.0), Complex(3.0, 0.0))
        val expected = listOf(
            Complex(6.0, 0.0),
            Complex(-1.5, 0.866),
            Complex(-1.5, -0.866)
        )

        val result = fft(input)

        assertComplexListEquals(expected, result)
    }

    private fun assertComplexListEquals(expected: List<Complex>, actual: List<Complex>) {
        assertEquals(expected.size, actual.size)
        for (i in expected.indices) {
            assertEquals(expected[i].re, actual[i].re, 0.001)
            assertEquals(expected[i].im, actual[i].im, 0.001)
        }
    }

    private fun fft(input: List<Complex>): List<Complex> {
        // Placeholder for the actual FFT implementation
        // This should be replaced with the actual implementation of the FFT algorithm
        return input
    }
}
