import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

public class GreybarsTest {

    @Test
    public void testPaint() {
        Greybars gb = new Greybars();
        gb.setSize(640, 320); // Ensure consistent size for testing

        BufferedImage image = new BufferedImage(gb.getWidth(), gb.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        gb.paint(g2d);
        g2d.dispose();

        // Test top quarter (8 bars, black to white)
        testGradient(image, 0, 0, 8, 0, 255);

        // Test second quarter (16 bars, white to black)
        testGradient(image, 0, 80, 16, 255, 0);

        // Test third quarter (32 bars, black to white)
        testGradient(image, 0, 160, 32, 0, 255);

        // Test bottom quarter (64 bars, white to black)
        testGradient(image, 0, 240, 64, 255, 0);
    }


    private void testGradient(BufferedImage image, int xStart, int yStart, int numBars, int startColor, int endColor) {
        int barWidth = image.getWidth() / numBars;
        int barHeight = image.getHeight() / 4;

        for (int i = 0; i < numBars; i++) {
            int x = xStart + i * barWidth;
            int y = yStart;

            // Sample the middle of the bar to avoid edge effects
            Color color = new Color(image.getRGB(x + barWidth / 2, y + barHeight / 2));

            int expectedGrey = calculateExpectedGrey(i, numBars, startColor, endColor);

            assertEquals(expectedGrey, color.getRed(), 1,  "Incorrect color at bar " + i + " in section starting at y=" + yStart);
            assertEquals(expectedGrey, color.getGreen(), 1, "Incorrect color at bar " + i + " in section starting at y=" + yStart);
            assertEquals(expectedGrey, color.getBlue(), 1, "Incorrect color at bar " + i + " in section starting at y=" + yStart);
        }
    }


    private int calculateExpectedGrey(int barIndex, int numBars, int startColor, int endColor) {
        double colorStep = (double)(endColor - startColor) / (numBars - 1);
        return (int)Math.round(startColor + barIndex * colorStep);
    }

}
