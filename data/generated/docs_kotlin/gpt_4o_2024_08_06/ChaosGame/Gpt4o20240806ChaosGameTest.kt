import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.awt.Color
import java.awt.Point

class ChaosGameTest {

    @Test
    fun testInitialSetup() {
        val chaosGame = ChaosGame()
        assertEquals(3, chaosGame.points.size, "There should be 3 reference points for the triangle.")
        assertEquals(Color.red, chaosGame.colors[0], "First color should be red.")
        assertEquals(Color.green, chaosGame.colors[1], "Second color should be green.")
        assertEquals(Color.blue, chaosGame.colors[2], "Third color should be blue.")
        assertEquals(1, chaosGame.stack.size, "Initial stack should have one point.")
    }

    @Test
    fun testHalfwayPoint() {
        val chaosGame = ChaosGame()
        val pointA = Point(0, 0)
        val pointB = Point(4, 4)
        val halfway = chaosGame.halfwayPoint(pointA, pointB, 0)
        assertEquals(2, halfway.x, "Halfway x-coordinate should be 2.")
        assertEquals(2, halfway.y, "Halfway y-coordinate should be 2.")
        assertEquals(0, halfway.colorIndex, "Color index should be 0.")
    }

    @Test
    fun testAddPoint() {
        val chaosGame = ChaosGame()
        val initialSize = chaosGame.stack.size
        chaosGame.addPoint()
        assertEquals(initialSize + 1, chaosGame.stack.size, "Stack size should increase by 1 after adding a point.")
    }

    @Test
    fun testRandomnessInAddPoint() {
        val chaosGame = ChaosGame()
        val initialPoint = chaosGame.stack.peek()
        chaosGame.addPoint()
        val newPoint = chaosGame.stack.peek()
        assertNotEquals(initialPoint, newPoint, "New point should not be the same as the initial point.")
    }

    @Test
    fun testDrawPoints() {
        val chaosGame = ChaosGame()
        val graphics = TestGraphics2D()
        chaosGame.drawPoints(graphics)
        assertTrue(graphics.drawnPoints.isNotEmpty(), "Points should be drawn on the graphics object.")
    }

    // Helper class to simulate Graphics2D for testing purposes
    class TestGraphics2D : Graphics2D() {
        val drawnPoints = mutableListOf<Point>()

        override fun fillOval(x: Int, y: Int, width: Int, height: Int) {
            drawnPoints.add(Point(x, y))
        }

        // Implement other abstract methods with empty bodies
        override fun draw(s: Shape?) {}
        override fun drawString(str: String?, x: Int, y: Int) {}
        override fun drawString(iterator: AttributedCharacterIterator?, x: Int, y: Int) {}
        override fun drawString(str: String?, x: Float, y: Float) {}
        override fun drawString(iterator: AttributedCharacterIterator?, x: Float, y: Float) {}
        override fun drawGlyphVector(gv: GlyphVector?, x: Float, y: Float) {}
        override fun fill(s: Shape?) {}
        override fun hit(rect: Rectangle?, s: Shape?, onStroke: Boolean): Boolean = false
        override fun getDeviceConfiguration(): GraphicsConfiguration? = null
        override fun setComposite(comp: Composite?) {}
        override fun setPaint(paint: Paint?) {}
        override fun setStroke(s: Stroke?) {}
        override fun setRenderingHint(hintKey: RenderingHints.Key?, hintValue: Any?) {}
        override fun getRenderingHint(hintKey: RenderingHints.Key?): Any? = null
        override fun setRenderingHints(hints: MutableMap<*, *>?) {}
        override fun addRenderingHints(hints: MutableMap<*, *>?) {}
        override fun getRenderingHints(): RenderingHints? = null
        override fun translate(x: Int, y: Int) {}
        override fun translate(tx: Double, ty: Double) {}
        override fun rotate(theta: Double) {}
        override fun rotate(theta: Double, x: Double, y: Double) {}
        override fun scale(sx: Double, sy: Double) {}
        override fun shear(shx: Double, shy: Double) {}
        override fun transform(Tx: AffineTransform?) {}
        override fun setTransform(Tx: AffineTransform?) {}
        override fun getTransform(): AffineTransform? = null
        override fun getPaint(): Paint? = null
        override fun getComposite(): Composite? = null
        override fun setBackground(color: Color?) {}
        override fun getBackground(): Color? = null
        override fun getStroke(): Stroke? = null
        override fun clip(s: Shape?) {}
        override fun getFontRenderContext(): FontRenderContext? = null
        override fun create(): Graphics? = null
        override fun getColor(): Color? = null
        override fun setColor(c: Color?) {}
        override fun setPaintMode() {}
        override fun setXORMode(c1: Color?) {}
        override fun getFont(): Font? = null
        override fun setFont(font: Font?) {}
        override fun getFontMetrics(f: Font?): FontMetrics? = null
        override fun getClipBounds(): Rectangle? = null
        override fun clipRect(x: Int, y: Int, width: Int, height: Int) {}
        override fun setClip(x: Int, y: Int, width: Int, height: Int) {}
        override fun getClip(): Shape? = null
        override fun setClip(clip: Shape?) {}
        override fun copyArea(x: Int, y: Int, width: Int, height: Int, dx: Int, dy: Int) {}
        override fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int) {}
        override fun fillRect(x: Int, y: Int, width: Int, height: Int) {}
        override fun clearRect(x: Int, y: Int, width: Int, height: Int) {}
        override fun drawRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) {}
        override fun fillRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) {}
        override fun drawOval(x: Int, y: Int, width: Int, height: Int) {}
        override fun drawArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) {}
        override fun fillArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) {}
        override fun drawPolyline(xPoints: IntArray?, yPoints: IntArray?, nPoints: Int) {}
        override fun drawPolygon(xPoints: IntArray?, yPoints: IntArray?, nPoints: Int) {}
        override fun fillPolygon(xPoints: IntArray?, yPoints: IntArray?, nPoints: Int) {}
    }
}
