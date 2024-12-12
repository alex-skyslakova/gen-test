import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AverageLoopLengthTest {

    @Test
    public void testAnalytical() {
        // Test cases for analytical method
        assertEquals(1.0, AverageLoopLength.analytical(1), 1e-4);
        assertEquals(1.5, AverageLoopLength.analytical(2), 1e-4);
        assertEquals(1.8889, AverageLoopLength.analytical(3), 1e-4);
        assertEquals(2.2188, AverageLoopLength.analytical(4), 1e-4);
        assertEquals(2.5104, AverageLoopLength.analytical(5), 1e-4);
        assertEquals(2.7747, AverageLoopLength.analytical(6), 1e-4);
        assertEquals(3.0181, AverageLoopLength.analytical(7), 1e-4);
        assertEquals(3.2450, AverageLoopLength.analytical(8), 1e-4);
        assertEquals(3.4583, AverageLoopLength.analytical(9), 1e-4);
        assertEquals(3.6602, AverageLoopLength.analytical(10), 1e-4);
        assertEquals(3.8524, AverageLoopLength.analytical(11), 1e-4);
        assertEquals(4.0361, AverageLoopLength.analytical(12), 1e-4);
        assertEquals(4.2123, AverageLoopLength.analytical(13), 1e-4);
        assertEquals(4.3820, AverageLoopLength.analytical(14), 1e-4);
        assertEquals(4.5458, AverageLoopLength.analytical(15), 1e-4);
        assertEquals(4.7043, AverageLoopLength.analytical(16), 1e-4);
        assertEquals(4.8579, AverageLoopLength.analytical(17), 1e-4);
        assertEquals(5.0071, AverageLoopLength.analytical(18), 1e-4);
        assertEquals(5.1522, AverageLoopLength.analytical(19), 1e-4);
        assertEquals(5.2936, AverageLoopLength.analytical(20), 1e-4);
    }

    @Test
    public void testAverage() {
        // Since the average method involves randomness, we can only check if it runs without errors
        for (int i = 1; i <= 20; i++) {
            double avg = AverageLoopLength.average(i);
            // Ensure the result is within a reasonable range
            assert(avg > 0 && avg < i * 2);
        }
    }
}
