import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiversityPredictionTheoremTest {

    private static final double DELTA = 1e-3;

    @Test
    public void testDiversityTheoremExample1() {
        double truth = 49.0;
        double[] predictions = {48.0, 47.0, 51.0};
        
        double averageError = averageSquareDiff(truth, predictions);
        double crowdError = square(truth - Arrays.stream(predictions).average().orElseThrow());
        double diversity = averageSquareDiff(Arrays.stream(predictions).average().orElseThrow(), predictions);

        assertEquals(2.333, averageError, DELTA);
        assertEquals(0.444, crowdError, DELTA);
        assertEquals(1.889, diversity, DELTA);
    }

    @Test
    public void testDiversityTheoremExample2() {
        double truth = 49.0;
        double[] predictions = {48.0, 47.0, 51.0, 42.0};
        
        double averageError = averageSquareDiff(truth, predictions);
        double crowdError = square(truth - Arrays.stream(predictions).average().orElseThrow());
        double diversity = averageSquareDiff(Arrays.stream(predictions).average().orElseThrow(), predictions);

        assertEquals(13.500, averageError, DELTA);
        assertEquals(0.250, crowdError, DELTA);
        assertEquals(13.250, diversity, DELTA);
    }

    private double square(double d) {
        return d * d;
    }

    private double averageSquareDiff(double d, double[] predictions) {
        return Arrays.stream(predictions)
            .map(it -> square(it - d))
            .average()
            .orElseThrow();
    }
}
