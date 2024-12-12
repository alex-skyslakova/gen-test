import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DragonCurveTest {

    @Test
    public void testGetSequenceZeroIterations() {
        DragonCurve dragonCurve = new DragonCurve(0);
        List<Integer> sequence = dragonCurve.getSequence(0);
        assertTrue(sequence.isEmpty(), "Sequence should be empty for 0 iterations");
    }

    @Test
    public void testGetSequenceOneIteration() {
        DragonCurve dragonCurve = new DragonCurve(1);
        List<Integer> sequence = dragonCurve.getSequence(1);
        assertEquals(List.of(1), sequence, "Sequence should be [1] for 1 iteration");
    }

    @Test
    public void testGetSequenceTwoIterations() {
        DragonCurve dragonCurve = new DragonCurve(2);
        List<Integer> sequence = dragonCurve.getSequence(2);
        assertEquals(List.of(1, 1, -1), sequence, "Sequence should be [1, 1, -1] for 2 iterations");
    }

    @Test
    public void testGetSequenceThreeIterations() {
        DragonCurve dragonCurve = new DragonCurve(3);
        List<Integer> sequence = dragonCurve.getSequence(3);
        assertEquals(List.of(1, 1, -1, 1, 1, -1, -1), sequence, "Sequence should be [1, 1, -1, 1, 1, -1, -1] for 3 iterations");
    }

    @Test
    public void testStartingAngle() {
        DragonCurve dragonCurve = new DragonCurve(4);
        double expectedAngle = -4 * (Math.PI / 4);
        assertEquals(expectedAngle, dragonCurve.startingAngle, "Starting angle should be -4 * (Math.PI / 4) for 4 iterations");
    }

    @Test
    public void testSideLength() {
        DragonCurve dragonCurve = new DragonCurve(4);
        double expectedSide = 400 / Math.pow(2, 4 / 2.);
        assertEquals(expectedSide, dragonCurve.side, "Side length should be 400 / Math.pow(2, 4 / 2.) for 4 iterations");
    }
}
