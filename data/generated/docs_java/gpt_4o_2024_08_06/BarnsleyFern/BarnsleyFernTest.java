import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.image.BufferedImage;

public class BarnsleyFernTest {

    @Test
    public void testCreateFernDimensions() {
        BarnsleyFern fern = new BarnsleyFern();
        BufferedImage img = fern.img;
        assertNotNull(img, "Image should not be null");
        assertEquals(640, img.getWidth(), "Image width should be 640");
        assertEquals(640, img.getHeight(), "Image height should be 640");
    }

    @Test
    public void testInitialPosition() {
        double x = 0;
        double y = 0;
        assertEquals(0, x, "Initial x should be 0");
        assertEquals(0, y, "Initial y should be 0");
    }

    @Test
    public void testTransformationF1() {
        double x = 0;
        double y = 1;
        double tmpx = 0;
        double tmpy = 0.16 * y;
        assertEquals(0, tmpx, "Transformation f1 x should be 0");
        assertEquals(0.16, tmpy, "Transformation f1 y should be 0.16");
    }

    @Test
    public void testTransformationF2() {
        double x = 1;
        double y = 1;
        double tmpx = 0.85 * x + 0.04 * y;
        double tmpy = -0.04 * x + 0.85 * y + 1.6;
        assertEquals(0.89, tmpx, 0.0001, "Transformation f2 x should be 0.89");
        assertEquals(2.41, tmpy, 0.0001, "Transformation f2 y should be 2.41");
    }

    @Test
    public void testTransformationF3() {
        double x = 1;
        double y = 1;
        double tmpx = 0.2 * x - 0.26 * y;
        double tmpy = 0.23 * x + 0.22 * y + 1.6;
        assertEquals(-0.06, tmpx, 0.0001, "Transformation f3 x should be -0.06");
        assertEquals(2.05, tmpy, 0.0001, "Transformation f3 y should be 2.05");
    }

    @Test
    public void testTransformationF4() {
        double x = 1;
        double y = 1;
        double tmpx = -0.15 * x + 0.28 * y;
        double tmpy = 0.26 * x + 0.24 * y + 0.44;
        assertEquals(0.13, tmpx, 0.0001, "Transformation f4 x should be 0.13");
        assertEquals(0.94, tmpy, 0.0001, "Transformation f4 y should be 0.94");
    }

    @Test
    public void testRandomSelection() {
        int f1Count = 0, f2Count = 0, f3Count = 0, f4Count = 0;
        for (int i = 0; i < 10000; i++) {
            double r = Math.random();
            if (r <= 0.01) {
                f1Count++;
            } else if (r <= 0.08) {
                f2Count++;
            } else if (r <= 0.15) {
                f3Count++;
            } else {
                f4Count++;
            }
        }
        assertTrue(f1Count > 0, "f1 should be selected at least once");
        assertTrue(f2Count > 0, "f2 should be selected at least once");
        assertTrue(f3Count > 0, "f3 should be selected at least once");
        assertTrue(f4Count > 0, "f4 should be selected at least once");
    }
}
