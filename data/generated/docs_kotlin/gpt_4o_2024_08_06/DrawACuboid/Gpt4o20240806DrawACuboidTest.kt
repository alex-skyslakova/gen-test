import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.awt.event.MouseEvent
import javax.swing.JFrame

class CuboidTest {

    @Test
    fun `test initial scale of cuboid`() {
        val cuboid = Cuboid()
        val expectedNodes = arrayOf(
            doubleArrayOf(-80.0, -120.0, -160.0),
            doubleArrayOf(-80.0, -120.0, 160.0),
            doubleArrayOf(-80.0, 120.0, -160.0),
            doubleArrayOf(-80.0, 120.0, 160.0),
            doubleArrayOf(80.0, -120.0, -160.0),
            doubleArrayOf(80.0, -120.0, 160.0),
            doubleArrayOf(80.0, 120.0, -160.0),
            doubleArrayOf(80.0, 120.0, 160.0)
        )
        assertArrayEquals(expectedNodes, cuboid.nodes)
    }

    @Test
    fun `test rotation of cuboid`() {
        val cuboid = Cuboid()
        cuboid.rotateCube(Math.PI / 5.0, Math.PI / 9.0)
        // Since rotation involves trigonometric functions, we check if the nodes are not in their initial positions
        assertNotEquals(-80.0, cuboid.nodes[0][0])
        assertNotEquals(-120.0, cuboid.nodes[0][1])
        assertNotEquals(-160.0, cuboid.nodes[0][2])
    }

    @Test
    fun `test mouse drag rotation`() {
        val cuboid = Cuboid()
        val initialNodePosition = cuboid.nodes[0].clone()
        
        // Simulate mouse press
        val mousePressedEvent = MouseEvent(cuboid, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 100, 100, 1, false)
        cuboid.mouseListeners.forEach { it.mousePressed(mousePressedEvent) }

        // Simulate mouse drag
        val mouseDraggedEvent = MouseEvent(cuboid, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 110, 110, 1, false)
        cuboid.mouseMotionListeners.forEach { it.mouseDragged(mouseDraggedEvent) }

        // Check if the node position has changed after dragging
        assertNotEquals(initialNodePosition[0], cuboid.nodes[0][0])
        assertNotEquals(initialNodePosition[1], cuboid.nodes[0][1])
        assertNotEquals(initialNodePosition[2], cuboid.nodes[0][2])
    }

    @Test
    fun `test JFrame setup`() {
        SwingUtilities.invokeAndWait {
            val frame = JFrame()
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.title = "Cuboid"
            frame.isResizable = false
            frame.add(Cuboid(), BorderLayout.CENTER)
            frame.pack()
            frame.setLocationRelativeTo(null)
            frame.isVisible = true

            assertEquals(JFrame.EXIT_ON_CLOSE, frame.defaultCloseOperation)
            assertEquals("Cuboid", frame.title)
            assertFalse(frame.isResizable)
            assertTrue(frame.isVisible)
        }
    }
}
