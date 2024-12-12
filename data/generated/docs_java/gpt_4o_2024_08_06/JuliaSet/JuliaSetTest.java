import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class JuliaSetTest {

    private JuliaSet juliaSet;

    @BeforeEach
    void setUp() {
        juliaSet = new JuliaSet();
    }

    @Test
    void testPreferredSize() {
        Dimension expectedSize = new Dimension(800, 600);
        assertEquals(expectedSize, juliaSet.getPreferredSize(), "Preferred size should be 800x600");
    }

    @Test
    void testBackgroundColor() {
        Color expectedColor = Color.white;
        assertEquals(expectedColor, juliaSet.getBackground(), "Background color should be white");
    }

    @Test
    void testDrawJuliaSet() {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        juliaSet.drawJuliaSet(g2d);

        // Check some pixel values to ensure the image is drawn
        int pixelColor = image.getRGB(400, 300); // Check the center pixel
        assertNotEquals(0, pixelColor, "Center pixel should not be black, indicating drawing occurred");

        g2d.dispose();
    }

    @Test
    void testPaintComponent() {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        juliaSet.paintComponent(g2d);

        // Check some pixel values to ensure the image is drawn
        int pixelColor = image.getRGB(400, 300); // Check the center pixel
        assertNotEquals(0, pixelColor, "Center pixel should not be black, indicating drawing occurred");

        g2d.dispose();
    }

    @Test
    void testMain() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Julia Set");
            frame.setResizable(false);
            frame.add(juliaSet, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            assertEquals("Julia Set", frame.getTitle(), "Frame title should be 'Julia Set'");
            assertFalse(frame.isResizable(), "Frame should not be resizable");
            assertTrue(frame.isVisible(), "Frame should be visible");
        });
    }
}
