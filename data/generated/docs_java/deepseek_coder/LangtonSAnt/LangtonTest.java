import static org.junit.Assert.*;
import org.junit.Test;

public class LangtonTest {

    @Test
    public void testRunAntInitialState() {
        boolean[][] plane = Langton.runAnt(100, 100);
        // The ant starts in the middle of the plane
        int middleX = 50;
        int middleY = 50;
        // The initial cell should be white (false)
        assertFalse(plane[middleY][middleX]);
    }

    @Test
    public void testRunAntMovement() {
        boolean[][] plane = Langton.runAnt(100, 100);
        // The ant starts in the middle of the plane
        int middleX = 50;
        int middleY = 50;
        // After one step, the ant should move up and the cell should be black (true)
        assertTrue(plane[middleY - 1][middleX]);
    }

    @Test
    public void testRunAntOutOfBounds() {
        boolean[][] plane = Langton.runAnt(100, 100);
        // The ant should eventually move out of bounds
        // We can't predict exactly when, but we can check that the ant is not in the middle
        int middleX = 50;
        int middleY = 50;
        boolean antInMiddle = false;
        for (int i = 0; i < plane.length; i++) {
            for (int j = 0; j < plane[0].length; j++) {
                if (plane[i][j]) {
                    antInMiddle = true;
                    break;
                }
            }
        }
        assertFalse(antInMiddle);
    }

    @Test
    public void testRunAntPlaneSize() {
        boolean[][] plane = Langton.runAnt(100, 100);
        // The plane should be 100x100
        assertEquals(100, plane.length);
        assertEquals(100, plane[0].length);
    }

    @Test
    public void testRunAntColorChange() {
        boolean[][] plane = Langton.runAnt(100, 100);
        // The ant starts in the middle of the plane
        int middleX = 50;
        int middleY = 50;
        // The initial cell should be white (false)
        assertFalse(plane[middleY][middleX]);
        // After one step, the cell should be black (true)
        assertTrue(plane[middleY - 1][middleX]);
    }

    @Test
    public void testRunAntDirectionChange() {
        boolean[][] plane = Langton.runAnt(100, 100);
        // The ant starts in the middle of the plane
        int middleX = 50;
        int middleY = 50;
        // After one step, the ant should move up
        assertTrue(plane[middleY - 1][middleX]);
        // After two steps, the ant should move right
        assertTrue(plane[middleY - 1][middleX + 1]);
    }
}
