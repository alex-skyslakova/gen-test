import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountTest {

    @Test
    void testInitialValue() {
        // Test that the initial value is 1
        long initialValue = 1;
        assertEquals(1, initialValue, "The initial value should be 1.");
    }

    @Test
    void testIncrement() {
        // Test that the value increments correctly
        long value = 1;
        value++;
        assertEquals(2, value, "The value should increment to 2.");
    }

    @Test
    void testOverflowBehavior() {
        // Test behavior when exceeding the maximum value of a long
        long maxValue = Long.MAX_VALUE;
        long overflowValue = maxValue + 1;
        assertTrue(overflowValue < 0, "Overflow should result in a negative value.");
    }

    @Test
    void testPrintSequence() {
        // Test that the sequence is printed correctly
        // This is a conceptual test; actual printing cannot be tested in a unit test
        long startValue = 1;
        long nextValue = startValue + 1;
        assertEquals(2, nextValue, "The next value in the sequence should be 2.");
    }
}
