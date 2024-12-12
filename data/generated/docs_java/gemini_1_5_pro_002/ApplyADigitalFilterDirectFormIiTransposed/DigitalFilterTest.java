import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DigitalFilterTest {

    @Test
    void testFilter_emptySignal() {
        double[] a = {1.0, 0.5};
        double[] b = {0.2, 0.3};
        double[] signal = {};
        double[] expected = {};
        double[] result = DigitalFilter.filter(a, b, signal);
        assertArrayEquals(expected, result);
    }

    @Test
    void testFilter_impulseResponse() {
        double[] a = {1.0, 0.0};
        double[] b = {1.0, 0.0};
        double[] signal = {1.0, 0.0, 0.0};
        double[] expected = {1.0, 0.0, 0.0};
        double[] result = DigitalFilter.filter(a, b, signal);
        assertArrayEquals(expected, result, 1e-8);
    }


    @Test
    void testFilter_simpleMovingAverage() {
        double[] a = {1.0};
        double[] b = {0.5, 0.5};
        double[] signal = {1.0, 2.0, 3.0};
        double[] expected = {0.5, 1.5, 2.5};
        double[] result = DigitalFilter.filter(a, b, signal);
        assertArrayEquals(expected, result, 1e-8);
    }

    @Test
    void testFilter_givenExample() {
        double[] a = new double[]{1.00000000, -2.77555756e-16, 3.33333333e-01, -1.85037171e-17};
        double[] b = new double[]{0.16666667, 0.5, 0.5, 0.16666667};

        double[] signal = new double[]{
                -0.917843918645, 0.141984778794, 1.20536903482, 0.190286794412,
                -0.662370894973, -1.00700480494, -0.404707073677, 0.800482325044,
                0.743500089861, 1.01090520172, 0.741527555207, 0.277841675195,
                0.400833448236, -0.2085993586, -0.172842103641, -0.134316096293,
                0.0259303398477, 0.490105989562, 0.549391221511, 0.9047198589
        };

        double[] expected = new double[]{
                -0.152973986441, 0.043676742933, 0.546119609986, 0.314552066634,
                -0.152238837911, -0.628755979839, -0.440255812625, 0.141324827857,
                0.560803212480, 0.822061838868, 0.673199059036, 0.404518770015,
                0.293354061954, -0.011787214107, -0.134255651967, -0.131330316954,
                -0.017398249570, 0.229061641299, 0.427120200696, 0.647647062467
        };
        double[] result = DigitalFilter.filter(a, b, signal);
        assertArrayEquals(expected, result, 1e-8);
    }

     @Test
    void testFilter_aEqualsZero() {
        double[] a = {0.0, 0.5};
        double[] b = {0.2, 0.3};
        double[] signal = {1.0, 2.0};
        assertThrows(ArithmeticException.class, () ->  DigitalFilter.filter(a, b, signal));
    }


}
