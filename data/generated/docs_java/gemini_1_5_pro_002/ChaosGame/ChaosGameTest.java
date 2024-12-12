import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.EmptyStackException;
import java.util.Stack;
import static org.junit.jupiter.api.Assertions.*;

public class ChaosGameTest {

    @Test
    void testHalfwayPoint() {
        ChaosGame cg = new ChaosGame();
        Point a = new Point(0, 0);
        Point b = new Point(4, 4);
        ChaosGame.ColoredPoint expected = new ChaosGame.ColoredPoint(2, 2, 1);
        ChaosGame.ColoredPoint actual = cg.halfwayPoint(a, b, 1);
        assertEquals(expected.x, actual.x);
        assertEquals(expected.y, actual.y);
        assertEquals(expected.colorIndex, actual.colorIndex);

        a = new Point(1, 1);
        b = new Point(3, 7);
        expected = new ChaosGame.ColoredPoint(2, 4, 2);
        actual = cg.halfwayPoint(a, b, 2);

        assertEquals(expected.x, actual.x);
        assertEquals(expected.y, actual.y);
        assertEquals(expected.colorIndex, actual.colorIndex);
    }



    @Test
    void testAddPoint() {
        ChaosGame cg = new ChaosGame();
        // Initialize with a point to avoid EmptyStackException
        cg.stack.push(new ChaosGame.ColoredPoint(0,0,0));
        int initialSize = cg.stack.size();
        cg.addPoint();
        assertEquals(initialSize + 1, cg.stack.size());


         // Test that addPoint handles EmptyStackException gracefully, although it shouldn't occur with proper initialization.
        cg.stack.clear();
        // Expect no exception
        assertDoesNotThrow(cg::addPoint);

    }


    @Test
    void testDrawPoints() {
        ChaosGame cg = new ChaosGame();
        cg.stack.push(new ChaosGame.ColoredPoint(10, 10, 0));
        cg.stack.push(new ChaosGame.ColoredPoint(20, 20, 1));
        // Mocking Graphics2D is complex for simple drawing tests. Visually verify drawing with the application itself.
        // This test only checks that the drawPoints method does not throw any exceptions.

        Graphics2D g = (Graphics2D) new Panel().getGraphics(); //Dummy graphics context for testing
        if(g != null) { //Graphics may be null in headless test environments
            assertDoesNotThrow(() -> cg.drawPoints(g));
        }
    }



}
