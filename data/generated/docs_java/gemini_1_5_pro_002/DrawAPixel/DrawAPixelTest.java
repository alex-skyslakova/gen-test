import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

public class DrawAPixelTest {

    @Test
    void testPixelColorAndPosition() {
        // Create a headless Graphics environment for testing
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();


        DrawAPixel frame = new DrawAPixel();
        frame.paint(g);
        // Dispose of the graphics context to avoid resource leaks in headless environment.
        g.dispose();


        // Check pixel color at (100, 100)
        Color pixelColor = new Color(image.getRGB(100, 100));
        assertEquals(255, pixelColor.getRed());
        assertEquals(0, pixelColor.getGreen());
        assertEquals(0, pixelColor.getBlue());

    }

    @Test
    void testWindowSize() {
        DrawAPixel frame = new DrawAPixel();
        assertEquals(320, frame.getWidth());
        assertEquals(240, frame.getHeight()); 
    }

    @Test
    void testWindowTitle(){
        DrawAPixel frame = new DrawAPixel();
        assertEquals("Red Pixel", frame.getTitle());
    }


}
