import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.*;

class CuboidTest {
    private Cuboid cuboid;

    @BeforeEach
    void setUp() {
        cuboid = new Cuboid();
    }

    @Test
    void testInitialScale() {
        double[][] expectedNodes = {
                {-80, -120, -160}, {-80, -120, 160}, {-80, 120, -160}, {-80, 120, 160},
                {80, -120, -160}, {80, -120, 160}, {80, 120, -160}, {80, 120, 160}
        };
        assertArrayEquals(expectedNodes, cuboid.nodes);
    }

    @Test
    void testRotateCube() {
        double[][] initialNodes = new double[cuboid.nodes.length][];
        for (int i = 0; i < cuboid.nodes.length; i++) {
            initialNodes[i] = cuboid.nodes[i].clone();
        }

        cuboid.rotateCube(Math.PI / 4, Math.PI / 4);

        assertNotEquals(initialNodes, cuboid.nodes);
    }

    @Test
    void testMousePressed() {
        MouseEvent pressEvent = new MouseEvent(cuboid, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 100, 100, 1, false);
        cuboid.getMouseListeners()[0].mousePressed(pressEvent);

        assertEquals(100, cuboid.mouseX);
        assertEquals(100, cuboid.mouseY);
    }

    @Test
    void testMouseDragged() {
        MouseEvent pressEvent = new MouseEvent(cuboid, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 100, 100, 1, false);
        cuboid.getMouseListeners()[0].mousePressed(pressEvent);

        MouseEvent dragEvent = new MouseEvent(cuboid, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 110, 110, 1, false);
        cuboid.getMouseMotionListeners()[0].mouseDragged(dragEvent);

        assertEquals(110, cuboid.mouseX);
        assertEquals(110, cuboid.mouseY);
    }

    @Test
    void testDrawCube() {
        JFrame frame = new JFrame();
        frame.add(cuboid);
        frame.pack();
        frame.setVisible(true);

        Graphics g = cuboid.getGraphics();
        assertNotNull(g);

        cuboid.drawCube((Graphics2D) g);
    }

    @Test
    void testPaintComponent() {
        JFrame frame = new JFrame();
        frame.add(cuboid);
        frame.pack();
        frame.setVisible(true);

        Graphics g = cuboid.getGraphics();
        assertNotNull(g);

        cuboid.paintComponent(g);
    }
}
