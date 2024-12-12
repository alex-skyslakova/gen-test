import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

class DrawAPixelTest {

    @Test
    void testWindowSize() {
        DrawAPixel window = new DrawAPixel();
        assertEquals(320, window.getWidth());
        assertEquals(240, window.getHeight());
    }

    @Test
    void testPixelColorAndPosition() {
        DrawAPixel window = new DrawAPixel();
        BufferedImage image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        window.paint(g);

        // Check the color of the pixel at (100, 100)
        int pixelColor = image.getRGB(100, 100);
        assertEquals(new Color(255, 0, 0).getRGB(), pixelColor);

        // Check the color of a pixel that should not be red
        int nonPixelColor = image.getRGB(0, 0);
        assertNotEquals(new Color(255, 0, 0).getRGB(), nonPixelColor);
    }
}
