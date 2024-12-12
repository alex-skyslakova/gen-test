import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FastFourierTransformTest {

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
    void testFftEmpty() {
        Complex[] input = new Complex[0];
        FastFourierTransform.fft(input);
        assertEquals(0, input.length);
    }

    @Test
    void testFftSingleValue() {
        Complex[] input = {new Complex(1, 0)};
        FastFourierTransform.fft(input);
        assertEquals(1, input.length);
        assertEquals(1, input[0].re, 0.0001);
        assertEquals(0, input[0].im, 0.0001);
    }

    @Test
    void testFftRealValues() {
        double[] input = {1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0};
        Complex[] cinput = new Complex[input.length];
        for (int i = 0; i < input.length; i++)
            cinput[i] = new Complex(input[i], 0.0);

        FastFourierTransform.fft(cinput);

        assertEquals(4, cinput[0].re, 0.0001);
        assertEquals(0, cinput[0].im, 0.0001);
        assertEquals(1, cinput[1].re, 0.0001);
        assertEquals(-2.4142, cinput[1].im, 0.0001);
        assertEquals(0, cinput[2].re, 0.0001);
        assertEquals(0, cinput[2].im, 0.0001);
        assertEquals(1, cinput[3].re, 0.0001);
        assertEquals(-0.4142, cinput[3].im, 0.0001);
         assertEquals(0, cinput[4].re, 0.0001);
        assertEquals(0, cinput[4].im, 0.0001);
        assertEquals(1, cinput[5].re, 0.0001);
        assertEquals(0.4142, cinput[5].im, 0.0001);
        assertEquals(0, cinput[6].re, 0.0001);
        assertEquals(0, cinput[6].im, 0.0001);
        assertEquals(1, cinput[7].re, 0.0001);
        assertEquals(2.4142, cinput[7].im, 0.0001);
    }


    @Test
    void testFftComplexValues() {
        Complex[] input = {new Complex(1, 1), new Complex(2, 2)};

        FastFourierTransform.fft(input);

        assertEquals(3, input[0].re, 0.0001);
        assertEquals(3, input[0].im, 0.0001);
        assertEquals(-1, input[1].re, 0.0001);
        assertEquals(-1, input[1].im, 0.0001);
    }
}
