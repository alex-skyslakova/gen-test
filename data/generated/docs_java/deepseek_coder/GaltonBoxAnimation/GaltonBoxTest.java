import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class GaltonBoxTest {

    @Test
    public void testConstructor() {
        GaltonBox box = new GaltonBox(5, 100);
        assertEquals(5, box.m_pinRows);
        assertEquals(6, box.m_startRow);
        assertEquals(100, box.m_balls.length);
    }

    @Test
    public void testDropBalls() {
        GaltonBox box = new GaltonBox(5, 1);
        int ballsInPlay = box.dropBalls();
        assertEquals(1, ballsInPlay);
        assertEquals(5, box.m_balls[0].m_row);
    }

    @Test
    public void testPrintBall() {
        GaltonBox.Position pos = new GaltonBox.Position(3, 2, 'o');
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GaltonBox.printBall(pos);
        System.setOut(System.out);
        assertEquals("   o", outContent.toString().trim());
    }

    @Test
    public void testPrintPins() {
        GaltonBox box = new GaltonBox(5, 1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        box.printPins(3);
        System.setOut(System.out);
        assertEquals("   . . .", outContent.toString().trim());
    }

    @Test
    public void testPrintCollectors() {
        GaltonBox box = new GaltonBox(5, 2);
        box.m_balls[0].m_row = 0;
        box.m_balls[0].m_col = 0;
        box.m_balls[1].m_row = 0;
        box.m_balls[1].m_col = 1;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        box.printCollectors();
        System.setOut(System.out);
        assertEquals("|o |o|", outContent.toString().trim());
    }

    @Test
    public void testLongest() {
        GaltonBox box = new GaltonBox(5, 3);
        box.m_balls[0].m_row = 0;
        box.m_balls[0].m_col = 0;
        box.m_balls[1].m_row = 0;
        box.m_balls[1].m_col = 1;
        box.m_balls[2].m_row = 0;
        box.m_balls[2].m_col = 1;
        assertEquals(2, box.longest(box.collectors));
    }

    @Test
    public void testRun() {
        GaltonBox box = new GaltonBox(5, 1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        box.run();
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("|o|"));
    }

    @Test
    public void testRandomness() {
        GaltonBox box = new GaltonBox(5, 100);
        int[] counts = new int[6];
        for (int i = 0; i < 100; i++) {
            box.m_balls[i].m_row = 0;
            counts[box.m_balls[i].m_col]++;
        }
        for (int count : counts) {
            assertTrue(count > 0);
        }
    }
}
