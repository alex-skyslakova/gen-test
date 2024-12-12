import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.awt.Color
import java.awt.image.BufferedImage

class JuliaPanelTest {

    @Test
    fun testPreferredSize() {
        val panel = JuliaPanel()
        assertEquals(800, panel.preferredSize.width)
        assertEquals(600, panel.preferredSize.height)
    }

    @Test
    fun testBackgroundColor() {
        val panel = JuliaPanel()
        assertEquals(Color.white, panel.background)
    }

    @Test
    fun testImageDimensions() {
        val panel = JuliaPanel()
        val image = BufferedImage(panel.preferredSize.width, panel.preferredSize.height, BufferedImage.TYPE_INT_RGB)
        assertEquals(800, image.width)
        assertEquals(600, image.height)
    }

    @Test
    fun testJuliaSetRendering() {
        val panel = JuliaPanel()
        val image = BufferedImage(panel.preferredSize.width, panel.preferredSize.height, BufferedImage.TYPE_INT_RGB)
        val graphics = image.createGraphics()
        panel.paintComponent(graphics)

        // Check a few pixels to ensure they are set (not all white)
        val pixelColor1 = Color(image.getRGB(400, 300))
        val pixelColor2 = Color(image.getRGB(200, 150))
        val pixelColor3 = Color(image.getRGB(600, 450))

        assertNotEquals(Color.white.rgb, pixelColor1.rgb)
        assertNotEquals(Color.white.rgb, pixelColor2.rgb)
        assertNotEquals(Color.white.rgb, pixelColor3.rgb)
    }

    @Test
    fun testJuliaSetColorRange() {
        val panel = JuliaPanel()
        val image = BufferedImage(panel.preferredSize.width, panel.preferredSize.height, BufferedImage.TYPE_INT_RGB)
        val graphics = image.createGraphics()
        panel.paintComponent(graphics)

        // Check that colors are within expected range
        for (x in 0 until image.width step 100) {
            for (y in 0 until image.height step 100) {
                val color = Color(image.getRGB(x, y))
                assertTrue(color.red in 0..255)
                assertTrue(color.green in 0..255)
                assertTrue(color.blue in 0..255)
            }
        }
    }
}
