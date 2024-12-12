import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GaltonBoxTest {

    private GaltonBox galtonBox;
    private final int pinRows = 8;
    private final int ballCount = 200;

    @Before
    public void setUp() {
        galtonBox = new GaltonBox(pinRows, ballCount);
    }

    @Test
    public void testInitialization() {
        assertEquals("Pin rows should be initialized correctly", pinRows, galtonBox.m_pinRows);
        assertEquals("Start row should be pinRows + 1", pinRows + 1, galtonBox.m_startRow);
        assertEquals("Number of balls should be initialized correctly", ballCount, galtonBox.m_balls.length);
    }

    @Test
    public void testBallInitialPosition() {
        for (GaltonBox.Position ball : galtonBox.m_balls) {
            assertEquals("Ball should start at the start row", galtonBox.m_startRow, ball.m_row);
            assertEquals("Ball should start at column 0", 0, ball.m_col);
            assertEquals("Ball character should be 'o'", 'o', ball.m_char);
        }
    }

    @Test
    public void testDropBalls() {
        int ballsInPlay = galtonBox.dropBalls();
        assertTrue("At least one ball should be in play after dropping", ballsInPlay > 0);
    }

    @Test
    public void testBallsReachBottom() {
        galtonBox.run();
        for (GaltonBox.Position ball : galtonBox.m_balls) {
            assertEquals("All balls should eventually reach row 0", 0, ball.m_row);
        }
    }

    @Test
    public void testBallDistribution() {
        galtonBox.run();
        int[] distribution = new int[galtonBox.m_startRow];
        for (GaltonBox.Position ball : galtonBox.m_balls) {
            distribution[ball.m_col]++;
        }

        int totalBalls = 0;
        for (int count : distribution) {
            totalBalls += count;
        }

        assertEquals("Total number of balls should be equal to ballCount", ballCount, totalBalls);
    }
}
