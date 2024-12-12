import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovingAverageTest {

    @Test
    void testPeriodOne() {
        MovingAverage ma = new MovingAverage(1);
        ma.newNum(5);
        assertEquals(5.0, ma.getAvg());
        ma.newNum(10);
        assertEquals(10.0, ma.getAvg());
    }

    @Test
    void testPeriodThree() {
        MovingAverage ma = new MovingAverage(3);
        ma.newNum(1);
        assertEquals(1.0, ma.getAvg());
        ma.newNum(2);
        assertEquals(1.5, ma.getAvg());
        ma.newNum(3);
        assertEquals(2.0, ma.getAvg());
        ma.newNum(4);
        assertEquals(3.0, ma.getAvg());
    }

    @Test
    void testPeriodFive() {
        MovingAverage ma = new MovingAverage(5);
        double[] testData = {1, 2, 3, 4, 5, 5, 4, 3, 2, 1};
        double[] expected = {1.0, 1.5, 2.0, 2.5, 3.0, 3.6, 3.8, 3.8, 3.6, 3.0};

        for (int i = 0; i < testData.length; i++) {
            ma.newNum(testData[i]);
            assertEquals(expected[i], ma.getAvg());
        }
    }


    @Test
    void testEmpty() {
        MovingAverage ma = new MovingAverage(3);
        assertEquals(0.0, ma.getAvg());
    }

    @Test
    void testZeroPeriod() {
        assertThrows(AssertionError.class, () -> new MovingAverage(0));
    }


    @Test
    void testNegativePeriod() {
        assertThrows(AssertionError.class, () -> new MovingAverage(-3));

    }
}
