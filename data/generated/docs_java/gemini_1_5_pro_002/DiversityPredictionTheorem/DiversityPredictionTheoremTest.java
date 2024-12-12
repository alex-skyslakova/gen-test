import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiversityPredictionTheoremTest {

    @Test
    void testSquare() {
        assertEquals(4.0, DiversityPredictionTheorem.square(2.0));
        assertEquals(0.0, DiversityPredictionTheorem.square(0.0));
        assertEquals(1.0, DiversityPredictionTheorem.square(-1.0));
    }

    @Test
    void testAverageSquareDiff() {
        assertEquals(2.0, DiversityPredictionTheorem.averageSquareDiff(2.0, new double[]{1.0, 2.0, 3.0}));
        assertEquals(0.0, DiversityPredictionTheorem.averageSquareDiff(2.0, new double[]{2.0, 2.0, 2.0}));
        assertEquals(0.5, DiversityPredictionTheorem.averageSquareDiff(2.0, new double[]{1.5, 2.0, 2.5}));
    }


    @Test
    void testDiversityTheorem_example1() {
        String expected = "average-error : 2.667\n" +
                "crowd-error   : 0.667\n" +
                "diversity     : 2.000\n";
        assertEquals(expected, DiversityPredictionTheorem.diversityTheorem(49.0, new double[]{48.0, 47.0, 51.0}));
    }

    @Test
    void testDiversityTheorem_example2() {
        String expected = "average-error : 11.667\n" +
                "crowd-error   : 2.667\n" +
                "diversity     : 9.000\n";
        assertEquals(expected, DiversityPredictionTheorem.diversityTheorem(49.0, new double[]{48.0, 47.0, 51.0, 42.0}));
    }

    @Test
    void testDiversityTheorem_noPredictions() {
        assertThrows(IllegalArgumentException.class, () -> DiversityPredictionTheorem.diversityTheorem(49.0, new double[]{}));
    }

    @Test
    void testDiversityTheorem_singlePrediction() {
        String expected = "average-error : 1.000\n" +
                "crowd-error   : 1.000\n" +
                "diversity     : 0.000\n";
        assertEquals(expected, DiversityPredictionTheorem.diversityTheorem(49.0, new double[]{48.0}));

    }
    @Test
    void testDiversityTheorem_perfectPrediction() {
        String expected = "average-error : 0.000\n" +
                "crowd-error   : 0.000\n" +
                "diversity     : 0.000\n";
        assertEquals(expected, DiversityPredictionTheorem.diversityTheorem(49.0, new double[]{49.0, 49.0, 49.0}));
    }


}
