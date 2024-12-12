import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiversityPredictionTheoremTest {

    @Test
    public void testDiversityTheoremWithThreeEstimates() {
        double truth = 49.0;
        double[] predictions = {48.0, 47.0, 51.0};

        String result = DiversityPredictionTheorem.diversityTheorem(truth, predictions);

        String expected = 
            "average-error : 4.000\n" +
            "crowd-error   : 0.111\n" +
            "diversity     : 3.889\n";

        assertEquals(expected, result);
    }

    @Test
    public void testDiversityTheoremWithFourEstimates() {
        double truth = 49.0;
        double[] predictions = {48.0, 47.0, 51.0, 42.0};

        String result = DiversityPredictionTheorem.diversityTheorem(truth, predictions);

        String expected = 
            "average-error : 14.500\n" +
            "crowd-error   : 1.000\n" +
            "diversity     : 13.500\n";

        assertEquals(expected, result);
    }

    @Test
    public void testAverageSquareDiff() {
        double truth = 49.0;
        double[] predictions = {48.0, 47.0, 51.0};

        double result = DiversityPredictionTheorem.averageSquareDiff(truth, predictions);
        double expected = 4.0;

        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testSquare() {
        double value = 3.0;

        double result = DiversityPredictionTheorem.square(value);
        double expected = 9.0;

        assertEquals(expected, result, 0.001);
    }
}
