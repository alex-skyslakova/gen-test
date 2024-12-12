import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FivenumTest {

    @Test
    public void testFivenumWithOddLengthArray() {
        double[] input = {15.0, 6.0, 42.0, 41.0, 7.0, 36.0, 49.0, 40.0, 39.0, 47.0, 43.0};
        double[] expected = {6.0, 39.0, 40.0, 43.0, 49.0};
        assertArrayEquals(expected, Fivenum.fivenum(input));
    }

    @Test
    public void testFivenumWithEvenLengthArray() {
        double[] input = {36.0, 40.0, 7.0, 39.0, 41.0, 15.0};
        double[] expected = {7.0, 15.0, 37.5, 40.0, 41.0};
        assertArrayEquals(expected, Fivenum.fivenum(input));
    }

    @Test
    public void testFivenumWithNegativeAndPositiveValues() {
        double[] input = {
            0.14082834,  0.09748790,  1.73131507,  0.87636009, -1.95059594,  0.73438555,
            -0.03035726,  1.46675970, -0.74621349, -0.72588772,  0.63905160,  0.61501527,
            -0.98983780, -1.00447874, -0.62759469,  0.66206163,  1.04312009, -0.10305385,
            0.75775634,  0.32566578
        };
        double[] expected = {-1.95059594, -0.43776780, 0.23324706, 0.74607094, 1.73131507};
        assertArrayEquals(expected, Fivenum.fivenum(input));
    }

    @Test
    public void testFivenumWithSingleElementArray() {
        double[] input = {5.0};
        double[] expected = {5.0, 5.0, 5.0, 5.0, 5.0};
        assertArrayEquals(expected, Fivenum.fivenum(input));
    }

    @Test
    public void testFivenumWithEmptyArray() {
        double[] input = {};
        assertThrows(IllegalArgumentException.class, () -> Fivenum.fivenum(input));
    }

    @Test
    public void testFivenumWithNaN() {
        double[] input = {1.0, Double.NaN, 3.0};
        assertThrows(IllegalArgumentException.class, () -> Fivenum.fivenum(input));
    }

    @Test
    public void testMedianWithOddLengthArray() {
        double[] input = {1.0, 2.0, 3.0, 4.0, 5.0};
        assertEquals(3.0, Fivenum.median(input, 0, 4));
    }

    @Test
    public void testMedianWithEvenLengthArray() {
        double[] input = {1.0, 2.0, 3.0, 4.0};
        assertEquals(2.5, Fivenum.median(input, 0, 3));
    }

    @Test
    public void testMedianWithEmptyArray() {
        double[] input = {};
        assertThrows(IllegalArgumentException.class, () -> Fivenum.median(input, 0, -1));
    }
}
