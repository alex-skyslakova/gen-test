import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class LoopsWrongRangesTest {

    @Test
    public void testNormal() {
        LoopTest loopTest = new LoopTest(-2, 2, 1, "Normal");
        List<Integer> expected = List.of(-2, -1, 0, 1, 2);
        assertEquals(expected, runTest(loopTest));
    }

    @Test
    public void testZeroIncrement() {
        LoopTest loopTest = new LoopTest(-2, 2, 0, "Zero increment");
        List<Integer> expected = List.of(-2); // Only the start value is added
        assertEquals(expected, runTest(loopTest));
    }

    @Test
    public void testIncrementsAwayFromStopValue() {
        LoopTest loopTest = new LoopTest(-2, 2, -1, "Increments away from stop value");
        List<Integer> expected = List.of(); // No values added as increment is negative
        assertEquals(expected, runTest(loopTest));
    }

    @Test
    public void testFirstIncrementBeyondStopValue() {
        LoopTest loopTest = new LoopTest(-2, 2, 10, "First increment is beyond stop value");
        List<Integer> expected = List.of(-2); // Only the start value is added
        assertEquals(expected, runTest(loopTest));
    }

    @Test
    public void testStartMoreThanStopPositiveIncrement() {
        LoopTest loopTest = new LoopTest(2, -2, 1, "Start more than stop: positive increment");
        List<Integer> expected = List.of(); // No values added as start is greater than stop
        assertEquals(expected, runTest(loopTest));
    }

    @Test
    public void testStartEqualStopPositiveIncrement() {
        LoopTest loopTest = new LoopTest(2, 2, 1, "Start equal stop: positive increment");
        List<Integer> expected = List.of(2); // Only the start value is added
        assertEquals(expected, runTest(loopTest));
    }

    @Test
    public void testStartEqualStopNegativeIncrement() {
        LoopTest loopTest = new LoopTest(2, 2, -1, "Start equal stop: negative increment");
        List<Integer> expected = List.of(2); // Only the start value is added
        assertEquals(expected, runTest(loopTest));
    }

    @Test
    public void testStartEqualStopZeroIncrement() {
        LoopTest loopTest = new LoopTest(2, 2, 0, "Start equal stop: zero increment");
        List<Integer> expected = List.of(2); // Only the start value is added
        assertEquals(expected, runTest(loopTest));
    }

    @Test
    public void testStartEqualStopEqualZeroZeroIncrement() {
        LoopTest loopTest = new LoopTest(0, 0, 0, "Start equal stop equal zero: zero increment");
        List<Integer> expected = List.of(0); // Only the start value is added
        assertEquals(expected, runTest(loopTest));
    }

    private List<Integer> runTest(LoopTest loopTest) {
        List<Integer> values = new ArrayList<>();
        if (loopTest.increment == 0) {
            // If increment is zero, only add the start value once
            values.add(loopTest.start);
        } else {
            for (int i = loopTest.start; i <= loopTest.stop; i += loopTest.increment) {
                values.add(i);
                if (values.size() >= 10) {
                    break;
                }
            }
        }
        return values;
    }

    private static class LoopTest {
        int start;
        int stop;
        int increment;
        String comment;

        public LoopTest(int start, int stop, int increment, String comment) {
            this.start = start;
            this.stop = stop;
            this.increment = increment;
            this.comment = comment;
        }
    }
}
