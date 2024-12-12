import org.junit.Test;
import static org.junit.Assert.*;

public class DotProductTest {

    @Test
    public void testDotProductWithPositiveNumbers() {
        double[] a = {1, 3, -5};
        double[] b = {4, -2, -1};
        double expected = 3;
        assertEquals(expected, DotProduct.dotProd(a, b), 0.0001);
    }

    @Test
    public void testDotProductWithZeros() {
        double[] a = {0, 0, 0};
        double[] b = {0, 0, 0};
        double expected = 0;
        assertEquals(expected, DotProduct.dotProd(a, b), 0.0001);
    }

    @Test
    public void testDotProductWithNegativeNumbers() {
        double[] a = {-1, -3, 5};
        double[] b = {-4, 2, 1};
        double expected = -12;
        assertEquals(expected, DotProduct.dotProd(a, b), 0.0001);
    }

    @Test
    public void testDotProductWithMixedNumbers() {
        double[] a = {1.5, -3.2, 4.8};
        double[] b = {2.5, 3.1, -1.2};
        double expected = -8.26;
        assertEquals(expected, DotProduct.dotProd(a, b), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDotProductWithDifferentLengths() {
        double[] a = {1, 2};
        double[] b = {1, 2, 3};
        DotProduct.dotProd(a, b);
    }

    @Test
    public void testDotProductWithEmptyVectors() {
        double[] a = {};
        double[] b = {};
        double expected = 0;
        assertEquals(expected, DotProduct.dotProd(a, b), 0.0001);
    }
}
