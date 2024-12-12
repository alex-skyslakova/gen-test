import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DotProductTest {

    @Test
    void testDotProduct_sameLength() {
        double[] a = {1, 3, -5};
        double[] b = {4, -2, -1};
        assertEquals(3, DotProduct.dotProd(a, b));
    }

    @Test
    void testDotProduct_emptyVectors() {
        double[] a = {};
        double[] b = {};
        assertEquals(0, DotProduct.dotProd(a, b));
    }

    @Test
    void testDotProduct_differentLengths() {
        double[] a = {1, 2, 3};
        double[] b = {4, 5};
        assertThrows(IllegalArgumentException.class, () -> DotProduct.dotProd(a, b));
    }

    @Test
    void testDotProduct_zeroVector() {
        double[] a = {1, 2, 3};
        double[] b = {0, 0, 0};
        assertEquals(0, DotProduct.dotProd(a, b));
    }

    @Test
    void testDotProduct_largeVectors() {
        double[] a = new double[1000];
        double[] b = new double[1000];
        for (int i = 0; i < 1000; i++) {
            a[i] = i;
            b[i] = i * 2;
        }
        double expected = 0;
        for (int i = 0; i < 1000; i++) {
            expected += a[i] * b[i];
        }
        assertEquals(expected, DotProduct.dotProd(a, b));
    }

    @Test
    void testDotProduct_negativeValues() {
        double[] a = {-1, -2, -3};
        double[] b = {-4, -5, -6};
        assertEquals(32, DotProduct.dotProd(a,b));

    }


}
