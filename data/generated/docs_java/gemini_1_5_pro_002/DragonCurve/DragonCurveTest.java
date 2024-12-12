import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DragonCurveTest {

    @Test
    void testGetSequenceZeroIterations() {
        DragonCurve dc = new DragonCurve(0);
        List<Integer> sequence = dc.getSequence(0);
        assertTrue(sequence.isEmpty());
    }

    @Test
    void testGetSequenceOneIteration() {
        DragonCurve dc = new DragonCurve(1);
        List<Integer> sequence = dc.getSequence(1);
        assertEquals(List.of(1), sequence);
    }

    @Test
    void testGetSequenceTwoIterations() {
        DragonCurve dc = new DragonCurve(2);
        List<Integer> sequence = dc.getSequence(2);
        assertEquals(List.of(1, 1, -1), sequence);
    }

    @Test
    void testGetSequenceThreeIterations() {
        DragonCurve dc = new DragonCurve(3);
        List<Integer> sequence = dc.getSequence(3);
        assertEquals(List.of(1, 1, -1, 1, 1, -1, -1), sequence);
    }


    @Test
    void testPaintMethodCalled() {
        // Testing paint() directly is difficult due to its reliance on Graphics.
        // This test checks for side effects instead of graphical output.
        // For proper graphical testing, consider using a headless environment and image comparison.
        int iterations = 5; // Use low iteration for faster test
        DragonCurve dc = new DragonCurve(iterations);
        dc.setVisible(true); // Makes paint() be called implicitly.

        // Check the size of turns list after painting. 
        // Indirectly ensures that getSequence and hence paint were invoked.
        assertEquals((int)(Math.pow(2, iterations)-1), dc.turns.size());
        dc.dispose(); // Close the frame to prevent resource leaks
    }

}
