import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PythagoreanMeansTest {

    @Test
    public void testArithmeticMean() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        double expected = 5.5;
        double result = PythagoreanMeans.arithmeticMean(numbers);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testGeometricMean() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        double expected = 4.5287; // Calculated geometric mean
        double result = PythagoreanMeans.geometricMean(numbers);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testHarmonicMean() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        double expected = 3.4142; // Calculated harmonic mean
        double result = PythagoreanMeans.harmonicMean(numbers);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testMeansInequality() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        double arithmetic = PythagoreanMeans.arithmeticMean(numbers);
        double geometric = PythagoreanMeans.geometricMean(numbers);
        double harmonic = PythagoreanMeans.harmonicMean(numbers);

        assertTrue(arithmetic >= geometric);
        assertTrue(geometric >= harmonic);
    }

    @Test
    public void testEmptyList() {
        List<Double> numbers = Arrays.asList();
        assertTrue(Double.isNaN(PythagoreanMeans.arithmeticMean(numbers)));
        assertTrue(Double.isNaN(PythagoreanMeans.geometricMean(numbers)));
        assertTrue(Double.isNaN(PythagoreanMeans.harmonicMean(numbers)));
    }

    @Test
    public void testHarmonicMeanWithZero() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 0.0, 4.0, 5.0);
        assertTrue(Double.isNaN(PythagoreanMeans.harmonicMean(numbers)));
    }
}
