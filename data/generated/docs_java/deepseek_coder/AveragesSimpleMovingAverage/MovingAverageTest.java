import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovingAverageTest {

    @Test
    public void testMovingAverageWithPeriod3() {
        MovingAverage ma = new MovingAverage(3);

        // Test with less than period numbers
        ma.newNum(1);
        assertEquals(1.0, ma.getAvg(), 0.0001);

        ma.newNum(2);
        assertEquals(1.5, ma.getAvg(), 0.0001);

        ma.newNum(3);
        assertEquals(2.0, ma.getAvg(), 0.0001);

        // Test with more than period numbers
        ma.newNum(4);
        assertEquals(3.0, ma.getAvg(), 0.0001);

        ma.newNum(5);
        assertEquals(4.0, ma.getAvg(), 0.0001);
    }

    @Test
    public void testMovingAverageWithPeriod5() {
        MovingAverage ma = new MovingAverage(5);

        // Test with less than period numbers
        ma.newNum(1);
        assertEquals(1.0, ma.getAvg(), 0.0001);

        ma.newNum(2);
        assertEquals(1.5, ma.getAvg(), 0.0001);

        ma.newNum(3);
        assertEquals(2.0, ma.getAvg(), 0.0001);

        ma.newNum(4);
        assertEquals(2.5, ma.getAvg(), 0.0001);

        ma.newNum(5);
        assertEquals(3.0, ma.getAvg(), 0.0001);

        // Test with more than period numbers
        ma.newNum(6);
        assertEquals(4.0, ma.getAvg(), 0.0001);

        ma.newNum(7);
        assertEquals(5.0, ma.getAvg(), 0.0001);
    }

    @Test
    public void testMovingAverageWithZeroPeriod() {
        assertThrows(AssertionError.class, () -> new MovingAverage(0));
    }

    @Test
    public void testMovingAverageWithNegativePeriod() {
        assertThrows(AssertionError.class, () -> new MovingAverage(-1));
    }

    @Test
    public void testMovingAverageWithEmptyWindow() {
        MovingAverage ma = new MovingAverage(3);
        assertEquals(0.0, ma.getAvg(), 0.0001);
    }

    @Test
    public void testMovingAverageWithSingleNumber() {
        MovingAverage ma = new MovingAverage(3);
        ma.newNum(10);
        assertEquals(10.0, ma.getAvg(), 0.0001);
    }

    @Test
    public void testMovingAverageWithSameNumbers() {
        MovingAverage ma = new MovingAverage(3);
        ma.newNum(5);
        ma.newNum(5);
        ma.newNum(5);
        assertEquals(5.0, ma.getAvg(), 0.0001);

        ma.newNum(5);
        assertEquals(5.0, ma.getAvg(), 0.0001);
    }

    @Test
    public void testMovingAverageWithDecreasingNumbers() {
        MovingAverage ma = new MovingAverage(3);
        ma.newNum(5);
        ma.newNum(4);
        ma.newNum(3);
        assertEquals(4.0, ma.getAvg(), 0.0001);

        ma.newNum(2);
        assertEquals(3.0, ma.getAvg(), 0.0001);

        ma.newNum(1);
        assertEquals(2.0, ma.getAvg(), 0.0001);
    }

    @Test
    public void testMovingAverageWithIncreasingNumbers() {
        MovingAverage ma = new MovingAverage(3);
        ma.newNum(1);
        ma.newNum(2);
        ma.newNum(3);
        assertEquals(2.0, ma.getAvg(), 0.0001);

        ma.newNum(4);
        assertEquals(3.0, ma.getAvg(), 0.0001);

        ma.newNum(5);
        assertEquals(4.0, ma.getAvg(), 0.0001);
    }
}
