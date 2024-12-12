import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import javax.swing.*;

class FibonacciWordFractalTest {

    @Test
    void testWordFractalBaseCases() {
        FibonacciWordFractal fractal1 = new FibonacciWordFractal(1);
        assertEquals("1", fractal1.wordFractal);

        FibonacciWordFractal fractal0 = new FibonacciWordFractal(0);
        assertEquals("", fractal0.wordFractal);
    }

    @Test
    void testWordFractalGeneralCase() {
        FibonacciWordFractal fractal = new FibonacciWordFractal(5);
        assertEquals("01001010", fractal.wordFractal);
    }

    @Test
    void testDrawWordFractal() {
        // Create a mock Graphics2D object
        Graphics2D g2d = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();
        
        FibonacciWordFractal fractal = new FibonacciWordFractal(3);
        fractal.drawWordFractal(g2d, 0, 0, 1, 0);
        
        // Since we can't directly test the drawing, we ensure no exceptions are thrown
        assertTrue(true);
    }

    @Test
    void testPaintComponent() {
        FibonacciWordFractal fractal = new FibonacciWordFractal(3);
        Graphics g = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).getGraphics();
        
        // Call paintComponent and ensure no exceptions are thrown
        fractal.paintComponent(g);
        assertTrue(true);
    }

    @Test
    void testMain() {
        // Test the main method to ensure it runs without exceptions
        FibonacciWordFractal.main(new String[]{});
        assertTrue(true);
    }
}
