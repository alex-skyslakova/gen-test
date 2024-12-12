import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

public class ArchimedeanSpiralTest {

    @Test
    void testDrawGrid() {
        ArchimedeanSpiral spiral = new ArchimedeanSpiral();
        spiral.setSize(640, 640); // Set size for consistent testing
        BufferedImage image = new BufferedImage(640, 640, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        spiral.drawGrid(g2d);

        // Check if lines are drawn by examining pixel colors.  Exact verification is tricky due to antialiasing.
        // This checks a few strategic points.  More comprehensive image diffing would be better for visual elements.
        assertNotEquals(image.getRGB(320, 320), image.getRGB(0,0)); // Center vs Corner, Assuming center has lines drawn
        
       // Similarly for other lines/rings
    }


    @Test
    void testDrawSpiral() {
        ArchimedeanSpiral spiral = new ArchimedeanSpiral();
        spiral.setSize(640, 640);
        BufferedImage image = new BufferedImage(640, 640, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        spiral.drawSpiral(g2d);
       
        // Similar pixel checks as grid. Spiral is harder to verify automatically.
        // This tests that SOME plot was made.
        boolean plotFound = false;
        for (int y = 0; y < 640; ++y) {
            for (int x = 0; x < 640; ++x) {
                if (image.getRGB(x, y) != 0) { // 0 = transparent; so anything drawn will not be 0.
                    plotFound = true;
                    break;
                }
            }
            if (plotFound) break;
        }
        assertTrue(plotFound);
    }

    @Test
    void testPlot() {
        ArchimedeanSpiral spiral = new ArchimedeanSpiral();
        spiral.setSize(640, 640);
        BufferedImage image = new BufferedImage(640, 640, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        spiral.plot(g2d, 320, 320);

        // Verify the specific pixel was plotted
        assertNotEquals(0, image.getRGB(320, 320));
    }



}
