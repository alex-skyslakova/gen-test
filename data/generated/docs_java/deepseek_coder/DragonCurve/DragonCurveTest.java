import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DragonCurveTest {

    @Test
    public void testGetSequence_ZeroIterations() {
        DragonCurve dragonCurve = new DragonCurve(0);
        List<Integer> sequence = dragonCurve.getSequence(0);
        assertTrue(sequence.isEmpty());
    }

    @Test
    public void testGetSequence_OneIteration() {
        DragonCurve dragonCurve = new DragonCurve(1);
        List<Integer> sequence = dragonCurve.getSequence(1);
        assertEquals(List.of(1), sequence);
    }

    @Test
    public void testGetSequence_TwoIterations() {
        DragonCurve dragonCurve = new DragonCurve(2);
        List<Integer> sequence = dragonCurve.getSequence(2);
        assertEquals(List.of(1, 1, -1), sequence);
    }

    @Test
    public void testGetSequence_ThreeIterations() {
        DragonCurve dragonCurve = new DragonCurve(3);
        List<Integer> sequence = dragonCurve.getSequence(3);
        assertEquals(List.of(1, 1, -1, 1, 1, -1, -1), sequence);
    }

    @Test
    public void testGetSequence_FourIterations() {
        DragonCurve dragonCurve = new DragonCurve(4);
        List<Integer> sequence = dragonCurve.getSequence(4);
        assertEquals(List.of(1, 1, -1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, -1, -1), sequence);
    }

    @Test
    public void testGetSequence_FiveIterations() {
        DragonCurve dragonCurve = new DragonCurve(5);
        List<Integer> sequence = dragonCurve.getSequence(5);
        assertEquals(List.of(1, 1, -1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, -1, -1, 1, 1, 1, -1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, -1, -1), sequence);
    }
}
