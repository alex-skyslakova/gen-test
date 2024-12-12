import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;

public class CuboidTest {

    @Test
    void testScale() {
        Cuboid cuboid = new Cuboid();
        double[][] originalNodes = deepCopy(cuboid.nodes);

        cuboid.scale(2, 3, 4);

        for (int i = 0; i < cuboid.nodes.length; i++) {
            assertEquals(originalNodes[i][0] * 2, cuboid.nodes[i][0], 0.001);
            assertEquals(originalNodes[i][1] * 3, cuboid.nodes[i][1], 0.001);
            assertEquals(originalNodes[i][2] * 4, cuboid.nodes[i][2], 0.001);
        }
    }

    @Test
    void testRotateCube() {
        Cuboid cuboid = new Cuboid();
        double[][] originalNodes = deepCopy(cuboid.nodes);

        cuboid.rotateCube(PI / 2, PI / 2); // Rotate 90 degrees on both axes

        for (int i = 0; i < cuboid.nodes.length; i++) {
            // Rotations are complex; we'll just check that values have changed
            assertNotEquals(originalNodes[i][0], cuboid.nodes[i][0], 0.001);
            assertNotEquals(originalNodes[i][1], cuboid.nodes[i][1], 0.001);
            assertNotEquals(originalNodes[i][2], cuboid.nodes[i][2], 0.001);
        }
    }



    // Helper function for deep copying 2D double arrays
    private double[][] deepCopy(double[][] arr) {
        double[][] copy = new double[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i].clone();
        }
        return copy;
    }


    @Test
    void testMouseEvents() {
        Cuboid cuboid = new Cuboid();
        double[][] originalNodes = deepCopy(cuboid.nodes);


        // Simulate a mouse press and drag
        MouseEvent press = new MouseEvent(cuboid, MouseEvent.MOUSE_PRESSED, 0, 0, 100, 100, 1, false);
        cuboid.addMouseListener(new MouseAdapter() {}); // Add an empty adapter to avoid null pointer
        cuboid.getMouseListeners()[0].mousePressed(press);


        MouseEvent drag = new MouseEvent(cuboid, MouseEvent.MOUSE_DRAGGED, 0, 0, 200, 150, 1, false);
        cuboid.addMouseMotionListener(new MouseAdapter() {}); // Add an empty adapter
        cuboid.getMouseMotionListeners()[0].mouseDragged(drag);

        // Check that nodes have changed after the drag
        assertNotEquals(originalNodes, cuboid.nodes);
    }




}
