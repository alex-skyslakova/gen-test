import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class FivenumTest {

    @Test
    public void testFivenumWithOddNumberOfElements() {
        double[] input = {15.0, 6.0, 42.0, 41.0, 7.0, 36.0, 49.0, 40.0, 39.0, 47.0, 43.0};
        double[] expected = {6.0, 15.0, 40.0, 43.0, 49.0};
        assertArrayEquals(expected, Fivenum.fivenum(input), 0.0001);
    }

    @Test
    public void testFivenumWithEvenNumberOfElements() {
        double[] input = {36.0, 40.0, 7.0, 39.0, 41.0, 15.0};
        double[] expected = {7.0, 15.0, 37.5, 40.0, 41.0};
        assertArrayEquals(expected, Fivenum.fivenum(input), 0.0001);
    }

    @Test
    public void testFivenumWithNegativeAndPositiveNumbers() {
        double[] input = {
            0.14082834, 0.09748790, 1.73131507, 0.87636009, -1.95059594, 0.73438555,
            -0.03035726, 1.46675970, -0.74621349, -0.72588772, 0.63905160, 0.61501527,
            -0.98983780, -1.00447874, -0.62759469, 0.66206163, 1.04312009, -0.10305385,
            0.75775634, 0.32566578
        };
        double[] expected = {-1.95059594, -0.74621349, 0.36825669, 0.75775634, 1.73131507};
        assertArrayEquals(expected, Fivenum.fivenum(input), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFivenumWithNaN() {
        double[] input = {1.0, 2.0, Double.NaN, 4.0, 5.0};
        Fivenum.fivenum(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFivenumWithEmptyArray() {
        double[] input = {};
        Fivenum.fivenum(input);
    }

    @Test
    public void testFivenumWithSingleElement() {
        double[] input = {42.0};
        double[] expected = {42.0, 42.0, 42.0, 42.0, 42.0};
        assertArrayEquals(expected, Fivenum.fivenum(input), 0.0001);
    }

    @Test
    public void testFivenumWithTwoElements() {
        double[] input = {5.0, 10.0};
        double[] expected = {5.0, 5.0, 7.5, 10.0, 10.0};
        assertArrayEquals(expected, Fivenum.fivenum(input), 0.0001);
    }

    @Test
    public void testFivenumWithIdenticalElements() {
        double[] input = {3.0, 3.0, 3.0, 3.0, 3.0};
        double[] expected = {3.0, 3.0, 3.0, 3.0, 3.0};
        assertArrayEquals(expected, Fivenum.fivenum(input), 0.0001);
    }
}
