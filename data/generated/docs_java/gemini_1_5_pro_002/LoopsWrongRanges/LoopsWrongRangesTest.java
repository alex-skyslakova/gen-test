import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LoopsWrongRangesTest {

    private List<Integer> getLoopValues(int start, int stop, int increment) {
        List<Integer> values = new ArrayList<>();
        for (int i = start; i <= stop; i += increment) {
            values.add(i);
            if (values.size() >= 10) {
                break;
            }
        }
        return values;
    }

    @Test
    void testNormal() {
        List<Integer> expected = List.of(-2, -1, 0, 1, 2);
        assertEquals(expected, getLoopValues(-2, 2, 1));
    }

    @Test
    void testZeroIncrement() {
        List<Integer> expected = List.of(-2);
        assertEquals(expected, getLoopValues(-2, 2, 0)); // Infinite loop prevented by size check
    }

    @Test
    void testIncrementsAwayFromStopValue() {
         List<Integer> expected = List.of(-2);
        assertEquals(expected, getLoopValues(-2, 2, -1)); // Infinite loop prevented by size check

    }

    @Test
    void testFirstIncrementIsBeyondStopValue() {
        List<Integer> expected = List.of(-2);
        assertEquals(expected, getLoopValues(-2, 2, 10));
    }

    @Test
    void testStartMoreThanStopPositiveIncrement() {
        List<Integer> expected = List.of(2);
        assertEquals(expected, getLoopValues(2, -2, 1));
    }

    @Test
    void testStartEqualStopPositiveIncrement() {
        List<Integer> expected = List.of(2);
        assertEquals(expected, getLoopValues(2, 2, 1));
    }

    @Test
    void testStartEqualStopNegativeIncrement() {
        List<Integer> expected = List.of(2);
        assertEquals(expected, getLoopValues(2, 2, -1)); // Infinite loop prevented by size check
    }

    @Test
    void testStartEqualStopZeroIncrement() {
        List<Integer> expected = List.of(2);
        assertEquals(expected, getLoopValues(2, 2, 0)); // Infinite loop prevented by size check
    }

    @Test
    void testStartEqualStopEqualZeroZeroIncrement() {
        List<Integer> expected = List.of(0);
        assertEquals(expected, getLoopValues(0, 0, 0)); // Infinite loop prevented by size check
    }
}
