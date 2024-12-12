import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PythagoreanMeansTest {

    @Test
    void testArithmeticMean_emptyList() {
        assertEquals(Double.NaN, PythagoreanMeans.arithmeticMean(Collections.emptyList()));
    }

    @Test
    void testArithmeticMean_positiveNumbers() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        assertEquals(3.0, PythagoreanMeans.arithmeticMean(numbers));
    }


    @Test
    void testGeometricMean_emptyList() {
        assertEquals(Double.NaN, PythagoreanMeans.geometricMean(Collections.emptyList()));
    }

    @Test
    void testGeometricMean_positiveNumbers() {
        List<Double> numbers = Arrays.asList(2.0, 4.0, 8.0);
        assertEquals(4.0, PythagoreanMeans.geometricMean(numbers));
    }

    @Test
    void testHarmonicMean_emptyList() {
        assertEquals(Double.NaN, PythagoreanMeans.harmonicMean(Collections.emptyList()));
    }

    @Test
    void testHarmonicMean_positiveNumbers() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0);
        assertEquals(1.92, PythagoreanMeans.harmonicMean(numbers), 0.001); 
    }
    
    @Test
    void testHarmonicMean_containsZero() {
        List<Double> numbers = Arrays.asList(1.0, 2.0, 0.0, 4.0);
        assertEquals(Double.NaN, PythagoreanMeans.harmonicMean(numbers));
    }

    @Test
    void testMeansRelationship() {
        Double[] array = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
        List<Double> list = Arrays.asList(array);
        double arithmetic = PythagoreanMeans.arithmeticMean(list);
        double geometric = PythagoreanMeans.geometricMean(list);
        double harmonic = PythagoreanMeans.harmonicMean(list);

        assertTrue(arithmetic >= geometric);
        assertTrue(geometric >= harmonic);
    }
}
