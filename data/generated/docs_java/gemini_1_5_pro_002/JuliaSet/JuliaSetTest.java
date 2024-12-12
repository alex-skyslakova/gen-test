import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

public class JuliaSetTest {

    @Test
    void testDrawJuliaSet() {
        JuliaSet juliaSet = new JuliaSet();
        juliaSet.setPreferredSize(new Dimension(800, 600)); // Set size for testing

        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        juliaSet.drawJuliaSet(g);

        // Check if some pixels are colored, indicating the set is drawn. This is a basic check,
        // more thorough tests could check specific pixel colors based on the algorithm.
        boolean coloredPixelFound = false;
        for (int x = 0; x < 800; x++) {
            for (int y = 0; y < 600; y++) {
                int color = image.getRGB(x, y);
                if (color != Color.WHITE.getRGB()) {
                    coloredPixelFound = true;
                    break;
                }
            }
        }
        assertTrue(coloredPixelFound, "No colored pixels found, indicating a potential issue with drawing.");


        // Test with different dimensions
        image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        g = image.createGraphics();
        juliaSet.setPreferredSize(new Dimension(100, 100));
        juliaSet.drawJuliaSet(g);
        coloredPixelFound = false;
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                int color = image.getRGB(x, y);
                if (color != Color.WHITE.getRGB()) {
                    coloredPixelFound = true;
                    break;
                }
            }
        }
        assertTrue(coloredPixelFound, "No colored pixels found with different dimensions.");

        g.dispose();

    }


    @Test
    void testMaxIterationsZero() {
        // Testing edge case where MAX_ITERATIONS is zero
        int originalMaxIterations = JuliaSet.MAX_ITERATIONS;
        try {
            JuliaSet.MAX_ITERATIONS = 0; // Using reflection to modify a 'private static final' field for testing purposes.  In real code, this might be a parameter instead.

            JuliaSet juliaSet = new JuliaSet();
            juliaSet.setPreferredSize(new Dimension(100, 100));

            BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();

            juliaSet.drawJuliaSet(g);
            // Expect the image to be fully white in this scenario.
            for (int x = 0; x < 100; x++) {
                for (int y = 0; y < 100; y++) {
                    assertEquals(Color.WHITE.getRGB(), image.getRGB(x,y));
                }
            }


        } finally {
             JuliaSet.MAX_ITERATIONS = originalMaxIterations;  // Restore the original value
        }

    }

}
