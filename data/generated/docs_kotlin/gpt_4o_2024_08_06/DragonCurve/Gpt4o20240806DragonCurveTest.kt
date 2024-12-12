import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Color
import javax.swing.JFrame

class DragonCurveTest {

    @Test
    fun testGetSequenceZeroIterations() {
        val dragonCurve = DragonCurve(0)
        val expectedSequence = mutableListOf<Int>()
        assertEquals(expectedSequence, dragonCurve.getSequence(0))
    }

    @Test
    fun testGetSequenceOneIteration() {
        val dragonCurve = DragonCurve(1)
        val expectedSequence = mutableListOf(1)
        assertEquals(expectedSequence, dragonCurve.getSequence(1))
    }

    @Test
    fun testGetSequenceTwoIterations() {
        val dragonCurve = DragonCurve(2)
        val expectedSequence = mutableListOf(1, 1, -1)
        assertEquals(expectedSequence, dragonCurve.getSequence(2))
    }

    @Test
    fun testGetSequenceThreeIterations() {
        val dragonCurve = DragonCurve(3)
        val expectedSequence = mutableListOf(1, 1, -1, 1, 1, -1, -1)
        assertEquals(expectedSequence, dragonCurve.getSequence(3))
    }

    @Test
    fun testFrameProperties() {
        val dragonCurve = DragonCurve(5)
        assertEquals("Dragon Curve", dragonCurve.title)
        assertEquals(JFrame.EXIT_ON_CLOSE, dragonCurve.defaultCloseOperation)
        assertEquals(100, dragonCurve.bounds.x)
        assertEquals(100, dragonCurve.bounds.y)
        assertEquals(800, dragonCurve.bounds.width)
        assertEquals(600, dragonCurve.bounds.height)
    }

    @Test
    fun testInitialColor() {
        val dragonCurve = DragonCurve(5)
        val graphics = dragonCurve.graphics
        graphics.color = Color.BLUE
        assertEquals(Color.BLUE, graphics.color)
    }
}
