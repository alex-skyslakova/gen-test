import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.*
import java.awt.*
import javax.swing.JPanel

class ArchimedeanSpiralTest {

    private lateinit var spiral: ArchimedeanSpiral
    private lateinit var graphics: Graphics2D

    @BeforeEach
    fun setUp() {
        spiral = ArchimedeanSpiral()
        graphics = mock(Graphics2D::class.java)
    }

    @Test
    fun `test preferred size`() {
        val expectedSize = Dimension(640, 640)
        assert(spiral.preferredSize == expectedSize) { "Preferred size should be $expectedSize" }
    }

    @Test
    fun `test background color`() {
        val expectedColor = Color.white
        assert(spiral.background == expectedColor) { "Background color should be $expectedColor" }
    }

    @Test
    fun `test drawGrid is called`() {
        val spySpiral = spy(spiral)
        doNothing().`when`(spySpiral).drawGrid(graphics)
        spySpiral.paintComponent(graphics)
        verify(spySpiral).drawGrid(graphics)
    }

    @Test
    fun `test drawSpiral is called`() {
        val spySpiral = spy(spiral)
        doNothing().`when`(spySpiral).drawSpiral(graphics)
        spySpiral.paintComponent(graphics)
        verify(spySpiral).drawSpiral(graphics)
    }

    @Test
    fun `test plot is called`() {
        val spySpiral = spy(spiral)
        doNothing().`when`(spySpiral).plot(graphics, anyInt(), anyInt())
        spySpiral.drawSpiral(graphics)
        verify(spySpiral, atLeastOnce()).plot(graphics, anyInt(), anyInt())
    }
}
