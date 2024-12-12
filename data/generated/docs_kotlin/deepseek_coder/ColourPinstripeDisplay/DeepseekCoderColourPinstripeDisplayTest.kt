import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel
import kotlin.test.assertEquals

class ColourPinstripeDisplayTest {

    private lateinit var panel: ColourPinstripeDisplay
    private lateinit var mockGraphics: Graphics

    @BeforeEach
    fun setUp() {
        panel = ColourPinstripeDisplay()
        mockGraphics = Mockito.mock(Graphics::class.java)
    }

    @Test
    fun testPaintComponentWithWidth900Height600() {
        panel.preferredSize = Dimension(900, 600)
        panel.paintComponent(mockGraphics)

        val expectedColors = arrayOf(
            Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.WHITE
        )

        // Verify the first band (1 pixel wide)
        var colIndex = 0
        for (x in 0 until 900 step 1) {
            Mockito.verify(mockGraphics).color = expectedColors[colIndex % expectedColors.size]
            Mockito.verify(mockGraphics).fillRect(x, 0, 1, 150)
            colIndex++
        }

        // Verify the second band (2 pixels wide)
        colIndex = 0
        for (x in 0 until 900 step 2) {
            Mockito.verify(mockGraphics).color = expectedColors[colIndex % expectedColors.size]
            Mockito.verify(mockGraphics).fillRect(x, 150, 2, 150)
            colIndex++
        }

        // Verify the third band (3 pixels wide)
        colIndex = 0
        for (x in 0 until 900 step 3) {
            Mockito.verify(mockGraphics).color = expectedColors[colIndex % expectedColors.size]
            Mockito.verify(mockGraphics).fillRect(x, 300, 3, 150)
            colIndex++
        }

        // Verify the fourth band (4 pixels wide)
        colIndex = 0
        for (x in 0 until 900 step 4) {
            Mockito.verify(mockGraphics).color = expectedColors[colIndex % expectedColors.size]
            Mockito.verify(mockGraphics).fillRect(x, 450, 4, 150)
            colIndex++
        }
    }

    @Test
    fun testPaintComponentWithDifferentDimensions() {
        panel.preferredSize = Dimension(600, 400)
        panel.paintComponent(mockGraphics)

        val expectedColors = arrayOf(
            Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.WHITE
        )

        // Verify the first band (1 pixel wide)
        var colIndex = 0
        for (x in 0 until 600 step 1) {
            Mockito.verify(mockGraphics).color = expectedColors[colIndex % expectedColors.size]
            Mockito.verify(mockGraphics).fillRect(x, 0, 1, 100)
            colIndex++
        }

        // Verify the second band (2 pixels wide)
        colIndex = 0
        for (x in 0 until 600 step 2) {
            Mockito.verify(mockGraphics).color = expectedColors[colIndex % expectedColors.size]
            Mockito.verify(mockGraphics).fillRect(x, 100, 2, 100)
            colIndex++
        }

        // Verify the third band (3 pixels wide)
        colIndex = 0
        for (x in 0 until 600 step 3) {
            Mockito.verify(mockGraphics).color = expectedColors[colIndex % expectedColors.size]
            Mockito.verify(mockGraphics).fillRect(x, 200, 3, 100)
            colIndex++
        }

        // Verify the fourth band (4 pixels wide)
        colIndex = 0
        for (x in 0 until 600 step 4) {
            Mockito.verify(mockGraphics).color = expectedColors[colIndex % expectedColors.size]
            Mockito.verify(mockGraphics).fillRect(x, 300, 4, 100)
            colIndex++
        }
    }

    @Test
    fun testPaletteOrder() {
        assertEquals(8, ColourPinstripeDisplay.palette.size)
        assertEquals(Color.BLACK, ColourPinstripeDisplay.palette[0])
        assertEquals(Color.RED, ColourPinstripeDisplay.palette[1])
        assertEquals(Color.GREEN, ColourPinstripeDisplay.palette[2])
        assertEquals(Color.BLUE, ColourPinstripeDisplay.palette[3])
        assertEquals(Color.MAGENTA, ColourPinstripeDisplay.palette[4])
        assertEquals(Color.CYAN, ColourPinstripeDisplay.palette[5])
        assertEquals(Color.YELLOW, ColourPinstripeDisplay.palette[6])
        assertEquals(Color.WHITE, ColourPinstripeDisplay.palette[7])
    }
}
