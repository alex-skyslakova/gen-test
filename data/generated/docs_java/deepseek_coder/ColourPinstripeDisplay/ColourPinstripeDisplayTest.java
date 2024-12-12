import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ColourPinstripeDisplayTest {

    private ColourPinstripeDisplay display;
    private Graphics g;

    @BeforeEach
    void setUp() {
        display = new ColourPinstripeDisplay();
        g = Mockito.mock(Graphics.class);
    }

    @Test
    void testPaintComponentWithCorrectDimensions() {
        // Set the preferred size to a specific value for testing
        display.setPreferredSize(new Dimension(900, 600));

        // Mock the getWidth and getHeight methods
        when(g.getClipBounds()).thenReturn(new Rectangle(0, 0, 900, 600));

        // Call the paintComponent method
        display.paintComponent(g);

        // Verify that the correct colors and widths are used
        verify(g, times(1)).setColor(Color.BLACK);
        verify(g, times(1)).setColor(Color.RED);
        verify(g, times(1)).setColor(Color.GREEN);
        verify(g, times(1)).setColor(Color.BLUE);
        verify(g, times(1)).setColor(Color.MAGENTA);
        verify(g, times(1)).setColor(Color.CYAN);
        verify(g, times(1)).setColor(Color.YELLOW);
        verify(g, times(1)).setColor(Color.WHITE);

        // Verify that the correct rectangles are drawn
        verify(g, times(1)).fillRect(0, 0, 1, 150);
        verify(g, times(1)).fillRect(2, 150, 2, 150);
        verify(g, times(1)).fillRect(3, 300, 3, 150);
        verify(g, times(1)).fillRect(4, 450, 4, 150);
    }

    @Test
    void testPaintComponentWithDifferentDimensions() {
        // Set the preferred size to a different value for testing
        display.setPreferredSize(new Dimension(600, 400));

        // Mock the getWidth and getHeight methods
        when(g.getClipBounds()).thenReturn(new Rectangle(0, 0, 600, 400));

        // Call the paintComponent method
        display.paintComponent(g);

        // Verify that the correct colors and widths are used
        verify(g, times(1)).setColor(Color.BLACK);
        verify(g, times(1)).setColor(Color.RED);
        verify(g, times(1)).setColor(Color.GREEN);
        verify(g, times(1)).setColor(Color.BLUE);
        verify(g, times(1)).setColor(Color.MAGENTA);
        verify(g, times(1)).setColor(Color.CYAN);
        verify(g, times(1)).setColor(Color.YELLOW);
        verify(g, times(1)).setColor(Color.WHITE);

        // Verify that the correct rectangles are drawn
        verify(g, times(1)).fillRect(0, 0, 1, 100);
        verify(g, times(1)).fillRect(2, 100, 2, 100);
        verify(g, times(1)).fillRect(3, 200, 3, 100);
        verify(g, times(1)).fillRect(4, 300, 4, 100);
    }

    @Test
    void testPaintComponentWithMinimumDimensions() {
        // Set the preferred size to the minimum value for testing
        display.setPreferredSize(new Dimension(1, 1));

        // Mock the getWidth and getHeight methods
        when(g.getClipBounds()).thenReturn(new Rectangle(0, 0, 1, 1));

        // Call the paintComponent method
        display.paintComponent(g);

        // Verify that the correct colors and widths are used
        verify(g, times(1)).setColor(Color.BLACK);
        verify(g, times(0)).setColor(Color.RED);
        verify(g, times(0)).setColor(Color.GREEN);
        verify(g, times(0)).setColor(Color.BLUE);
        verify(g, times(0)).setColor(Color.MAGENTA);
        verify(g, times(0)).setColor(Color.CYAN);
        verify(g, times(0)).setColor(Color.YELLOW);
        verify(g, times(0)).setColor(Color.WHITE);

        // Verify that the correct rectangles are drawn
        verify(g, times(1)).fillRect(0, 0, 1, 1);
    }
}
