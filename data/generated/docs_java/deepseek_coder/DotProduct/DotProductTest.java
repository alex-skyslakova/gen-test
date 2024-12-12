import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DotProductTest {

    @Test
    public void testDotProductSameLengthVectors() {
        double[] a = {1, 3, -5};
        double[] b = {4, -2, -1};
        double expected = 3.0;
        double result = DotProduct.dotProd(a, b);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testDotProductWithZeroVector() {
        double[] a = {0, 0, 0};
        double[] b = {4, -2, -1};
        double expected = 0.0;
        double result = DotProduct.dotProd(a, b);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testDotProductWithNegativeValues() {
        double[] a = {-1, -2, -3};
        double[] b = {4, -2, -1};
        double expected = 3.0;
        double result = DotProduct.dotProd(a, b);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testDotProductWithDifferentLengthVectors() {
        double[] a = {1, 3, -5, 2};
        double[] b = {4, -2, -1};
        assertThrows(IllegalArgumentException.class, () -> {
            DotProduct.dotProd(a, b);
        });
    }

    @Test
    public void testDotProductWithEmptyVectors() {
        double[] a = {};
        double[] b = {};
        double expected = 0.0;
        double result = DotProduct.dotProd(a, b);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testDotProductWithSingleElementVectors() {
        double[] a = {2};
        double[] b = {3};
        double expected = 6.0;
        double result = DotProduct.dotProd(a, b);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testDotProductWithLargeVectors() {
        double[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        double expected = 385.0;
        double result = DotProduct.dotProd(a, b);
        assertEquals(expected, result, 0.0001);
    }
}
