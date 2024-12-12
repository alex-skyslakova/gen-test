import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovingAverageTest {

    private MovingAverage movingAverage3;
    private MovingAverage movingAverage5;

    @BeforeEach
    public void setUp() {
        movingAverage3 = new MovingAverage(3);
        movingAverage5 = new MovingAverage(5);
    }

    @Test
    public void testInitialAverageIsZero() {
        assertEquals(0.0, movingAverage3.getAvg(), 0.0001);
        assertEquals(0.0, movingAverage5.getAvg(), 0.0001);
    }

    @Test
    public void testMovingAverageWithPeriod3() {
        double[] testData = {1, 2, 3, 4, 5};
        double[] expectedAverages = {1.0, 1.5, 2.0, 3.0, 4.0};

        for (int i = 0; i < testData.length; i++) {
            movingAverage3.newNum(testData[i]);
            assertEquals(expectedAverages[i], movingAverage3.getAvg(), 0.0001);
        }
    }

    @Test
    public void testMovingAverageWithPeriod5() {
        double[] testData = {1, 2, 3, 4, 5, 5, 4, 3, 2, 1};
        double[] expectedAverages = {1.0, 1.5, 2.0, 2.5, 3.0, 3.8, 4.2, 4.2, 3.8, 3.0};

        for (int i = 0; i < testData.length; i++) {
            movingAverage5.newNum(testData[i]);
            assertEquals(expectedAverages[i], movingAverage5.getAvg(), 0.0001);
        }
    }

    @Test
    public void testMovingAverageWithSingleElement() {
        movingAverage3.newNum(10);
        assertEquals(10.0, movingAverage3.getAvg(), 0.0001);
    }

    @Test
    public void testMovingAverageWithNoElements() {
        assertEquals(0.0, movingAverage3.getAvg(), 0.0001);
    }

    @Test
    public void testMovingAverageWithNegativeNumbers() {
        double[] testData = {-1, -2, -3, -4, -5};
        double[] expectedAverages = {-1.0, -1.5, -2.0, -3.0, -4.0};

        for (int i = 0; i < testData.length; i++) {
            movingAverage3.newNum(testData[i]);
            assertEquals(expectedAverages[i], movingAverage3.getAvg(), 0.0001);
        }
    }
}
