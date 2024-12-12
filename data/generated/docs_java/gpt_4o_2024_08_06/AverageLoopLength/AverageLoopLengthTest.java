import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AverageLoopLengthTest {

    private static final double DELTA = 0.01;

    @Test
    public void testAnalytical() {
        // Test known values for the analytical method
        assertEquals(1.0, AverageLoopLength.analytical(1), DELTA);
        assertEquals(1.5, AverageLoopLength.analytical(2), DELTA);
        assertEquals(1.8889, AverageLoopLength.analytical(3), DELTA);
        assertEquals(2.2188, AverageLoopLength.analytical(4), DELTA);
        assertEquals(2.5104, AverageLoopLength.analytical(5), DELTA);
    }

    @Test
    public void testAverage() {
        // Test average method for small N values
        // Note: Due to randomness, these tests may occasionally fail.
        assertTrue(AverageLoopLength.average(1) >= 1.0);
        assertTrue(AverageLoopLength.average(2) >= 1.0);
        assertTrue(AverageLoopLength.average(3) >= 1.0);
    }

    @Test
    public void testAverageAndAnalyticalComparison() {
        // Compare average and analytical results for small N values
        for (int i = 1; i <= 5; i++) {
            double avg = AverageLoopLength.average(i);
            double ana = AverageLoopLength.analytical(i);
            assertTrue(Math.abs(avg - ana) / ana < 0.1, "Average and analytical values differ significantly for N = " + i);
        }
    }
}
