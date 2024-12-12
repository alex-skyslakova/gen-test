import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.EmptyStackException;

public class ChaosGameTest {

    private ChaosGame chaosGame;

    @BeforeEach
    public void setUp() {
        chaosGame = new ChaosGame();
    }

    @Test
    public void testInitialSetup() {
        // Test if the initial stack has one element
        assertEquals(1, chaosGame.stack.size());

        // Test if the initial point is (-1, -1)
        ChaosGame.ColoredPoint initialPoint = chaosGame.stack.peek();
        assertEquals(-1, initialPoint.x);
        assertEquals(-1, initialPoint.y);
    }

    @Test
    public void testHalfwayPoint() {
        Point a = new Point(0, 0);
        Point b = new Point(4, 4);
        int colorIndex = 1;

        ChaosGame.ColoredPoint result = chaosGame.halfwayPoint(a, b, colorIndex);

        // Test if the halfway point is calculated correctly
        assertEquals(2, result.x);
        assertEquals(2, result.y);
        assertEquals(colorIndex, result.colorIndex);
    }

    @Test
    public void testAddPoint() {
        // Add a point and check if the stack size increases
        int initialSize = chaosGame.stack.size();
        chaosGame.addPoint();
        assertEquals(initialSize + 1, chaosGame.stack.size());
    }

    @Test
    public void testAddPointWithEmptyStack() {
        // Clear the stack to simulate an empty stack scenario
        chaosGame.stack.clear();

        // Try to add a point and catch the exception
        assertThrows(EmptyStackException.class, () -> chaosGame.addPoint());
    }

    @Test
    public void testDrawPoints() {
        // Create a mock Graphics2D object
        Graphics2D g2d = new BufferedImage(640, 640, BufferedImage.TYPE_INT_ARGB).createGraphics();

        // Call drawPoints and ensure no exceptions are thrown
        assertDoesNotThrow(() -> chaosGame.drawPoints(g2d));
    }
}
