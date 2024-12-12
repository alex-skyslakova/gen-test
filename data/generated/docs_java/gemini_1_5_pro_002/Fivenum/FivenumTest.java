import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class FivenumTest {

    @Test
    void testFivenum_oddLength() {
        double[] x = {15.0, 6.0, 42.0, 41.0, 7.0, 36.0, 49.0, 40.0, 39.0, 47.0, 43.0};
        double[] expected = {6.0, 15.0, 40.0, 43.0, 49.0};
        double[] actual = Fivenum.fivenum(x.clone()); // Use a clone to avoid modifying the original array
        assertArrayEquals(expected, actual, 0.0001);
    }

    @Test
    void testFivenum_evenLength() {
        double[] x = {36.0, 40.0, 7.0, 39.0, 41.0, 15.0};
        double[] expected = {7.0, 15.0, 37.5, 40.0, 41.0};
        double[] actual = Fivenum.fivenum(x.clone());
        assertArrayEquals(expected, actual, 0.0001);
    }

    @Test
    void testFivenum_withNegativeAndDecimals() {
        double[] x = {
                0.14082834,  0.09748790,  1.73131507,  0.87636009, -1.95059594,  0.73438555,
                -0.03035726,  1.46675970, -0.74621349, -0.72588772,  0.63905160,  0.61501527,
                -0.98983780, -1.00447874, -0.62759469,  0.66206163,  1.04312009, -0.10305385,
                0.75775634,  0.32566578
        };
        double[] expected = {-1.95059594, -0.868050605, 0.39535869, 0.74605604, 1.73131507};
        double[] actual = Fivenum.fivenum(x.clone());
        assertArrayEquals(expected, actual, 0.0001);
    }

    @Test
    void testFivenum_singleElement() {
        double[] x = {5.0};
        double[] expected = {5.0, 5.0, 5.0, 5.0, 5.0};
        double[] actual = Fivenum.fivenum(x.clone());
        assertArrayEquals(expected, actual, 0.0001);
    }


    @Test
    void testFivenum_emptyArray() {
        double[] x = {};
        assertThrows(IllegalArgumentException.class, () -> Fivenum.fivenum(x));
    }

    @Test
    void testFivenum_withNaN() {
        double[] x = {1.0, 2.0, Double.NaN, 4.0};
        assertThrows(IllegalArgumentException.class, () -> Fivenum.fivenum(x));
    }

    @Test
    void testMedian_emptyArray() {
        double[] x = {};
        assertThrows(IllegalArgumentException.class, () -> Fivenum.median(x, 0, -1));
    }


}
