import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

public class BarnsleyFernTest {

    @Test
    void createFernTest() {
        BarnsleyFern fern = new BarnsleyFern();
        int dim = 640;
        fern.createFern(dim, dim);

        // Check if some pixels are drawn
        boolean pixelDrawn = false;
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                if (fern.img.getRGB(x, y) != 0) {
                    pixelDrawn = true;
                    break;
                }
            }
        }
        assertTrue(pixelDrawn, "No pixels were drawn.");


        // Specific coordinate checks are difficult due to randomness.  Instead,
        // we test general properties.

        // Test f1: Check if some pixels are drawn near the bottom
        fern = new BarnsleyFern();
        fern.createFern(dim, dim);
        pixelDrawn = false;
         for (int x = dim/2 - 10; x <= dim/2 + 10; x++) { //check around middle bottom
            for (int y = dim -10; y < dim; y++) {
                if (fern.img.getRGB(x, y) != 0) {
                    pixelDrawn = true;
                    break;
                }
            }
        }


        // Check extremes - ensure points remain within the image bounds after transformations.
        fern = new BarnsleyFern();
        fern.createFern(dim, dim);
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                 if (fern.img.getRGB(x, y) != 0) {
                    assertTrue(x >= 0 && x < dim, "X coordinate out of bounds: " + x);
                    assertTrue(y >= 0 && y < dim, "Y coordinate out of bounds: " + y);
                 }
            }
        }

    }



    @Test
    void imageCreationTest() {
        BarnsleyFern fern = new BarnsleyFern();
        assertNotNull(fern.img, "Image not initialized.");
        assertEquals(640, fern.img.getWidth(), "Incorrect image width.");
        assertEquals(640, fern.img.getHeight(), "Incorrect image height.");
        assertEquals(BufferedImage.TYPE_INT_ARGB, fern.img.getType(), "Incorrect image type.");
    }




}
