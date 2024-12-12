import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.awt.event.MouseEvent
import javax.swing.JLabel

class RotateTest {

    private lateinit var rotate: Rotate

    @BeforeEach
    fun setUp() {
        rotate = Rotate()
    }

    @Test
    fun testInitialText() {
        assertEquals("Hello World! ", rotate.label.text)
    }

    @Test
    fun testRotateRight() {
        rotate.rotRight = true
        rotate.startIdx = 0
        rotate.label.text = getRotatedText(rotate.text, rotate.startIdx)
        assertEquals("Hello World! ", rotate.label.text)

        // Simulate one rotation step to the right
        rotate.startIdx = (rotate.startIdx - 1 + rotate.text.length) % rotate.text.length
        rotate.label.text = getRotatedText(rotate.text, rotate.startIdx)
        assertEquals(" Hello World!", rotate.label.text)
    }

    @Test
    fun testRotateLeft() {
        rotate.rotRight = false
        rotate.startIdx = 0
        rotate.label.text = getRotatedText(rotate.text, rotate.startIdx)
        assertEquals("Hello World! ", rotate.label.text)

        // Simulate one rotation step to the left
        rotate.startIdx = (rotate.startIdx + 1) % rotate.text.length
        rotate.label.text = getRotatedText(rotate.text, rotate.startIdx)
        assertEquals("ello World! H", rotate.label.text)
    }

    @Test
    fun testMouseClickReversesDirection() {
        val initialDirection = rotate.rotRight
        val mouseEvent = MouseEvent(rotate.label, 0, 0, 0, 0, 0, 1, false)
        rotate.label.mouseListeners.forEach { it.mouseClicked(mouseEvent) }
        assertEquals(!initialDirection, rotate.rotRight)
    }

    @Test
    fun testGetRotatedText() {
        assertEquals("Hello World! ", getRotatedText("Hello World! ", 0))
        assertEquals("ello World! H", getRotatedText("Hello World! ", 1))
        assertEquals(" World! Hello", getRotatedText("Hello World! ", 6))
    }
}
