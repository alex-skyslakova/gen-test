import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoopsWrongRangesTest {

    @Test
    public void testNormalCase() {
        LoopTest test = new LoopTest(-2, 2, 1, "Normal");
        List<Integer> expected = Arrays.asList(-2, -1, 0, 1, 2);
        List<Integer> result = runTestAndGetValues(test);
        assertEquals(expected, result);
    }

    @Test
    public void testZeroIncrement() {
        LoopTest test = new LoopTest(-2, 2, 0, "Zero increment");
        List<Integer> expected = Arrays.asList(-2, -2, -2, -2, -2, -2, -2, -2, -2, -2);
        List<Integer> result = runTestAndGetValues(test);
        assertEquals(expected, result);
    }

    @Test
    public void testIncrementsAwayFromStopValue() {
        LoopTest test = new LoopTest(-2, 2, -1, "Increments away from stop value");
        List<Integer> expected = Arrays.asList(-2, -3, -4, -5, -6, -7, -8, -9, -10, -11);
        List<Integer> result = runTestAndGetValues(test);
        assertEquals(expected, result);
    }

    @Test
    public void testFirstIncrementIsBeyondStopValue() {
        LoopTest test = new LoopTest(-2, 2, 10, "First increment is beyond stop value");
        List<Integer> expected = Arrays.asList(-2);
        List<Integer> result = runTestAndGetValues(test);
        assertEquals(expected, result);
    }

    @Test
    public void testStartMoreThanStopPositiveIncrement() {
        LoopTest test = new LoopTest(2, -2, 1, "Start more than stop: positive increment");
        List<Integer> expected = Arrays.asList(2);
        List<Integer> result = runTestAndGetValues(test);
        assertEquals(expected, result);
    }

    @Test
    public void testStartEqualStopPositiveIncrement() {
        LoopTest test = new LoopTest(2, 2, 1, "Start equal stop: positive increment");
        List<Integer> expected = Arrays.asList(2);
        List<Integer> result = runTestAndGetValues(test);
        assertEquals(expected, result);
    }

    @Test
    public void testStartEqualStopNegativeIncrement() {
        LoopTest test = new LoopTest(2, 2, -1, "Start equal stop: negative increment");
        List<Integer> expected = Arrays.asList(2);
        List<Integer> result = runTestAndGetValues(test);
        assertEquals(expected, result);
    }

    @Test
    public void testStartEqualStopZeroIncrement() {
        LoopTest test = new LoopTest(2, 2, 0, "Start equal stop: zero increment");
        List<Integer> expected = Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2);
        List<Integer> result = runTestAndGetValues(test);
        assertEquals(expected, result);
    }

    @Test
    public void testStartEqualStopEqualZeroZeroIncrement() {
        LoopTest test = new LoopTest(0, 0, 0, "Start equal stop equal zero: zero increment");
        List<Integer> expected = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<Integer> result = runTestAndGetValues(test);
        assertEquals(expected, result);
    }

    private List<Integer> runTestAndGetValues(LoopTest loopTest) {
        List<Integer> values = new ArrayList<>();
        for (int i = loopTest.start; i <= loopTest.stop; i += loopTest.increment) {
            values.add(i);
            if (values.size() >= 10) {
                break;
            }
        }
        return values;
    }
}
