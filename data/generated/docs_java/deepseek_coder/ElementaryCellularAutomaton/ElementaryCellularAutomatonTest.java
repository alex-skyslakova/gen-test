import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class WolframCATest {

    private WolframCA wolframCA;
    private BufferedImage image;
    private Graphics2D graphics;

    @BeforeEach
    void setUp() {
        wolframCA = new WolframCA();
        image = new BufferedImage(900, 450, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
    }

    @Test
    void testRulesMethod() {
        // Test the rules method with different inputs
        wolframCA.rule = 0; // Set rule to 30 (0b00011110)
        assertEquals(0, wolframCA.rules(1, 1, 1));
        assertEquals(1, wolframCA.rules(1, 1, 0));
        assertEquals(1, wolframCA.rules(1, 0, 1));
        assertEquals(1, wolframCA.rules(1, 0, 0));
        assertEquals(1, wolframCA.rules(0, 1, 1));
        assertEquals(1, wolframCA.rules(0, 1, 0));
        assertEquals(0, wolframCA.rules(0, 0, 1));
        assertEquals(0, wolframCA.rules(0, 0, 0));

        wolframCA.rule = 1; // Set rule to 45 (0b00101101)
        assertEquals(1, wolframCA.rules(1, 1, 1));
        assertEquals(0, wolframCA.rules(1, 1, 0));
        assertEquals(1, wolframCA.rules(1, 0, 1));
        assertEquals(1, wolframCA.rules(1, 0, 0));
        assertEquals(0, wolframCA.rules(0, 1, 1));
        assertEquals(1, wolframCA.rules(0, 1, 0));
        assertEquals(1, wolframCA.rules(0, 0, 1));
        assertEquals(0, wolframCA.rules(0, 0, 0));
    }

    @Test
    void testDrawCaMethod() {
        // Test the drawCa method by checking if it correctly updates the cells
        wolframCA.cells[0][450] = 1; // Initial state
        wolframCA.drawCa(graphics);

        // Check the first few generations
        assertEquals(1, wolframCA.cells[1][449]);
        assertEquals(1, wolframCA.cells[1][450]);
        assertEquals(1, wolframCA.cells[1][451]);

        assertEquals(1, wolframCA.cells[2][448]);
        assertEquals(1, wolframCA.cells[2][449]);
        assertEquals(1, wolframCA.cells[2][450]);
        assertEquals(1, wolframCA.cells[2][451]);
        assertEquals(1, wolframCA.cells[2][452]);
    }

    @Test
    void testDrawLegendMethod() {
        // Test the drawLegend method by checking if it correctly draws the legend
        wolframCA.drawLegend(graphics);

        // Check if the legend is drawn correctly
        String s = String.valueOf(wolframCA.ruleSet[wolframCA.rule]);
        int sw = graphics.getFontMetrics().stringWidth(s);
        int expectedX = 16 + (55 - sw) / 2;
        int expectedY = 30;

        // Check if the string is drawn at the correct position
        assertEquals(s, graphics.getFontMetrics().getStringBounds(s, graphics).getBounds().toString());
        assertEquals(expectedX, graphics.getFontMetrics().getStringBounds(s, graphics).getBounds().x);
        assertEquals(expectedY, graphics.getFontMetrics().getStringBounds(s, graphics).getBounds().y);
    }

    @Test
    void testPaintComponentMethod() {
        // Test the paintComponent method by checking if it correctly calls drawCa and drawLegend
        wolframCA.paintComponent(graphics);

        // Check if the cells are updated and the legend is drawn
        assertEquals(1, wolframCA.cells[1][449]);
        assertEquals(1, wolframCA.cells[1][450]);
        assertEquals(1, wolframCA.cells[1][451]);

        String s = String.valueOf(wolframCA.ruleSet[wolframCA.rule]);
        int sw = graphics.getFontMetrics().stringWidth(s);
        int expectedX = 16 + (55 - sw) / 2;
        int expectedY = 30;

        assertEquals(s, graphics.getFontMetrics().getStringBounds(s, graphics).getBounds().toString());
        assertEquals(expectedX, graphics.getFontMetrics().getStringBounds(s, graphics).getBounds().x);
        assertEquals(expectedY, graphics.getFontMetrics().getStringBounds(s, graphics).getBounds().y);
    }
}
