import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class ColourPinstripeDisplayTest {

    @Test
    fun testPaletteSequence() {
        val panel = ColourPinstripeDisplay()
        val palette = arrayOf(Color.black, Color.red, Color.green, Color.blue, Color.magenta, Color.cyan, Color.yellow, Color.white)
        assertArrayEquals(palette, ColourPinstripeDisplay.palette)
    }

    @Test
    fun testPreferredSize() {
        val panel = ColourPinstripeDisplay()
        assertEquals(900, panel.preferredSize.width)
        assertEquals(600, panel.preferredSize.height)
    }

    @Test
    fun testPaintComponent() {
        val panel = ColourPinstripeDisplay()
        val graphics = TestGraphics()
        panel.paintComponent(graphics)

        // Test for 1 pixel wide pinstripes in the first quarter
        for (x in 0 until panel.width step 1) {
            val expectedColor = ColourPinstripeDisplay.palette[(x / 1) % ColourPinstripeDisplay.palette.size]
            assertEquals(expectedColor, graphics.getColorAt(x, 0))
        }

        // Test for 2 pixel wide pinstripes in the second quarter
        for (x in 0 until panel.width step 2) {
            val expectedColor = ColourPinstripeDisplay.palette[(x / 2) % ColourPinstripeDisplay.palette.size]
            assertEquals(expectedColor, graphics.getColorAt(x, panel.height / 4))
        }

        // Test for 3 pixel wide pinstripes in the third quarter
        for (x in 0 until panel.width step 3) {
            val expectedColor = ColourPinstripeDisplay.palette[(x / 3) % ColourPinstripeDisplay.palette.size]
            assertEquals(expectedColor, graphics.getColorAt(x, panel.height / 2))
        }

        // Test for 4 pixel wide pinstripes in the last quarter
        for (x in 0 until panel.width step 4) {
            val expectedColor = ColourPinstripeDisplay.palette[(x / 4) % ColourPinstripeDisplay.palette.size]
            assertEquals(expectedColor, graphics.getColorAt(x, 3 * panel.height / 4))
        }
    }

    // Mock Graphics class to test paintComponent
    private class TestGraphics : Graphics() {
        private val colorMap = mutableMapOf<Pair<Int, Int>, Color>()

        override fun setColor(c: Color) {
            currentColor = c
        }

        override fun fillRect(x: Int, y: Int, width: Int, height: Int) {
            for (i in x until x + width) {
                for (j in y until y + height) {
                    colorMap[i to j] = currentColor
                }
            }
        }

        fun getColorAt(x: Int, y: Int): Color? {
            return colorMap[x to y]
        }

        // Other methods are not implemented for brevity
        // ...
    }
}
