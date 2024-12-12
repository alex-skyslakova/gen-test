import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ColourPinstripeDisplayTest {

    @Test
    void testPinstripeWidths() {
        ColourPinstripeDisplay display = new ColourPinstripeDisplay();
        display.setSize(900, 600); // Set size for testing
        BufferedImage image = new BufferedImage(900, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        display.paintComponent(g2d);

        int h = 600;
        int[] bandHeights = {h/4, h/2, 3*h/4, h};
        int[] stripeWidths = {1, 2, 3, 4};


        for (int b = 0; b < 4; b++) {
            int expectedWidth = stripeWidths[b];
            for (int x = 0; x < 10 * expectedWidth ; x += expectedWidth) { // Check first 10 stripes of each band
                Color color1 = new Color(image.getRGB(x, bandHeights[b]-1));
                Color color2 = new Color(image.getRGB(x + 1, bandHeights[b]-1));

                if(x + 1 < 900) { // Boundary check to avoid going outside the image bounds
                  if(expectedWidth == 1) {
                      assertNotEquals(color1, color2, "Stripe width incorrect at band " + (b+1) + " and x=" + x);
                  } else {
                      assertEquals(color1, color2, "Stripe width incorrect at band " + (b+1) + " and x=" + x);
                  }
                }
                

            }

        }

    }
     @Test
    void testPaletteUsage() {
        ColourPinstripeDisplay display = new ColourPinstripeDisplay();
        Color[] expectedPalette = ColourPinstripeDisplay.palette;
        
        display.setSize(900,600);
        BufferedImage image = new BufferedImage(900,600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        display.paintComponent(g2d);

        for(int i = 0; i < expectedPalette.length; i++) {
            Color actualColor = new Color(image.getRGB(i, 0)); // Check colors in the first row
            assertEquals(expectedPalette[i], actualColor, "Palette color mismatch at index" + i);
        }

    }

}
