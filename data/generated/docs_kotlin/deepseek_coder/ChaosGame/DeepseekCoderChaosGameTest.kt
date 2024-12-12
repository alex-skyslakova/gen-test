import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.awt.Point
import java.util.*

class ChaosGameTest {

    @Test
    fun `test initial points setup`() {
        val chaosGame = ChaosGame()
        val expectedPoints = listOf(
            Point(320, 60),
            Point(60, 580),
            Point(580, 580)
        )
        assertEquals(expectedPoints, chaosGame.points)
    }

    @Test
    fun `test initial stack setup`() {
        val chaosGame = ChaosGame()
        assertEquals(1, chaosGame.stack.size)
        assertEquals(-1, chaosGame.stack.peek().x)
        assertEquals(-1, chaosGame.stack.peek().y)
        assertEquals(0, chaosGame.stack.peek().colorIndex)
    }

    @Test
    fun `test halfwayPoint calculation`() {
        val chaosGame = ChaosGame()
        val pointA = Point(100, 100)
        val pointB = Point(200, 200)
        val halfway = chaosGame.halfwayPoint(pointA, pointB, 1)
        assertEquals(150, halfway.x)
        assertEquals(150, halfway.y)
        assertEquals(1, halfway.colorIndex)
    }

    @Test
    fun `test addPoint function`() {
        val chaosGame = ChaosGame()
        val initialStackSize = chaosGame.stack.size
        chaosGame.addPoint()
        assertEquals(initialStackSize + 1, chaosGame.stack.size)
    }

    @Test
    fun `test stack size limit`() {
        val chaosGame = ChaosGame()
        // Simulate adding points until the stack size limit is reached
        while (chaosGame.stack.size < 50_000) {
            chaosGame.addPoint()
        }
        assertEquals(50_000, chaosGame.stack.size)
    }

    @Test
    fun `test color index range`() {
        val chaosGame = ChaosGame()
        val colorIndices = mutableSetOf<Int>()
        for (i in 0 until 1000) {
            chaosGame.addPoint()
            colorIndices.add(chaosGame.stack.peek().colorIndex)
        }
        assertTrue(colorIndices.contains(0))
        assertTrue(colorIndices.contains(1))
        assertTrue(colorIndices.contains(2))
    }

    @Test
    fun `test drawPoints function`() {
        val chaosGame = ChaosGame()
        val g = MockGraphics2D()
        chaosGame.drawPoints(g)
        // Since drawPoints only draws points, we can't directly assert the drawing,
        // but we can assert that the function runs without exceptions.
        // Further assertions would require mocking the Graphics2D object more extensively.
    }

    // Mock class for Graphics2D
    class MockGraphics2D : java.awt.Graphics2D() {
        override fun draw(s: java.awt.Shape?) {}
        override fun drawImage(img: java.awt.Image?, xform: java.awt.image.AffineTransform?) {}
        override fun drawImage(img: java.awt.Image?, op: java.awt.image.BufferedImageOp?) {}
        override fun drawImage(img: java.awt.Image?, x: Int, y: Int, observer: java.awt.image.ImageObserver?) {}
        override fun drawImage(img: java.awt.Image?, x: Int, y: Int, width: Int, height: Int, observer: java.awt.image.ImageObserver?) {}
        override fun drawImage(img: java.awt.Image?, x: Int, y: Int, bgcolor: java.awt.Color?, observer: java.awt.image.ImageObserver?) {}
        override fun drawImage(img: java.awt.Image?, x: Int, y: Int, width: Int, height: Int, bgcolor: java.awt.Color?, observer: java.awt.image.ImageObserver?) {}
        override fun drawImage(img: java.awt.Image?, dx1: Int, dy1: Int, dx2: Int, dy2: Int, sx1: Int, sy1: Int, sx2: Int, sy2: Int, observer: java.awt.image.ImageObserver?) {}
        override fun drawImage(img: java.awt.Image?, dx1: Int, dy1: Int, dx2: Int, dy2: Int, sx1: Int, sy1: Int, sx2: Int, sy2: Int, bgcolor: java.awt.Color?, observer: java.awt.image.ImageObserver?) {}
        override fun drawRenderedImage(img: java.awt.image.RenderedImage?, xform: java.awt.image.AffineTransform?) {}
        override fun drawRenderableImage(img: java.awt.image.renderable.RenderableImage?, xform: java.awt.image.AffineTransform?) {}
        override fun drawString(str: String?, x: Float, y: Float) {}
        override fun drawString(str: String?, x: Int, y: Int) {}
        override fun drawString(iterator: java.text.AttributedCharacterIterator?, x: Float, y: Float) {}
        override fun drawString(iterator: java.text.AttributedCharacterIterator?, x: Int, y: Int) {}
        override fun drawGlyphVector(g: java.awt.font.GlyphVector?, x: Float, y: Float) {}
        override fun fill(s: java.awt.Shape?) {}
        override fun hit(rect: java.awt.Rectangle?, s: java.awt.Shape?, onStroke: Boolean) {}
        override fun getDeviceConfiguration(): java.awt.GraphicsConfiguration? { return null }
        override fun setComposite(comp: java.awt.Composite?) {}
        override fun setPaint(paint: java.awt.Paint?) {}
        override fun setStroke(s: java.awt.Stroke?) {}
        override fun setRenderingHint(hintKey: java.awt.RenderingHints.Key?, hintValue: Any?) {}
        override fun getRenderingHint(hintKey: java.awt.RenderingHints.Key?): Any? { return null }
        override fun setRenderingHints(hints: MutableMap<*, *>?) {}
        override fun addRenderingHints(hints: MutableMap<*, *>?) {}
        override fun getRenderingHints(): java.awt.RenderingHints? { return null }
        override fun translate(x: Int, y: Int) {}
        override fun translate(x: Double, y: Double) {}
        override fun rotate(theta: Double) {}
        override fun rotate(theta: Double, x: Double, y: Double) {}
        override fun scale(sx: Double, sy: Double) {}
        override fun shear(shx: Double, shy: Double) {}
        override fun transform(Tx: java.awt.geom.AffineTransform?) {}
        override fun setTransform(Tx: java.awt.geom.AffineTransform?) {}
        override fun getTransform(): java.awt.geom.AffineTransform? { return null }
        override fun getPaint(): java.awt.Paint? { return null }
        override fun getComposite(): java.awt.Composite? { return null }
        override fun setBackground(color: java.awt.Color?) {}
        override fun getBackground(): java.awt.Color? { return null }
        override fun getStroke(): java.awt.Stroke? { return null }
        override fun clip(s: java.awt.Shape?) {}
        override fun getFontRenderContext(): java.awt.font.FontRenderContext? { return null }
        override fun getFont(): java.awt.Font? { return null }
        override fun setFont(font: java.awt.Font?) {}
        override fun getColor(): java.awt.Color? { return null }
        override fun setColor(c: java.awt.Color?) {}
        override fun getClip(): java.awt.Shape? { return null }
        override fun setClip(clip: java.awt.Shape?) {}
        override fun setClip(x: Int, y: Int, width: Int, height: Int) {}
        override fun copyArea(x: Int, y: Int, width: Int, height: Int, dx: Int, dy: Int) {}
        override fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int) {}
        override fun fillRect(x: Int, y: Int, width: Int, height: Int) {}
        override fun clearRect(x: Int, y: Int, width: Int, height: Int) {}
        override fun drawRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) {}
        override fun fillRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) {}
        override fun drawOval(x: Int, y: Int, width: Int, height: Int) {}
        override fun fillOval(x: Int, y: Int, width: Int, height: Int) {}
        override fun drawArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) {}
        override fun fillArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) {}
        override fun drawPolyline(xPoints: IntArray?, yPoints: IntArray?, nPoints: Int) {}
        override fun drawPolygon(xPoints: IntArray?, yPoints: IntArray?, nPoints: Int) {}
        override fun fillPolygon(xPoints: IntArray?, yPoints: IntArray?, nPoints: Int) {}
        override fun drawChars(data: CharArray?, offset: Int, length: Int, x: Int, y: Int) {}
        override fun drawBytes(data: ByteArray?, offset: Int, length: Int, x: Int, y: Int) {}
    }
}
