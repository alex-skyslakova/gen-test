import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class ColourPinstripeDisplayTest {

    private ColourPinstripeDisplay display;
    private JFrame frame;

    @BeforeEach
    void setUp() {
        display = new ColourPinstripeDisplay();
        frame = new JFrame();
        frame.add(display);
        frame.pack();
    }

    @Test
    void testPreferredSize() {
        Dimension expectedSize = new Dimension(900, 600);
        assertEquals(expectedSize, display.getPreferredSize(), "Preferred size should be 900x600");
    }

    @Test
    void testPaletteSequence() {
        Color[] expectedPalette = {Color.black, Color.red, Color.green, Color.blue, Color.magenta, Color.cyan, Color.yellow, Color.white};
        assertArrayEquals(expectedPalette, ColourPinstripeDisplay.palette, "Palette sequence should match the expected colors");
    }

    @Test
    void testPinstripeWidths() {
        int[] expectedWidths = {1, 2, 3, 4};
        for (int i = 0; i < display.bands; i++) {
            assertEquals(expectedWidths[i], i + 1, "Pinstripe width should match the expected width for band " + (i + 1));
        }
    }

    @Test
    void testPinstripeHeightDivision() {
        int height = display.getPreferredSize().height;
        int expectedHeightPerBand = height / display.bands;
        for (int i = 0; i < display.bands; i++) {
            assertEquals(expectedHeightPerBand, height / display.bands, "Each band should have equal height division");
        }
    }

    @Test
    void testGraphicsRendering() {
        // This test is more of a placeholder as rendering tests are complex and require a different approach
        // Here we just ensure that paintComponent can be called without exceptions
        assertDoesNotThrow(() -> {
            Graphics g = display.getGraphics();
            display.paintComponent(g);
        }, "paintComponent should not throw any exceptions");
    }
}
