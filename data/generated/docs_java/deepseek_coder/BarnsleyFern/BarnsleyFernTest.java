import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

public class BarnsleyFernTest {

    @Test
    public void testBarnsleyFernConstructor() {
        BarnsleyFern fern = new BarnsleyFern();
        assertNotNull(fern);
        assertEquals(640, fern.getPreferredSize().width);
        assertEquals(640, fern.getPreferredSize().height);
        assertEquals(Color.WHITE, fern.getBackground());
        assertNotNull(fern.img);
        assertEquals(640, fern.img.getWidth());
        assertEquals(640, fern.img.getHeight());
    }

    @Test
    public void testCreateFern() {
        BarnsleyFern fern = new BarnsleyFern();
        BufferedImage img = fern.img;

        // Check if the image has been modified
        boolean hasNonZeroPixel = false;
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                if (img.getRGB(x, y) != 0) {
                    hasNonZeroPixel = true;
                    break;
                }
            }
            if (hasNonZeroPixel) break;
        }
        assertTrue(hasNonZeroPixel, "The image should have non-zero pixels after creating the fern.");
    }

    @Test
    public void testPaintComponent() {
        BarnsleyFern fern = new BarnsleyFern();
        JFrame frame = new JFrame();
        frame.add(fern, BorderLayout.CENTER);
        frame.pack();

        // Create a mock Graphics2D object
        BufferedImage testImage = new BufferedImage(640, 640, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = testImage.createGraphics();

        // Call the paintComponent method
        fern.paintComponent(g);

        // Check if the image has been drawn
        boolean hasNonZeroPixel = false;
        for (int x = 0; x < testImage.getWidth(); x++) {
            for (int y = 0; y < testImage.getHeight(); y++) {
                if (testImage.getRGB(x, y) != 0) {
                    hasNonZeroPixel = true;
                    break;
                }
            }
            if (hasNonZeroPixel) break;
        }
        assertTrue(hasNonZeroPixel, "The image should have non-zero pixels after painting.");
    }

    @Test
    public void testMainMethod() {
        // This test will just check if the main method runs without exceptions
        assertDoesNotThrow(() -> BarnsleyFern.main(new String[]{}));
    }
}
