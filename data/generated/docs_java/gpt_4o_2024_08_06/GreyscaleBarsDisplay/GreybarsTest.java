import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreybarsTest {

    private Greybars greybars;
    private BufferedImage image;

    @BeforeEach
    public void setUp() {
        greybars = new Greybars();
        image = new BufferedImage(greybars.getWidth(), greybars.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    @Test
    public void testTopQuarterBars() {
        Graphics g = image.getGraphics();
        greybars.paint(g);

        int expectedBars = 8;
        int columnWidth = greybars.getWidth() / expectedBars;
        int yStart = 0;
        int columnHeight = greybars.getHeight() / 4;

        for (int i = 0; i < expectedBars; i++) {
            int expectedColor = (int) Math.round(i * (255.0 / (expectedBars - 1)));
            Color color = new Color(image.getRGB(i * columnWidth, yStart));
            assertEquals(expectedColor, color.getRed(), "Top quarter bar " + i + " color mismatch");
        }
    }

    @Test
    public void testSecondQuarterBars() {
        Graphics g = image.getGraphics();
        greybars.paint(g);

        int expectedBars = 16;
        int columnWidth = greybars.getWidth() / expectedBars;
        int yStart = greybars.getHeight() / 4;
        int columnHeight = greybars.getHeight() / 4;

        for (int i = 0; i < expectedBars; i++) {
            int expectedColor = (int) Math.round(255 - i * (255.0 / (expectedBars - 1)));
            Color color = new Color(image.getRGB(i * columnWidth, yStart));
            assertEquals(expectedColor, color.getRed(), "Second quarter bar " + i + " color mismatch");
        }
    }

    @Test
    public void testThirdQuarterBars() {
        Graphics g = image.getGraphics();
        greybars.paint(g);

        int expectedBars = 32;
        int columnWidth = greybars.getWidth() / expectedBars;
        int yStart = greybars.getHeight() / 2;
        int columnHeight = greybars.getHeight() / 4;

        for (int i = 0; i < expectedBars; i++) {
            int expectedColor = (int) Math.round(i * (255.0 / (expectedBars - 1)));
            Color color = new Color(image.getRGB(i * columnWidth, yStart));
            assertEquals(expectedColor, color.getRed(), "Third quarter bar " + i + " color mismatch");
        }
    }

    @Test
    public void testBottomQuarterBars() {
        Graphics g = image.getGraphics();
        greybars.paint(g);

        int expectedBars = 64;
        int columnWidth = greybars.getWidth() / expectedBars;
        int yStart = 3 * greybars.getHeight() / 4;
        int columnHeight = greybars.getHeight() / 4;

        for (int i = 0; i < expectedBars; i++) {
            int expectedColor = (int) Math.round(255 - i * (255.0 / (expectedBars - 1)));
            Color color = new Color(image.getRGB(i * columnWidth, yStart));
            assertEquals(expectedColor, color.getRed(), "Bottom quarter bar " + i + " color mismatch");
        }
    }
}
