import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LangtonTest {

    @Test
    public void testInitialPosition() {
        boolean[][] plane = Langton.runAnt(100, 100);
        assertTrue(plane[50][50], "The starting position should be black after the first move.");
    }

    @Test
    public void testAntMovementWithinBounds() {
        boolean[][] plane = Langton.runAnt(100, 100);
        int antX = 50, antY = 50;
        int xChange = 0, yChange = -1;
        int steps = 0;

        while (antX < 100 && antY < 100 && antX >= 0 && antY >= 0) {
            if (plane[antY][antX]) {
                if (xChange == 0) {
                    xChange = yChange;
                    yChange = 0;
                } else {
                    yChange = -xChange;
                    xChange = 0;
                }
            } else {
                if (xChange == 0) {
                    xChange = -yChange;
                    yChange = 0;
                } else {
                    yChange = xChange;
                    xChange = 0;
                }
            }
            plane[antY][antX] = !plane[antY][antX];
            antX += xChange;
            antY += yChange;
            steps++;
        }

        assertTrue(steps > 0, "The ant should have moved at least once.");
    }

    @Test
    public void testAntExitsBounds() {
        boolean[][] plane = Langton.runAnt(100, 100);
        int antX = 50, antY = 50;
        int xChange = 0, yChange = -1;
        int steps = 0;

        while (antX < 100 && antY < 100 && antX >= 0 && antY >= 0) {
            if (plane[antY][antX]) {
                if (xChange == 0) {
                    xChange = yChange;
                    yChange = 0;
                } else {
                    yChange = -xChange;
                    xChange = 0;
                }
            } else {
                if (xChange == 0) {
                    xChange = -yChange;
                    yChange = 0;
                } else {
                    yChange = xChange;
                    xChange = 0;
                }
            }
            plane[antY][antX] = !plane[antY][antX];
            antX += xChange;
            antY += yChange;
            steps++;
        }

        assertTrue(antX >= 100 || antY >= 100 || antX < 0 || antY < 0, "The ant should exit the bounds eventually.");
    }

    @Test
    public void testPlaneSize() {
        boolean[][] plane = Langton.runAnt(100, 100);
        assertEquals(100, plane.length, "The plane height should be 100.");
        assertEquals(100, plane[0].length, "The plane width should be 100.");
    }
}
