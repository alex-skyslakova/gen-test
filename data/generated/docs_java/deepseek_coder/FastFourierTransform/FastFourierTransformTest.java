import static org.junit.Assert.*;
import org.junit.Test;

public class FastFourierTransformTest {

    @Test
    public void testBitReverse() {
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
    public void testFFT() {
        double[] input = {1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0};
        Complex[] cinput = new Complex[input.length];
        for (int i = 0; i < input.length; i++)
            cinput[i] = new Complex(input[i], 0.0);

        FastFourierTransform.fft(cinput);

        Complex[] expected = {
            new Complex(4.0, 0.0),
            new Complex(1.0, -2.414),
            new Complex(0.0, 0.0),
            new Complex(1.0, -0.414),
            new Complex(0.0, 0.0),
            new Complex(1.0, 0.414),
            new Complex(0.0, 0.0),
            new Complex(1.0, 2.414)
        };

        for (int i = 0; i < cinput.length; i++) {
            assertEquals(expected[i].re, cinput[i].re, 0.001);
            assertEquals(expected[i].im, cinput[i].im, 0.001);
        }
    }

    @Test
    public void testFFTWithSingleElement() {
        double[] input = {1.0};
        Complex[] cinput = new Complex[input.length];
        for (int i = 0; i < input.length; i++)
            cinput[i] = new Complex(input[i], 0.0);

        FastFourierTransform.fft(cinput);

        Complex[] expected = {
            new Complex(1.0, 0.0)
        };

        assertEquals(expected[0].re, cinput[0].re, 0.001);
        assertEquals(expected[0].im, cinput[0].im, 0.001);
    }

    @Test
    public void testFFTWithComplexInput() {
        Complex[] input = {
            new Complex(1.0, 0.0),
            new Complex(0.0, 1.0),
            new Complex(1.0, 0.0),
            new Complex(0.0, 1.0)
        };

        FastFourierTransform.fft(input);

        Complex[] expected = {
            new Complex(2.0, 2.0),
            new Complex(0.0, 0.0),
            new Complex(2.0, -2.0),
            new Complex(0.0, 0.0)
        };

        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i].re, input[i].re, 0.001);
            assertEquals(expected[i].im, input[i].im, 0.001);
        }
    }

    @Test
    public void testFFTWithEmptyInput() {
        Complex[] input = {};
        FastFourierTransform.fft(input);
        assertEquals(0, input.length);
    }
}
