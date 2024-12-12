import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class PythagoreanMeansTest {

    @Test
    public void testArithmeticMean() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        double expected = 5.5;
        double actual = PythagoreanMeans.arithmeticMean(numbers);
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testArithmeticMeanEmptyList() {
        List<Double> numbers = Arrays.asList();
        double expected = Double.NaN;
        double actual = PythagoreanMeans.arithmeticMean(numbers);
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testGeometricMean() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        double expected = 4.528728688116765;
        double actual = PythagoreanMeans.geometricMean(numbers);
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testGeometricMeanEmptyList() {
        List<Double> numbers = Arrays.asList();
        double expected = Double.NaN;
        double actual = PythagoreanMeans.geometricMean(numbers);
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testHarmonicMean() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        double expected = 3.414171521474055;
        double actual = PythagoreanMeans.harmonicMean(numbers);
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testHarmonicMeanEmptyList() {
        List<Double> numbers = Arrays.asList();
        double expected = Double.NaN;
        double actual = PythagoreanMeans.harmonicMean(numbers);
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testHarmonicMeanWithZero() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 0.0);
        double expected = Double.NaN;
        double actual = PythagoreanMeans.harmonicMean(numbers);
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testInequality() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        double arithmetic = PythagoreanMeans.arithmeticMean(numbers);
        double geometric = PythagoreanMeans.geometricMean(numbers);
        double harmonic = PythagoreanMeans.harmonicMean(numbers);
        assertTrue(arithmetic >= geometric);
        assertTrue(geometric >= harmonic);
    }
}
