import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AverageLoopLengthTest {

    @Test
    void testAnalytical_n1() {
        assertEquals(1.0, AverageLoopLength.analytical(1), 0.0001);
    }

    @Test
    void testAnalytical_n2() {
        assertEquals(1.5, AverageLoopLength.analytical(2), 0.0001);
    }

    @Test
    void testAnalytical_n5() {
        assertEquals(2.5104, AverageLoopLength.analytical(5), 0.0001);
    }

    @Test
    void testAnalytical_n10() {
        assertEquals(3.6602, AverageLoopLength.analytical(10), 0.0001);
    }


    @Test
    void testAverage_n1() {
        assertEquals(1.0, AverageLoopLength.average(1), 0.1); // Increased delta for probabilistic nature
    }

    @Test
    void testAverage_n2() {
        assertEquals(1.5, AverageLoopLength.average(2), 0.1);
    }

    @Test
    void testAverage_n5() {
        assertEquals(2.51, AverageLoopLength.average(5), 0.1);
    }

    @Test
    void testAverage_n10() {
        assertEquals(3.66, AverageLoopLength.average(10), 0.15);  // Increased delta for larger N
    }



    @Test
    void testEdgeCase_n0() {
        assertEquals(0.0, AverageLoopLength.analytical(0), 0.0001);
        // For average, n=0 leads to creating an array of size 0.
        // The loop inside average(0) won't execute, resulting in 0.0.
        assertEquals(0.0, AverageLoopLength.average(0), 0.0001);

    }




}
