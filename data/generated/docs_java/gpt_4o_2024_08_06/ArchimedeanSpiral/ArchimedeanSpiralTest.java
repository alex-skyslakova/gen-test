import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.*;
import javax.swing.*;

public class ArchimedeanSpiralTest {

    @Test
    public void testPreferredSize() {
        ArchimedeanSpiral spiral = new ArchimedeanSpiral();
        Dimension expectedSize = new Dimension(640, 640);
        assertEquals(expectedSize, spiral.getPreferredSize(), "Preferred size should be 640x640");
    }

    @Test
    public void testBackgroundColor() {
        ArchimedeanSpiral spiral = new ArchimedeanSpiral();
        assertEquals(Color.white, spiral.getBackground(), "Background color should be white");
    }

    @Test
    public void testDrawGrid() {
        ArchimedeanSpiral spiral = new ArchimedeanSpiral();
        BufferedImage image = new BufferedImage(640, 640, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        spiral.drawGrid(g2d);

        // Check if grid lines are drawn by verifying pixel colors
        // This is a basic check, more sophisticated image comparison can be done
        int gridColor = new Color(0xEEEEEE).getRGB();
        assertEquals(gridColor, image.getRGB(320, 320), "Grid should be drawn at the center");
    }

    @Test
    public void testDrawSpiral() {
        ArchimedeanSpiral spiral = new ArchimedeanSpiral();
        BufferedImage image = new BufferedImage(640, 640, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        spiral.drawSpiral(g2d);

        // Check if spiral is drawn by verifying pixel colors
        // This is a basic check, more sophisticated image comparison can be done
        int spiralColor = Color.orange.getRGB();
        assertNotEquals(0, image.getRGB(320, 320), "Spiral should be drawn at the center");
    }

    @Test
    public void testPlot() {
        ArchimedeanSpiral spiral = new ArchimedeanSpiral();
        BufferedImage image = new BufferedImage(640, 640, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        spiral.plot(g2d, 320, 320);

        // Check if a point is plotted at the center
        int pointColor = Color.orange.getRGB();
        assertNotEquals(0, image.getRGB(320, 320), "Point should be plotted at the center");
    }
}
