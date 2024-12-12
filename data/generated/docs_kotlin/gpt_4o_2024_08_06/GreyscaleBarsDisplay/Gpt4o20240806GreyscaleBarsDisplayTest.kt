import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.awt.Color
import java.awt.Graphics
import javax.swing.JFrame

class GreyBarsTest {

    @Test
    fun testInitialWindowSize() {
        val greyBars = GreyBars()
        assertEquals(640, greyBars.width)
        assertEquals(320, greyBars.height)
    }

    @Test
    fun testWindowTitle() {
        val greyBars = GreyBars()
        assertEquals("grey bars example!", greyBars.title)
    }

    @Test
    fun testPaintMethod() {
        val greyBars = GreyBars()
        val graphics = TestGraphics()

        greyBars.paint(graphics)

        // Check the number of rectangles drawn
        assertEquals(120, graphics.rectangles.size)

        // Validate the colors for each section
        // Top quarter: 8 bars from black to white
        for (i in 0 until 8) {
            val expectedColorValue = (i * 255 / 7).toInt()
            assertEquals(Color(expectedColorValue, expectedColorValue, expectedColorValue), graphics.rectangles[i].color)
        }

        // Second quarter: 16 bars from white to black
        for (i in 8 until 24) {
            val expectedColorValue = (255 - ((i - 8) * 255 / 15)).toInt()
            assertEquals(Color(expectedColorValue, expectedColorValue, expectedColorValue), graphics.rectangles[i].color)
        }

        // Third quarter: 32 bars from black to white
        for (i in 24 until 56) {
            val expectedColorValue = ((i - 24) * 255 / 31).toInt()
            assertEquals(Color(expectedColorValue, expectedColorValue, expectedColorValue), graphics.rectangles[i].color)
        }

        // Bottom quarter: 64 bars from white to black
        for (i in 56 until 120) {
            val expectedColorValue = (255 - ((i - 56) * 255 / 63)).toInt()
            assertEquals(Color(expectedColorValue, expectedColorValue, expectedColorValue), graphics.rectangles[i].color)
        }
    }

    // Helper class to simulate Graphics for testing
    private class TestGraphics : Graphics() {
        val rectangles = mutableListOf<Rectangle>()

        override fun fillRect(x: Int, y: Int, width: Int, height: Int) {
            rectangles.add(Rectangle(x, y, width, height, color))
        }

        // Other Graphics methods are not needed for this test
        override fun create(): Graphics = this
        override fun translate(x: Int, y: Int) {}
        override fun dispose() {}
        override fun setColor(c: Color) { color = c }
        override fun getColor(): Color = color
        override fun setPaintMode() {}
        override fun setXORMode(c1: Color) {}
        override fun getFont(): Font? = null
        override fun setFont(font: Font) {}
        override fun getFontMetrics(f: Font): FontMetrics? = null
        override fun clipRect(x: Int, y: Int, width: Int, height: Int) {}
        override fun setClip(x: Int, y: Int, width: Int, height: Int) {}
        override fun getClipBounds(): Rectangle? = null
        override fun getClip(): Shape? = null
        override fun setClip(clip: Shape) {}
        override fun drawString(str: String, x: Int, y: Int) {}
        override fun drawString(iterator: AttributedCharacterIterator, x: Int, y: Int) {}
        override fun drawBytes(data: ByteArray, offset: Int, length: Int, x: Int, y: Int) {}
        override fun drawChars(data: CharArray, offset: Int, length: Int, x: Int, y: Int) {}
        override fun drawImage(img: Image, x: Int, y: Int, observer: ImageObserver): Boolean = false
        override fun drawImage(img: Image, x: Int, y: Int, width: Int, height: Int, observer: ImageObserver): Boolean = false
        override fun drawImage(img: Image, x: Int, y: Int, bgcolor: Color, observer: ImageObserver): Boolean = false
        override fun drawImage(img: Image, x: Int, y: Int, width: Int, height: Int, bgcolor: Color, observer: ImageObserver): Boolean = false
        override fun drawImage(img: Image, dx1: Int, dy1: Int, dx2: Int, dy2: Int, sx1: Int, sy1: Int, sx2: Int, sy2: Int, observer: ImageObserver): Boolean = false
        override fun drawImage(img: Image, dx1: Int, dy1: Int, dx2: Int, dy2: Int, sx1: Int, sy1: Int, sx2: Int, sy2: Int, bgcolor: Color, observer: ImageObserver): Boolean = false
        override fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int) {}
        override fun drawRect(x: Int, y: Int, width: Int, height: Int) {}
        override fun clearRect(x: Int, y: Int, width: Int, height: Int) {}
        override fun drawRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) {}
        override fun fillRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) {}
        override fun drawOval(x: Int, y: Int, width: Int, height: Int) {}
        override fun fillOval(x: Int, y: Int, width: Int, height: Int) {}
        override fun drawArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) {}
        override fun fillArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) {}
        override fun drawPolyline(xPoints: IntArray, yPoints: IntArray, nPoints: Int) {}
        override fun drawPolygon(xPoints: IntArray, yPoints: IntArray, nPoints: Int) {}
        override fun fillPolygon(xPoints: IntArray, yPoints: IntArray, nPoints: Int) {}

        private var color: Color = Color.BLACK
    }

    private data class Rectangle(val x: Int, val y: Int, val width: Int, val height: Int, val color: Color)
}
