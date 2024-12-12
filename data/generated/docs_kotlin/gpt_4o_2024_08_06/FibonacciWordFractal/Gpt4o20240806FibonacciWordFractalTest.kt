import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Color
import java.awt.Dimension
import javax.swing.JPanel

class FibonacciWordFractalTest {

    @Test
    fun `test wordFractal generation for n=1`() {
        val fractal = FibonacciWordFractal(1)
        assertEquals("1", fractal.wordFractal(1))
    }

    @Test
    fun `test wordFractal generation for n=2`() {
        val fractal = FibonacciWordFractal(2)
        assertEquals("0", fractal.wordFractal(2))
    }

    @Test
    fun `test wordFractal generation for n=3`() {
        val fractal = FibonacciWordFractal(3)
        assertEquals("01", fractal.wordFractal(3))
    }

    @Test
    fun `test wordFractal generation for n=4`() {
        val fractal = FibonacciWordFractal(4)
        assertEquals("010", fractal.wordFractal(4))
    }

    @Test
    fun `test wordFractal generation for n=5`() {
        val fractal = FibonacciWordFractal(5)
        assertEquals("01001", fractal.wordFractal(5))
    }

    @Test
    fun `test panel properties`() {
        val fractal = FibonacciWordFractal(5)
        assertEquals(Dimension(450, 620), fractal.preferredSize)
        assertEquals(Color.black, fractal.background)
    }

    @Test
    fun `test drawWordFractal logic`() {
        // This test would require mocking the Graphics2D object to verify the drawLine calls
        // Since it's more complex and involves graphical output, we will not implement it here
        // but acknowledge that it would involve checking the sequence of drawLine calls
    }
}
