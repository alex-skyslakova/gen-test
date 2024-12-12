import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FastFourierTransformTest {

    @Test
    void testBitReverse() {
        assertEquals(0, FastFourierTransform.bitReverse(0, 3));
        assertEquals(4, FastFourierTransform.bitReverse(1, 3));
        assertEquals(2, FastFourierTransform.bitReverse(2, 3));
        assertEquals(6, FastFourierTransform.bitReverse(3, 3));
        assertEquals(1, FastFourierTransform.bitReverse(4, 3));
        assertEquals(5, FastFourierTransform.bitReverse(5, 3));
        assertEquals(3, FastFourierTransform.bitReverse(6, 3));
        assertEquals(7, FastFourierTransform.bitReverse(7, 3));
    }

    @Test
    void testFFTWithRealNumbers() {
        double[] input = {1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0};
        Complex[] cinput = new Complex[input.length];
        for (int i = 0; i < input.length; i++) {
            cinput[i] = new Complex(input[i], 0.0);
        }

        FastFourierTransform.fft(cinput);

        double[] expectedMagnitudes = {4.0, 2.613, 0.0, 0.613, 0.0, 0.613, 0.0, 2.613};
        for (int i = 0; i < cinput.length; i++) {
            double magnitude = Math.sqrt(cinput[i].re * cinput[i].re + cinput[i].im * cinput[i].im);
            assertEquals(expectedMagnitudes[i], magnitude, 0.001);
        }
    }

    @Test
    void testFFTWithComplexNumbers() {
        Complex[] input = {
            new Complex(1.0, 1.0),
            new Complex(1.0, -1.0),
            new Complex(-1.0, 1.0),
            new Complex(-1.0, -1.0),
            new Complex(0.0, 0.0),
            new Complex(0.0, 0.0),
            new Complex(0.0, 0.0),
            new Complex(0.0, 0.0)
        };

        FastFourierTransform.fft(input);

        // Expected results are derived from manual calculation or a trusted FFT library
        Complex[] expected = {
            new Complex(0.0, 0.0),
            new Complex(0.0, 0.0),
            new Complex(0.0, 0.0),
            new Complex(0.0, 0.0),
            new Complex(4.0, 0.0),
            new Complex(0.0, 0.0),
            new Complex(0.0, 0.0),
            new Complex(0.0, 0.0)
        };

        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i].re, input[i].re, 0.001);
            assertEquals(expected[i].im, input[i].im, 0.001);
        }
    }
}
