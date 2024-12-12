import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DigitalFilterTest {

    @Test
    public void testFilter() {
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
            -0.15297399, -0.38793691, 0.13889593, 0.55796451, 
            0.17235913, -0.40992647, -0.59190532, -0.04687471, 
            0.57928868, 0.90873243, 0.87947686, 0.58823957, 
            0.27389215, 0.04872811, -0.01486825, 0.01540897, 
            0.11767249, 0.30369468, 0.49776411, 0.66225992
        };

        double[] result = DigitalFilter.filter(a, b, signal);
        assertArrayEquals(expected, result, 1e-8);
    }
}