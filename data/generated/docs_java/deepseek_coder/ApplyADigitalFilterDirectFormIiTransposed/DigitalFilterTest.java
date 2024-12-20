import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DigitalFilterTest {

    @Test
    public void testFilterWithProvidedCoefficientsAndSignal() {
        double[] a = new double[]{1.00000000, -2.77555756e-16, 3.33333333e-01, -1.85037171e-17};
        double[] b = new double[]{0.16666667, 0.5, 0.5, 0.16666667};

        double[] signal = new double[]{
            -0.917843918645, 0.141984778794, 1.20536903482, 0.190286794412,
            -0.662370894973, -1.00700480494, -0.404707073677, 0.800482325044,
            0.743500089861, 1.01090520172, 0.741527555207, 0.277841675195,
            0.400833448236, -0.2085993586, -0.172842103641, -0.134316096293,
            0.0259303398477, 0.490105989562, 0.549391221511, 0.9047198589
        };

        double[] expectedResult = new double[]{
            -0.15297399, 0.02366413, 0.20089484, 0.03171447, -0.11039515,
            -0.16783413, -0.06745118, 0.13341372, 0.12391668, 0.16848420,
            0.12358793, 0.04630695, 0.06680557, -0.03476656, -0.02880702,
            -0.02238602, 0.00432172, 0.08168433, 0.09156520, 0.15078664
        };

        double[] result = DigitalFilter.filter(a, b, signal);

        assertArrayEquals(expectedResult, result, 1e-8);
    }

    @Test
    public void testFilterWithEmptySignal() {
        double[] a = new double[]{1.00000000, -2.77555756e-16, 3.33333333e-01, -1.85037171e-17};
        double[] b = new double[]{0.16666667, 0.5, 0.5, 0.16666667};

        double[] signal = new double[]{};

        double[] expectedResult = new double[]{};

        double[] result = DigitalFilter.filter(a, b, signal);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testFilterWithSingleElementSignal() {
        double[] a = new double[]{1.00000000, -2.77555756e-16, 3.33333333e-01, -1.85037171e-17};
        double[] b = new double[]{0.16666667, 0.5, 0.5, 0.16666667};

        double[] signal = new double[]{-0.917843918645};

        double[] expectedResult = new double[]{-0.15297399};

        double[] result = DigitalFilter.filter(a, b, signal);

        assertArrayEquals(expectedResult, result, 1e-8);
    }

    @Test
    public void testFilterWithAllZeroCoefficients() {
        double[] a = new double[]{1.0, 0.0, 0.0, 0.0};
        double[] b = new double[]{0.0, 0.0, 0.0, 0.0};

        double[] signal = new double[]{
            -0.917843918645, 0.141984778794, 1.20536903482, 0.190286794412,
            -0.662370894973, -1.00700480494, -0.404707073677, 0.800482325044,
            0.743500089861, 1.01090520172, 0.741527555207, 0.277841675195,
            0.400833448236, -0.2085993586, -0.172842103641, -0.134316096293,
            0.0259303398477, 0.490105989562, 0.549391221511, 0.9047198589
        };

        double[] expectedResult = new double[]{
            0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0
        };

        double[] result = DigitalFilter.filter(a, b, signal);

        assertArrayEquals(expectedResult, result, 1e-8);
    }
}
