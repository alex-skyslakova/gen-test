import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.awt.event.MouseEvent
import javax.swing.JLabel

class RotateTest {

    @Test
    fun `test initial text`() {
        val rot = Rotate()
        assertEquals("Hello World! ", rot.label.text)
    }

    @Test
    fun `test text rotation right`() {
        val rot = Rotate()
        rot.startIdx = 1
        val rotatedText = getRotatedText(rot.text, rot.startIdx)
        assertEquals("ello World! H", rotatedText)
    }

    @Test
    fun `test text rotation left`() {
        val rot = Rotate()
        rot.rotRight = false
        rot.startIdx = 1
        val rotatedText = getRotatedText(rot.text, rot.startIdx)
        assertEquals("ello World! H", rotatedText)
    }

    @Test
    fun `test mouse click reverses direction`() {
        val rot = Rotate()
        assertTrue(rot.rotRight)

        // Simulate a mouse click
        val mouseEvent = MouseEvent(rot.label, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false)
        rot.label.mouseListeners[0].mouseClicked(mouseEvent)

        assertFalse(rot.rotRight)
    }

    @Test
    fun `test text rotation after mouse click`() {
        val rot = Rotate()
        assertTrue(rot.rotRight)

        // Simulate a mouse click
        val mouseEvent = MouseEvent(rot.label, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false)
        rot.label.mouseListeners[0].mouseClicked(mouseEvent)

        assertFalse(rot.rotRight)

        rot.startIdx = 1
        val rotatedText = getRotatedText(rot.text, rot.startIdx)
        assertEquals("ello World! H", rotatedText)
    }

    @Test
    fun `test text rotation wraps around`() {
        val rot = Rotate()
        rot.startIdx = rot.text.length - 1
        val rotatedText = getRotatedText(rot.text, rot.startIdx)
        assertEquals(" Hello World!", rotatedText)
    }

    @Test
    fun `test text rotation wraps around left`() {
        val rot = Rotate()
        rot.rotRight = false
        rot.startIdx = 0
        val rotatedText = getRotatedText(rot.text, rot.startIdx)
        assertEquals(" Hello World!", rotatedText)
    }
}
